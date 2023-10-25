package com.ssafy.herehear.music.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberReadList;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.music.MemberRepository;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.MemberReadListRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisteredMusicService {
    private final RegisteredMusicRepository registeredMusicRepository;
    private final RegisterMusicMapper registerMusicMapper;

    private final MemberReadListRepository memberReadListRepository;
    private final MemberRepository memberRepository;

    //음악 등록
    public void registerMusic(Long memberId, RegisteredMusicReqDto registeredMusicReqDto) {
        // 등록한지 1분이 지나지 않았으면 리턴
        // 하루에 같은 음악 한개만 등록 가능

        RegisteredMusic registeredMusic = registerMusicMapper.toRegisteredMusic(registeredMusicReqDto);
        RegisteredMusic savedMusic = registeredMusicRepository.save(registeredMusic);
        log.info("[음악 등록] mapping registeredMusic: "+registeredMusic+", RegisteredMusicId: "+savedMusic.getRegisteredMusicId());

        Member member = getMember(memberId);
        MemberReadList memberReadList = registerMusicMapper.registeredMusicToMemberReadList(member, savedMusic);
        log.info("[등록 음악] mapping MemberReadList: "+memberReadList.getMember().getNickname());
        memberReadListRepository.save(memberReadList);
        log.info("[사용자 등록 음악] mapping MemberReadList: "+memberReadList);

    }

    public RegisteredMusic getMusic(long musicId) {
        Optional<RegisteredMusic> findMusic = registeredMusicRepository.findById(musicId);

        return findMusic.orElseThrow(() -> new CustomException(ExceptionStatus.NOT_FOUND_MUSIC));
    }

    public List<RegisteredMusic> getMusicList() {
        return registeredMusicRepository.findAll();
    }

    //
    public List<RegisteredMusic> myRegisterMusics(long memberId) {
        log.info("[내가 등록한 음악 전체 조회] mapping memberId: "+memberId);
        return registeredMusicRepository.findAll();
    }

    //등록 음악 삭제(수정)
    public void updateMusic(long musicId) {
        RegisteredMusic findMusic = getMusic(musicId);
        findMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findMusic);
    }

    public Member getMember(long memberId){
        Optional<Member> findMusic = memberRepository.findByMemberId(memberId);

        return findMusic.orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));
    }

}
