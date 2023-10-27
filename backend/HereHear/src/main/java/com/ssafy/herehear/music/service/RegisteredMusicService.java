package com.ssafy.herehear.music.service;

import com.ssafy.herehear.achievement.repository.MemberRepository;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisteredMusicService {
    private final RegisteredMusicRepository registeredMusicRepository;
    private final RegisterMusicMapper registerMusicMapper;

    private final OccasionRepository occasionRepository;
    private final MusicOccasionRepository musicOccasionRepository;

    private final MemberRepository memberRepository;

    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;

    @Transactional
    public void registerMusic(Long memberId, RegisteredMusicReqDto registeredMusicReqDto) {
        Member member = findMember(memberId);

        RegisteredMusic registeredMusic = registeredMusicRepository.save(registerMusicMapper.toRegisteredMusic(member, registeredMusicReqDto));
        log.info("registeredMusicId: "+registeredMusic.getRegisteredMusicId());

        for (long occasionId: registeredMusicReqDto.getMusicOccasionIds()) {
            musicOccasionRepository.save(registerMusicMapper.toMusicOccasion(findOccasion(occasionId), registeredMusic));
        }
        log.info("[사용자 음악 등록 완료]");
    }

    @Transactional
    public List<Occasion> getTag() {
        return occasionRepository.findAll();
    }

//    @Transactional
//    public RegisteredMusicResDto getRegisteredMusic(long registeredMusicId) {
//        RegisteredMusic findRegisteredMusic = findRegisteredMusic(registeredMusicId);
//        RegisteredMusicResDto registeredMusicResDto = registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic);
//        log.info("RegisteredMusicResDto: "+registeredMusicResDto);
//
//        return registeredMusicResDto;
//    }

    @Transactional
    public List<RegisteredMusicResDto> getMusicList() {

//        List<RegisteredMusicResDto> registeredMusicResDtoList = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
//                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic))
//                .toList();
//        log.info("List<RegisteredMusicResDto>: "+registeredMusicResDtoList);

        return new ArrayList<>();
    }

    @Transactional
    public List<RegisteredMusicResDto> getMyMusicList(long memberId) {
//        findMember(memberId);

//        List<RegisteredMusicResDto> registeredMusicResDtoList = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
//                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic))
//                .toList();
//        log.info("List<RegisteredMusicResDto>: "+registeredMusicResDtoList);

        return new ArrayList<>();
    }

    @Transactional
    public void updateMusic(long memberId, long registeredMusicId) {
//        findMember(memberId);
//
//        RegisteredMusic findRegisteredMusic = registeredMusicRepositoryImpl.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
//                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
//        );
//
//        findRegisteredMusic.updateRegisteredMusic(true);
//        registeredMusicRepository.save(findRegisteredMusic);
//        log.info("[내가 등록 음악 삭제(수정)]");
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

//    public RegisteredMusic findRegisteredMusic(long registeredMusicId){
//        return registeredMusicRepositoryImpl.findByRegisterMusic(registeredMusicId).orElseThrow(
//                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
//        );
//    }

}
