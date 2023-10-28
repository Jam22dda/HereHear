package com.ssafy.herehear.music.service;

import com.ssafy.herehear.achievement.repository.MemberRepository;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicDetailsResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.MusicOccasionRepository;
import com.ssafy.herehear.music.repository.OccasionRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public void registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto) {
        Member member = findMember(memberId);

        RegisteredMusic registeredMusic = registeredMusicRepository.save(registerMusicMapper.toRegisteredMusic(member, registerMusicReqDto));
        log.info("registeredMusicId: "+registeredMusic.getRegisteredMusicId());

        for (long occasionId: registerMusicReqDto.getMusicOccasionIds()) {
            musicOccasionRepository.save(registerMusicMapper.toMusicOccasion(findOccasion(occasionId), registeredMusic));
        }
        log.info("[사용자 음악 등록 완료]");
    }

    public List<Occasion> getTag() {
        return occasionRepository.findAll();
    }

    @Transactional
    public RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId) {
        Member member = findMember(memberId);

        RegisteredMusicDetailsResDto registeredMusicDetailsResDto = registerMusicMapper.toRegisteredMusicResDto(
                findByRegisterMusicDetails(registeredMusicId),
                findRegisteredMusicLike(memberId, registeredMusicId),
                member.getNickname(),
                registeredMusicRepositoryImpl.findByOccasion(registeredMusicId)
        );
        log.info("RegisteredMusicResDto: "+ registeredMusicDetailsResDto);

        return registeredMusicDetailsResDto;
    }
//
//    @Transactional
//    public List<RegisteredMusicDetailsResDto> getMusicList() {
//
////        List<RegisteredMusicResDto> registeredMusicResDtoList = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
////                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic))
////                .toList();
////        log.info("List<RegisteredMusicResDto>: "+registeredMusicResDtoList);
//
//        return new ArrayList<>();
//    }
//
//    @Transactional
//    public List<RegisteredMusicDetailsResDto> getMyMusicList(long memberId) {
////        findMember(memberId);
//
////        List<RegisteredMusicResDto> registeredMusicResDtoList = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
////                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic))
////                .toList();
////        log.info("List<RegisteredMusicResDto>: "+registeredMusicResDtoList);
//
//        return new ArrayList<>();
//    }
//
//    @Transactional
//    public void updateMusic(long memberId, long registeredMusicId) {
////        findMember(memberId);
////
////        RegisteredMusic findRegisteredMusic = registeredMusicRepositoryImpl.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
////                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
////        );
////
////        findRegisteredMusic.updateRegisteredMusic(true);
////        registeredMusicRepository.save(findRegisteredMusic);
////        log.info("[내가 등록 음악 삭제(수정)]");
//    }

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

    public RegisteredMusic findByRegisterMusicDetails(long registeredMusicId){
        return registeredMusicRepositoryImpl.findByRegisterMusicDetails(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    public boolean findRegisteredMusicLike(long memberId, long registeredMusicId) {
        return registeredMusicRepositoryImpl.findByRegisteredMusicLike(memberId, registeredMusicId).isPresent();
    }

}
