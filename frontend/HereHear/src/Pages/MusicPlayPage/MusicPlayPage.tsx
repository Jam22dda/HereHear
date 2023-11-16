import * as S from "./MusicPlayPage.styles";
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
import { usePostLikeMusic } from "../../apis/Music/Mutations/useLikeMusic";
import { useGetMusicPlay } from "../../apis/Music/Quries/useGetMusicPlay";
import { useMusicHistory } from "../../apis/Music/Mutations/useMusicHistory";
import { SignUpInfoAtom } from "../../states/SignUpAtoms";
import { useRecoilValue } from "recoil";
import { useGetUserinfo } from "../../apis/Mypage/Quries/useGetUserInfo";
import iconPlayer from "../../assets/MusicPlay/icon-player.png";
import iconBasket from "../../assets/MusicPlay/icon-basket.png";
import { usePostYoutubeList } from "../../apis/Music/Mutations/usePostYoutubeList";

export default function MusicPlay() {
    const UserInfo = useGetUserinfo();
    const { id } = useParams();
    // console.log(typeof id);

    const MusicNumber = id ? Number(id) : null;
    // console.log(typeof MusicNumber);

    const navigate = useNavigate();
    const navigatePage = (path: string) => {
        navigate(path);
    };

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

    // 음악 재생 바
    // const [currentTime, setCurrentTime] = useState(0);

    // useEffect(() => {
    //     const interval = setInterval(() => {
    //         setCurrentTime((prevTime) => {
    //             const newTime = prevTime + 1;
    //             if (newTime < duration) {
    //                 return newTime;
    //             }
    //             clearInterval(interval);
    //             return prevTime;
    //         });
    //     }, 1000);

    //     return () => clearInterval(interval);
    // }, [duration]);

    // const handleProgressBarClick = (e) => {
    //     const newTime = (e.nativeEvent.offsetX / 300) * duration;
    //     setCurrentTime(newTime);
    //     onSeek(newTime);
    // };

    // const progressWidth = (currentTime / duration) * 100;

    // 음악 API
    const { musicPlay, isLoading, isError } = useGetMusicPlay(MusicNumber);
    console.log(musicPlay);
    const mySignUpInfo = useRecoilValue(SignUpInfoAtom);
    const myId = mySignUpInfo.memberId;

    // 유튜브 리스트 추가
    const { mutate: postYoutubeListMutate } = usePostYoutubeList();

    const Search =
        musicPlay && musicPlay.data
            ? musicPlay.data.singer + " " + musicPlay.data.subject
            : "";

    const youtubeHandler = (Search: string) => {
        postYoutubeListMutate(Search);
    };

    useEffect(() => {
        if (musicPlay) {
            setIsLiked(musicPlay.data.like ? musicPlay.data.like : false);
        }
    }, [musicPlay]);

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
                    {/* <S.ProgressBarWrapper onClick={handleProgressBarClick}>
                        <S.ProgressBar width={progressWidth} />
                    </S.ProgressBarWrapper> */}
                    <S.PlayBtn>
                        <Image
                            src={iconPlayer}
                            width={6}
                            style={{
                                filter:
                                    UserInfo &&
                                    (UserInfo.provider === "google" ||
                                        UserInfo.provider === "kakao")
                                        ? "grayscale(100%)"
                                        : "none",
                            }}
                            onClick={() => {
                                if (
                                    UserInfo &&
                                    UserInfo.provider === "spotify"
                                ) {
                                    navigatePage(
                                        `/musicPlayer/${musicPlay.data.registeredMusicId}`
                                    );
                                }
                            }}
                        ></Image>
                        <Image
                            src={youtube}
                            width={6}
                            onClick={() => {
                                registMusicHistory();
                                const subjectEncoded = encodeURIComponent(
                                    musicPlay.data.subject
                                );
                                const singerEncoded = encodeURIComponent(
                                    musicPlay.data.singer
                                );
                                const youtubeSearchUrl = `https://www.youtube.com/results?search_query=${subjectEncoded}+${singerEncoded}`;
                                window.location.href = youtubeSearchUrl;
                            }}
                        ></Image>
                        <Image
                            src={iconBasket}
                            width={5.5}
                            height={5.5}
                            style={{
                                filter:
                                    UserInfo &&
                                    (UserInfo.provider === "spotify" ||
                                        UserInfo.provider === "kakao")
                                        ? "grayscale(100%)"
                                        : "none",
                            }}
                            onClick={() => {
                                if (
                                    UserInfo &&
                                    UserInfo.provider === "google"
                                ) {
                                    youtubeHandler(Search);
                                }
                            }}
                        ></Image>
                    </S.PlayBtn>
                </S.MusicPlayWrapper>
            </div>
        </div>
    );
}
