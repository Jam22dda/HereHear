import styled from "styled-components";

export const Profile = styled.button`
    width: 200px;
    height: 200px;
    border-radius: 120px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    margin-top: 16px;
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
