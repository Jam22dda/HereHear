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
