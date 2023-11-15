import { Image } from "../../components/atoms/Image/Image";
import OnBoarding2 from "../../assets/OnBoardingPage/onBoarding2-empty.png";
import iconClock from "../../assets/OnBoardingPage/icon-clock.png";
import * as S from "./OnBoardingPage2.styles";

type OnBoardingPageProps = {
    onNextPage: () => void;
};

export default function OnBoardingPage(props: OnBoardingPageProps) {
    const { onNextPage } = props;
    return (
        <div id="display">
            <S.Container>
                <Image
                    src={OnBoarding2}
                    width={390}
                    $unit="px"
                    onClick={onNextPage}
                    style={{ position: "relative" }}
                />
                <S.ClockWrapper>
                    <Image
                        src={iconClock}
                        width={660}
                        $unit="px"
                        onClick={onNextPage}
                    />
                </S.ClockWrapper>
            </S.Container>
        </div>
    );
}
