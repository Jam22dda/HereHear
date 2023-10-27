package com.ssafy.herehear.music.service;

import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.music.MemberRepository;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.MemberReadListRepository;
import com.ssafy.herehear.music.repository.MusicRepository;
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

    private final MemberReadListRepository memberReadListRepository;
    private final MemberRepository memberRepository;

    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;
    private final MusicRepository musicRepository;

    @Transactional
    public void registerMusic(Long memberId, RegisteredMusicReqDto registeredMusicReqDto) {

        Music music = musicRepository.findBySubject(registeredMusicReqDto.getSubject())
                .orElseGet(() -> {
                    Music newMusic = registerMusicMapper.toMusic(registeredMusicReqDto);
                    return musicRepository.save(newMusic);
                });
        log.info("musicId "+music.getMusicId());

        RegisteredMusic registeredMusicMapping = registerMusicMapper.toRegisteredMusic(music, registeredMusicReqDto);
        RegisteredMusic registeredMusic = registeredMusicRepository.save(registeredMusicMapping);
        log.info("registeredMusicId: "+registeredMusic.getRegisteredMusicId());

        Member member = findMember(memberId);

        MemberMusicId memberMusicId = new MemberMusicId( memberId, registeredMusic.getRegisteredMusicId(), music.getMusicId());
//        MemberReadList memberReadListMapping = registerMusicMapper.registeredMusicToMemberReadList(memberMusicId, member, registeredMusic, music);
        MemberReadList memberReadListMapping = MemberReadList.builder()
                .id(memberMusicId)
                .member(member)
                .registeredMusic(registeredMusic)
                .music(music)
                .build();
        memberReadListRepository.save(memberReadListMapping);

        log.info("[사용자 음악 등록 완료]");
    }

    @Transactional
    public RegisteredMusicResDto getRegisteredMusic(long registeredMusicId) {
        RegisteredMusic findRegisteredMusic = findRegisteredMusic(registeredMusicId);
        RegisteredMusicResDto registeredMusicResDto = registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic, findRegisteredMusic.getMusic());
        log.info("RegisteredMusicResDto: "+registeredMusicResDto);

        return registeredMusicResDto;
    }

    @Transactional
    public List<RegisteredMusicResDto> getMusicList() {

        List<RegisteredMusicResDto> registeredMusicResDtoList = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic, findRegisteredMusic.getMusic()))
                .toList();
        log.info("List<RegisteredMusicResDto>: "+registeredMusicResDtoList);

        return registeredMusicResDtoList;
    }

    @Transactional
    public List<RegisteredMusicResDto> getMyMusicList(long memberId) {
        findMember(memberId);

        List<RegisteredMusicResDto> registeredMusicResDtoList = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic, findRegisteredMusic.getMusic()))
                .toList();
        log.info("List<RegisteredMusicResDto>: "+registeredMusicResDtoList);

        return registeredMusicResDtoList;
    }

    @Transactional
    public void updateMusic(long memberId, long registeredMusicId) {
        findMember(memberId);

        RegisteredMusic findRegisteredMusic = registeredMusicRepositoryImpl.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );

        findRegisteredMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findRegisteredMusic);
        log.info("[내가 등록 음악 삭제(수정)]");
    }

    public Member findMember(long memberId){
        return memberRepository.findByMemberId(memberId).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );
    }

    public RegisteredMusic findRegisteredMusic(long registeredMusicId){
        return registeredMusicRepositoryImpl.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

}
