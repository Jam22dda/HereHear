import * as S from "./MyRegistPage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import { Text } from "../../components/atoms/Text/Text.styles";
import BTS_answer from "../../../public/images/BTS_answer.jpg";
import { useNavigate } from "react-router-dom";
import iconBack from "../../assets/CircleButton/icon-back.png";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Image } from "../../components/atoms/Image/Image";

export default function MyRegistPage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigatePage("/mypage/1")}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>
                <Text size="subtitle1" fontWeight="bold" $margin="50px 0 30px 0">
                    내가 등록한 노래
                </Text>
                <S.MyRegistWrapper>
                    <MusicItem src={BTS_answer} title="Answer : Love Myself" artist="방탄소년단"></MusicItem>
                </S.MyRegistWrapper>
                <S.MyRegistWrapper>
                    <MusicItem src={BTS_answer} title="Answer : Love Myself" artist="방탄소년단"></MusicItem>
                </S.MyRegistWrapper>
            </div>
        </div>
    );
}
