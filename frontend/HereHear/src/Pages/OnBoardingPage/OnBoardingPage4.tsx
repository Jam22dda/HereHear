import { Image } from "../../components/atoms/Image/Image";
import OnBoarding4 from "../../assets/OnBoardingPage/onBoarding4-empty.png";
import iconTags from "../../assets/OnBoardingPage/icon-tags.png";
import * as S from "./OnBoardingPage4.styles";

type OnBoardingPageProps = {
    onNextPage: () => void;
};

export default function OnBoardingPage(props: OnBoardingPageProps) {
    const { onNextPage } = props;
    return (
        <div id="display">
            <S.Container>
                <Image
                    src={OnBoarding4}
                    width={390}
                    $unit="px"
                    onClick={onNextPage}
                    style={{ position: "relative" }}
                />
                <S.TagsWrapper>
                    <Image
                        src={iconTags}
                        width={355}
                        $unit="px"
                        onClick={onNextPage}
                    />
                </S.TagsWrapper>
            </S.Container>
        </div>
    );
}
