import styled from "styled-components";

const MessagePlus = styled.textarea`
    /* height: 30vw;
    width: 80vw; */
    height: 40vw; /* 2차 배포 때 수정 */
    width: 70vw; /* 2차 배포 때 수정 */
    border-radius: 20px;
    margin: 10px 0 10px 0;
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
        box-shadow: ${({ theme }) =>
            theme.shadow.shadow_smallbtn}; // 클릭 시 원하는 box-shadow를 설정
    }

    // 미디어 쿼리를 추가하여 웹에서의 크기 조정
    @media (min-width: 768px) {
        height: 150px; // 웹에서의 textarea 높이를 조정합니다.
        width: 500px; // 웹에서의 textarea 너비를 조정합니다.
    }
`;

export { MessagePlus };
