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
    width?: number;
    style?: React.CSSProperties;
}

export default function ItemBox({
    src,
    title,
    $isselected = false,
    onClick = () => {},
    width = 80,
    style,
}: ItemBoxProps) {
    return (
        <S.ItemBoxWrapper
            onClick={onClick}
            $isselected={$isselected}
            style={style}
        >
            <Image src={src} width={width} $unit="px" $margin="0 0 4px 0" />
            <S.ItemBoxTextWrapper>
                <Text size="small2" $textAlign="center">
                    {title}
                </Text>
            </S.ItemBoxTextWrapper>
        </S.ItemBoxWrapper>
    );
}
