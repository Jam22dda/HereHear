import * as S from "./SpotifyMusicPlayerPage.styles";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { Image } from "../../components/atoms/Image/Image";
import AlbumCover from "../../components/atoms/AlbumCover/AlbumCover";
import Button from "../../components/atoms/Button/Button";
import Message from "../../components/atoms/Message/Message";
import emptyHeart from "../../assets/CircleButton/icon-emptyheart.png";
import Heart from "../../assets/CircleButton/icon-heart.png";
import youtube from "../../assets/CircleButton/icon-youtubePlay.png";
import playBtn from "../../assets/CircleButton/icon-play.png";
import pauseBtn from "../../assets/CircleButton/icon-pause.png";
import spotifyPlay from "../../assets/CircleButton/icon-spotifyPlay.png";
import spotifyPause from "../../assets/CircleButton/icon-spotifyPause.png";
import { usePostLikeMusic } from "../../apis/Music/Mutations/useLikeMusic";
import { useGetMusicPlay } from "../../apis/Music/Quries/useGetMusicPlay";
import { useGetSpotifyAccessToken } from "../../apis/Music/Quries/useGetSpotifyAccessToken";
import { useMusicHistory } from "../../apis/Music/Mutations/useMusicHistory";
import { SignUpInfoAtom } from "../../states/SignUpAtoms";
import { useRecoilValue } from "recoil";
import axios from "axios";

