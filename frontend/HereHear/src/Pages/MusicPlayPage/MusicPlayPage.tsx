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
// import { memberId } from "../../apis/Mypage/Mutations/useFollow";

export default function MusicPlay() {
    const { id } = useParams();
    // console.log(typeof id);

    const MusicNumber = id ? Number(id) : null;
    // console.log(typeof MusicNumber);

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
        // console.log("---------", MusicNumber);
        if (MusicNumber !== null) {
            postMusicHostiryMutate(MusicNumber);
        }
    };

    // 음악 API
    const { musicPlay, isLoading, isError } = useGetMusicPlay(MusicNumber);
    console.log(musicPlay);

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
                    {/* {myId !== parseInt(musicPlay.data.memberId, 10) && (
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
                    ) : (
                        <CircleButton option="pinkDeActivated" style={{ marginLeft: "17rem" }} onClick={toggleLike}>
                            <Image src={emptyHeart} width={23} height={21} $unit="px"></Image>
                        </CircleButton>
                    )}  */}

                    <CircleButton
                        option={isLiked ? "pinkActivated" : "pinkDeActivated"}
                        style={{ marginLeft: "17rem" }}
                        onClick={toggleLike} // 여기서는 함수를 바로 전달합니다.
                    >
                        <Image src={isLiked ? Heart : emptyHeart} width={23} height={21} $unit="px"></Image>
                    </CircleButton>

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
                    <Image
                        src={youtube}
                        width={6}
                        onClick={() => {
                            registMusicHistory();
                            const subjectEncoded = encodeURIComponent(musicPlay.data.subject);
                            const singerEncoded = encodeURIComponent(musicPlay.data.singer);
                            const youtubeSearchUrl = `https://www.youtube.com/results?search_query=${subjectEncoded}+${singerEncoded}`;
                            window.location.href = youtubeSearchUrl;
                        }}
                    ></Image>
                </S.MusicPlayWrapper>
            </div>
        </div>
    );
}
