import styled, { css } from "styled-components";

interface ItemBoxWrapperProps {
    isSelected: boolean;
}

const ItemBoxWrapper = styled.div<ItemBoxWrapperProps>`
    width: 128px;
    height: 128px;
    background: ${({ theme }) => theme.gradient.gradient1};
    border-radius: 30px;
    display: flex;
    flex-direction: column;
    justify-content: end;
    align-items: center;
    ${({ isSelected, theme }) =>
        isSelected
            ? css`
                  box-shadow: ${theme.shadow.shadow_itembox_selected};
              `
            : css`
                  box-shadow: ${theme.shadow.shadow_itembox};
              `}
`;

const ItemBoxTextWrapper = styled.div`
    width: 128px;
    height: 40px;
    background: ${({ theme }) => theme.gradient.gradient2};
    border-radius: 0 0 30px 30px;
    text-align: center;
    line-height: 40px;
`;

export { ItemBoxWrapper, ItemBoxTextWrapper };
