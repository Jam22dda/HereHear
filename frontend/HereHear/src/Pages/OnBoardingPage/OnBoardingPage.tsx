import { useState } from "react";
import OnBoardingPage1 from "./OnBoardingPage1";
import OnBoardingPage2 from "./OnBoardingPage2";
import OnBoardingPage3 from "./OnBoardingPage3";
import OnBoardingPage4 from "./OnBoardingPage4";
import OnBoardingPage5 from "./OnBoardingPage5";
import OnBoardingPage6 from "./OnBoardingPage6";

export default function OnBoardingPage() {
    const [activatedNum, setActivatedNum] =
        useState<keyof typeof PayOnlinePages>(1);

    const handleNextPage = () => {
        if (activatedNum < 6) {
            setActivatedNum(
                (prevNum) => (prevNum + 1) as keyof typeof PayOnlinePages
            );
        } else if (activatedNum === 6) {
            setActivatedNum(6 as keyof typeof PayOnlinePages);
        }
    };

    const PayOnlinePages = {
        1: <OnBoardingPage1 onNextPage={handleNextPage} />,
        2: <OnBoardingPage2 onNextPage={handleNextPage} />,
        3: <OnBoardingPage3 onNextPage={handleNextPage} />,
        4: <OnBoardingPage4 onNextPage={handleNextPage} />,
        5: <OnBoardingPage5 onNextPage={handleNextPage} />,
        6: <OnBoardingPage6 />,
    };

    return <div id="display">{PayOnlinePages[activatedNum]}</div>;
}
