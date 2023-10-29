import styled from "styled-components";

const Message = styled.input`
  height: 135px;
  width: 440px;
  border-radius 30px;
  background-color: ${({ theme }) => theme.color.Linear};
  box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
`;

export { Message };
