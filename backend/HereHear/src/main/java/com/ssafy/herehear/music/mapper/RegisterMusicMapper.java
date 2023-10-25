package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MemberReadList;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.request.RegisteredMusicReqDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RegisterMusicMapper {

    RegisteredMusic toRegisteredMusic(RegisteredMusicReqDto registeredMusicReqDto);

    MemberReadList registeredMusicToMemberReadList(Member member, RegisteredMusic registeredMusic);

}
