// import React from "react";
import * as S from "./Follow.styles";
import { Text } from "../../atoms/Text/Text.styles";
import CircleButton from "../../atoms/CircleButton/CircleButton";
// import theme from "../../../styles/theme";

export default function Follow() {
    return (
        <S.FollowWrapper>
            <CircleButton $backgroundColor="gradient2" size="large" box-shadow="shadow_smallbtn"></CircleButton>
            <S.FollowTextWrapper>
                <Text size="small2" fontWeight="bold">
                    이름이요
                </Text>
                <Text size="small3" fontWeight="medium">
                    대표뱃지
                </Text>
            </S.FollowTextWrapper>
        </S.FollowWrapper>
    );
}
