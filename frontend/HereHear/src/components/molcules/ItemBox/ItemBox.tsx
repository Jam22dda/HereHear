// ItemBox.tsx
import React from "react";
import * as S from "./ItemBox.styles";
import { Image } from "../../atoms/Image/Image";
import { Text } from "../../atoms/Text/Text.styles";

interface ItemBoxProps {
    src: string;
    title: string;
    $isselected?: boolean;
    onClick?: () => void;
}

export default function ItemBox({
    src,
    title,
    $isselected = false,
    onClick = () => {},
}: ItemBoxProps) {
    return (
        <S.ItemBoxWrapper onClick={onClick} $isselected={$isselected}>
            <Image src={src} width={80} $unit="px" $margin="0 0 4px 0" />
            <S.ItemBoxTextWrapper>
                <Text size="small2">{title}</Text>
            </S.ItemBoxTextWrapper>
        </S.ItemBoxWrapper>
    );
}
