import styled, { css } from "styled-components";

export const AchievementPageWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    min-height: 117vw;
`;

export const AchievementWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 40px;
    justify-content: center;
    /* min-height: 117vw; */
`;

export const ExitWrapper = styled.div`
    display: flex;
    justify-content: end;
`;

export const TextWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-top: 12px;
`;

type PaginationButtonProps = {
    active?: boolean;
    disabled?: boolean;
};

export const PaginationButton = styled.button<PaginationButtonProps>`
    padding: 5px 10px;
    margin: 0 5px;
    background-color: transparent;
    color: #333;
    text-align: center;
    cursor: pointer;
    border-radius: 10px;
    outline: none;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 32px;
    height: 32px;
    line-height: 32px;

    ${(props) =>
        props.disabled &&
        css`
            color: #bdbdbd;
            cursor: not-allowed;
        `}

    ${(props) =>
        props.active &&
        css`
            background-color: #667eea;
            color: white;
            border-color: #667eea;
            border: 2px solid white;
        `}
`;

// 페이지네이션 컨테이너 스타일
export const PaginationContainer = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 40px 0 0 0;
`;
