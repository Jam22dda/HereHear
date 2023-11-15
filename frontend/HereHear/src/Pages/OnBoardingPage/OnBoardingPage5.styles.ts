import styled, { keyframes } from "styled-components";

const floatAnimation = keyframes`
  0% { transform: translateY(0px); }
  50% { transform: translateY(-20px); }
  100% { transform: translateY(0px); }
`;

const shimmerAnimation = keyframes`
  0% { box-shadow: 0 0 10px 0 white; }
  50% { box-shadow: 0 0 20px 10px white; }
  100% { box-shadow: 0 0 10px 0 white; }
`;

export const RocketWrapper = styled.div`
    position: absolute;
    z-index: 1;
    top: 35%;
    left: 12%;
    animation: ${floatAnimation} 2s ease-in-out infinite;
`;

export const StartBtnWrapper = styled.div`
    position: absolute;
    z-index: 1;
    bottom: 10%;
    left: 20%;
    border-radius: 80px;
    animation: ${shimmerAnimation} 2s ease-in-out infinite;
`;

export const Container = styled.div`
    position: relative;
    width: 390px;
    overflow: hidden;
`;
