import { useEffect, useState } from "react";
import halfCircle from "../../../assets/ClockTest/icon-halfCircle.png";
import ImageComponent from "./ImageComponent";

export default function ClockTest() {
  // 현재 시간으로부터 초침의 위치를 계산합니다.
  const calculateDegrees = () => {
    const now = new Date();

    const hour = now.getHours() % 12;
    const minutes = now.getMinutes();
    const second = now.getSeconds();
    const degrees = hour * 30 + minutes * 0.5 + (second * 0.5) / 60;

    console.log("degrees", degrees);
    return degrees;
    // return now.getSeconds() * 6;
  };

  const [secondsDegrees, setSecondsDegrees] = useState(calculateDegrees());

  useEffect(() => {
    const interval = setInterval(() => {
      console.log("interval", calculateDegrees());
      setSecondsDegrees(calculateDegrees());
    }, 1000);

    return () => clearInterval(interval); // 컴포넌트가 언마운트될 때 interval을 제거합니다.
  }, []);

  return (
    <>
      <ImageComponent src={halfCircle} degree={secondsDegrees}></ImageComponent>
    </>
  );
}
