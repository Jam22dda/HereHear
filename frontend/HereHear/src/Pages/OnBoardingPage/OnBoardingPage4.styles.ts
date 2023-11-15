import styled, { keyframes } from "styled-components";

const floatAnimation = keyframes`
  0% { transform: translateY(0px); }
  50% { transform: translateY(-20px); }
  100% { transform: translateY(0px); }
`;

export const TagsWrapper = styled.div`
    position: absolute;
    z-index: 1;
    top: 33%;
    left: 5%;
    animation: ${floatAnimation} 2s ease-in-out infinite;
`;

export const Container = styled.div`
    position: relative;
    width: 390px;
    overflow: hidden;
`;
