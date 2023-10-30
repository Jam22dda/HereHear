//Landing.styles.ts
import styled from "styled-components";

export const LoginBtn = styled.button`
    border-radius: 30px;
    width: 288px;
    height: 88px;
    background-color: ${({ theme }) => theme.color.yellow};
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    position: fixed;
    bottom: 140px;
    display: flex;
    justify-content: center;
    align-items: center;
`;

export const LandingWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
`;
