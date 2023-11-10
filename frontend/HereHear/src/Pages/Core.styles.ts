import styled from "styled-components";

export const Map = styled.div`
    width: 100%;
    height: 100%;
`;

export const MapDisplay = styled.div`
    display: flex;
    position: relative;
    flex-wrap: wrap;
    justify-content: center;
    width: 100%;
    height: 100%;
`;

export const ImgOuter = styled.div`
    position: absolute;
    top: 20px;
    right: 20px;
    z-index: 996;

    img {
        width: 50px;
    }
`;

export const ClockOuter = styled.div`
    position: absolute;
    top: 20px;
    left: 20px;
    z-index: 996;

    /* img {
        width: 50px;
    } */
`;
