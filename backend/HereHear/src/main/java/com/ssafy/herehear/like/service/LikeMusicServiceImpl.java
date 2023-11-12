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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LikeMusicServiceImpl implements LikeMusicService {

    private final LikeMusicRepository likeMusicRepository;
    private final LikeMusicDslRepository likeMusicDslRepository;

    private final LikeMusicMapper likeMusicMapper;

    @Override
    @Transactional
    public void registerlikeMusic(Long memberId, Long registeredMusicId){
        log.info("[{}] memberId: {}, registeredMusicId: {}", ConstantsUtil.LIKE_REGISTER_DELETE,memberId,registeredMusicId);

        findLikeMusic(memberId, registeredMusicId).ifPresentOrElse(
                likeMusicRepository::delete,
                () -> {
                    LikeMusic likeMusic = LikeMusic.builder()
                            .id(findMemberMusicId(memberId,registeredMusicId))
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
    public List<LikeRegisteredMusicResDto> likeMusicList(long memberId){
        log.info("[{}], [{}] memberId: {}", ConstantsUtil.LIKE_LIST, ConstantsUtil.OTHER_MEMBER_LIKE_MUSIC, memberId);

        List<LikeRegisteredMusicResDto> likeRegisteredMusicResDtos = likeMusicDslRepository.findByLikeMusics(memberId).stream()
                .map(findRegisteredMusic -> likeMusicMapper.toLikeRegisteredMusicResDto(
                        findRegisteredMusic,
                        likeMusicDslRepository.findByRegisteredMusicLike(memberId,findRegisteredMusic.getRegisteredMusicId()).isPresent())
                )
                .toList();
        log.info("[{}], [{}] likeMusicList: {}", ConstantsUtil.LIKE_LIST, ConstantsUtil.OTHER_MEMBER_LIKE_MUSIC, likeRegisteredMusicResDtos);

        return likeRegisteredMusicResDtos;
    }

    public Optional<LikeMusic> findLikeMusic(long memberId, long registeredMusicId){
        return likeMusicRepository.findById(findMemberMusicId(memberId, registeredMusicId));
    }

    private RegisteredMusic findByRegisterMusic(long registeredMusicId){
        return likeMusicDslRepository.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    private MemberMusicId findMemberMusicId(long memberId, long registeredMusicId){
        return MemberMusicId.builder().memberId(memberId).registeredMusicId(registeredMusicId).build();
    }

}