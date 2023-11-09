// import theme from "../../../styles/theme";
import styled from 'styled-components';

const Outer = styled.div`
    width: 100%;
    height: 100%;
    /* background-color: red; */
    position: absolute;
    display: flex;
    justify-content: center;
`;
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
    bottom: 130px;
    z-index: 998;

    /* @media screen and (max-width: 425px) {
        width: 303px;
    } */
`;

const ImageLeftOuter = styled.div`
    position: absolute;
    top: 36%;
    left: -20px;
`;
const ImageRightOuter = styled.div`
    position: absolute;
    top: 36%;
    right: -20px;
`;

const MusicBoxInner = styled.div`
    display: flex;
    position: relative;
    width: 100%;
    height: 100%;
`;

const BigWrapper = styled.div`
    display: flex;
    align-items: center;
    padding: 16px;

    .album_img {
        margin-right: 12px;
    }
`;
const MidWrapper = styled.div`
    display: flex;
    flex-direction: column;
`;
const MapTextrapper = styled.div`
    display: flex;
    flex-direction: column;

    .ellipsis {
        width: 160px; /* 원하는 너비 설정 */
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    .marquee {
        width: 160px; /* 원하는 너비 설정 */
        overflow: hidden;
        white-space: nowrap;
        box-shadow: 0 0 1px #000;
    }
    .marquee_inner {
        display: inline-block;
        padding-left: 100%; /* 시작하기 전에 뷰포트 밖에서 대기 */
        animation: marquee 10s linear infinite; /* 10초 동안 애니메이션 반복 */
    }
    .static {
        position: relative;
        animation: none;
    }

    @keyframes marquee {
        0% {
            transform: translateX(0);
        }
        100% {
            transform: translateX(-100%);
        }
    }
`;

const MapMusicTagWrapper = styled.div`
    display: flex;
    /* flex-direction: column; */
`;

export { MusicBox, BigWrapper, MidWrapper, MapMusicTagWrapper, MapTextrapper, Outer, MusicBoxInner, ImageLeftOuter, ImageRightOuter };
