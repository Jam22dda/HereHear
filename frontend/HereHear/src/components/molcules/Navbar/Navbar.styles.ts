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

export const StyledNavBarBackground = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: ${({ theme }) => theme.color.nav};
    position: fixed;
    bottom: 24px;
    width: 340px;
    height: 88px;
    border-radius: 24px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
`;
