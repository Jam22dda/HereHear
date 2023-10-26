package com.ssafy.herehear.music.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberReadList;
import com.ssafy.herehear.entity.Music;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.response.ResponseService;
import com.ssafy.herehear.global.response.ResponseStatus;
import com.ssafy.herehear.music.MemberRepository;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.MemberReadListRepository;
import com.ssafy.herehear.music.repository.MusicRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepositoryImpl;
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

    private final MemberReadListRepository memberReadListRepository;
    private final MemberRepository memberRepository;

    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;
    private final MusicRepository musicRepository;

    private final ResponseService responseService;

//    @Transactional
    public CommonResponse registerMusic(Long memberId, RegisteredMusicReqDto req) {
        // 등록한지 1분이 지나지 않았으면 리턴, 하루에 같은 음악 한개만 등록 가능

        Music music = musicRepository.findBySubject(req.getSubject());
        if (music == null) {
            music = registerMusicMapper.toMusic(req.getSubject(), req.getSubject(), req.getAlbumImg());
            music = musicRepository.save(music);
        }

        RegisteredMusic registeredMusic = registerMusicMapper.toRegisteredMusic(music, req.getLng(), req.getLat(), req.getComment());
        RegisteredMusic savedMusic = registeredMusicRepository.save(registeredMusic);
        log.info("[음악 등록] mapping registeredMusic: "+savedMusic.getRegisteredMusicId());

        Member member = findMember(memberId);

        //복합키 저장 다시
        MemberReadList memberReadList = registerMusicMapper.registeredMusicToMemberReadList(member, savedMusic, music);
//        MemberReadList memberReadList2 = MemberReadList.builder()
//                .member(member).registeredMusic(savedMusic).music(music).build();
        log.info("mapping memberReadList: "+memberReadList.getMember().getMemberId()+", "+memberReadList.getRegisteredMusic().getRegisteredMusicId()+", "+memberReadList.getMusic().getMusicId());
        memberReadListRepository.save(memberReadList);
//        memberReadListRepository.save(memberReadList2);
        log.info("[사용자 음악 등록 완료]");

        return responseService.successResponse(ResponseStatus.RESOPNSE_SUCCESS);
    }

    public DataResponse<RegisteredMusicResDto> getRegisteredMusic(long registeredMusicId) {
        RegisteredMusic findRegisteredMusic = findRegisteredMusic(registeredMusicId);
        Music findMusic = findMusic(findRegisteredMusic.getMusic().getMusicId());
        RegisteredMusicResDto registeredMusicResDto = registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic, findMusic);
        log.info("[음악 상세 조회]: "+registeredMusicResDto);

        return responseService.successDataResponse(ResponseStatus.RESOPNSE_SUCCESS, registeredMusicResDto);
    }

    public DataResponse<List<RegisteredMusicResDto>> getMusicList() {
        List<RegisteredMusic> findRegisteredMusicList = registeredMusicRepository.findAll();
        List<RegisteredMusicResDto> registeredMusicResDtoList = new ArrayList<>();

        for (RegisteredMusic findRegisteredMusic : findRegisteredMusicList) {
            Music findMusic = findMusic(findRegisteredMusic.getMusic().getMusicId());
            RegisteredMusicResDto registeredMusicResDto = registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic, findMusic);
            registeredMusicResDtoList.add(registeredMusicResDto);
        }
        log.info("[음악 전체 조회]: "+registeredMusicResDtoList);

        return responseService.successDataResponse(ResponseStatus.RESOPNSE_SUCCESS, registeredMusicResDtoList);
    }

    public DataResponse<List<RegisteredMusicResDto>> getMyMusicList(long memberId) {
        //다시
        List<RegisteredMusic> findRegisteredMusicList = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId);
        List<RegisteredMusicResDto> registeredMusicResDtoList = new ArrayList<>();

        for (RegisteredMusic findRegisteredMusic : findRegisteredMusicList) {
            Music findMusic = findMusic(findRegisteredMusic.getMusic().getMusicId());
            RegisteredMusicResDto registeredMusicResDto = registerMusicMapper.toRegisteredMusicResDto(findRegisteredMusic, findMusic);
            registeredMusicResDtoList.add(registeredMusicResDto);
        }
        log.info("[내 음악 전체 조회]: "+registeredMusicResDtoList);

        return responseService.successDataResponse(ResponseStatus.RESOPNSE_SUCCESS, registeredMusicResDtoList);
    }

    public CommonResponse updateMusic(long musicId) {
        RegisteredMusic findMusic = findRegisteredMusic(musicId);
        findMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findMusic);
        log.info("[내가 등록 음악 삭제(수정)]");

        return responseService.successResponse(ResponseStatus.RESOPNSE_SUCCESS);
    }

    public Member findMember(long memberId){
        return memberRepository.findByMemberId(memberId).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );
    }

    public RegisteredMusic findRegisteredMusic(long registeredMusicId){
        return registeredMusicRepository.findById(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    public Music findMusic(long musicId){
        return musicRepository.findById(musicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_MUSIC)
        );
    }
}
