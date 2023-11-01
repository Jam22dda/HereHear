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

    RegisteredMusicDetailsResDto toRegisteredMusicResDto(RegisteredMusic registeredMusic, boolean like, String nickname, List<Occasion> occasions);

    RegisteredMusicResDto toRegisteredMusicListResDto(RegisteredMusic registeredMusic, List<Occasion> occasions);

    MyRegisteredMusicResDto toMyRegisteredMusicResDto(RegisteredMusic registeredMusic);

    OccasionResDto toOccasionResDto(Occasion occasion);

    AroundMusicResDto toAroundMusicResDto(RegisteredMusic registeredMusic, List<String> occasionName);

}
