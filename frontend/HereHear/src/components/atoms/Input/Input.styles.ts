import styled from "styled-components";

const Input = styled.input`
    width: 280px;
    height: 44px;
    padding-left: 12px;
    background-color: ${({ theme }) => theme.color.white1};
    border-radius: 14px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};

    outline: none; // 브라우저 기본 아웃라인 스타일 제거
    &:focus {
        box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    }
    &::placeholder {
        color: ${({ theme }) => theme.color.grey2};
        text-align: right;
        padding: 0 16px;
    }
`;

export { Input };
