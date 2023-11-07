import * as S from "./LikePage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconHeart from "../../assets/CircleButton/icon-heart.png";
import { Image } from "../../components/atoms/Image/Image";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { useNavigate } from "react-router-dom";
import { useGetLikeMusic } from "../../apis/Mypage/Quries/useGetLikeMusic";

export default function LikePage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    // const navigatePage = (path: string) => {
    //     navigate(path);
    // };

    interface LikeMusicType {
        albumImg: string;
        like: boolean;
        registeredMusicId: number;
        singer: string;
        subject: string;
    }

    const LikeMusic: LikeMusicType[] = useGetLikeMusic();
    console.log(LikeMusic);

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
                <Text
                    size="subtitle1"
                    fontWeight="bold"
                    $margin="30px 0 50px 0"
                >
                    내가 좋아요한 노래
                </Text>
                {LikeMusic.map((item: LikeMusicType, index: number) => (
                    <S.LikeItemWrapper key={index}>
                        <MusicItem
                            src={item.albumImg}
                            songtitle={item.subject}
                            artist={item.singer}
                        />
                        <CircleButton option="gradDeActivated" size="large">
                            <Image
                                src={iconHeart}
                                width={24}
                                height={20}
                                $unit="px"
                            ></Image>
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
