package com.ssafy.herehear.history.mapper;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.history.dto.response.PlayRegisteredMusicResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicHistoryMapper {
    PlayRegisteredMusicResDto toPlayRegisteredMusicResDto(RegisteredMusic registeredMusic, boolean like);
}
