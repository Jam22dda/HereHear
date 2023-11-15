import styled from "styled-components";

export const Map = styled.div`
    width: 100%;
    height: 100%;
    overflow: hidden;
`;

export const MapDisplay = styled.div`
    display: flex;
    position: relative;
    flex-wrap: wrap;
    justify-content: center;
    width: 100%;
    height: 100%;
    overflow: hidden;
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

export const questionMark = styled.div`
    position: absolute;
    top: 180px;
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

export const WaitWrapper = styled.div`
    position: absolute;
    width: 100%;
    height: 100%;
    z-index: 999;
    background-color: black;

    background-image: url("../public//images//icon-Background.png");
    background-size: 400% 400%;
    background-position: 50% 40%;
    background-repeat: no-repeat;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    img {
        width: 70px;
        margin: 25px;
    }
    p {
        color: white;
    }
    .floating {
        animation-name: floating;
        animation-duration: 0.7s;
        animation-iteration-count: infinite;
        animation-timing-function: ease-in-out;
    }

    @keyframes floating {
        0% {
            transform: translate(0, 0px);
        }
        50% {
            transform: translate(0px, -15px);
        }
        100% {
            transform: translate(0, -0px);
        }
    }
`;
