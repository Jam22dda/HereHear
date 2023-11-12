package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.entity.ProfileCharacter;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.*;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisteredMusicServiceImpl implements RegisteredMusicService {

    private final OccasionRepository occasionRepository;
    private final MusicOccasionRepository musicOccasionRepository;
    private final RegisteredMusicRepository registeredMusicRepository;
    private final ProfileCharacterRepository profileCharacterRepository;
    private final RegisteredMusicDslRepository registeredMusicDslRepository;

    private final RegisterMusicMapper registerMusicMapper;

    @Override
    @Transactional
    public List<SseResDto> registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto) {
        log.info("[{}] memberId: {}, registerMusicReqDto: {}", ConstantsUtil.MUSIC_REGISTER, memberId, registerMusicReqDto);

        RegisteredMusic registeredMusic = registeredMusicRepository.save(
                registerMusicMapper.toRegisteredMusic(MemberUtil.findMember(memberId), registerMusicReqDto));
        log.info("[{}] registeredMusicId: {}", ConstantsUtil.MUSIC_REGISTER, registeredMusic.getRegisteredMusicId());

        for (long occasionId : registerMusicReqDto.getMusicOccasionIds())
            musicOccasionRepository.save(registerMusicMapper.toMusicOccasion(findOccasion(occasionId), registeredMusic));
        log.info("[{}] 상황 태그 등록 성공", ConstantsUtil.MUSIC_REGISTER);

        List<SseResDto> sseResDtos = new ArrayList<>();
        sseResDtos.add(registerMusicMapper.toSseResDto(1, registeredMusic));
        log.info("[{}] SSE 등록 이벤트 sseResDtos: {}", ConstantsUtil.MUSIC_REGISTER, sseResDtos);

        return sseResDtos;
    }

    @Override
    @Transactional
    public List<OccasionResDto> getAllTags() {
        log.info("[{}]", ConstantsUtil.ALL_TAGS);

        List<OccasionResDto> occasionResDtos = occasionRepository.findAll().stream()
                .map(registerMusicMapper::toOccasionResDto)
                .toList();
        log.info("[{}] AllTags: {}", ConstantsUtil.ALL_TAGS,occasionResDtos);

        return occasionResDtos;
    }

    @Override
    @Transactional
    public RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId) {
        log.info("[{}] memberId: {}, registeredMusicId: {}", ConstantsUtil.MUSIC_DETAILS,memberId,registeredMusicId);

        Member registerMember = findByRegisterMusic(registeredMusicId).getMember();
        RegisteredMusicDetailsResDto registeredMusicDetailsResDto = registerMusicMapper.toRegisteredMusicDetailsResDto(
                registerMember,
                findByRegisterMusic(registeredMusicId),
                registeredMusicDslRepository.findByRegisteredMusicLike(memberId, registeredMusicId).isPresent(),
                findByProfileCharacter(registerMember.getProfileCharacter().getProfileCharacterId()),
                registeredMusicDslRepository.findByOccasionName(registeredMusicId)
        );
        log.info("[{}] 성공  result: {}", ConstantsUtil.MUSIC_DETAILS, registeredMusicDetailsResDto);

        registeredMusicDetailsResDto.setCreateTime(convertAndSetCreateTime(registeredMusicDetailsResDto.getCreateTime()));
        log.info("[{}] 날짜 변환: {}", ConstantsUtil.MUSIC_DETAILS,registeredMusicDetailsResDto);

        return registeredMusicDetailsResDto;
    }

    @Override
    @Transactional
    public List<RegisteredMusicMapResDto> getRegisteredMusicList() {
        log.info("[{}]", ConstantsUtil.ALL_REGISTERED_MUSIC);

        List<RegisteredMusicMapResDto> registeredMusicMapResDtos = registeredMusicDslRepository.findByRegisterMusics(180,180).stream()
                .map(registerMusicMapper::toRegisteredMusicListResDto)
                .toList();
        log.info("[{}] 성공, result: {}", ConstantsUtil.ALL_REGISTERED_MUSIC, registeredMusicMapResDtos);

        return registeredMusicMapResDtos;
    }

    @Override
    @Transactional
    public List<SseResDto> updateMyRegisteredMusic(long memberId, long registeredMusicId) {
        log.info("[{}] memberId: {}, registeredMusicId: {}", ConstantsUtil.MUSIC_DELETE,memberId,registeredMusicId);

        RegisteredMusic findRegisteredMusic = registeredMusicDslRepository.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
        findRegisteredMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findRegisteredMusic);
        log.info("[{}] 성공", ConstantsUtil.MUSIC_DELETE);

        List<SseResDto> sseResDtos = new ArrayList<>();
        sseResDtos.add(registerMusicMapper.toSseResDto(0, findRegisteredMusic));
        log.info("[{}] SSE 삭제 이벤트 sseResDtos: {}", ConstantsUtil.MUSIC_DELETE, sseResDtos);

        return sseResDtos;
    }

    @Override
    @Transactional
    public List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId, String contents) {
        log.info("[{}] memberId: {}",contents, memberId);

        List<MyRegisteredMusicResDto> myRegisteredMusicResDtos = registeredMusicDslRepository.findByMyRegisterMusics(memberId).stream()
                .map(registerMusicMapper::toMyRegisteredMusicResDto)
                .toList();
        log.info("[{}] 성공 result: {}",contents, myRegisteredMusicResDtos);

        return myRegisteredMusicResDtos;
    }

    public Occasion findOccasion(long musicOccasionId) {
        return occasionRepository.findById(musicOccasionId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_OCCASION)
        );
    }

    public RegisteredMusic findByRegisterMusic(long registeredMusicId) {
        return registeredMusicDslRepository.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    public ProfileCharacter findByProfileCharacter(long profileCharacterId) {
        return profileCharacterRepository.findById(profileCharacterId).orElseThrow(
                () -> new CustomException(ExceptionStatus.PROFILE_CHARACTER_NOT_FOUND)
        );
    }

    public String convertAndSetCreateTime(String createTime) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .appendFraction(ChronoField.MICRO_OF_SECOND, 0, 6, true)
                .toFormatter();
        LocalDateTime dateTime = LocalDateTime.parse(createTime, formatter);
        return dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일 HH시"));
    }

}
