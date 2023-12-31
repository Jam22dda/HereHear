package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface RegisterMusicMapper {

    RegisteredMusic toRegisteredMusic(Member member, RegisterMusicReqDto req);

    MusicOccasion toMusicOccasion(Occasion occasion, RegisteredMusic registeredMusic);

    RegisteredMusicDetailsResDto toRegisteredMusicDetailsResDto(Member member, RegisteredMusic registeredMusic, boolean like, ProfileCharacter profileCharacter, List<String> occasionName);

    RegisteredMusicMapResDto toRegisteredMusicListResDto(RegisteredMusic registeredMusic);

    MyRegisteredMusicResDto toMyRegisteredMusicResDto(RegisteredMusic registeredMusic);

    OccasionResDto toOccasionResDto(Occasion occasion);

    SseResDto toSseResDto(int status, RegisteredMusic registeredMusic);
}
