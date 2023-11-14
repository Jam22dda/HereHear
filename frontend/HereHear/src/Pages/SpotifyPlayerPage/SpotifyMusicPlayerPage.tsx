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

// 임시로 axios 사용 - react query 로 수정 필요
import { instance } from "../../apis/instance";

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
  console.log(musicPlay);
  const mySignUpInfo = useRecoilValue(SignUpInfoAtom);
  const myId = mySignUpInfo.memberId;
  // console.log(myId == musicPlay.data.memberId);

  // Spotify 음악 재생
  const spotifyAccessToken = useGetSpotifyAccessToken();
  const [paused, setPaused] = useState(true);
  const [active, setActive] = useState(false);
  const [player, setPlayer] = useState(undefined);

  useEffect(() => {
    if (musicPlay) {
      setIsLiked(musicPlay.data.like ? musicPlay.data.like : false);
    }

    // if (musicPlay && spotifyAccessToken) {
    //   console.log("MusicPlay && SpotifyAccessToken", spotifyAccessToken.data);
    //   const script = document.createElement("script");
    //   script.src = "https://sdk.scdn.co/spotify-player.js";
    //   script.async = true;

    //   document.body.appendChild(script);

    //   window.onSpotifyWebPlaybackSDKReady = () => {
    //     console.log("spotifyAccessToken 발급", spotifyAccessToken.data);
    //     const inPlayer = new window.Spotify.Player({
    //       name: "HereHear! Spotify Player",
    //       getOAuthToken: (cb) => {
    //         cb(spotifyAccessToken.data);
    //       },
    //       volume: 0.4,
    //     });

    //     setPlayer(inPlayer);

    //     inPlayer.addListener("ready", ({ device_id }) => {
    //       console.log("Ready with Device ID", device_id);
    //       //   playMusic(device_id);
    //     });

    //     inPlayer.addListener("not_ready", ({ device_id }) => {
    //       console.log("Device ID has gone offline", device_id);
    //     });

    //     inPlayer.connect();
    //   };
    // }
  }, [musicPlay, spotifyAccessToken]);

  const playMusic = () => {
    const data = {
      trackId: musicPlay.data.spotifyUri,
    };

    instance.post("/spotify/play", data);
  };

  if (isLoading) {
    return <div>Loading...</div>;
  }
  if (isError || !musicPlay) {
    return <div>Error occurred or no data!</div>;
  }

  // console.log(musicPlay, "이거 들어와????????????");
  const occasionName = musicPlay.data.occasionName;
  // console.log(occasionName, "occasionName");
  return (
    <div id="display">
      <div className="container">
        <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
          <Image src={iconBack} width={10} height={18} $unit="px"></Image>
        </CircleButton>
        <S.MusicPlayWrapper>
          <S.SelectTagWrapper>
            {occasionName.map((item: string, index: number) => (
              <Button option="unfollow" $shadow="" size="mediumplus" $margin="5px" $width="80px" key={index} tag={item}></Button>
            ))}
          </S.SelectTagWrapper>
          <AlbumCover src={musicPlay.data.albumImg}></AlbumCover>
          {myId !== parseInt(musicPlay.data.memberId, 10) && (
            <CircleButton
              option={isLiked ? "pinkActivated" : "pinkDeActivated"}
              style={{ marginLeft: "17rem" }}
              onClick={toggleLike} // 여기서는 함수를 바로 전달합니다.
            >
              <Image src={isLiked ? Heart : emptyHeart} width={23} height={21} $unit="px"></Image>
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

          <S.PlayerWrapper>
            <Image
              src={playBtn}
              width={4}
              onClick={() => {
                registMusicHistory();
                playMusic();
              }}
            ></Image>
          </S.PlayerWrapper>
        </S.MusicPlayWrapper>
      </div>
    </div>
  );
}
