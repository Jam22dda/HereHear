import styled from "styled-components";

const Message = styled.div`
    height: auto;
    width: 300px;
    border-radius: 30px;
    margin: 32px 0;
    display: flex;
    flex-direction: column;
    padding: 16px;
    background-color: ${({ theme }) => theme.color.Linear};
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
`;

const WriterWrapper = styled.div`
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
    align-items: flex-end;
`;
const userWrapper = styled.div`
    display: flex;
    align-items: center;
`;
export { Message, WriterWrapper, userWrapper };
