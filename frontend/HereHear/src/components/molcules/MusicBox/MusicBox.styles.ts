// import theme from "../../../styles/theme";
import styled, { keyframes } from 'styled-components';
import { Text } from '../../atoms/Text/Text.styles';

const Outer = styled.div`
    width: 100%;
    height: 100%;
    /* background-color: red; */
    position: absolute;
    display: flex;
    justify-content: center;
`;
const MusicBox = styled.div`
    height: 149px;
    width: 303px;
    border-radius: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: ${({ theme }) => theme.color.white1};
    position: absolute;
    bottom: 130px;
    z-index: 998;
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};

    /* @media screen and (max-width: 425px) {
        width: 303px;
    } */
`;

const ImageLeftOuter = styled.div`
    position: absolute;
    top: 36%;
    left: -20px;
`;
const ImageRightOuter = styled.div`
    position: absolute;
    top: 36%;
    right: -20px;
`;

const MusicBoxInner = styled.div`
    display: flex;
    position: relative;
    width: 100%;
    height: 100%;
`;

const BigWrapper = styled.div`
    display: flex;
    align-items: center;
    padding: 16px;

    .album_img {
        margin-right: 12px;
    }
`;
const MidWrapper = styled.div`
    display: flex;
    flex-direction: column;
`;
const MapTextrapper = styled.div`
    display: flex;
    flex-direction: column;
    overflow: hidden;
`;

const MapMusicTagWrapper = styled.div`
    display: flex;
    /* flex-direction: column; */
`;

const scroll = keyframes`
  0% {
    transform: translateX(0%);
  }
  100% {
    transform: translateX(-100%);
  }
`;

// MarqueeTextProps 타입을 정의합니다.
interface MarqueeTextProps {
    isOverflowing: boolean;
    animate: boolean;
    key: string; // key prop을 추가합니다.
}

// MarqueeText를 styled-component로 정의할 때 해당 타입을 적용합니다.
const MarqueeText = styled(Text)<MarqueeTextProps>`
    white-space: nowrap;
    display: inline-block;
    width: 160px;
    min-width: 100%;
    animation: ${props => (props.isOverflowing && props.animate ? scroll : 'none')} 10s linear infinite;
    animation-play-state: ${props => (props.animate ? 'running' : 'paused')};
    animation-delay: ${props => (props.isOverflowing && props.animate ? '3s' : '0s')};
    animation-fill-mode: forwards;
`;

export { MusicBox, BigWrapper, MidWrapper, MapMusicTagWrapper, MapTextrapper, Outer, MusicBoxInner, ImageLeftOuter, ImageRightOuter, MarqueeText };
