import styled from "styled-components";

export const RegistMusicWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
    padding-bottom: 48px;
`;

export const SelectTagWrapper = styled.div`
    display: flex;
    margin: 10px;
`;

export const BgWrapper = styled.div`
    display: flex; /* Flexbox 컨테이너 설정 */
    align-items: center; /* 세로 방향 중앙 정렬 */
    justify-content: center; /* 가로 방향 중앙 정렬 */
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 10; /* 다른 콘텐츠 위에 오도록 설정 */
`;
