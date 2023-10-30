import { useNavigate } from "react-router-dom";
import React from "react";
import { Text } from "../../components/atoms/Text/Text.styles";
import * as S from "./LandingPage.styles";
import { Image } from "../../components/atoms/Image/Image";
import IconKakao from "../../assets/Landing/icon-kakao.png";

export default function Landing() {
    // const id = "angel";

    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    return (
        <div id="display">
            <S.LandingWrapper className="container">
                <Text size="heading1" fontWeight="bold" $marginTop="150px">
                    HERE HEAR
                </Text>
                <S.LoginBtn onClick={() => navigatePage("/nickname")}>
                    <Image
                        src={IconKakao}
                        width={24}
                        height={20}
                        $unit="px"
                        $margin="0 8px 0 0"
                    ></Image>
                    <Text size="body1" fontWeight="bold">
                        카카오 로그인
                    </Text>
                </S.LoginBtn>
            </S.LandingWrapper>
        </div>
    );
}
