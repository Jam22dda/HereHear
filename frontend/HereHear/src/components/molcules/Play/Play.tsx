import CircleButton from "../../atoms/CircleButton/CircleButton";
import * as S from "./Play.styles";
// import { Text } from "../../atoms/Text/Text.styles";

export default function Play() {
    return (
        <S.PlayWrapper>
            <CircleButton option="playNextBtn" size="largePlus">
                {/* <Text></Text> */}
            </CircleButton>
            {/* TODO: 피그마에서 이미지로 가져오기(그림자 2개 합쳐진거임) */}
            <CircleButton option="playBtn" size="largePlus"></CircleButton>
            <CircleButton option="playNextBtn" size="largePlus"></CircleButton>
        </S.PlayWrapper>
    );
}
