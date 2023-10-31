// import React from "react";
import * as S from "./MusicItem.styles";
import { Image } from "../../atoms/Image/Image";
// import BTS_answer from "../../../assets/MusicItem/BTS_answer.jpg";
import { Text } from "../../atoms/Text/Text.styles";

interface MusicItemProps {
    src: string;
    title: string;
    artist: string;
}

const MusicItem: React.FC<MusicItemProps> = ({ src, title, artist }) => {
    return (
        <S.MusicItemWrapper>
            <Image src={src} width={50} height={50} $unit="px" $boxShadow="shadow_goback" $borderRadius="10px"></Image>
            <S.MusicTextWrapper>
                <Text size="small2" fontWeight="bold">
                    {title}
                </Text>
                <Text size="small3">{artist}</Text>
            </S.MusicTextWrapper>
        </S.MusicItemWrapper>
    );
};

export default MusicItem;
