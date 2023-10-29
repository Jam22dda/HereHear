package com.ssafy.herehear.member.service;

import com.ssafy.herehear.member.dto.response.FollowResDto;

import java.util.List;

public interface MemberService {

    List<FollowResDto> getFollowerList(Long memberId);

    List<FollowResDto> getFollowingList(Long memberId);

    void follow(Long memberId, Long followingMemberId);

    void unfollow(Long memberId, Long followingMemberId);
}
