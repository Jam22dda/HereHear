import * as S from "./ClockTest.styles";
import clock from "../../../assets/ClockTest/icon-clock.png";
import { Image } from "../../atoms/Image/Image";
import { useEffect, useState, FC } from "react";
import halfCircle from "../../../assets/ClockTest/icon-halfCircle.png";
import dawnCircle from "../../../assets/ClockTest/icon-halfCircle-dawn.png";
import morningCircle from "../../../assets/ClockTest/icon-halfCircle-morning.png";
import noonCircle from "../../../assets/ClockTest/icon-halfCircle-noon.png";
import nightCircle from "../../../assets/ClockTest/icon-halfCircle-night.png";
import clockHour from "../../../assets/ClockTest/icon-clockHour.png";
import clockMinutes from "../../../assets/ClockTest/icon-clockMinute.png";
import clockSecond from "../../../assets/ClockTest/icon-clockSecond.png";
import centerCircle from "../../../assets/ClockTest/icon-centerCircle.png"

interface MapClockProps {
  onClick?: () => void; // 이 prop은 선택적이므로 '?'를 사용합니다.
}
const MapClock: FC<MapClockProps> = ({ onClick }) => {
  const calculateDegrees = () => {
    const now = new Date();

    const hour = now.getHours() % 12;
    const minutes = now.getMinutes();
    const second = now.getSeconds();
    const degrees = hour * 30 + minutes * 0.5 + (second * 0.5) / 60;

    return degrees;
  };

  const calculateHourDegrees = () => {
    const now = new Date();
    const hour = now.getHours() % 12;
    const minutes = now.getMinutes();

    return hour * 30 + minutes * 0.5;
  };

  const calculateMinuteDegrees = () => {
    const now = new Date();
    const minutes = now.getMinutes();

    return minutes * 6;
  };

  const calculateSecondDegrees = () => {
    const now = new Date();
    const second = now.getSeconds();

    return second * 6;
  };

  const [degrees, setDegrees] = useState(calculateDegrees());
  const [hourDegrees, setHourDegrees] = useState(calculateHourDegrees());
  const [minuteDegrees, setMinuteDegrees] = useState(calculateMinuteDegrees());
  const [secondDegrees, setSecondDegrees] = useState(calculateSecondDegrees());
  const [currentImage, setCurrentImage] = useState("");

  useEffect(() => {
    const updateImageBasedOnTime = () => {
      const hours = new Date().getHours(); // 현재 시간을 24시간 형태로 가져옴

      if (hours < 6) {
        setCurrentImage(dawnCircle); // 새벽 이미지
      } else if (hours < 12) {
        setCurrentImage(morningCircle); // 오전 이미지
      } else if (hours < 18) {
        setCurrentImage(noonCircle); // 오후 이미지
      } else {
        setCurrentImage(nightCircle); // 밤 이미지
      }
    };

    const interval = setInterval(() => {
      updateImageBasedOnTime();
      setDegrees(calculateDegrees());
      setHourDegrees(calculateHourDegrees());
      setMinuteDegrees(calculateMinuteDegrees());
      setSecondDegrees(calculateSecondDegrees());
    }, 1000);

    return () => clearInterval(interval); // 컴포넌트가 언마운트될 때 interval을 제거합니다.
  }, []);

  return (
    <S.ImageContainer onClick={onClick}>
      {/* 새벽, 오전, 오후, 밤 시간에 따라 이미지 변경 */}
      <S.RotatingImage src={currentImage} style={{ transform: `rotate(${degrees}deg)` }} />
      <S.RotatingImage src={clockHour} style={{ transform: `rotate(${hourDegrees}deg)` }} />
      <S.RotatingImage src={clockMinutes} style={{ transform: `rotate(${minuteDegrees}deg)` }} />
      <S.RotatingImage src={clockSecond} style={{ transform: `rotate(${secondDegrees}deg)` }} />
      <S.RotatingImage src={centerCircle} style={{ transform: `rotate(${degrees}deg)` }} />

      <Image className="bg" src={clock} width={100} height={100} $unit="%"></Image>
    </S.ImageContainer>
  );
};

export default MapClock;
