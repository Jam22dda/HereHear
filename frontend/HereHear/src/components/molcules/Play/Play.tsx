import CircleButton from "../../atoms/CircleButton/CircleButton";
import * as S from "./Play.styles";
// import { Text } from "../../atoms/Text/Text.styles";

export default function PlayBtn() {
    return (
        <S.PlayWrapper>
            <CircleButton
                background-color="lightblue3"
                size="largeplus"
                box-shadow="shadow_goback"
            >
                {/* <Text></Text> */}
            </CircleButton>
            <CircleButton
                background-color="gradient4"
                size="largeplus"
                box-shadow="shadow_play2"
            ></CircleButton>
            <CircleButton
                background-color="lightblue3"
                size="largeplus"
                box-shadow="shadow_goback"
            ></CircleButton>
        </S.PlayWrapper>
    );
}
