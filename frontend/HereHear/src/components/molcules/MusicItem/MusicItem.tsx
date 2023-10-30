// import React from "react";
import * as S from "./MusicItem.styles";
import { Image } from "../../atoms/Image/Image";
import BTS_answer from "../../../assets/MusicItem/BTS_answer.jpg";
import { Text } from "../../atoms/Text/Text.styles";

export default function MusicItem() {
    return (
        <S.MusicItemWrapper>
            <Image src={BTS_answer} width={50} height={50} $unit="px" $boxShadow="shadow_goback" $borderRadius="10px"></Image>
            <S.MusicTextWrapper>
                <Text size="small2" fontWeight="bold">
                    Answer : Love Myself
                </Text>
                <Text size="small3">방탄소년단</Text>
            </S.MusicTextWrapper>
        </S.MusicItemWrapper>
    );
}
