package com.ssafy.herehear.achievement.mapper;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.BadgeCodeDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;
import com.ssafy.herehear.achievement.dto.TitleCodeDto;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.BadgeCode;
import com.ssafy.herehear.entity.TitleCode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AchievementMapper {

    AchievementMapper INSTANCE = Mappers.getMapper(AchievementMapper.class);

    @Mapping(source = "badgeCode", target = "badge")
    @Mapping(source = "titleCode", target = "title")
    AchievementDto toAchievementDto(Achievement achievement);

    BadgeCodeDto toBadgeCode(BadgeCode badgeCode);

    TitleCodeDto toTitleCodeDto(TitleCode titleCode);

    @Mapping(source = "achievement.badgeCode", target = "badge")
    @Mapping(source = "achievement.titleCode", target = "title")
    @Mapping(source = "clearTime", target = "clearTime")
    @Mapping(source = "userId", target = "userId")
    MemberAchievementDto toMemberAchievementDto(Achievement achievement, Long userId, String clearTime);

}
