package com.ssafy.herehear.member.service;

import com.ssafy.herehear.entity.Follow;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.ProfileCharacter;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.CookieUtil;
import com.ssafy.herehear.global.util.JwtProvider;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.member.dto.request.SignUpReqDto;
import com.ssafy.herehear.member.dto.request.UpdateCharacterReqDto;
import com.ssafy.herehear.member.dto.request.UpdateMemberReqDto;
import com.ssafy.herehear.member.dto.response.FollowerResDto;
import com.ssafy.herehear.member.dto.response.MemberInfoResDto;
import com.ssafy.herehear.member.mapper.MemberMapper;
import com.ssafy.herehear.member.repository.FollowRepository;
import com.ssafy.herehear.member.repository.MemberRepository;
import com.ssafy.herehear.member.repository.ProfileCharacterRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final ProfileCharacterRepository profileCharacterRepository;
    private final MemberMapper memberMapper;
    private final JwtProvider jwtProvider;
    private static final String REFRESH_TOKEN = "refreshToken";

    @Override
    @Transactional
    public String signUp(SignUpReqDto signUpDto, HttpServletResponse response) {
        Member findMember = MemberUtil.findMember(signUpDto.getMemberId());

        if (findMember.getNickname() != null && findMember.getProfileCharacter() != null) {
            throw new CustomException(ExceptionStatus.MEMBER_ALREADY_SIGNED);
        }

        log.info("Sign Up memberId: {}", findMember.getMemberId());

        if (!checkNickname(signUpDto.getNickname())) {
            throw new CustomException(ExceptionStatus.NICKNAME_ALREADY_USED);
        }

        findMember.updateNickname(signUpDto.getNickname());
        ProfileCharacter profileCharacter = profileCharacterRepository.findById(signUpDto.getProfileCharacterCode()).orElseThrow(
                () -> new CustomException(ExceptionStatus.PROFILE_CHARACTER_NOT_FOUND)
        );
        findMember.updateCharacter(profileCharacter);
        memberRepository.save(findMember);
        log.info("{}", findMember.getProfileCharacter());

        String accessToken = jwtProvider.createAccessToken(findMember);

        String refreshToken = jwtProvider.createRefreshToken();
        Cookie cookie = CookieUtil.createCookie(refreshToken);
        response.addCookie(cookie);

        return accessToken;
    }

    @Override
    @Transactional
    public void updateNickname(UpdateMemberReqDto updateMemberReqDto, Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);

        if (!checkNickname(updateMemberReqDto.getNickname())) {
            throw new CustomException(ExceptionStatus.NICKNAME_ALREADY_USED);
        }
        findMember.updateNickname(updateMemberReqDto.getNickname());
        memberRepository.save(findMember);
    }

    @Override
    @Transactional
    public void updateCharacter(UpdateCharacterReqDto updateCharacterReqDto, Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);

        ProfileCharacter findCharcter = profileCharacterRepository.findById(updateCharacterReqDto.getCharacterId()).orElseThrow(
                () -> new CustomException(ExceptionStatus.PROFILE_CHARACTER_NOT_FOUND)
        );

        findMember.updateCharacter(findCharcter);
        memberRepository.save(findMember);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = CookieUtil.getCookie(request, REFRESH_TOKEN).orElseThrow(
                () -> new CustomException(ExceptionStatus.TOKEN_NOT_FOUND_IN_COOKIE)
        );
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);

        findMember.deleteMember();
        memberRepository.save(findMember);
    }

    @Override
    @Transactional
    public MemberInfoResDto getMemberInfo(Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);

        MemberInfoResDto res = memberMapper.toMemberInfoResDto(findMember, findMember.getAchievement());
        log.info("Member Info: {}", res);

        return res;
    }

    @Override
    @Transactional
    public List<FollowerResDto> getFollowerList(Long memberId) {
        Set<Long> followingSet = followRepository.findByFollowingMemberId(memberId).stream()
                .map(Follow::getFollowMemberId).collect(Collectors.toSet());

        return followRepository.findByFollowerMemberId(memberId).stream()
                .map(follow -> MemberMapper.INSTANCE.toFollowerListDto(follow.getMember(), follow.getMember().getAchievement(), followingSet.contains(follow.getMember().getMemberId())))
                .toList();
    }

    @Override
    @Transactional
    public List<FollowerResDto> getFollowerByTargetIdList(Long memberId, Long targetId) {
        Set<Long> followingSet = followRepository.findByFollowingMemberId(memberId).stream()
                .map(Follow::getFollowMemberId).collect(Collectors.toSet());

        return followRepository.findByFollowerMemberId(targetId).stream()
                .map(follow -> MemberMapper.INSTANCE.toFollowerListDto(follow.getMember(), follow.getMember().getAchievement(), followingSet.contains(follow.getMember().getMemberId())))
                .toList();
    }

    @Override
    @Transactional
    public List<FollowerResDto> getFollowingList(Long memberId) {
        return followRepository.findByFollowingMemberId(memberId).stream()
                .map(follow -> {
                    Member followingMember = MemberUtil.findMember(follow.getFollowMemberId());
                    Optional<Follow> isFollowed = followRepository.isFollowed(follow.getFollowMemberId(), memberId);
                    return MemberMapper.INSTANCE.toFollowerListDto(followingMember, followingMember.getAchievement(),
                            isFollowed.isPresent());
                }).toList();
    }

    @Override
    public List<FollowerResDto> getFollowingByTargetIdList(Long memberId, Long targetId) {
        return followRepository.findByFollowingMemberId(targetId).stream()
                .map(follow -> {
                    Member followingMember = MemberUtil.findMember(follow.getFollowMemberId());
                    Optional<Follow> isFollowed = followRepository.isFollowed(follow.getFollowMemberId(), memberId);
                    return MemberMapper.INSTANCE.toFollowerListDto(followingMember, followingMember.getAchievement(),
                            isFollowed.isPresent());
                }).toList();
    }

    @Override
    public void follow(Long memberId, Long followingMemberId) {
        Member findMember = MemberUtil.findMember(memberId);
        MemberUtil.findMember(followingMemberId);

        // 중복 팔로우에 대한 예외처리
        followRepository.findByMemberIdAndFollowMemberId(memberId, followingMemberId)
                .ifPresent(o -> {
                    throw new CustomException(ExceptionStatus.FOLLOW_ALREADY_EXIST);
                });

        followRepository.save(MemberMapper.INSTANCE.toFollow(findMember, followingMemberId));
    }

    @Override
    public void unfollow(Long memberId, Long followingMemberId) {
        MemberUtil.findMember(memberId);
        MemberUtil.findMember(followingMemberId);

        Follow follow = followRepository.findByMemberIdAndFollowMemberId(memberId, followingMemberId)
                .orElseThrow(() -> new CustomException(ExceptionStatus.FOLLOW_NOT_FOUND));

        followRepository.delete(follow);
    }

    @Override
    public boolean checkNickname(String nickname) {
        return memberRepository.findByNickname(nickname).isEmpty();
    }

}
