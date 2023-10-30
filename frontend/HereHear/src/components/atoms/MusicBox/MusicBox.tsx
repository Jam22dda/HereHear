// import React from "react";
import * as S from "./MusicBox.styles";
import { Image } from "../../atoms/Image/Image";
import BTS_answer from "../../../assets/MusicItem/BTS_answer.jpg";
import { Text } from "../../atoms/Text/Text.styles";
import Button from "../Button/Button";
export default function MusicBox() {
  return (
    <S.MusicBox>
      <Image
        src={BTS_answer}
        width={50}
        height={50}
        $unit="px"
        $boxShadow="shadow_goback"
        $borderRadius="10px"
      ></Image>
      <S.MusicTextWrapper>
        <Text size="body2" fontWeight="bold">
          Answer : Love Myself
        </Text>
        <Text size="body2" fontWeight="medium">
          방탄소년단
        </Text>
        {/* TODO:버튼 크기 다시확인(아톰 버튼에 없음?) */}
        <Button option="tag_selected" size="small" $fontSize="10px">
          공부
        </Button>
      </S.MusicTextWrapper>
    </S.MusicBox>
  );
}
