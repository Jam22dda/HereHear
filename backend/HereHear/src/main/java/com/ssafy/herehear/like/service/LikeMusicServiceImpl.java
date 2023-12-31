package com.ssafy.herehear.like.service;

import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.MemberMusicId;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.like.dto.response.LikeRegisteredMusicResDto;
import com.ssafy.herehear.like.mapper.LikeMusicMapper;
import com.ssafy.herehear.like.repository.LikeMusicDslRepository;
import com.ssafy.herehear.like.repository.LikeMusicRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeMusicServiceImpl implements LikeMusicService {

    private final LikeMusicRepository likeMusicRepository;
    private final LikeMusicDslRepository likeMusicDslRepository;

    private final LikeMusicMapper likeMusicMapper;

    @Override
    @Transactional
    public void registerlikeMusic(Long memberId, Long registeredMusicId) {
        log.info("[{}] memberId: {}, registeredMusicId: {}", ConstantsUtil.LIKE_REGISTER_DELETE, memberId, registeredMusicId);

        likeMusicRepository.findById(findMemberMusicId(memberId, registeredMusicId))
                .ifPresentOrElse(
                        likeMusicRepository::delete,
                        () -> {
                            LikeMusic likeMusic = LikeMusic.builder()
                                    .id(findMemberMusicId(memberId, registeredMusicId))
                                    .member(MemberUtil.findMember(memberId))
                                    .registeredMusic(findByRegisterMusic(registeredMusicId))
                                    .build();
                            likeMusicRepository.save(likeMusic);
                        }
                );
        log.info("[{}] 성공", ConstantsUtil.LIKE_REGISTER_DELETE);
    }

    @Override
    @Transactional
    public List<LikeRegisteredMusicResDto> likeMusicList(long memberId, String contents) {
        log.info("[{}] memberId: {}", contents, memberId);

        List<LikeRegisteredMusicResDto> result = likeMusicDslRepository.findByLikeMusics(memberId).stream()
                .map(registeredMusic -> likeMusicMapper.toLikeRegisteredMusicResDto(registeredMusic,true))
                .toList();
        log.info("[{}] likeMusicList: {}", contents, result);

        return result;
    }

    private RegisteredMusic findByRegisterMusic(long registeredMusicId) {
        return likeMusicDslRepository.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    private MemberMusicId findMemberMusicId(long memberId, long registeredMusicId) {
        return MemberMusicId.builder().memberId(memberId).registeredMusicId(registeredMusicId).build();
    }

}