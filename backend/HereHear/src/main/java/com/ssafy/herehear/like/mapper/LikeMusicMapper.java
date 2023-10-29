package com.ssafy.herehear.like.mapper;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.response.LikeRegisteredMusicResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LikeMusicMapper {
    LikeRegisteredMusicResDto toLikeRegisteredMusicResDto(RegisteredMusic registeredMusic, boolean like);
}
