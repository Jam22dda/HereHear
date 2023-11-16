import styled from "styled-components";

export const MusicItemWrapper = styled.div`
    display: flex;
    padding-top: 15px;
    justify-content: space-between;
    width: 100%;
`;

export const InputWrapper = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%; // 컨테이너의 폭을 부모 요소의 100%로 설정
    margin: 20px 0;
`;

export const RegistMusicContainer = styled.div`
    /* 기존 스타일 */
    padding-bottom: 30px; // 여기에 padding-bottom 추가
`;

export const NoResultsText = styled.div`
    text-align: center;
    color: #757575; // 원하는 색상으로 변경
    margin-top: 20px; // 원하는 마진으로 변경
`;
