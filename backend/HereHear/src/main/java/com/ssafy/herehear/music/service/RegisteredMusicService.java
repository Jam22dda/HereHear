package com.ssafy.herehear.music.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.MemberRepository;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.RegisteredMusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisteredMusicService {
    private final RegisteredMusicRepository registeredMusicRepository;
    private final RegisterMusicMapper registerMusicMapper;
    private final MemberRepository memberRepository;

    //음악 등록
    public void registerMusic(Long memberId, RegisteredMusicReqDto registeredMusicReqDto) {
        // 등록한지 1분이 지나지 않았으면 리턴
        // 하루에 같은 음악 한개만 등록 가능

        Member member = getMember(memberId);

        RegisteredMusic registeredMusic = registerMusicMapper.toRegisteredMusic(registeredMusicReqDto);
        registeredMusicRepository.save(registeredMusic);
    }

    //음악 개별 조회
    public RegisteredMusic readMusic(int musicId) {
        Optional<RegisteredMusic> optionalMusic = registeredMusicRepository.findById(musicId);
        if (optionalMusic.isPresent()) { //음악이 존재하지 않는 경우
            throw new RuntimeException("존재하지 않는 음악입니다."); //임시 예외
        }

        return optionalMusic.get();
    }

    //등록 음악 전체 조회
    public List<RegisteredMusic> readMusics() {
        return registeredMusicRepository.findAll();
    }

    //내가 등록한 음악 전체 조회
    public List<RegisteredMusic> myRegisterMusics(int memberId) {
//        List<RegisteredMusic> myRegisterMusics = registeredMusicRepository.findMyRegisterMusics(memberId);
        return registeredMusicRepository.findAll();
    }

    //등록 음악 삭제(수정)
    public RegisteredMusic updateMusic(int MusicId) {
        RegisteredMusic findMusic = readMusic(MusicId);
//        findMusic.updateMusic(true);
        return registeredMusicRepository.save(findMusic);
    }

    public Member getMember(long memberId){
        return memberRepository.findByMemberId(memberId).orElseThrow(() -> new RuntimeException("등록되지 않은 회원"));
    }

}
