package com.ssafy.herehear.music.service.musicServiceImpl;

import com.ssafy.herehear.entity.RegisteredMusic;
import com.ssafy.herehear.music.dto.request.AroundMusicReqDto;
import com.ssafy.herehear.music.dto.request.AroundSearchReqDto;
import com.ssafy.herehear.music.dto.response.AroundMusicResDto;
import com.ssafy.herehear.music.mapper.AroundMapper;
import com.ssafy.herehear.music.repository.musicRepositoryImpl.AroundRepositoryImpl;
import com.ssafy.herehear.music.service.AroundService;
import com.ssafy.herehear.music.util.GeoUtils;
import com.ssafy.herehear.music.util.HourFilterUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AroundServiceImpl implements AroundService {

    private final AroundRepositoryImpl aroundRepositoryImpl;
    private final AroundMapper aroundMapper;


    @Override
    @Transactional
    public List<AroundMusicResDto> getAroundMusicList(AroundMusicReqDto aroundMusicReqDto) {
        log.info("[주변 음악 조회] AroundMusicReqDto: " + aroundMusicReqDto);

        List<AroundMusicResDto> aroundMusicResDtos = findByAroundMusics(
                        aroundMusicReqDto.getLat(),
                        aroundMusicReqDto.getLng(),
                        aroundRepositoryImpl.findByRegisterMusics()
                ).stream()
                .filter(HourFilterUtils::findHourFilter)
                .map(registeredMusic -> aroundMapper.toAroundMusicResDto(
                                registeredMusic,
                                aroundRepositoryImpl.findByOccasionName(registeredMusic.getRegisteredMusicId())
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
                        aroundRepositoryImpl.findByAroundSearchMusics(aroundSearchReqDto.getKeyword(),aroundSearchReqDto.getOccasions())
                ).stream()
                .filter(HourFilterUtils::findHourFilter)
                .map(registeredMusic -> aroundMapper.toAroundMusicResDto(
                                registeredMusic,
                                aroundRepositoryImpl.findByOccasionName(registeredMusic.getRegisteredMusicId())
                        )
                ).toList();
        log.info("getAroundMusicList: " + aroundMusicResDtos);

        return aroundMusicResDtos;
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

}