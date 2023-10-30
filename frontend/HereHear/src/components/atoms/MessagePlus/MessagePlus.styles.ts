import styled from "styled-components";

const MessagePlus = styled.input`
    height: 149px;
    width: 303px;
    border-radius: 20px;
    background-color: ${({ theme }) => theme.color.white1};
    box-shadow: ${({ theme }) => theme.shadow.shadow_dark};
`;

export { MessagePlus };
