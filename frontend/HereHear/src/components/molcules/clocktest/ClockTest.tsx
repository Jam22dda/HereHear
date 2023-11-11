import { FC, useEffect, useState } from "react";
import clock from "../../../assets/ClockTest/icon-clock.png";
import dawnCircle from "../../../assets/ClockTest/icon-halfCircle-dawn.png";
import morningCircle from "../../../assets/ClockTest/icon-halfCircle-morning.png";
import nightCircle from "../../../assets/ClockTest/icon-halfCircle-night.png";
import noonCircle from "../../../assets/ClockTest/icon-halfCircle-noon.png";
import { Image } from "../../atoms/Image/Image";
import * as S from "./ClockTest.styles";

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

    return degrees;
  };

  const [degrees, setDegrees] = useState(calculateDegrees());

  useEffect(() => {
    const interval = setInterval(() => {
      setDegrees(calculateDegrees());
    }, 1000);

    return () => clearInterval(interval); // 컴포넌트가 언마운트될 때 interval을 제거합니다.
  }, []);

  return (
    <S.ImageContainer onClick={onClick}>
      {/* 오전, 오후, 밤, 새벽 시간에 따라 RotatingImage 의 src가 변하도록 구현 */}
      {new Date().getHours() < 6 ? (
        <S.RotatingImage src={dawnCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      ) : new Date().getHours() < 12 ? (
        <S.RotatingImage src={morningCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      ) : new Date().getHours() < 18 ? (
        <S.RotatingImage src={noonCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      ) : (
        <S.RotatingImage src={nightCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      )}
      <Image className="bg" src={clock} width={100} height={100} $unit="%"></Image>
    </S.ImageContainer>
  );
};

export default MapClock;