export default function SpotifyMusicPlayer() {
    const { id } = useParams();
    const MusicNumber = id ? Number(id) : null;

    const navigate = useNavigate();

    //좋아요 API
    const { mutate: postLikeMusicMutate } = usePostLikeMusic();
    const { mutate: postMusicHostiryMutate } = useMusicHistory();

    // 좋아요체크

    const [isLiked, setIsLiked] = useState(false); //musicPlay.data.like

    const toggleLike = () => {
        const newIsLiked = !isLiked;
        setIsLiked(newIsLiked);
        if (MusicNumber !== null) {
            postLikeMusicMutate(MusicNumber);
        }
    };

    const registMusicHistory = () => {
        if (MusicNumber !== null) {
            postMusicHostiryMutate(MusicNumber);
        }
    };

    // 음악 API
    const { musicPlay, isLoading, isError } = useGetMusicPlay(MusicNumber);
    // console.log(musicPlay);
    const mySignUpInfo = useRecoilValue(SignUpInfoAtom);
    const myId = mySignUpInfo.memberId;
    // console.log(myId == musicPlay.data.memberId);

    // Spotify 음악 재생
    const spotifyAccessToken = useGetSpotifyAccessToken();
    const [paused, setPaused] = useState(true);
    const [active, setActive] = useState(false);
    const [player, setPlayer] = useState<Spotify.Player>({} as Spotify.Player);
    const [timeMs, setTimeMs] = useState(0);
    const [durationMs, setDurationMs] = useState(0);

    // Spotify SDK 사용하여 플레이어 생성
    useEffect(() => {
        if (musicPlay) {
            setIsLiked(musicPlay.data.like ? musicPlay.data.like : false);
        }

        if (musicPlay && spotifyAccessToken) {
            const script = document.createElement("script");
            script.src = "https://sdk.scdn.co/spotify-player.js";
            script.async = true;

            document.body.appendChild(script);

            window.onSpotifyWebPlaybackSDKReady = () => {
                const inPlayer = new window.Spotify.Player({
                    name: "HereHear! Spotify Player",
                    getOAuthToken: (cb) => {
                        cb(spotifyAccessToken.data);
                    },
                    volume: 0.4,
                });

                inPlayer.addListener("ready", ({ device_id }) => {
                    // console.log("Ready with Device ID", device_id);

                    // 음악 재생 등록요청
                    axios({
                        method: "put",
                        url:
                            "https://api.spotify.com/v1/me/player/play?device_id=" +
                            device_id,
                        data: {
                            uris: [musicPlay.data.spotifyUri],
                            position_ms: 0,
                        },
                        headers: {
                            Authorization: "Bearer " + spotifyAccessToken.data,
                        },
                    })
                        .then(function (response) {
                            console.log("Spotify player SUCCESS");
                        })
                        .catch(function (error) {
                            console.log("Spotify player ERROR");
                        });
                });

                inPlayer.addListener("not_ready", ({ device_id }) => {
                    // console.log("Device ID has gone offline", device_id);
                });

                inPlayer.addListener(
                    "player_state_changed",
                    ({
                        position,
                        duration,
                        paused,
                        track_window: { current_track },
                    }) => {
                        // console.log("Position in Song", position);
                        // console.log("Duration of Song", duration);
                        // console.log("Paused", paused);
                        // console.log("Currently Playing", current_track);
                        setTimeMs(position);
                        setDurationMs(duration);
                        setActive(true);
                        setPaused(paused);
                    }
                );

                inPlayer.connect();

                setPlayer(inPlayer);
            };
        }
    }, [musicPlay, spotifyAccessToken]);

    useEffect(() => {
        window.location.reload();
        const interval = setInterval(() => {
            if (!paused) {
                setTimeMs(timeMs + 1000);
            }
        }, 1000);

        return () => {
            clearInterval(interval); // 컴포넌트가 언마운트될 때 interval을 제거합니다.
            player.disconnect();
        }
    }, []);

    if (isLoading) {
        return <div>Loading...</div>;
    }
    if (isError || !musicPlay) {
        return <div>Error occurred or no data!</div>;
    }

    const occasionName = musicPlay.data.occasionName;

    return (
        <div id="display">
            <div className="container">
                <CircleButton
                    option="default2"
                    size="medium"
                    onClick={() => navigate(-1)}
                >
                    <Image
                        src={iconBack}
                        width={10}
                        height={18}
                        $unit="px"
                    ></Image>
                </CircleButton>
                <S.MusicPlayWrapper>
                    <S.SelectTagWrapper>
                        {occasionName.map((item: string, index: number) => (
                            <Button
                                option="unfollow"
                                $shadow=""
                                size="mediumplus"
                                $margin="5px"
                                $width="80px"
                                key={index}
                                tag={item}
                            ></Button>
                        ))}
                    </S.SelectTagWrapper>
                    <AlbumCover src={musicPlay.data.albumImg}></AlbumCover>
                    {myId !== parseInt(musicPlay.data.memberId, 10) && (
                        <CircleButton
                            option={
                                isLiked ? "pinkActivated" : "pinkDeActivated"
                            }
                            style={{ marginLeft: "17rem" }}
                            onClick={toggleLike} // 여기서는 함수를 바로 전달합니다.
                        >
                            <Image
                                src={isLiked ? Heart : emptyHeart}
                                width={23}
                                height={21}
                                $unit="px"
                            ></Image>
                        </CircleButton>
                    )}
                    <Text size="body2" fontWeight="medium" $marginTop="10px">
                        {musicPlay.data.subject}
                    </Text>
                    <Text size="body2" fontWeight="bold" $marginTop="5px">
                        {musicPlay.data.singer}
                    </Text>

                    <Message
                        comment={musicPlay.data.comment}
                        createTime={musicPlay.data.createTime}
                        nickname={musicPlay.data.nickname}
                        characterImage={musicPlay.data.characterImage}
                        musicRegistId={musicPlay.data.memberId}
                    ></Message>

                    {/* 음악 재생 진행바 UI 구현 필요 */}

                    {active ? (
                        <Image
                            src={paused ? playBtn : pauseBtn}
                            width={6}
                            onClick={() => {
                                registMusicHistory();
                                player.togglePlay();
                                setPaused(!paused);
                            }}
                        ></Image>
                    ) : (
                        <div>Loading...</div>
                    )}
                </S.MusicPlayWrapper>
            </div>
        </div>
    );
}
