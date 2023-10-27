import styled from "styled-components";

const ItemBoxWrapper = styled.div`
    width: 128px;
    height: 128px;
    background: ${({ theme }) => theme.gradient.gradient1};
    border-radius: 30px;
    display: flex;
    flex-direction: column;
    justify-content: end;
    align-items: center;
    box-shadow: ${({ theme }) => theme.shadow.shadow_itembox};
`;

const ItemBoxTextWrapper = styled.div`
    width: 128px;
    height: 40px;
    background: ${({ theme }) => theme.gradient.gradient2};
    border-radius: 0 0 20px 20px;
    text-align: center;
    line-height: 40px;
`;

export { ItemBoxWrapper, ItemBoxTextWrapper };
