import React, { useEffect, useState } from "react";
import { Text } from "../../components/atoms/Text/Text.styles";
import * as S from "./LandingPage.styles";
import { Image } from "../../components/atoms/Image/Image";
import IconKakao from "../../assets/Landing/icon-kakao.png";
import IconDown from "../../assets/Landing/icon-down.png";
import logo from "../../assets/Landing/logo-herehear.png";
import IconGoogle from "../../assets/Landing/icon-google.png";
// import IconSpotify from "../../assets/Landing/icon-spotify.png";

export default function Landing() {
    // const id = "angel";
    const serverUrl = import.meta.env.VITE_SERVER_URL;

    const navigatePage = (service: string) => {
        window.location.href = `${serverUrl}/oauth2/authorization/${service}`;
    };

    // pwa 버튼 기능
    const [installPrompt, setInstallPrompt] = useState<any>(null);

    useEffect(() => {
        window.addEventListener("beforeinstallprompt", handleBeforeInstallPrompt);

        return () => {
            window.removeEventListener("beforeinstallprompt", handleBeforeInstallPrompt);
        };
    }, []);

    const handleBeforeInstallPrompt = (event: Event) => {
        event.preventDefault();
        setInstallPrompt(event);
    };

    const handleInstallClick = () => {
        if (installPrompt) {
            installPrompt.prompt();

            installPrompt.userChoice.then((choiceResult: { outcome: string }) => {
                if (choiceResult.outcome === "accepted") {
                    // console.log('사용자가 설치 프롬프트에 동의했습니다.');
                } else {
                    // console.log('사용자가 설치 프롬프트를 무시했습니다.');
                }

                setInstallPrompt(null);
            });
        }
    };

    return (
        <div id="display">
            <div className="container">
                <S.LandingWrapper>
                    <Image width={70} $unit="%" src={logo} $margin="120px 0 0 0"></Image>
                    {installPrompt && (
                        <S.PwaBtn onClick={handleInstallClick}>
                            <Image src={IconDown} width={24} height={20} $unit="px" $margin="0 8px 0 0"></Image>
                            <Text size="body1" fontWeight="bold">
                                앱 설치하기
                            </Text>
                        </S.PwaBtn>
                    )}
                    <S.LoginWrapper>
                        <S.KaKaoLoginBtn onClick={() => navigatePage("kakao")}>
                            <Image src={IconKakao} width={85} $unit="px"></Image>
                        </S.KaKaoLoginBtn>
                        <S.GoogleLoginBtn onClick={() => navigatePage("google")}>
                            <Image src={IconGoogle} width={24} $unit="px"></Image>
                        </S.GoogleLoginBtn>
                        {/* <S.SpotifyLoginBtn
                            onClick={() => navigatePage("spotify")}
                        >
                            <Image
                                src={IconSpotify}
                                width={50}
                                $unit="px"
                            ></Image>
                        </S.SpotifyLoginBtn> */}
                    </S.LoginWrapper>
                </S.LandingWrapper>
            </div>
        </div>
    );
}
