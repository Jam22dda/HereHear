package com.ssafy.herehear.admin.mapper;

import com.ssafy.herehear.admin.dto.CreateAchievementDto;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.BorderCode;
import com.ssafy.herehear.entity.TitleCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CreateAchievementMapper {

    CreateAchievementMapper INSTANCE = Mappers.getMapper(CreateAchievementMapper.class);

    TitleCode toTitleCodeEntity(CreateAchievementDto createAchievementDto);

    BorderCode toBorderCodeEntity(CreateAchievementDto createAchievementDto);

    @Mapping(source = "titleCode", target = "titleCode")
    @Mapping(source = "borderCode", target = "borderCode")
    Achievement toAchievementEntity(CreateAchievementDto createAchievementDto, TitleCode titleCode, BorderCode borderCode);

}
