package com.ssafy.herehear.achievement.mapper;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.BadgeCodeDto;
import com.ssafy.herehear.achievement.dto.MemberAchievementDto;
import com.ssafy.herehear.achievement.dto.TitleCodeDto;
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
public class AchievementMapperImpl implements AchievementMapper {

    @Override
    public AchievementDto toAchievementDto(Achievement achievement) {
        if ( achievement == null ) {
            return null;
        }

        AchievementDto achievementDto = new AchievementDto();

        achievementDto.setBadge( toBadgeCode( achievement.getBadgeCode() ) );
        achievementDto.setTitle( toTitleCodeDto( achievement.getTitleCode() ) );
        achievementDto.setAchievementId( achievement.getAchievementId() );
        achievementDto.setMission( achievement.getMission() );

        return achievementDto;
    }

    @Override
    public BadgeCodeDto toBadgeCode(BadgeCode badgeCode) {
        if ( badgeCode == null ) {
            return null;
        }

        BadgeCodeDto badgeCodeDto = new BadgeCodeDto();

        badgeCodeDto.setBadgeCode( badgeCode.getBadgeCode() );
        badgeCodeDto.setBadgeName( badgeCode.getBadgeName() );
        badgeCodeDto.setBadgeImg( badgeCode.getBadgeImg() );

        return badgeCodeDto;
    }

    @Override
    public TitleCodeDto toTitleCodeDto(TitleCode titleCode) {
        if ( titleCode == null ) {
            return null;
        }

        TitleCodeDto titleCodeDto = new TitleCodeDto();

        titleCodeDto.setTitleCode( titleCode.getTitleCode() );
        titleCodeDto.setTitleName( titleCode.getTitleName() );

        return titleCodeDto;
    }

    @Override
    public MemberAchievementDto toMemberAchievementDto(Achievement achievement, Long userId, String clearTime) {
        if ( achievement == null && userId == null && clearTime == null ) {
            return null;
        }

        MemberAchievementDto memberAchievementDto = new MemberAchievementDto();

        if ( achievement != null ) {
            memberAchievementDto.setBadge( toBadgeCode( achievement.getBadgeCode() ) );
            memberAchievementDto.setTitle( toTitleCodeDto( achievement.getTitleCode() ) );
            memberAchievementDto.setAchievementId( achievement.getAchievementId() );
            memberAchievementDto.setMission( achievement.getMission() );
        }
        memberAchievementDto.setUserId( userId );
        memberAchievementDto.setClearTime( clearTime );

        return memberAchievementDto;
    }
}
