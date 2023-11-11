import * as S from "./ClockTest.styles";
import clock from "../../../assets/ClockTest/icon-clock.png";
import { Image } from "../../atoms/Image/Image";
import { useEffect, useState, FC } from "react";
import halfCircle from "../../../assets/ClockTest/icon-halfCircle.png";
import dawnCircle from "../../../assets/ClockTest/icon-halfCircle-dawn.png";
import morningCircle from "../../../assets/ClockTest/icon-halfCircle-morning.png";
import noonCircle from "../../../assets/ClockTest/icon-halfCircle-noon.png";
import nightCircle from "../../../assets/ClockTest/icon-halfCircle-night.png";

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

  const [degrees, setDegrees] = useState(calculateDegrees());
  const hours = new Date().getHours();

  useEffect(() => {
    const interval = setInterval(() => {
      // console.log("interval", calculateDegrees());
      setDegrees(calculateDegrees());
    }, 1000);

    return () => clearInterval(interval); // 컴포넌트가 언마운트될 때 interval을 제거합니다.
  }, []);

  return (
    <S.ImageContainer onClick={onClick}>
      {/* 새벽, 오전, 오후, 밤 시간에 따라 이미지 변경 */}
      {hours < 6 ? (
        <S.RotatingImage src={dawnCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      ) : hours < 12 ? (
        <S.RotatingImage src={morningCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      ) : hours < 18 ? (
        <S.RotatingImage src={noonCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      ) : (
        <S.RotatingImage src={nightCircle} style={{ transform: `rotate(${degrees}deg)` }} />
      )}
      <Image className="bg" src={clock} width={100} height={100} $unit="%"></Image>
    </S.ImageContainer>
  );
};

export default MapClock;
