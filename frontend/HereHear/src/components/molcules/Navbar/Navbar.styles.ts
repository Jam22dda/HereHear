//NavBar.styles.ts
import styled from "styled-components";

export const StyledNavBar = styled.div`
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: transparent;
    width: 340px;
    height: 88px;
`;

export const StyledNavBarBackground = styled.div<{ active?: boolean }>`
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: ${({ theme, active }) =>
        active !== undefined ? theme.color.white1 : theme.color.nav};
    position: fixed;
    width: 340px;
    height: 88px;
    border-radius: 24px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};

    position: absolute;
    bottom: 24px;
    z-index: 997;
`;
