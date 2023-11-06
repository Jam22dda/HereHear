package com.ssafy.herehear.member.service;

import com.ssafy.herehear.entity.Follow;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.ProfileCharacter;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.CookieUtil;
import com.ssafy.herehear.global.util.JwtProvider;
import com.ssafy.herehear.member.dto.request.SignUpReqDto;
import com.ssafy.herehear.member.dto.request.UpdateCharacterReqDto;
import com.ssafy.herehear.member.dto.request.UpdateMemberReqDto;
import com.ssafy.herehear.member.dto.response.FollowResDto;
import com.ssafy.herehear.member.dto.response.MemberInfoResDto;
import com.ssafy.herehear.member.mapper.MemberMapper;
import com.ssafy.herehear.member.mapper.ProfileCharacterMapper;
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

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;
    private final ProfileCharacterRepository profileCharacterRepository;
    private final MemberMapper memberMapper;
    private final ProfileCharacterMapper profileCharacterMapper;
    private final JwtProvider jwtProvider;
    private final CookieUtil cookieUtil;
    private static final String REFRESH_TOKEN = "refreshToken";

    @Override
    @Transactional
    public String signUp(SignUpReqDto signUpDto, HttpServletResponse response) {
        Member findMember = memberRepository.findById(signUpDto.getMemberId()).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );

        if(findMember.getNickname() != null && findMember.getProfileCharacter() != null){
            throw new CustomException(ExceptionStatus.MEMBER_ALREADY_SIGNED);
        }

        log.info("Sign Up memberId: {}", findMember.getMemberId());

        findMember.updateNickname(signUpDto.getNickname());
        ProfileCharacter profileCharacter = profileCharacterRepository.findById(signUpDto.getProfileCharacterCode()).orElseThrow(
                () -> new CustomException(ExceptionStatus.PROFILE_CHARACTER_NOT_FOUND)
        );
        findMember.updateCharacter(profileCharacter);
        memberRepository.save(findMember);
        log.info("{}", findMember.getProfileCharacter());

        String accessToken = jwtProvider.createAccessToken(findMember);

        String refreshToken = jwtProvider.createRefreshToken();
        Cookie cookie = cookieUtil.createCookie(refreshToken);
        response.addCookie(cookie);

        return accessToken;
    }

    @Override
    @Transactional
    public void updateNickname(UpdateMemberReqDto updateMemberReqDto, Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );

        findMember.updateNickname(updateMemberReqDto.getNickname());
        memberRepository.save(findMember);
    }

    @Override
    @Transactional
    public void updateCharacter(UpdateCharacterReqDto updateCharacterReqDto, Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );

        ProfileCharacter findCharcter = profileCharacterRepository.findById(updateCharacterReqDto.getCharacterId()).orElseThrow(
                () -> new CustomException(ExceptionStatus.PROFILE_CHARACTER_NOT_FOUND)
        );

        findMember.updateCharacter(findCharcter);
        memberRepository.save(findMember);
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = cookieUtil.getCookie(request, REFRESH_TOKEN).orElseThrow(
                () -> new CustomException(ExceptionStatus.TOKEN_NOT_FOUND_IN_COOKIE)
        );
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );
        if(findMember.getRemoveDate() != null)
            throw new CustomException(ExceptionStatus.MEMBER_IS_DELETED);

        findMember.deleteMember();
        memberRepository.save(findMember);
    }

    @Override
    @Transactional
    public MemberInfoResDto getMemberInfo(Long memberId) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(ExceptionStatus.MEMBER_NOT_FOUND)
        );
        if(findMember.getRemoveDate() != null)
            throw new CustomException(ExceptionStatus.MEMBER_IS_DELETED);

        MemberInfoResDto res = memberMapper.toMemberInfoResDto(findMember, findMember.getAchievement());
        log.info("Member Info: {}", res);

        return res;
    }

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
