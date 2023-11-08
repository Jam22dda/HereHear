import CircleButton from "../../atoms/CircleButton/CircleButton";
import * as S from "./Play.styles";
import { Image } from "../../atoms/Image/Image";
import youtube from "../../../assets/CircleButton/icon-youtube.png";
import beforeMusic from "../../../assets/CircleButton/icon-musicBefore.png";
import afterMusic from "../../../assets/CircleButton/icon-musicNext.png";
// import { Text } from "../../atoms/Text/Text.styles";

export default function PlayBtn() {
    return (
        <S.PlayWrapper>
            <CircleButton option="playNextBtn" size="largePlus">
                <Image src={beforeMusic} width={1.4}></Image>
            </CircleButton>
            <S.BtnWrapper>
                <CircleButton option="playBtnOuter" size="largePlus" />
                <CircleButton style={{ position: "absolute" }} $shadow="none" option="playBtn" size="large60">
                    <Image src={youtube} width={1.7}></Image>
                </CircleButton>
            </S.BtnWrapper>

            <CircleButton option="playNextBtn" size="largePlus">
                <Image src={afterMusic} width={1.4}></Image>
            </CircleButton>
        </S.PlayWrapper>
    );
}
