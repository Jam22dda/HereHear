package com.ssafy.herehear.statistics.repository;
import com.ssafy.herehear.statistics.dto.response.TagResDto;

import java.util.List;

public interface RegisteredMusicRepositoryCustom {
    List<TagResDto> findAllTagsCountByMemberId(Long memberId);
    List<Integer> findAllTimeByMemberId(Long memberId);
}
