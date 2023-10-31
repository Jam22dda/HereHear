import styled from "styled-components";

export const Profile = styled.div`
    position: relative;
    width: 200px;
    height: 200px;
    border-radius: 120px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    margin-top: 16px;
    display: flex;
    align-items: center;
    justify-content: center;
`;

export const OverlappingImageWrapper = styled.div`
    position: absolute;
    bottom: -10px; // 원하는 만큼 위치 조정
    right: 0; // 원하는 만큼 위치 조정
`;

export const MyPageWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
`;

export const MydataWrapper = styled.div`
    display: flex;
    margin-top: 12px;
`;

export const FollowWrapper = styled.div`
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    margin-top: 32px;
`;

export const MyItemWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 36px;
    justify-content: center;
    margin-top: 24px;
`;
