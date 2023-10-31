import styled from "styled-components";

const Input = styled.input`
    width: 292px;
    height: 44px;
    padding-left: 12px;
    background-color: ${({ theme }) => theme.color.white1};
    border-radius: 14px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    margin-bottom: 40px;
`;

export { Input };
