package com.ssafy.herehear.history.mapper;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.history.dto.response.LikeRegisteredMusicResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MusicHistoryMapper {
    LikeRegisteredMusicResDto toLikeRegisteredMusicResDto(RegisteredMusic registeredMusic, boolean like);
}
