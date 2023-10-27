import React from "react";
import * as S from "./ItemBox.styles";
import { Image } from "../../atoms/Image/Image";
import IconHeartBeat from "../../../assets/ItemBox/icon-heartbeat.png";
import { Text } from "../../atoms/Text/Text.styles";

export default function ItemBox() {
    return (
        <S.ItemBoxWrapper>
            <Image src={IconHeartBeat} width={80} $unit="px"></Image>
            <S.ItemBoxTextWrapper>
                <Text size="small2">하트비트</Text>
            </S.ItemBoxTextWrapper>
        </S.ItemBoxWrapper>
    );
}
