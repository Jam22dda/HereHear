import styled, { css } from "styled-components";
import { CircleButtonProps } from "./CircleButton";
// import theme from "@/styles/theme";

const getOptionStyling = (
  option: Required<CircleButtonProps>["option"],
  props: CircleButtonProps
) => {
  const style = {
    default: css`
      background: ${({ theme }) => theme.color.white3};
      color: ${({ theme }) => theme.color.blue};
      border: none;
    `,
    pinkActivated: css`
      background: ${({ theme }) => theme.color.blue};
      color: ${({ theme }) => theme.color.white};
      border: none;
    `,
    pinkDeActivated: css`
      background: ${({ theme }) => theme.color.grey5};
      border: none;
      color: ${({ theme }) => theme.color.grey2};
    `,
    GradActivated: css`
      background: ${({ theme }) => theme.color.white};
      border: none;
      color: ${({ theme }) => theme.color.grey1};
    `,
    GradDeActivated: css`
      background: ${({ theme }) => theme.color.danger};
      border: none;
      color: ${({ theme }) => theme.color.white};
    `,
    //TODO: 활성화 이걸로 사용해보기..
    keypad: css`
      background: ${({ theme }) => theme.color.grey5};
      color: ${({ theme }) => theme.color.grey2};
      font-weight: bold;
      font-size: ${({ theme }) => theme.fontSize.body1};

      &:active {
        background: ${({ theme }) => theme.color.blue};
        color: ${({ theme }) => theme.color.white};

        .invert {
          filter: invert(1);
        }
      }
    `,
  };
  return props.$backgroundColor
    ? css`
        background: ${props.$backgroundColor};
      `
    // : style[option];
};

const getSizeStyling = (size: Required<CircleButtonProps>["size"]) => {
  const style = {
    small: css`
      height: 40px;
      width: 40px;
      border-radius: 15px;
    `,
    medium1: css`
      height: 40px;
      width: 40px;
      border-radius: 20px;
    `,
    medium2: css`
      height: 45px;
      width: 45px;
      border-radius: 22.5px;
    `,
    large: css`
      height: 50px;
      width: 50px;
      border-radius: 25px;
    `,
  };
  return style[size];
};

const Button = styled.button<CircleButtonProps>`
  width: ${(props) => props.$width || "100%"};
  text-align: ${(props) => props.$textAlign || "center"};
  color: ${(props) =>
    props.color ? props.theme.color[props.color] : theme.color.black1};
  border: ${(props) =>
    props.$border ||
    `1.5px solid ${props.$borderColor || props.theme.color.blue}`};
  border-radius: ${(props) => props.$borderRadius || "23px"};
  ${({ size = "large" }) => getSizeStyling(size)};
  ${({ option = "default", ...props }) => getOptionStyling(option, props)};
  font-size: ${(props) => props.$fontSize || "20px"};
  background-color: ${(props) => props.$backgroundColor};
`;

export { Button };
