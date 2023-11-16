//Landing.styles.ts
import styled from "styled-components";

export const PwaBtn = styled.button`
    border-radius: 30px;
    width: 288px;
    height: 78px;
    background-color: ${({ theme }) => theme.color.pink3};
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    position: fixed;
    bottom: 250px;
    display: flex;
    justify-content: center;
    align-items: center;
`;

export const KaKaoLoginBtn = styled.button`
    border-radius: 30px;
    width: 50px;
    height: 50px;
    background-color: transparent;
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    /* position: fixed;
    bottom: 140px;*/
    display: flex;
    justify-content: center;
    align-items: center;
`;

export const GoogleLoginBtn = styled.button`
    border-radius: 30px;
    width: 50px;
    height: 50px;
    background-color: ${({ theme }) => theme.color.white};
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    /* position: fixed;
    bottom: 140px;*/
    display: flex;
    justify-content: center;
    align-items: center;
`;

export const SpotifyLoginBtn = styled.button`
    border-radius: 30px;
    width: 50px;
    height: 50px;
    background-color: ${({ theme }) => theme.color.white};
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    /* position: fixed;
    bottom: 140px;*/
    display: flex;
    justify-content: center;
    align-items: center;
`;

export const LandingWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
`;

export const LoginWrapper = styled.div`
    display: flex;
    width: 200px;
    justify-content: space-between;
    position: fixed;
    bottom: 140px;
`;
