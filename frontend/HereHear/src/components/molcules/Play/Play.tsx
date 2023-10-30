import CircleButton from "../../atoms/CircleButton/CircleButton";
import * as S from "./Play.styles";
// import { Text } from "../../atoms/Text/Text.styles";

export default function PlayBtn() {
    return (
        <S.PlayWrapper>
            <CircleButton option="playNextBtn" size="largePlus"></CircleButton>
            <CircleButton option="playBtnOuter" size="largePlus">
                <CircleButton option="playBtn" size="large60"></CircleButton>
            </CircleButton>
            <CircleButton option="playNextBtn" size="largePlus"></CircleButton>
        </S.PlayWrapper>
    );
}
