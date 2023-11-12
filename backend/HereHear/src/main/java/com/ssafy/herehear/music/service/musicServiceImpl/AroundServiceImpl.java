package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.global.util.ConstantsUtil;
import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.dto.response.AroundMusicResDto;
import com.ssafy.herehear.music.mapper.AroundMapper;
import com.ssafy.herehear.music.repository.AroundRepository;
import com.ssafy.herehear.music.repository.RegisteredMusicDslRepository;
import com.ssafy.herehear.music.service.AroundService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AroundServiceImpl implements AroundService {
    private static final double EARTH_RADIUS = 6371.0; // 지구 반지름 (킬로미터)

    private final RegisteredMusicDslRepository registeredMusicDslRepository;
    private final AroundRepository aroundRepository;
    private final AroundMapper aroundMapper;

    @Override
    @Transactional
    public List<AroundMusicResDto> getAroundMusicList(Double lat, Double lng) {
        log.info("[{}}] lat: {}, lng: {}", ConstantsUtil.AROUND_MUSIC, lat, lng);

        List<AroundMusicResDto> aroundMusicResDtos = findByAroundMusics(lat, lng, registeredMusicDslRepository.findByRegisterMusics(180, 180)).stream()
                .map(registeredMusic -> aroundMapper.toAroundMusicResDto(registeredMusic,
                        registeredMusicDslRepository.findByOccasionName(registeredMusic.getRegisteredMusicId()))
                ).toList();
        log.info("[{}}] 성공 result: {}", ConstantsUtil.AROUND_MUSIC, aroundMusicResDtos);

        return aroundMusicResDtos;
    }

    @Override
    @Transactional
    public List<AroundMusicResDto> getAroundSearchMusic(AroundSearchReqDto aroundSearchReqDto) {
        log.info("[{}}] aroundSearchReqDto: {}", ConstantsUtil.AROUND_MUSIC_SEARCH, aroundSearchReqDto);

        List<AroundMusicResDto> aroundMusicResDtos = findByAroundMusics(
                aroundSearchReqDto.getLat(),
                aroundSearchReqDto.getLng(),
                aroundRepository.findByAroundSearchMusics(aroundSearchReqDto.getKeyword(), aroundSearchReqDto.getOccasions())).stream()
                .map(registeredMusic -> aroundMapper.toAroundMusicResDto(registeredMusic,
                        registeredMusicDslRepository.findByOccasionName(registeredMusic.getRegisteredMusicId()))
                ).toList();
        log.info("[{}}] 성공 result: {}", ConstantsUtil.AROUND_MUSIC_SEARCH, aroundMusicResDtos);

        return aroundMusicResDtos;
    }

    public List<RegisteredMusic> findByAroundMusics(double lat, double lng, List<RegisteredMusic> allMusics) {
        double radiusInKm = 0.6; // 500미터를 킬로미터로 변환
        return allMusics.stream()
                .filter(music -> {
                    double distance = calculateHaversineDistance(lat, lng, music.getLat(), music.getLng());
                    log.info("Distance to " + music + ": " + distance + " km");
                    return distance <= radiusInKm;
                })
                .toList();
    }

    public static double calculateHaversineDistance(double lat1, double lng1, double lat2, double lng2) {

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}