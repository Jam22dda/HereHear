package com.ssafy.herehear.music.service;

import com.ssafy.herehear.achievement.repository.MemberRepository;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.LikeRegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.MyRegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicDetailsResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisteredMusicService {
    private final RegisteredMusicRepository registeredMusicRepository;
    private final RegisterMusicMapper registerMusicMapper;

    private final OccasionRepository occasionRepository;
    private final MusicOccasionRepository musicOccasionRepository;

    private final MemberRepository memberRepository;
    private final MusicHistoryRepository musicHistoryRepository;

    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;

    @Transactional
    public void registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto) {
        Member member = findMember(memberId);

        RegisteredMusic registeredMusic = registeredMusicRepository.save(registerMusicMapper.toRegisteredMusic(member, registerMusicReqDto));
        log.info("registeredMusicId: "+registeredMusic.getRegisteredMusicId());

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
        Member member = findMember(memberId);

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
                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicListResDto(findRegisteredMusic, registeredMusicRepositoryImpl.findByOccasion(findRegisteredMusic.getRegisteredMusicId())))
                .toList();
        log.info("getRegisteredMusicList: "+ registeredMusicResDtos);

        return registeredMusicResDtos;
    }

    @Transactional
    public void updateMyRegisteredMusic(long memberId, long registeredMusicId) {
        findMember(memberId);

        RegisteredMusic findRegisteredMusic = registeredMusicRepositoryImpl.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
        findRegisteredMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findRegisteredMusic);

        log.info("updateMyRegisteredMusic success");
    }


    @Transactional
    public List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId) {
        findMember(memberId);

        List<MyRegisteredMusicResDto> myRegisteredMusicResDtos = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
                .map(registerMusicMapper::toMyRegisteredMusicResDto)
                .toList();
        log.info("getMyRegisteredMusicList: "+ myRegisteredMusicResDtos);

        return myRegisteredMusicResDtos;
    }

    public void registerPlayMusic(long memberId, long registeredMusicId) {
        findMusicHistory(memberId, registeredMusicId).ifPresentOrElse(
                existingMusicHistory -> {
                    existingMusicHistory.updateCreatTime(LocalDateTime.now());
                    musicHistoryRepository.save(existingMusicHistory);
                },
                () -> {
                    MusicHistory musicHistory = MusicHistory.builder()
                            .id(findMemberMusicId(memberId,registeredMusicId))
                            .member(findMember(memberId))
                            .registeredMusic(findByRegisterMusic(registeredMusicId))
                            .build();
                    musicHistoryRepository.save(musicHistory);
                }
        );

        log.info("registerPlayMusic success");
    }

    public void deletePlayMusic(long memberId, long registeredMusicId){
        findMember(memberId);

        findMusicHistory(memberId, registeredMusicId)
                .ifPresentOrElse(
                        musicHistoryRepository::delete,
                        () -> {
                            throw new CustomException(ExceptionStatus.NOT_FOUND_HISTORY_MUSIC);
                        }
                );
        log.info("deletePlayMusic success");
    }

    public List<LikeRegisteredMusicResDto> getMusicHistoryList(long memberId){
        findMember(memberId);

        List<LikeRegisteredMusicResDto> likeRegisteredMusicResDtos = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
                .map(findRegisteredMusic -> registerMusicMapper.toLikeRegisteredMusicResDto(findRegisteredMusic, findRegisteredMusicLike(memberId, findRegisteredMusic.getRegisteredMusicId())))
                .toList();
        log.info("getMusicHistoryList: "+ likeRegisteredMusicResDtos);

        return likeRegisteredMusicResDtos;
    }



    public Member findMember(long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );
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

    public Optional<MusicHistory> findMusicHistory(long memberId, long registeredMusicId){
        return musicHistoryRepository.findById(findMemberMusicId(memberId, registeredMusicId));
    }

    public MemberMusicId findMemberMusicId(long memberId, long registeredMusicId){
        return MemberMusicId.builder().memberId(memberId).registeredMusicId(registeredMusicId).build();
    }

    public String convertAndSetCreateTime(String createTime) {
        LocalDateTime dateTime = LocalDateTime.parse(createTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일 HH시"));
    }
}
