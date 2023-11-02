// import React from "react";
import * as S from "./MusicBox.styles";
import { Image } from "../../atoms/Image/Image";
import BTS_answer from "../../../assets/MusicItem/BTS_answer.jpg";
import { Text } from "../../atoms/Text/Text.styles";
import afterBtn from "../../../assets/MusicBox/icon-musicAfterBtn.png";
import beforeBtn from "../../../assets/MusicBox/icon-musicBeforeBtn.png";
import Button from "../../atoms/Button/Button";

export default function MusicBox() {
    return (
        <S.MusicBox>
            <Image src={beforeBtn} width={35} height={35} $unit="px"></Image>
            <S.BigWrapper>
                <Image src={BTS_answer} width={100} height={100} $unit="px" $borderRadius="10px"></Image>

                <S.MidWrapper>
                    <S.MapTextrapper>
                        <Text size="body2" fontWeight="bold" color="main1" $margin="0 0 5px">
                            Answer : Love Myself
                        </Text>
                        <Text size="body2" fontWeight="medium" color="main1" $margin="0 0 10px">
                            방탄소년단
                        </Text>
                    </S.MapTextrapper>

                    <S.MapMusicTagWrapper>
                        {/* TODO:버튼 크기 다시확인(아톰 버튼에 없음?) */}
                        <Button option="tag1" $width="55px" $height="25px" size="small">
                            받은 값
                        </Button>
                        <Button option="tag1" $width="55px" $height="25px" size="small">
                            받은 값
                        </Button>
                        <Button option="tag1" $width="55px" $height="25px" size="small">
                            받은 값
                        </Button>
                    </S.MapMusicTagWrapper>
                </S.MidWrapper>
            </S.BigWrapper>
            <Image src={afterBtn} width={35} height={35} $unit="px"></Image>
        </S.MusicBox>
    );
}
