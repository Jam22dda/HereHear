package com.ssafy.herehear.member.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import com.ssafy.herehear.member.dto.request.FollowReqDto;
import com.ssafy.herehear.member.dto.request.SignUpReqDto;
import com.ssafy.herehear.member.dto.response.FollowResDto;
import com.ssafy.herehear.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/follower")
    public DataResponse<List<FollowResDto>> getFollowerList() {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[팔로워 목록 조회] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<FollowResDto> followerList = memberService.getFollowerList(memberId);

        return new DataResponse<>("200", "팔로워 목록 조회", followerList);
    }

    @GetMapping("/following")
    public DataResponse<List<FollowResDto>> getFollowing() {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[팔로잉 목록 조회] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<FollowResDto> followingList = memberService.getFollowingList(memberId);

        return new DataResponse<>("200", "팔로잉 목록 조회", followingList);
    }

    @PostMapping("/follow")
    public CommonResponse follow(@RequestBody FollowReqDto followReqDto) {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[팔로우] memberId: {}, followMemberId: {}, time: {}", memberId, followReqDto.getMemberId(), TimeFormatUtil.formatTime(LocalDateTime.now()));

        memberService.follow(memberId, followReqDto.getMemberId());

        return new CommonResponse("200", "팔로우 성공");
    }

    @DeleteMapping("/follow")
    public CommonResponse unfollow(@RequestBody FollowReqDto followReqDto) {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[언팔로우] memberId: {}, followMemberId: {}, time: {}", memberId, followReqDto.getMemberId(), TimeFormatUtil.formatTime(LocalDateTime.now()));

        memberService.unfollow(memberId, followReqDto.getMemberId());

        return new CommonResponse("200", "언팔로우 성공");
    }

    @PostMapping("/signup")
    public DataResponse signUp(@RequestBody SignUpReqDto req, HttpServletResponse response) {
        String accessToken =  memberService.signUp(req, response);
        return new DataResponse("200", "회원 가입이 완료되었습니다", accessToken);
    }

    @DeleteMapping("/logout")
    public CommonResponse logout(HttpServletRequest request, HttpServletResponse response) {
        memberService.logout(request, response);
        return new CommonResponse("200", "로그아웃을 성공하였습니다");
    }

    @GetMapping("/info")
    public DataResponse getMemberInfo(Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        return new DataResponse("200", "멤버 정보 반환에 성공하였습니다", memberService.getMemberInfo(memberId));
    }

    @GetMapping("/info/{memberId}")
    public DataResponse getOtherMemberInfo(@PathVariable("memberId") Long memberId) {
        return new DataResponse("200", "다른 멤버 정보 반환에 성공하였습니다", memberService.getMemberInfo(memberId));
    }

    @PostMapping("/update/nickname")
    public CommonResponse updateNickname(@RequestBody String nickname, Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        memberService.updateNickname(nickname, memberId);
        return new CommonResponse("200", "닉네임 변경을 성공하였습니다");
    }

    @PostMapping("/update/character")
    public CommonResponse updateCharacter(@RequestBody Long characterCode, Authentication authentication) {
        Long memberId = Long.parseLong(authentication.getName());
        memberService.updateCharacter(characterCode, memberId);
        return new CommonResponse("200", "캐릭터 변경을 성공하였습니다");
    }
}
