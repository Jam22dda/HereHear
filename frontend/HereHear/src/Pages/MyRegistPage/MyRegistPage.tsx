import * as S from "./MyRegistPage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import { Text } from "../../components/atoms/Text/Text.styles";
import { useNavigate } from "react-router-dom";
import iconBack from "../../assets/CircleButton/icon-back.png";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Image } from "../../components/atoms/Image/Image";
import { useGetRegistMusic } from "../../apis/Mypage/Quries/useGetRegistMusic";

export default function MyRegistPage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    // const navigatePage = (path: string) => {
    //     navigate(path);
    // };

    interface RegistMusicType {
        albumImg: string;
        registeredMusicId: number;
        singer: string;
        subject: string;
    }

    const RegistMusic: RegistMusicType[] = useGetRegistMusic();
    console.log(RegistMusic);

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
                    $margin="50px 0 30px 0"
                >
                    내가 등록한 노래
                </Text>
                {RegistMusic.map((item: RegistMusicType, index: number) => (
                    <S.MyRegistWrapper key={index}>
                        <MusicItem
                            src={item.albumImg}
                            songtitle={item.subject}
                            artist={item.singer}
                        />
                    </S.MyRegistWrapper>
                ))}
                {/* <S.MyRegistWrapper>
                    <MusicItem
                        src={BTS_answer}
                        songtitle="Answer : Love Myself"
                        artist="방탄소년단"
                    ></MusicItem>
                </S.MyRegistWrapper> */}
            </div>
        </div>
    );
}
