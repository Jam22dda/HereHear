package com.ssafy.herehear.member.service;

import com.ssafy.herehear.entity.Follow;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.member.dto.response.FollowResDto;
import com.ssafy.herehear.member.mapper.MemberMapper;
import com.ssafy.herehear.member.repository.FollowRepository;
import com.ssafy.herehear.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public List<FollowResDto> getFollowerList(Long memberId) {
        return followRepository.findByFollowerMemberId(memberId).stream()
                .map(follow -> MemberMapper.INSTANCE.toFollowResDto(follow.getMember()))
                .toList();
    }

    @Override
    @Transactional
    public List<FollowResDto> getFollowingList(Long memberId) {
        return followRepository.findByFollowingMemberId(memberId).stream()
                .map(follow -> {
                    Member followingMember = memberRepository.findById(follow.getFollowMemberId())
                            .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));
                    return MemberMapper.INSTANCE.toFollowResDto(followingMember);
                }).toList();
    }

    @Override
    public void follow(Long memberId, Long followingMemberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));
        memberRepository.findById(followingMemberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));

        followRepository.save(MemberMapper.INSTANCE.toFollow(member, followingMemberId));
    }

    @Override
    public void unfollow(Long memberId, Long followingMemberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));
        memberRepository.findById(followingMemberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND));

        Follow follow = followRepository.findByMemberIdAndFollowMemberId(memberId, followingMemberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.FOLLOW_NOT_FOUND));

        followRepository.delete(follow);
    }
}
