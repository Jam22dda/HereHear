import styled from "styled-components";

export const MusicPlayWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
    padding-bottom: 48px;
`;

export const SelectTagWrapper = styled.div`
    display: flex;
    margin: 10px 0 40px 0;
`;

export const PlayBtn = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
`;

export const ProgressBarWrapper = styled.div`
    width: 300px;
    height: 5px;
    background-color: #ddd;
    border-radius: 5px;
    cursor: pointer;
`;

interface ProgressBarProps {
    width: number;
}

export const ProgressBar = styled.div<ProgressBarProps>`
    height: 100%;
    background: ${({ theme }) => theme.gradient.gradient2};
    border-radius: 5px;
    width: ${(props) => props.width}%;
`;

export const ModalWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 10px;
`;
