import styled, { keyframes } from "styled-components";
import { Text } from "../../atoms/Text/Text.styles";

const scroll = keyframes`
  from {
    transform: translateX(0%);
  }
  to {
    transform: translateX(-100%);
  }
`;

const MusicItemWrapper = styled.div`
    display: flex;
    align-items: center; /* 이미지와 텍스트를 세로 중앙 정렬합니다. */
`;

const MusicTextWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    margin-left: 12px;
    overflow: hidden; /* 텍스트가 넘칠 경우 숨김 처리합니다. */
    height: 50px;
`;

// MarqueeTextProps 타입을 정의합니다.
interface MarqueeTextProps {
    isOverflowing: boolean;
    animate: boolean;
}

// MarqueeText를 styled-component로 정의할 때 해당 타입을 적용합니다.
const MarqueeText = styled(Text)<MarqueeTextProps>`
    white-space: nowrap;
    display: inline-block;
    width: 190px;
    min-width: 100%;
    animation: ${scroll} 10s linear infinite;
    animation-play-state: ${(props) => (props.animate ? "running" : "paused")};
    animation-delay: ${(props) =>
        props.isOverflowing && !props.animate ? "3s" : "0s"};
    animation-fill-mode: forwards;
`;

export { MusicItemWrapper, MusicTextWrapper, MarqueeText };
