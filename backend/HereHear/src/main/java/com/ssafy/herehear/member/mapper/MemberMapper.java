package com.ssafy.herehear.member.mapper;

import com.ssafy.herehear.entity.Follow;
import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.ProfileCharacter;
import com.ssafy.herehear.member.dto.response.FollowResDto;
import com.ssafy.herehear.member.dto.response.ProfileCharacterResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    ProfileCharacterResDto toProfileCharacterResDto(ProfileCharacter profileCharacter);

    FollowResDto toFollowResDto(Member member);

    Follow toFollow(Member member, Long followMemberId);

}
