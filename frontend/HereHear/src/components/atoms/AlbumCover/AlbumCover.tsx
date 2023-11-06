import React from "react";
import * as S from "./AlbumCover.styles";
// import btsAnswer from "../../../assets/AlbumCover/BTS_answer.jpg";
import { Image } from "../Image/Image";

interface Albumcover {
    src: string;
}

export default function ItemBox({ src }: Albumcover) {
    return (
        <S.AlbumCoverWrapper>
            <Image src={src} width={210} height={210} $unit="px" $borderRadius="104px"></Image>
        </S.AlbumCoverWrapper>
    );
}
