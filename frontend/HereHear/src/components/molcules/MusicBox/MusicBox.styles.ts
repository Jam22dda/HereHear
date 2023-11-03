// import theme from "../../../styles/theme";
import styled from 'styled-components';

const MusicBox = styled.div`
    height: 149px;
    width: 303px;
    border-radius: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: ${({ theme }) => theme.color.white1};
    box-shadow: ${({ theme }) => theme.shadow.shadow_dark};
    position: absolute;
    bottom: 150px;
    z-index: 998;

    /* @media screen and (max-width: 425px) {
        width: 303px;
    } */
`;

const BigWrapper = styled.div`
    display: flex;
    align-items: center;
    padding-left: 16px;
`;
const MidWrapper = styled.div`
    display: flex;
    flex-direction: column;
`;
const MapTextrapper = styled.div`
    display: flex;
    flex-direction: column;
`;

const MapMusicTagWrapper = styled.div`
    display: flex;
    /* flex-direction: column; */
`;

export { MusicBox, BigWrapper, MidWrapper, MapMusicTagWrapper, MapTextrapper };
