import { Image } from "../../components/atoms/Image/Image";
import OnBoarding1 from "../../assets/OnBoardingPage/onBoarding1.png";

type OnBoardingPageProps = {
    onNextPage: () => void;
};

export default function OnBoardingPage(props: OnBoardingPageProps) {
    const { onNextPage } = props;
    return (
        <div id="display">
            <Image
                src={OnBoarding1}
                width={390}
                $unit="px"
                onClick={onNextPage}
            />
        </div>
    );
}
