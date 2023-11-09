import styled from "styled-components";

export const CharacterPageWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
`;

export const CharacterWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 36px;
    justify-content: center;
    margin-top: 28px;
    width: 86%; /* 2차 배포 때 삭제 */
`;

export const CharacterModalWrapper = styled.div`
    display: flex;
    justify-content: end;
`;
