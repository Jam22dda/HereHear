import React from "react";
import * as S from "./MusicItem.styles";
import { Image } from "../../atoms/Image/Image";
import { Text } from "../../atoms/Text/Text.styles";

interface MusicItemProps {
    src: string;
    songtitle: string;
    artist: string;
    onClick?: () => void;
}

function MusicItem({
    src,
    songtitle,
    artist,
    onClick = () => {},
}: MusicItemProps) {
    return (
        <S.MusicItemWrapper onClick={onClick}>
            <Image
                src={src}
                width={50}
                height={50}
                $unit="px"
                $boxShadow="shadow_goback"
                $borderRadius="10px"
            ></Image>
            <S.MusicTextWrapper>
                <Text
                    size="small2"
                    fontWeight="bold"
                    style={{ width: "230px" }}
                    color="main2"
                >
                    {songtitle}
                </Text>
                <Text size="small3" color="main2" style={{ width: "230px" }}>
                    {artist}
                </Text>
            </S.MusicTextWrapper>
        </S.MusicItemWrapper>
    );
}

export default MusicItem;
