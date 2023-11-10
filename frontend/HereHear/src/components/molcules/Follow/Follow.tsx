// import React from "react";
import * as S from "./Follow.styles";
import { Text } from "../../atoms/Text/Text.styles";
import CircleButton from "../../atoms/CircleButton/CircleButton";
import { Image } from "../../atoms/Image/Image";

interface FollowProps {
    nickname: string;
    characterImage: string;
    titleName?: string;
    onClick?: () => void;
}

export default function Follow({
    nickname,
    characterImage,
    titleName = "",
    onClick = () => {},
}: FollowProps) {
    return (
        <S.FollowWrapper onClick={onClick}>
            <CircleButton option="follow" size="large">
                <Image src={characterImage} width={32} height={32} $unit="px" />
            </CircleButton>
            <S.FollowTextWrapper>
                <Text size="small2" fontWeight="bold" color="main2">
                    {nickname}
                </Text>
                <Text size="small3" fontWeight="medium" color="main2">
                    {titleName}
                </Text>
            </S.FollowTextWrapper>
        </S.FollowWrapper>
    );
}
