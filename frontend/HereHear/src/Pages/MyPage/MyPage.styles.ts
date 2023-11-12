import styled from "styled-components";

export const Profile = styled.div`
    position: relative;
    width: 180px;
    height: 180px;
    border-radius: 120px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    margin: 0 0 8px 0;
    display: flex;
    align-items: center;
    justify-content: center;
    /* margin-top: 48px; // 2차배포때 삭제 */
`;

export const MyPageWrapper = styled.div`
    /* display: flex;
    flex-direction: column;
    align-items: center;
    height: 120vh;
    overflow: auto; */
    display: flex;
    flex-direction: column;
    align-items: center;
    height: auto; // 100vh 또는 120vh 대신 auto를 사용하여 내용에 따라 높이가 결정되도록 변경합니다.
    overflow: auto; // 스크롤이 필요한 경우 내부에만 적용됩니다.
    padding-bottom: 60px;
`;

export const MydataWrapper = styled.div`
    display: flex;
    margin-top: 12px;
    align-items: center;
`;

export const FollowWrapper = styled.div`
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    margin-top: 24px;
`;

export const MyItemWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 24px;
    justify-content: center;
    margin-top: 32px;
    padding: 10px 0 80px;
`;

export const EditWrapper = styled.button`
    background-color: transparent;
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

export const NavbarWrapper = styled.div`
    /* display: flex;
    left: 0;
    width: 100%;
    justify-content: center;
    position: absolute;
    bottom: 115px; */
    display: flex;
    left: 0;
    width: 100%;
    justify-content: center;
    position: fixed; // 이 부분을 fixed로 변경
    bottom: 115px; // bottom 값을 조절하여 네비게이션 바의 위치를 조정합니다.
`;
