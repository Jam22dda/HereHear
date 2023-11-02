import * as S from "./ListenedMusicPage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconHeart from "../../assets/CircleButton/icon-heart.png";
import iconEmptyheart from "../../assets/CircleButton/icon-emptyheart.png";
import { Image } from "../../components/atoms/Image/Image";
import { Text } from "../../components/atoms/Text/Text.styles";
import Navbar from "../../components/molcules/Navbar/Navbar";
import BTS_answer from "../../../public/images/BTS_answer.jpg";

export default function ListenedMusicPage() {
    return (
        <div id="display">
            <div className="container">
                <Text size="subtitle1" fontWeight="bold" $margin="50px 0 30px 0">
                    최근 들은 노래 리스트
                </Text>
                <S.MusicItemWrapper>
                    <MusicItem src={BTS_answer} title="Answer : Love Myself" artist="방탄소년단"></MusicItem>
                    <CircleButton option="gradDeActivated" size="large">
                        <Image src={iconHeart} width={24} height={20} $unit="px"></Image>
                    </CircleButton>
                </S.MusicItemWrapper>
                <S.MusicItemWrapper>
                    <MusicItem src={BTS_answer} title="Answer : Love Myself" artist="방탄소년단"></MusicItem>
                    <CircleButton option="gradActivated" size="large">
                        <Image src={iconEmptyheart} width={24} height={20} $unit="px"></Image>
                    </CircleButton>
                </S.MusicItemWrapper>
                <Navbar></Navbar>
            </div>
        </div>
    );
}
