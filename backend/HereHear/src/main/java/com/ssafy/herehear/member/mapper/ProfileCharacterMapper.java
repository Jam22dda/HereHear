package com.ssafy.herehear.member.mapper;

import com.ssafy.herehear.entity.ProfileCharacter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileCharacterMapper {
    ProfileCharacter profileCharacterIdToProfileCharacter(Long profileCharacterId);
}
