import React from "react";
import * as S from "./MusicItem.styles";
import { Image } from "../../atoms/Image/Image";
import { Text } from "../../atoms/Text/Text.styles";

interface MusicItemProps {
    src: string;
    songtitle: string;
    artist: string;
}

function MusicItem({ src, songtitle, artist }: MusicItemProps) {
    return (
        <S.MusicItemWrapper>
            <Image src={src} width={50} height={50} $unit="px" $boxShadow="shadow_goback" $borderRadius="10px"></Image>
            <S.MusicTextWrapper>
                <Text size="small2" fontWeight="bold" style={{ width: "230px" }}>
                    {songtitle}
                </Text>
                <Text size="small3" style={{ width: "230px" }}>
                    {artist}
                </Text>
            </S.MusicTextWrapper>
        </S.MusicItemWrapper>
    );
}

export default MusicItem;
