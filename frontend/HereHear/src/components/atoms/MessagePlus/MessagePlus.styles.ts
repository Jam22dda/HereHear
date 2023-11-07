import styled from "styled-components";

const MessagePlus = styled.textarea`
    height: 30vw;
    width: 80vw;
    border-radius: 20px;
    margin-bottom: 10px;
    background-color: ${({ theme }) => theme.color.white1};
    box-shadow: ${({ theme }) => theme.shadow.shadow_itembox};
    resize: none; // 사용자가 크기를 조정하지 못하게 합니다.
    border: none; // 테두리를 없앱니다.
    padding: 12px; // 상단 왼쪽 패딩을 추가합니다.
    &::placeholder {
        color: ${({ theme }) => theme.color.grey2};
        text-align: left;
        padding-left: 5px;
    }

    &:focus {
        /* TODO:박스 테두리 배경 수정 후 다시 손보기 */
        outline: none;
        box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn}; // 클릭 시 원하는 box-shadow를 설정
    }
`;

export { MessagePlus };
