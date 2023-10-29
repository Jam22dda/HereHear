package com.ssafy.herehear.admin.mapper;

import com.ssafy.herehear.admin.dto.CreateAchievementDto;
import com.ssafy.herehear.entity.Achievement;
import com.ssafy.herehear.entity.BadgeCode;
import com.ssafy.herehear.entity.TitleCode;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-28T01:29:44+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Azul Systems, Inc.)"
)
@Component
public class CreateAchievementMapperImpl implements CreateAchievementMapper {

    @Override
    public TitleCode toTitleCodeEntity(CreateAchievementDto createAchievementDto) {
        if ( createAchievementDto == null ) {
            return null;
        }

        TitleCode.TitleCodeBuilder titleCode = TitleCode.builder();

        titleCode.titleName( createAchievementDto.getTitleName() );

        return titleCode.build();
    }

    @Override
    public BadgeCode toBadgeCodeEntity(CreateAchievementDto createAchievementDto) {
        if ( createAchievementDto == null ) {
            return null;
        }

        BadgeCode.BadgeCodeBuilder badgeCode = BadgeCode.builder();

        badgeCode.badgeName( createAchievementDto.getBadgeName() );
        badgeCode.badgeImg( createAchievementDto.getBadgeImg() );

        return badgeCode.build();
    }

    @Override
    public Achievement toAchievementEntity(CreateAchievementDto createAchievementDto, TitleCode titleCode, BadgeCode badgeCode) {
        if ( createAchievementDto == null && titleCode == null && badgeCode == null ) {
            return null;
        }

        Achievement.AchievementBuilder achievement = Achievement.builder();

        if ( createAchievementDto != null ) {
            achievement.mission( createAchievementDto.getMission() );
        }
        achievement.titleCode( titleCode );
        achievement.badgeCode( badgeCode );

        return achievement.build();
    }
}
