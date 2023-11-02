package com.ssafy.herehear.member.service;

import com.ssafy.herehear.member.dto.request.SignUpReqDto;
import com.ssafy.herehear.member.dto.response.FollowResDto;
import com.ssafy.herehear.member.dto.response.MemberInfoResDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface MemberService {
    String signUp(SignUpReqDto signUpReqDto, HttpServletResponse response);

    void updateNickname(String nickname, Long memberId);
    void updateCharacter(Long characterId, Long memberId);

    void logout(HttpServletRequest request, HttpServletResponse response);

    void deleteMember(Long memberId);

    MemberInfoResDto getMemberInfo(Long memberId);

    List<FollowResDto> getFollowerList(Long memberId);

    List<FollowResDto> getFollowingList(Long memberId);

    void follow(Long memberId, Long followingMemberId);

    void unfollow(Long memberId, Long followingMemberId);
}
