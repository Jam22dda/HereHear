package com.ssafy.herehear.member.service;

import com.ssafy.herehear.member.dto.request.SignUpReqDto;
import com.ssafy.herehear.member.dto.request.UpdateCharacterReqDto;
import com.ssafy.herehear.member.dto.request.UpdateMemberReqDto;
import com.ssafy.herehear.member.dto.response.FollowerResDto;
import com.ssafy.herehear.member.dto.response.MemberInfoResDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface MemberService {
    String signUp(SignUpReqDto signUpReqDto, HttpServletResponse response);

    void updateNickname(UpdateMemberReqDto updateMemberReqDto, Long memberId);
    void updateCharacter(UpdateCharacterReqDto updateCharacterReqDto, Long memberId);

    void logout(HttpServletRequest request, HttpServletResponse response);

    void deleteMember(Long memberId);

    MemberInfoResDto getMemberInfo(Long memberId);

    List<FollowerResDto> getFollowerList(Long memberId);

    List<FollowerResDto> getFollowingList(Long memberId);

    void follow(Long memberId, Long followingMemberId);

    void unfollow(Long memberId, Long followingMemberId);

    boolean checkNickname(String nickname);

    List<FollowerResDto> getFollowerByTargetIdList(Long memberId, Long targetId);

    List<FollowerResDto> getFollowingByTargetIdList(Long memberId, Long targetId);
}
