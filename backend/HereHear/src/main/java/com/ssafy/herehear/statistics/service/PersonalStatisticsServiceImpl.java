package com.ssafy.herehear.statistics.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.statistics.dto.response.PersonalHearTimeResDto;
import com.ssafy.herehear.statistics.dto.response.PersonalTagsResDto;
import com.ssafy.herehear.statistics.dto.response.TagResDto;
import com.ssafy.herehear.statistics.repository.LikeMusicRepositoryImpl;
import com.ssafy.herehear.statistics.repository.RegisteredMusicRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalStatisticsServiceImpl implements PersonalStatisticsService {

    private final LikeMusicRepositoryImpl likeMusicRepository;
    private final RegisteredMusicRepositoryImpl musicRepository;

    @Override
    public int getLikeCount(Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);

        int likeCount = likeMusicRepository.findLikeCountByMemberId(memberId);
        log.info("[개인 통계 좋아요 개수 반환] memberId: "+memberId+", count: "+likeCount);
        return likeCount;
    }

    @Override
    public PersonalHearTimeResDto getHearTime(Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);
        List<Integer> list = musicRepository.findAllTimeByMemberId(memberId);

        int[] time = new int[4];
        int max = 0, maxIndex = 0;
        for(Integer i : list) {
            time[i/6]++;
            if(max < time[i/6]) {
                max = time[i/6];
                maxIndex = i/6;
            }
        }
        String[] ment = {"00~06시", "06~12시", "12~18시", "18~00시"};

        PersonalHearTimeResDto personalHearTimeResDto = PersonalHearTimeResDto.builder()
                .time(time)
                .mostTime(ment[maxIndex])
                .build();

        return personalHearTimeResDto;
    }

    @Override
    public PersonalTagsResDto getPersonalTags(Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);
        List<TagResDto> list = musicRepository.findAllTagsCountByMemberId(memberId);

        PersonalTagsResDto personalTagsResDto = PersonalTagsResDto.builder()
                .nickname(findMember.getNickname())
                .tagResDtoList(list).build();
        return personalTagsResDto;
    }
}
