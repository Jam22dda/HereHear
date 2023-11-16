import styled from "styled-components";

const TagSelectWrapper = styled.div`
    height: 660px;
    width: 317px;
    border-radius: 29px;
    display: flex;
    flex-direction: column;
    padding: 20px;
    background-color: ${({ theme }) => theme.color.white1};
    box-shadow: ${({ theme }) => theme.shadow.shadow_heart};
`;

const TagTopWrapper = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
`;

const TagBtnWrapper = styled.div`
    display: flex;
    flex-wrap: wrap;

    justify-content: flex-start;
    margin-bottom: 36px;
    gap: 15px;
`;

export { TagSelectWrapper, TagTopWrapper, TagBtnWrapper };
