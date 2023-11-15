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

export const PlayerWrapper = styled.div`
    display: flex;
    margin: 10px 0 40px 0;
`;

// Styled component for the container of the progress bar
export const ProgressBarContainer = styled.div`
  width: 100%;
  background-color: #000000;
  border-radius: 10px;
  margin: 10px 0;
`;

// Styled component for the actual progress bar
export const ProgressBar = styled.div`
  height: 10px;
  background-color: #4caf50;
  border-radius: 10px;
  width: 20%;
  transition: width 0.4s ease;
`;
