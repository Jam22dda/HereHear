import { Image } from "../../components/atoms/Image/Image";
import OnBoarding5 from "../../assets/OnBoardingPage/onBoarding5-empty.png";
import iconRocket from "../../assets/OnBoardingPage/icon-rocket.png";
import * as S from "./OnBoardingPage5.styles";
import startBtn from "../../assets/OnBoardingPage/start-btn.png";

type OnBoardingPageProps = {
    onNextPage: () => void;
};

export default function OnBoardingPage(props: OnBoardingPageProps) {
    const { onNextPage } = props;
    return (
        <div id="display">
            <S.Container>
                <Image
                    src={OnBoarding5}
                    width={390}
                    $unit="px"
                    style={{ position: "relative" }}
                />
                <S.RocketWrapper>
                    <Image src={iconRocket} width={300} $unit="px" />
                </S.RocketWrapper>
                <S.StartBtnWrapper>
                    <Image
                        src={startBtn}
                        width={238}
                        $unit="px"
                        onClick={onNextPage}
                    />
                </S.StartBtnWrapper>
            </S.Container>
        </div>
    );
}
