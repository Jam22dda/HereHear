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

const ItemBox: React.FC<ItemBoxProps> = ({
    src,
    title,
    $isselected = false, // 기본값 설정
    onClick = () => {}, // 기본값 설정
}) => {
    return (
        <S.ItemBoxWrapper onClick={onClick} $isselected={$isselected}>
            <Image src={src} width={72} $unit="px" $margin="0 0 8px 0" />
            <S.ItemBoxTextWrapper>
                <Text size="small2">{title}</Text>
            </S.ItemBoxTextWrapper>
        </S.ItemBoxWrapper>
    );
};

export default ItemBox;
