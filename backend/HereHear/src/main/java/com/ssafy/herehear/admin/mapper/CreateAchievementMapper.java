package com.ssafy.herehear.admin.mapper;

import com.ssafy.herehear.admin.dto.CreateAchievementDto;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.BadgeCode;
import com.ssafy.herehear.entity.TitleCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreateAchievementMapper {

    CreateAchievementMapper INSTANCE = Mappers.getMapper(CreateAchievementMapper.class);

    TitleCode toTitleCodeEntity(CreateAchievementDto createAchievementDto);

    BadgeCode toBadgeCodeEntity(CreateAchievementDto createAchievementDto);

    @Mapping(source = "titleCode", target = "titleCode")
    @Mapping(source = "badgeCode", target = "badgeCode")
    Achievement toAchievementEntity(CreateAchievementDto createAchievementDto, TitleCode titleCode, BadgeCode badgeCode);

}
