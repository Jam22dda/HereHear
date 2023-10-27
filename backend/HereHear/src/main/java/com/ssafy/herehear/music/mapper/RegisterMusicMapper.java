package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RegisterMusicMapper {

    RegisteredMusic toRegisteredMusic(Member member, RegisteredMusicReqDto req);

    MusicOccasion toMusicOccasion(Occasion occasion, RegisteredMusic registeredMusic);
    RegisteredMusicResDto toRegisteredMusicResDto(RegisteredMusic registeredMusic);
}
