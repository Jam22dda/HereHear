import * as S from "./ClockTest.styles";
import clock from "../../../assets/ClockTest/icon-clock.png";
import { Image } from "../../atoms/Image/Image";
import { useEffect, useState, FC } from "react";
import halfCircle from "../../../assets/ClockTest/icon-halfCircle.png";

interface MapClockProps {
    onClick?: () => void; // 이 prop은 선택적이므로 '?'를 사용합니다.
}
const MapClock: FC<MapClockProps> = ({ onClick }) => {
    // 현재 시간으로부터 초침의 위치를 계산합니다.
    const calculateDegrees = () => {
        const now = new Date();

        const hour = now.getHours() % 12;
        const minutes = now.getMinutes();
        const second = now.getSeconds();
        const degrees = hour * 30 + minutes * 0.5 + (second * 0.5) / 60;

        // console.log("degrees", degrees);
        return degrees;
        // return now.getSeconds() * 6;
    };

    const [degrees, setDegrees] = useState(calculateDegrees());

    useEffect(() => {
        const interval = setInterval(() => {
            // console.log("interval", calculateDegrees());
            setDegrees(calculateDegrees());
        }, 1000);

        return () => clearInterval(interval); // 컴포넌트가 언마운트될 때 interval을 제거합니다.
    }, []);

    return (
        <S.ImageContainer onClick={onClick}>
            <S.RotatingImage src={halfCircle} style={{ transform: `rotate(${degrees}deg)` }} />
            <Image className="bg" src={clock} width={100} height={100} $unit="%"></Image>
        </S.ImageContainer>
    );
};

export default MapClock;
