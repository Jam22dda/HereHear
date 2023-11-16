package com.ssafy.herehear.statistics.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MusicHistory;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.history.repository.MusicHistoryRepository;
import com.ssafy.herehear.statistics.dto.response.PersonalHearTimeResDto;
import com.ssafy.herehear.statistics.dto.response.PersonalTagsResDto;
import com.ssafy.herehear.statistics.dto.response.TagResDto;
import com.ssafy.herehear.statistics.repository.LikeMusicRepositoryImpl;
import com.ssafy.herehear.statistics.repository.RegisteredMusicRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonalStatisticsServiceImpl implements PersonalStatisticsService {

    private final LikeMusicRepositoryImpl likeMusicRepository;
    private final RegisteredMusicRepositoryImpl musicRepository;
    private final MusicHistoryRepository musicHistoryRepository;

    @Override
    public int getLikeCount(Long memberId) {
        MemberUtil.findMember(memberId);

        int likeCount = likeMusicRepository.findLikeCountByMemberId(memberId);
        log.info("[개인 통계 좋아요 개수 반환] memberId: "+memberId+", count: "+likeCount);
        return likeCount;
    }

    @Override
    public PersonalHearTimeResDto getHearTime(Long memberId) {
        MemberUtil.findMember(memberId);
        List<Integer> list = musicRepository.findAllTimeHistoryByMemberId(memberId);

        String[] ment = {"00~03시", "03~06시", "06~09시", "09~12시",
                "12~15시", "15~18시", "18~21시", "21~24시"};
        Map<String, Integer> timeMap = new HashMap<>();

        for(String key : ment) {
            timeMap.put(key, 0);
        }

        int max = 0;
        int maxIndex = 0;
        for(Integer i : list) {
            int value = timeMap.get(ment[i/3]);
            timeMap.put(ment[i/3], value+1);
            if(max < value) {
                max = value;
                maxIndex = i/3;
            }
        }

        return PersonalHearTimeResDto.builder()
                .time(timeMap)
                .mostTime(ment[maxIndex])
                .build();
    }

    @Override
    public PersonalTagsResDto getPersonalTags(Long memberId) {
        Member findMember = MemberUtil.findMember(memberId);
        List<TagResDto> list = musicRepository.findAllTagsCountByMemberId(memberId);

        return  PersonalTagsResDto.builder()
                .nickname(findMember.getNickname())
                .tagResDtoList(list).build();
    }
}
