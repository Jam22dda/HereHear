//NavBar.styles.ts
import styled from "styled-components";

// Nav안에 하나씩 묶은 wrapper
export const StyledNavBar = styled.div`
    display: flex;
    justify-content: space-around;
    align-items: center;
    /* background-color: transparent; */
    width: 340px;
    height: 88px;
`;

// 전체 Nav바 박스
export const StyledNavBarBackground = styled.div<{ active?: boolean }>`
    display: flex;
    justify-content: center;
    background-color: ${({ theme, active }) => (active !== undefined ? theme.color.white1 : theme.color.nav)};
    width: 340px;
    height: 88px;
    border-radius: 24px;

    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    position: absolute;
`;
