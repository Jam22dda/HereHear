// import theme from "../../../styles/theme";
import styled from "styled-components";

const MusicBox = styled.div`
  height: 149px;
  width: 303px;
  border-radius 20px;
  background-color: ${({ theme }) => theme.color.white1};
  box-shadow: ${({ theme }) => theme.shadow.shadow_dark};
`;
const MusicTextWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-evenly;
  margin-left: 16px;
`;
export { MusicBox, MusicTextWrapper };
