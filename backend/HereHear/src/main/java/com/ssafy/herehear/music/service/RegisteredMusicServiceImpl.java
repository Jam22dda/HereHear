package com.ssafy.herehear.music.service;

import com.ssafy.herehear.entity.Member;
import com.ssafy.herehear.entity.Occasion;
import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.exception.CustomException;
import com.ssafy.herehear.global.exception.ExceptionStatus;
import com.ssafy.herehear.global.util.MemberUtil;
import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.util.GeoUtils;
import com.ssafy.herehear.music.dto.request.AroundMusicReqDto;
import com.ssafy.herehear.music.dto.request.RegisterMusicReqDto;
import com.ssafy.herehear.music.dto.response.*;
import com.ssafy.herehear.music.mapper.RegisterMusicMapper;
import com.ssafy.herehear.music.repository.MusicOccasionRepository;
import com.ssafy.herehear.music.repository.OccasionRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicRepositoryImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisteredMusicServiceImpl implements RegisteredMusicService {

    private final RegisteredMusicRepository registeredMusicRepository;
    private final OccasionRepository occasionRepository;
    private final MusicOccasionRepository musicOccasionRepository;
    private final RegisteredMusicRepositoryImpl registeredMusicRepositoryImpl;
    private final RegisterMusicMapper registerMusicMapper;

    @Override
    @Transactional
    public void registerMusic(Long memberId, RegisterMusicReqDto registerMusicReqDto) {
        log.info(logComment("음악 등록", memberId, registerMusicReqDto));

        Member member = MemberUtil.findMember(memberId);

        //음악 등록
        RegisteredMusic registeredMusic = registeredMusicRepository.save(registerMusicMapper.toRegisteredMusic(member, registerMusicReqDto));
        log.info("registeredMusicId: " + registeredMusic.getRegisteredMusicId());

        //상황 태그 등록
        for (long occasionId : registerMusicReqDto.getMusicOccasionIds()) {
            musicOccasionRepository.save(registerMusicMapper.toMusicOccasion(findOccasion(occasionId), registeredMusic));
        }
        log.info("registerMusic success");
    }

    @Override
    @Transactional
    public List<OccasionResDto> getTag() {
        List<Occasion> occasions = occasionRepository.findAll();

        List<OccasionResDto> occasionResDtos = occasions.stream()
                .map(registerMusicMapper::toOccasionResDto)
                .toList();
        log.info("getTag: " + occasionResDtos);

        return occasionResDtos;
    }

    @Override
    @Transactional
    public RegisteredMusicDetailsResDto getRegisteredMusicDetails(long memberId, long registeredMusicId) {
        log.info(logComment("음악 상세 조회", memberId, registeredMusicId));

        Member member = MemberUtil.findMember(memberId);

        RegisteredMusicDetailsResDto registeredMusicDetailsResDto = registerMusicMapper.toRegisteredMusicResDto(
                findByRegisterMusic(registeredMusicId),
                findRegisteredMusicLike(memberId, registeredMusicId),
                member.getNickname(),
                registeredMusicRepositoryImpl.findByOccasion(registeredMusicId)
        );

        registeredMusicDetailsResDto.setCreateTime(convertAndSetCreateTime(registeredMusicDetailsResDto.getCreateTime()));
        log.info("getRegisteredMusicDetails: " + registeredMusicDetailsResDto);

        return registeredMusicDetailsResDto;
    }

    @Override
    @Transactional
    public List<RegisteredMusicResDto> getRegisteredMusicList() {
        List<RegisteredMusicResDto> registeredMusicResDtos = registeredMusicRepositoryImpl.findByRegisterMusics().stream()
                .filter(this::findHourFilter)
                .map(findRegisteredMusic -> registerMusicMapper.toRegisteredMusicListResDto(
                        findRegisteredMusic,
                        registeredMusicRepositoryImpl.findByOccasion(findRegisteredMusic.getRegisteredMusicId()))
                )
                .toList();
        log.info("getRegisteredMusicList: " + registeredMusicResDtos);

        return registeredMusicResDtos;
    }

    @Override
    @Transactional
    public void updateMyRegisteredMusic(long memberId, long registeredMusicId) {
        log.info(logComment("등록한 음악 삭제", memberId, registeredMusicId));

        MemberUtil.findMember(memberId);

        RegisteredMusic findRegisteredMusic = registeredMusicRepositoryImpl.findByMyRegisterMusic(memberId, registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
        findRegisteredMusic.updateRegisteredMusic(true);
        registeredMusicRepository.save(findRegisteredMusic);

        log.info("updateMyRegisteredMusic success");
    }

    @Override
    @Transactional
    public List<MyRegisteredMusicResDto> getMyRegisteredMusicList(long memberId) {
        MemberUtil.findMember(memberId);

        List<MyRegisteredMusicResDto> myRegisteredMusicResDtos = registeredMusicRepositoryImpl.findByMyRegisterMusics(memberId).stream()
                .map(registerMusicMapper::toMyRegisteredMusicResDto)
                .toList();
        log.info("getMyRegisteredMusicList: " + myRegisteredMusicResDtos);

        return myRegisteredMusicResDtos;
    }

    @Override
    @Transactional
    public List<AroundMusicResDto> getAroundMusicList(AroundMusicReqDto aroundMusicReqDto) {
        log.info("[주변 음악 조회] AroundMusicReqDto: " + aroundMusicReqDto);

        List<AroundMusicResDto> aroundMusicResDtos = findByAroundMusics(
                        aroundMusicReqDto.getLat(),
                        aroundMusicReqDto.getLng(),
                        registeredMusicRepositoryImpl.findByRegisterMusics()
                ).stream()
                .filter(this::findHourFilter)
                .map(registeredMusic -> registerMusicMapper.toAroundMusicResDto(
                                registeredMusic,
                                registeredMusicRepositoryImpl.findByOccasionName(registeredMusic.getRegisteredMusicId())
                        )
                ).toList();
        log.info("getAroundMusicList: " + aroundMusicResDtos);

        return aroundMusicResDtos;
    }

    @Override
    public List<AroundMusicResDto> getAroundSearchMusic(AroundSearchReqDto aroundSearchReqDto) {
        log.info("[주변 음악 검색] AroundSearchReqDto: " + aroundSearchReqDto);

        List<AroundMusicResDto> aroundMusicResDtos = findByAroundMusics(
                        aroundSearchReqDto.getLat(),
                        aroundSearchReqDto.getLng(),
                        registeredMusicRepositoryImpl.findByAroundSearchMusics(aroundSearchReqDto.getKeyword(),aroundSearchReqDto.getOccasions())
                ).stream()
                .filter(this::findHourFilter)
                .map(registeredMusic -> registerMusicMapper.toAroundMusicResDto(
                                registeredMusic,
                                registeredMusicRepositoryImpl.findByOccasionName(registeredMusic.getRegisteredMusicId())
                        )
                ).toList();
        log.info("getAroundMusicList: " + aroundMusicResDtos);

        return aroundMusicResDtos;
    }

    public boolean findHourFilter(RegisteredMusic findRegisteredMusic){
        LocalTime currentTime = LocalDateTime.now().toLocalTime();
        log.info("currentTime: "+currentTime);

        // 현재 시간으로부터 +3h 이내 또는 -3h 이내인 데이터만 필터링
        long hoursDifference = ChronoUnit.HOURS.between(currentTime, findRegisteredMusic.getCreateTime());
        log.info("getRegisteredMusicId: "+findRegisteredMusic.getRegisteredMusicId()+", hoursDifference: "+hoursDifference);
        return hoursDifference >= -3 && hoursDifference <= 3 || hoursDifference >= 21;
    }

    public List<RegisteredMusic> findByAroundMusics(double lat, double lng, List<RegisteredMusic> allMusics) {
        double radiusInKm = 0.6; // 500미터를 킬로미터로 변환
        return allMusics.stream()
                .filter(music -> {
                    double distance = GeoUtils.calculateHaversineDistance(lat, lng, music.getLat(), music.getLng());
                    log.info("Distance to " + music + ": " + distance + " km");
                    return distance <= radiusInKm;
                })
                .toList();
    }

    public Occasion findOccasion(long musicOccasionId) {
        return occasionRepository.findById(musicOccasionId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_OCCASION)
        );
    }

    public RegisteredMusic findByRegisterMusic(long registeredMusicId) {
        return registeredMusicRepositoryImpl.findByRegisterMusic(registeredMusicId).orElseThrow(
                () -> new CustomException(ExceptionStatus.NOT_FOUND_REGISTERED_MUSIC)
        );
    }

    public boolean findRegisteredMusicLike(long memberId, long registeredMusicId) {
        return registeredMusicRepositoryImpl.findByRegisteredMusicLike(memberId, registeredMusicId).isPresent();
    }

    public String convertAndSetCreateTime(String createTime) {
        LocalDateTime dateTime = LocalDateTime.parse(createTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일 HH시"));
    }

    public String logComment(String title, long memberId, Object req) {
        return "[" + title + "] memberId: " + memberId + ", registeredMusicId: " + req;
    }
}