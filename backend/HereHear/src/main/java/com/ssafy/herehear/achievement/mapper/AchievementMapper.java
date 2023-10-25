package com.ssafy.herehear.achievement.mapper;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.BorderCodeDto;
import com.ssafy.herehear.achievement.dto.TitleCodeDto;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.BorderCode;
import com.ssafy.herehear.entity.TitleCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AchievementMapper {

    AchievementMapper INSTANCE = Mappers.getMapper(AchievementMapper.class);

    @Mapping(source = "borderCode", target = "border")
    @Mapping(source = "titleCode", target = "title")
    AchievementDto toAchievementDto(Achievement achievement);

    BorderCodeDto toBorderCodeDto(BorderCode borderCode);

    TitleCodeDto toTitleCodeDto(TitleCode titleCode);

}
