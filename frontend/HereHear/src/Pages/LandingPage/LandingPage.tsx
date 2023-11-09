import React from "react";
import { Text } from "../../components/atoms/Text/Text.styles";
import * as S from "./LandingPage.styles";
import { Image } from "../../components/atoms/Image/Image";
import IconKakao from "../../assets/Landing/icon-kakao.png";
import logo from "../../assets/Landing/logo-herehear.png";
// import axios from "axios";

export default function Landing() {
    // const id = "angel";
    const serverUrl = import.meta.env.VITE_SERVER_URL;

    const navigatePage = () => {
        window.location.href = `${serverUrl}/oauth2/authorization/kakao`;
    };

    return (
        <div id="display">
            <S.LandingWrapper className="container">
                {/* <Text size="heading1" fontWeight="bold" $marginTop="150px">
                    HERE HEAR
                </Text> */}
                <Image width={70} $unit="%" src={logo} $margin="120px 0 0 0"></Image>
                <S.LoginBtn onClick={navigatePage}>
                    <Image src={IconKakao} width={24} height={20} $unit="px" $margin="0 8px 0 0"></Image>
                    <Text size="body1" fontWeight="bold">
                        카카오 로그인
                    </Text>
                </S.LoginBtn>
            </S.LandingWrapper>
        </div>
    );
}
