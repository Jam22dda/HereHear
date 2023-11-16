import styled from "styled-components";
export const MusicItemWrapper = styled.div`
    display: flex;
    justify-content: space-evenly;
    margin-bottom: 24px;
    width: 100%;
`;
// export const readyPageWrapper = styled.div`
//     display: flex;
//     flex-direction: column;
//     align-items: center;
//     height: 80vh;
//     justify-content: center;
// `;

export const ScrollWrapper = styled.div`
    height: calc(100vh - 250px);
    padding-bottom: 20px;
    overflow: auto;
    /* width: auto; */
`;

export const NavbarWrapper = styled.div`
    display: flex;
    left: 0;
    width: 100%;
    justify-content: center;
    position: fixed;
    bottom: 115px;
    z-index: 10px;

    /* 기타 필요한 스타일링 */
`;
