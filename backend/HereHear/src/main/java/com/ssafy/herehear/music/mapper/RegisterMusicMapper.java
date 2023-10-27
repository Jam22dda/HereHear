package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.*;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RegisterMusicMapper {

    RegisteredMusic toRegisteredMusic(RegisteredMusicReqDto req);

//    MemberReadList registeredMusicToMemberReadList(MemberMusicId id, Member member, RegisteredMusic registeredMusic);

    RegisteredMusicResDto toRegisteredMusicResDto(RegisteredMusic registeredMusic);
}
