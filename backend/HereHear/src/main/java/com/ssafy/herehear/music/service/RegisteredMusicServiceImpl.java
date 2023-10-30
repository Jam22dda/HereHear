package com.ssafy.herehear.music.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberMusicId;
import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.MyRegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicDetailsResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.MusicOccasionRepository;
import com.ssafy.herehear.music.repository.OccasionRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisteredMusicServiceImpl implements RegisteredMusicService{

    private final RegisteredMusicRepository registeredMusicRepository;
    private final OccasionRepository occasionRepository;
    private final MusicOccasionRepository musicOccasionRepository;
    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;
    private final RegisterMusicMapper registerMusicMapper;

    @Transactional
    public void registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto) {
        log.info(logComment("음악 등록",memberId,registerMusicReqDto));

        Member member = MemberUtil.findMember(memberId);

        //음악 등록
        RegisteredMusic registeredMusic = registeredMusicRepository.save(registerMusicMapper.toRegisteredMusic(member, registerMusicReqDto));
        log.info("registeredMusicId: "+registeredMusic.getRegisteredMusicId());

        //상황 태그 등록
        for (long occasionId: registerMusicReqDto.getMusicOccasionIds()) {
            musicOccasionRepository.save(registerMusicMapper.toMusicOccasion(findOccasion(occasionId), registeredMusic));
        }
        log.info("registerMusic success");
    }

    public List<Occasion> getTag() {
        return occasionRepository.findAll();
    }

    @Transactional
    public RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId) {
        log.info(logComment("음악 상세 조회",memberId,registeredMusicId));

        Member member = MemberUtil.findMember(memberId);

        RegisteredMusicDetailsResDto registeredMusicDetailsResDto = registerMusicMapper.toRegisteredMusicResDto(
                findByRegisterMusic(registeredMusicId),
                findRegisteredMusicLike(memberId, registeredMusicId),
                member.getNickname(),
                registeredMusicRepositoryImpl.findByOccasion(registeredMusicId)
        );

        registeredMusicDetailsResDto.setCreateTime(convertAndSetCreateTime(registeredMusicDetailsResDto.getCreateTime()));
        log.info("getRegisteredMusicDetails: "+ registeredMusicDetailsResDto);

        return registeredMusicDetailsResDto;
    }

    @Transactional
    public List<RegisteredMusicResDto> getRegisteredMusicList() {
        List<RegisteredMusicResDto> registeredMusicResDtos = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicListResDto(
                        findRegisteredMusic,
                        registeredMusicRepositoryImpl.findByOccasion(findRegisteredMusic.getRegisteredMusicId()))
                )
                .toList();
        log.info("getRegisteredMusicList: "+ registeredMusicResDtos);

        return registeredMusicResDtos;
    }

    @Transactional
    public void updateMyRegisteredMusic(long memberId, long registeredMusicId) {
        log.info(logComment("등록한 음악 삭제",memberId,registeredMusicId));

        MemberUtil.findMember(memberId);

        RegisteredMusic findRegisteredMusic = registeredMusicRepositoryImpl.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
        findRegisteredMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findRegisteredMusic);

        log.info("updateMyRegisteredMusic success");
    }


    @Transactional
    public List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId) {
        MemberUtil.findMember(memberId);

        List<MyRegisteredMusicResDto> myRegisteredMusicResDtos = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
                .map(registerMusicMapper::toMyRegisteredMusicResDto)
                .toList();
        log.info("getMyRegisteredMusicList: "+ myRegisteredMusicResDtos);

        return myRegisteredMusicResDtos;
    }

    public Occasion findOccasion(long musicOccasionId){
        return occasionRepository.findById(musicOccasionId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_OCCASION)
        );
    }

    public RegisteredMusic findByRegisterMusic(long registeredMusicId){
        return registeredMusicRepositoryImpl.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    public boolean findRegisteredMusicLike(long memberId, long registeredMusicId) {
        return registeredMusicRepositoryImpl.findByRegisteredMusicLike(memberId, registeredMusicId).isPresent();
    }

    public MemberMusicId findMemberMusicId(long memberId, long registeredMusicId){
        return MemberMusicId.builder().memberId(memberId).registeredMusicId(registeredMusicId).build();
    }

    public String convertAndSetCreateTime(String createTime) {
        LocalDateTime dateTime = LocalDateTime.parse(createTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일 HH시"));
    }

    public String logComment(String title, long memberId, Object req){
        return "["+title+"[ param] memberId: "+memberId+", registeredMusicId: "+req;
    }
}