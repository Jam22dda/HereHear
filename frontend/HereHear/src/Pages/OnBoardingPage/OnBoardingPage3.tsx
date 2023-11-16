import { Image } from "../../components/atoms/Image/Image";
import OnBoarding3 from "../../assets/OnBoardingPage/onBoarding3-empty.png";
import * as S from "./OnBoardingPage3.styles";
import iconDirection from "../../assets/OnBoardingPage/icon-direction.png";

type OnBoardingPageProps = {
    onNextPage: () => void;
};

export default function OnBoardingPage(props: OnBoardingPageProps) {
    const { onNextPage } = props;
    return (
        <div id="display">
            <S.Container>
                <Image
                    src={OnBoarding3}
                    width={390}
                    $unit="px"
                    onClick={onNextPage}
                    style={{ position: "relative" }}
                />
                <S.DirectionWrapper>
                    <Image
                        src={iconDirection}
                        width={126}
                        $unit="px"
                        onClick={onNextPage}
                    />
                </S.DirectionWrapper>
            </S.Container>
        </div>
    );
}
