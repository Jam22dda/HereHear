package com.ssafy.herehear.member.controller;

import com.ssafy.herehear.global.response.CommonResponse;
import com.ssafy.herehear.global.response.DataResponse;
import com.ssafy.herehear.global.util.TimeFormatUtil;
import com.ssafy.herehear.member.dto.request.FollowReqDto;
import com.ssafy.herehear.member.dto.response.FollowResDto;
import com.ssafy.herehear.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/follower")
    public DataResponse<List<FollowResDto>> getFollowerList() {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[팔로워 목록 조회] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<FollowResDto> followerList = memberService.getFollowerList(memberId);

        return new DataResponse<>("200", "팔로워 목록 조회", followerList);
    }

    @GetMapping("/member/following")
    public DataResponse<List<FollowResDto>> getFollowing() {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[팔로잉 목록 조회] memberId: {}, time: {}", memberId, TimeFormatUtil.formatTime(LocalDateTime.now()));

        List<FollowResDto> followingList = memberService.getFollowingList(memberId);

        return new DataResponse<>("200", "팔로잉 목록 조회", followingList);
    }

    @PostMapping("/member/follow")
    public CommonResponse follow(@RequestBody FollowReqDto followReqDto) {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[팔로우] memberId: {}, followMemberId: {}, time: {}", memberId, followReqDto.getMemberId(), TimeFormatUtil.formatTime(LocalDateTime.now()));

        memberService.follow(memberId, followReqDto.getMemberId());

        return new CommonResponse("200", "팔로우 성공");
    }

    @DeleteMapping("/member/follow")
    public CommonResponse unfollow(@RequestBody FollowReqDto followReqDto) {
        // TODO: OAuth2.0으로 받아온 userId로 변경해야 함
        Long memberId = 1L;

        log.info("[언팔로우] memberId: {}, followMemberId: {}, time: {}", memberId, followReqDto.getMemberId(), TimeFormatUtil.formatTime(LocalDateTime.now()));

        memberService.unfollow(memberId, followReqDto.getMemberId());

        return new CommonResponse("200", "언팔로우 성공");
    }
}
