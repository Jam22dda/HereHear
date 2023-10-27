import styled from "styled-components";

const Input = styled.input`
    width: 292px;
    height: 44px;
    background-color: ${({ theme }) => theme.color.white1};
    border-radius: 14px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
`;

export { Input };
