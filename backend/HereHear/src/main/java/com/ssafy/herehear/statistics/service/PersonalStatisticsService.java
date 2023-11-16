package com.ssafy.herehear.statistics.service;

import com.ssafy.herehear.statistics.dto.response.PersonalHearTimeResDto;
import com.ssafy.herehear.statistics.dto.response.PersonalTagsResDto;

public interface PersonalStatisticsService {
    int getLikeCount(Long memberId);
    PersonalHearTimeResDto getHearTime(Long memberId);
    PersonalTagsResDto getPersonalTags(Long memberId);
}
