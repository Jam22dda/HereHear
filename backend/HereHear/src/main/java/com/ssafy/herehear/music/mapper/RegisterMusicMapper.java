package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RegisterMusicMapper {

    Music toMusic(String subject, String singer, String albumImg);

    RegisteredMusic toRegisteredMusic(Music music, Double lng, Double lat, String comment);

    MemberReadList registeredMusicToMemberReadList(Member member, RegisteredMusic registeredMusic, Music music);

    RegisteredMusicResDto toRegisteredMusicResDto(RegisteredMusic registeredMusic, Music music);
}
