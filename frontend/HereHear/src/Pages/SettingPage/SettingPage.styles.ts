import styled from "styled-components";

export const SettingWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

export const PinBox = styled.button`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 300px;
    height: 70px;
    background-image: ${({ theme }) => theme.gradient.gradient1};
    box-shadow: ${({ theme }) => theme.shadow.shadow_itembox};
    border-radius: 25px;
    margin-bottom: 20px;
`;

export const SettingModalWrapper = styled.div`
    width: 250px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;
