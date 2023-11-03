package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.response.AroundMusicResDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AroundMapper {

    AroundMusicResDto toAroundMusicResDto(RegisteredMusic registeredMusic, List<String> occasionName);
}
