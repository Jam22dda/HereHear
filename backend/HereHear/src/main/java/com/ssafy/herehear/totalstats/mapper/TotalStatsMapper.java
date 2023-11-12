package com.ssafy.herehear.totalstats.mapper;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.totalstats.dto.TotalStatsLikesResDto;
import com.ssafy.herehear.totalstats.dto.TotalStatsTagsResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TotalStatsMapper {

    TotalStatsLikesResDto toTotalStatsLikesResDto(RegisteredMusic registeredMusic, Long likeCount);

    TotalStatsTagsResDto toTotalStatsTagsResDto(String tagName, Long tagCount);
}
