package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.MusicOccasionRepository;
import com.ssafy.herehear.music.repository.OccasionRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepository;
import com.ssafy.herehear.music.repository.musicRepositoryImpl.RegisteredMusicRepositoryImpl;
import com.ssafy.herehear.music.service.RegisteredMusicService;
import com.ssafy.herehear.music.util.HourFilterUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegisteredMusicServiceImpl implements RegisteredMusicService {

    private final OccasionRepository occasionRepository;
    private final MusicOccasionRepository musicOccasionRepository;
    private final RegisteredMusicRepository registeredMusicRepository;
    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;

    private final RegisterMusicMapper registerMusicMapper;

    @Override
    @Transactional
    public List<SseResDto> registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto) {
        log.info(logComment("음악 등록", memberId, registerMusicReqDto));

        Member member = MemberUtil.findMember(memberId);

        //음악 등록
        RegisteredMusic registeredMusic = registeredMusicRepository.save(registerMusicMapper.toRegisteredMusic(member, registerMusicReqDto));
        log.info("registerMusic registeredMusicId: " + registeredMusic.getRegisteredMusicId());

        //상황 태그 등록
        for (long occasionId : registerMusicReqDto.getMusicOccasionIds()) {
            musicOccasionRepository.save(registerMusicMapper.toMusicOccasion(findOccasion(occasionId), registeredMusic));
        }
        log.info("registerMusic success");

        //SSE 등록 이벤트
        List<SseResDto> sseResDtos = new ArrayList<>();
        sseResDtos.add(registerMusicMapper.toSseResDto(0, registeredMusic));
        log.info("registerMusic SseResDto: " + sseResDtos);

        return sseResDtos;
    }

    @Override
    @Transactional
    public List<OccasionResDto> getAllTags() {

        //전체 태그 조회
        List<OccasionResDto> occasionResDtos = occasionRepository.findAll().stream()
                .map(registerMusicMapper::toOccasionResDto)
                .toList();
        log.info("getAllTags: " + occasionResDtos);

        return occasionResDtos;
    }

    @Override
    @Transactional
    public RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId) {
        log.info(logComment("음악 상세 조회", memberId, registeredMusicId));

        //음악 상세 조회
        RegisteredMusicDetailsResDto registeredMusicDetailsResDto = registerMusicMapper.toRegisteredMusicDetailsResDto(
                findByRegisterMusic(registeredMusicId),
                findRegisteredMusicLike(memberId, registeredMusicId),
                MemberUtil.findMember(memberId),
                registeredMusicRepositoryImpl.findByOccasionName(registeredMusicId)
        );
        log.info("getRegisteredMusicDetails: " + registeredMusicDetailsResDto);

        //날짜 형식 변환
        registeredMusicDetailsResDto.setCreateTime(convertAndSetCreateTime(registeredMusicDetailsResDto.getCreateTime()));
        log.info("convert getRegisteredMusicDetails: " + registeredMusicDetailsResDto);

        return registeredMusicDetailsResDto;
    }

    @Override
    @Transactional
    public List<RegisteredMusicResDto> getRegisteredMusicList() {

        //전체 음악 조회
        List<RegisteredMusicResDto> registeredMusicResDtos = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .filter(HourFilterUtils::findHourFilter)
                .map(registerMusicMapper::toRegisteredMusicListResDto)
                .toList();
        log.info("getRegisteredMusicList: " + registeredMusicResDtos);

        return registeredMusicResDtos;
    }

    @Override
    @Transactional
    public List<SseResDto> updateMyRegisteredMusic(long memberId, long registeredMusicId) {
        log.info(logComment("등록한 음악 삭제", memberId, registeredMusicId));

        MemberUtil.findMember(memberId);

        RegisteredMusic findRegisteredMusic = registeredMusicRepositoryImpl.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
        findRegisteredMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findRegisteredMusic);

        log.info("updateMyRegisteredMusic success");

        //SSE 삭제 이벤트
        List<SseResDto> sseResDtos = new ArrayList<>();
        sseResDtos.add(registerMusicMapper.toSseResDto(0, findRegisteredMusic));
        log.info("updateMyRegisteredMusic SseResDto: " + sseResDtos);

        return sseResDtos;
    }

    @Override
    @Transactional
    public List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId) {
        MemberUtil.findMember(memberId);

        List<MyRegisteredMusicResDto> myRegisteredMusicResDtos = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
                .map(registerMusicMapper::toMyRegisteredMusicResDto)
                .toList();
        log.info("getMyRegisteredMusicList: " + myRegisteredMusicResDtos);

        return myRegisteredMusicResDtos;
    }

    public Occasion findOccasion(long musicOccasionId) {
        return occasionRepository.findById(musicOccasionId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_OCCASION)
        );
    }

    public RegisteredMusic findByRegisterMusic(long registeredMusicId) {
        return registeredMusicRepositoryImpl.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    public boolean findRegisteredMusicLike(long memberId, long registeredMusicId) {
        return registeredMusicRepositoryImpl.findByRegisteredMusicLike(memberId, registeredMusicId).isPresent();
    }

    public String convertAndSetCreateTime(String createTime) {
        LocalDateTime dateTime = LocalDateTime.parse(createTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일 HH시"));
    }

    public String logComment(String title, long memberId, Object req) {
        return "[" + title + "] memberId: " + memberId + ", data: " + req;
    }
}