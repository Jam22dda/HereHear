package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegisterMusicMapper {
    //RegisteredMusicReqDto를 받아 RegisteredMusic에 매핑
    RegisteredMusic toRegisteredMusic(RegisteredMusicReqDto registeredMusicReqDto);

}
