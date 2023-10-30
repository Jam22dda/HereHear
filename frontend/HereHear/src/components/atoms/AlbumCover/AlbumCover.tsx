import React from "react";
import * as S from "./AlbumCover.styles";
import btsAnswer from "../../../assets/AlbumCover/BTS_answer.jpg";
import { Image } from "../Image/Image";

export default function ItemBox() {
    return (
        <S.AlbumCoverWrapper>
            <Image src={btsAnswer} width={210} height={210} $unit="px" $borderRadius="104px"></Image>
        </S.AlbumCoverWrapper>
    );
}
