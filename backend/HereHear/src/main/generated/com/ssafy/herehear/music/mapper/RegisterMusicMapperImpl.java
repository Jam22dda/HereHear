package com.ssafy.herehear.music.mapper;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.MusicOccasion;
import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.MyRegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.OccasionResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicDetailsResDto;
import com.ssafy.herehear.music.dto.response.RegisteredMusicResDto;
import com.ssafy.herehear.music.dto.response.SseResDto;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-02T09:48:09+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Azul Systems, Inc.)"
)
@Component
public class RegisterMusicMapperImpl implements RegisterMusicMapper {

    @Override
    public RegisteredMusic toRegisteredMusic(Member member, RegisterMusicReqDto req) {
        if ( member == null && req == null ) {
            return null;
        }

        RegisteredMusic.RegisteredMusicBuilder registeredMusic = RegisteredMusic.builder();

        if ( req != null ) {
            registeredMusic.lng( req.getLng() );
            registeredMusic.lat( req.getLat() );
            registeredMusic.comment( req.getComment() );
            registeredMusic.subject( req.getSubject() );
            registeredMusic.singer( req.getSinger() );
            registeredMusic.albumImg( req.getAlbumImg() );
            registeredMusic.releaseTime( req.getReleaseTime() );
        }
        registeredMusic.member( member );

        return registeredMusic.build();
    }

    @Override
    public MusicOccasion toMusicOccasion(Occasion occasion, RegisteredMusic registeredMusic) {
        if ( occasion == null && registeredMusic == null ) {
            return null;
        }

        MusicOccasion.MusicOccasionBuilder musicOccasion = MusicOccasion.builder();

        musicOccasion.occasion( occasion );
        musicOccasion.registeredMusic( registeredMusic );

        return musicOccasion.build();
    }

    @Override
    public RegisteredMusicDetailsResDto toRegisteredMusicDetailsResDto(RegisteredMusic registeredMusic, boolean like, String nickname, List<String> occasionName) {
        if ( registeredMusic == null && nickname == null && occasionName == null ) {
            return null;
        }

        RegisteredMusicDetailsResDto.RegisteredMusicDetailsResDtoBuilder registeredMusicDetailsResDto = RegisteredMusicDetailsResDto.builder();

        if ( registeredMusic != null ) {
            registeredMusicDetailsResDto.registeredMusicId( registeredMusic.getRegisteredMusicId() );
            registeredMusicDetailsResDto.lng( registeredMusic.getLng() );
            registeredMusicDetailsResDto.lat( registeredMusic.getLat() );
            registeredMusicDetailsResDto.comment( registeredMusic.getComment() );
            registeredMusicDetailsResDto.subject( registeredMusic.getSubject() );
            registeredMusicDetailsResDto.singer( registeredMusic.getSinger() );
            registeredMusicDetailsResDto.albumImg( registeredMusic.getAlbumImg() );
            registeredMusicDetailsResDto.releaseTime( registeredMusic.getReleaseTime() );
            if ( registeredMusic.getCreateTime() != null ) {
                registeredMusicDetailsResDto.createTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( registeredMusic.getCreateTime() ) );
            }
        }
        registeredMusicDetailsResDto.like( like );
        registeredMusicDetailsResDto.nickname( nickname );
        List<String> list = occasionName;
        if ( list != null ) {
            registeredMusicDetailsResDto.occasionName( new ArrayList<String>( list ) );
        }

        return registeredMusicDetailsResDto.build();
    }

    @Override
    public RegisteredMusicResDto toRegisteredMusicListResDto(RegisteredMusic registeredMusic, List<String> occasionName) {
        if ( registeredMusic == null && occasionName == null ) {
            return null;
        }

        RegisteredMusicResDto.RegisteredMusicResDtoBuilder registeredMusicResDto = RegisteredMusicResDto.builder();

        if ( registeredMusic != null ) {
            registeredMusicResDto.registeredMusicId( registeredMusic.getRegisteredMusicId() );
            registeredMusicResDto.lng( registeredMusic.getLng() );
            registeredMusicResDto.lat( registeredMusic.getLat() );
            registeredMusicResDto.subject( registeredMusic.getSubject() );
            registeredMusicResDto.singer( registeredMusic.getSinger() );
            registeredMusicResDto.albumImg( registeredMusic.getAlbumImg() );
        }
        List<String> list = occasionName;
        if ( list != null ) {
            registeredMusicResDto.occasionName( new ArrayList<String>( list ) );
        }

        return registeredMusicResDto.build();
    }

    @Override
    public MyRegisteredMusicResDto toMyRegisteredMusicResDto(RegisteredMusic registeredMusic) {
        if ( registeredMusic == null ) {
            return null;
        }

        MyRegisteredMusicResDto.MyRegisteredMusicResDtoBuilder myRegisteredMusicResDto = MyRegisteredMusicResDto.builder();

        myRegisteredMusicResDto.registeredMusicId( registeredMusic.getRegisteredMusicId() );
        myRegisteredMusicResDto.subject( registeredMusic.getSubject() );
        myRegisteredMusicResDto.singer( registeredMusic.getSinger() );
        myRegisteredMusicResDto.albumImg( registeredMusic.getAlbumImg() );

        return myRegisteredMusicResDto.build();
    }

    @Override
    public OccasionResDto toOccasionResDto(Occasion occasion) {
        if ( occasion == null ) {
            return null;
        }

        OccasionResDto.OccasionResDtoBuilder occasionResDto = OccasionResDto.builder();

        occasionResDto.occasionCode( occasion.getOccasionCode() );
        occasionResDto.occasionName( occasion.getOccasionName() );
        occasionResDto.category( occasion.getCategory() );

        return occasionResDto.build();
    }

    @Override
    public SseResDto toSseResDto(int status, RegisteredMusic registeredMusic, List<String> occasionName) {
        if ( registeredMusic == null && occasionName == null ) {
            return null;
        }

        SseResDto.SseResDtoBuilder sseResDto = SseResDto.builder();

        if ( registeredMusic != null ) {
            sseResDto.registeredMusicId( registeredMusic.getRegisteredMusicId() );
            sseResDto.lng( registeredMusic.getLng() );
            sseResDto.lat( registeredMusic.getLat() );
            sseResDto.subject( registeredMusic.getSubject() );
            sseResDto.singer( registeredMusic.getSinger() );
            sseResDto.albumImg( registeredMusic.getAlbumImg() );
        }
        sseResDto.status( status );
        List<String> list = occasionName;
        if ( list != null ) {
            sseResDto.occasionName( new ArrayList<String>( list ) );
        }

        return sseResDto.build();
    }
}
