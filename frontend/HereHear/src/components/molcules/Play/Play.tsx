import CircleButton from "../../atoms/CircleButton/CircleButton";
import * as S from "./Play.styles";
// import { Text } from "../../atoms/Text/Text.styles";

export default function PlayBtn() {
    return (
        <S.PlayWrapper>
            <CircleButton option="playNextBtn" size="largePlus"></CircleButton>
            <S.BtnWrapper>
                <CircleButton option="playBtnOuter" size="largePlus" />
                <CircleButton style={{ position: "absolute" }} $shadow="none" option="playBtn" size="large60"></CircleButton>
            </S.BtnWrapper>

            <CircleButton option="playNextBtn" size="largePlus"></CircleButton>
        </S.PlayWrapper>
    );
}
