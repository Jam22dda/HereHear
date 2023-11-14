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
    display: flex;
    /* flex-direction: column; */

    /* img {
        width: 50px;
    } */
`;
export const giftOuter = styled.div`
    position: absolute;
    top: 100px;
    left: 20px;
    z-index: 996;
`;

export const NavbarWrapper = styled.div`
    display: flex;
    left: 0;
    width: 100%;
    justify-content: center;
    position: absolute;
    bottom: 115px;
    /* z-index: 996; */
    /* 기타 필요한 스타일링 */
`;
