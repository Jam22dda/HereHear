//NavBar.styles.ts
import styled from "styled-components";

export const StyledNavBar = styled.div`
    display: flex;
    justify-content: space-around;
    align-items: center;
    background-color: transparent;
    position: fixed;
    bottom: 0;
    width: 340px;
    height: 70px;
`;

export const StyledNavBarBackground = styled.div`
    display: flex;
    justify-content: center;
    background-color: ${({ theme }) => theme.color.nav};
    position: fixed;
    bottom: 0;
    width: 340px;
    height: 88px;
    border-radius: 24px;
`;
