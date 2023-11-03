package com.ssafy.herehear.like.service;

import com.ssafy.herehear.entity.LikeMusic;
import com.ssafy.herehear.entity.MemberMusicId;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.like.dto.response.LikeRegisteredMusicResDto;
import com.ssafy.herehear.like.mapper.LikeMusicMapper;
import com.ssafy.herehear.like.repository.LikeMusicRepository;
import com.ssafy.herehear.like.repository.LikeMusicRepositoryImpl;
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
    private final LikeMusicRepositoryImpl likeMusicRepositoryImpl;

    private final LikeMusicMapper likeMusicMapper;

    @Override
    @Transactional
    public void registerlikeMusic(Long memberId, Long registeredMusicId){
        log.info("[좋아요 등록 및 취소] memberId: "+memberId+", registeredMusicId: "+registeredMusicId);

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
        log.info("registerLike success");
    }

    @Override
    @Transactional
    public List<LikeRegisteredMusicResDto> likeMusicList(long memberId){
        MemberUtil.findMember(memberId);

        // 좋아요 목록 조회
        List<LikeRegisteredMusicResDto> likeRegisteredMusicResDtos = likeMusicRepositoryImpl.findByLikeMusics(memberId).stream()
                .map(findRegisteredMusic -> likeMusicMapper.toLikeRegisteredMusicResDto(
                        findRegisteredMusic,
                        likeMusicRepositoryImpl.findByRegisteredMusicLike(memberId,findRegisteredMusic.getRegisteredMusicId()).isPresent())
                )
                .toList();
        log.info("getMusicHistoryList: "+ likeRegisteredMusicResDtos);

        return likeRegisteredMusicResDtos;
    }

    public Optional<LikeMusic> findLikeMusic(long memberId, long registeredMusicId){
        return likeMusicRepository.findById(findMemberMusicId(memberId, registeredMusicId));
    }

    private RegisteredMusic findByRegisterMusic(long registeredMusicId){
        return likeMusicRepositoryImpl.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    private MemberMusicId findMemberMusicId(long memberId, long registeredMusicId){
        return MemberMusicId.builder().memberId(memberId).registeredMusicId(registeredMusicId).build();
    }

}