import { Image } from "../../components/atoms/Image/Image";
import OnBoarding1 from "../../assets/OnBoardingPage/onBoarding1.png";
import OnBoarding2 from "../../assets/OnBoardingPage/onBoarding2.png";
import OnBoarding3 from "../../assets/OnBoardingPage/onBoarding3.png";
import OnBoarding4 from "../../assets/OnBoardingPage/onBoarding4.png";
import OnBoarding5 from "../../assets/OnBoardingPage/onBoarding5.png";
import OnBoarding6 from "../../assets/OnBoardingPage/onBoarding6.png";
import OnBoarding7 from "../../assets/OnBoardingPage/onBoarding7.png";
import OnBoarding8 from "../../assets/OnBoardingPage/onBoarding8.png";
import OnBoarding9 from "../../assets/OnBoardingPage/onBoarding9.png";
import OnBoarding10 from "../../assets/OnBoardingPage/onBoarding10.png";
import OnBoarding11 from "../../assets/OnBoardingPage/onBoarding11.png";
import OnBoarding12 from "../../assets/OnBoardingPage/onBoarding12.png";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

export default function OnBoardingPage() {
    const navigate = useNavigate();
    const [currentImage, setCurrentImage] = useState<string>(OnBoarding1);

    const handleImageClick = () => {
        // 이미지를 순차적으로 변경합니다.
        const imageSequence = [
            OnBoarding1,
            OnBoarding2,
            OnBoarding3,
            OnBoarding4,
            OnBoarding5,
            OnBoarding6,
            OnBoarding7,
            OnBoarding8,
            OnBoarding9,
            OnBoarding10,
            OnBoarding11,
            OnBoarding12,
        ];

        // 현재 이미지의 인덱스를 찾습니다.
        const currentIndex = imageSequence.indexOf(currentImage);

        // 마지막 이미지가 아니라면 다음 이미지로 변경합니다.
        if (currentIndex >= 0 && currentIndex < imageSequence.length - 1) {
            setCurrentImage(imageSequence[currentIndex + 1]);
        } else if (currentIndex === imageSequence.length - 1) {
            // 마지막 이미지(OnBoarding12)를 클릭하면 /core로 이동합니다.
            navigate("/core");
        }
    };

    return (
        <div id="display">
            <Image
                src={currentImage}
                width={100}
                $unit="%"
                onClick={handleImageClick}
            />
        </div>
    );
}
