import { createGlobalStyle } from "styled-components";

const GlobalStyle = createGlobalStyle`
    *{
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        color: ${({ theme }) =>
          theme.color.main1}; // 모든 text의 기본 색상은 main1
        font-family: 'GmarketSans';
    }
`;

export default GlobalStyle;
