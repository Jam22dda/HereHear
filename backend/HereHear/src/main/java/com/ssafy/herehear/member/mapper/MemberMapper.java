package com.ssafy.herehear.member.mapper;

import com.ssafy.herehear.achievement.dto.AchievementDto;
import com.ssafy.herehear.achievement.dto.BadgeCodeDto;
import com.ssafy.herehear.achievement.dto.TitleCodeDto;
import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.member.dto.response.FollowResDto;
import com.ssafy.herehear.member.dto.response.FollowerResDto;
import com.ssafy.herehear.member.dto.response.MemberInfoResDto;
import com.ssafy.herehear.member.dto.response.ProfileCharacterResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    ProfileCharacterResDto toProfileCharacterResDto(ProfileCharacter profileCharacter);

    @Mapping(source = "badgeCode", target = "badge")
    @Mapping(source = "titleCode", target = "title")
    AchievementDto toAchievementDto(Achievement achievement);

    BadgeCodeDto toBadgeCode(BadgeCode badgeCode);

    TitleCodeDto toTitleCodeDto(TitleCode titleCode);

    FollowResDto toFollowResDto(Member member, Achievement achievement);

    FollowerResDto toFollowerListDto(Member member, Achievement achievement, Boolean isFollowed);

    Follow toFollow(Member member, Long followMemberId);

    MemberInfoResDto toMemberInfoResDto(Member member, Achievement achievement);

    Member toMember(String nickname, String email, String provider);
}
