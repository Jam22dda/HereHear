import * as S from "./LikePage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconHeart from "../../assets/CircleButton/icon-heart.png";
import { Image } from "../../components/atoms/Image/Image";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { useNavigate } from "react-router-dom";
import { useGetLikeMusic } from "../../apis/Mypage/Quries/useGetLikeMusic";
import { usePostLikeMusic } from "../../apis/Music/Mutations/useLikeMusic";
import { registeredMusicId } from "../../types/music";

export default function LikePage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    interface LikeMusicType {
        albumImg: string;
        like: boolean;
        registeredMusicId: number;
        singer: string;
        subject: string;
    }

    const LikeMusic: LikeMusicType[] = useGetLikeMusic();
    const { mutate: postLikeMusicMutate } = usePostLikeMusic();
    console.log(LikeMusic);

    const handleLikeMusicClick = (registeredMusicId: registeredMusicId) => {
        postLikeMusicMutate(registeredMusicId);
    };

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    내가 좋아요한 노래
                </Text>
                {LikeMusic &&
                    LikeMusic.map((item: LikeMusicType, index: number) => (
                        <S.LikeItemWrapper key={index}>
                            <MusicItem
                                src={item.albumImg}
                                songtitle={item.subject}
                                artist={item.singer}
                                onClick={() => navigatePage(`/musicPlay/${item.registeredMusicId}`)}
                            />
                            <CircleButton option="gradDeActivated" size="large" onClick={() => handleLikeMusicClick(item.registeredMusicId)}>
                                <Image src={iconHeart} width={24} height={20} $unit="px"></Image>
                            </CircleButton>
                        </S.LikeItemWrapper>
                    ))}
                {/* <S.LikeItemWrapper>
                    <MusicItem
                        src={BTS_answer}
                        songtitle="Answer : Love Myself"
                        artist="방탄소년단"
                    ></MusicItem>
                    <CircleButton option="gradDeActivated" size="large">
                        <Image
                            src={iconHeart}
                            width={24}
                            height={20}
                            $unit="px"
                        ></Image>
                    </CircleButton>
                </S.LikeItemWrapper> */}
            </div>
        </div>
    );
}
