import styled from "styled-components";
import clock from "../../../assets/ClockTest/icon-clock.png";
import { Image } from "../../atoms/Image/Image";

// 이미지를 감싸는 컨테이너 스타일입니다.
const ImageContainer = styled.div`
  width: 100px;
  height: 100px;
  /* display: inline-block; // 또는 'block'이 적절할 수 있습니다. */
  transform-origin: center; // 회전의 중심을 이미지 중앙으로 설정합니다.
  position: relative;
`;

// 이미지 스타일입니다.
const RotatingImage = styled.img`
  /* display: block; // 이미지가 라인의 전체 폭을 차지하도록 합니다. */
  position: absolute;
  left: 26%;
  top: 26%;
  width: 50%;
  height: 50%;
`;

// 이미지 컴포넌트입니다.
const ImageComponent = ({ src, degree }) => {
  return (
    <ImageContainer>
      <RotatingImage src={src} style={{ transform: `rotate(${degree}deg)` }} />
      <Image className="bg" src={clock} width={100} height={100} $unit="%"></Image>
    </ImageContainer>
  );
};

export default ImageComponent;
