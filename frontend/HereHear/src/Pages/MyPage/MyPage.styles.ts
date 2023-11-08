import styled from "styled-components";

export const Profile = styled.div`
    position: relative;
    width: 200px;
    height: 200px;
    border-radius: 120px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    margin: 16px 0 8px 0;
    display: flex;
    align-items: center;
    justify-content: center;
`;

export const MyPageWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
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
    margin-top: 32px;
`;

export const MyItemWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 24px;
    justify-content: center;
    margin-top: 48px;
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
