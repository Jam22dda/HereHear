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

export const YourPageWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
`;

export const YourdataWrapper = styled.div`
    display: flex;
    margin-top: 12px;
    align-items: center;
`;

export const FollowWrapper = styled.div`
    display: flex;
    width: 100%;
    justify-content: space-evenly;
    margin-top: 28px;
`;

export const YourItemWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 32px;
    justify-content: center;
    margin-top: 36px;
`;

export const FollowBtnWrapper = styled.div`
    display: flex;
    justify-content: space-between;
`;
