import styled, { css } from "styled-components";
import { ButtonProps } from "./Button";
import theme from "../../../styles/theme";

const getOptionStyling = (
  option: Required<ButtonProps>["option"],
  props: ButtonProps
) => {
  const style = {
    tag1: css`
      background: ${({ theme }) => theme.gradient.gradient2};
      color: ${({ theme }) => theme.color.main1};
      box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    `,
    tag_selected: css`
      background: ${({ theme }) => theme.gradient.gradient4};
      color: ${({ theme }) => theme.color.white1};
      box-shadow: ${({ theme }) => theme.shadow.shadow_play1};
    `,
    tag_unselected: css`
      background: ${({ theme }) => theme.gradient.gradient1};
      color: ${({ theme }) => theme.color.main1};
      box-shadow: ${({ theme }) => theme.shadow.shadow_play1};
    `,
    tag_plus: css`
      background: ${({ theme }) => theme.color.white1};
      color: ${({ theme }) => theme.color.main1};
      box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    `,
    save: css`
      background: ${({ theme }) => theme.gradient.gradient4};
      color: ${({ theme }) => theme.color.white1};
      box-shadow: ${({ theme }) => theme.shadow.shadow_play2};
      font-weight: bold;
    `,
    follow: css`
      background: ${({ theme }) => theme.gradient.gradient4};
      color: ${({ theme }) => theme.color.white};
      box-shadow: ${({ theme }) => theme.shadow.shadow_play2};
    `,
    unfollow: css`
      background: ${({ theme }) => theme.gradient.gradient1};
      color: ${({ theme }) => theme.color.main1};
      box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
    `,
  };
  return props.$backgroundColor
    ? css`
        background: ${props.$backgroundColor};
      `
    : style[option];
};

const getSizeStyling = (size: Required<ButtonProps>["size"]) => {
  const style = {
    small: css`
      height: 20px;
      font-size: ${({ theme }) => theme.fontSize.small3};
      border-radius: 24px;
    `,
    medium: css`
      height: 32px;
      font-size: ${({ theme }) => theme.fontSize.small2};
      border-radius: 32px;
    `,
    mediumplus: css`
    height: 36px;
    font-size: ${({ theme }) => theme.fontSize.small1};
    border-radius: 20px;
  `,
    large: css`
      height: 40px;
      font-size: ${({ theme }) => theme.fontSize.body2};
      font-weight: bold;
      border-radius: 28px;
    `,
  };
  return style[size];
};

const Button = styled.button<ButtonProps>`
  width: ${(props) => props.$width || "100%"};
  text-align: ${(props) => props.$textAlign || "center"};
  color: ${(props) =>
    props.color ? props.theme.color[props.color] : theme.color.main1};
  border-radius: ${(props) => props.$borderRadius || "24px"};
  ${({ size = "large" }) => getSizeStyling(size)};
  ${({ option = "save", ...props }) => getOptionStyling(option, props)};
  font-size: ${(props) => props.$fontSize || "20px"};
  background-color: ${(props) => props.$backgroundColor};
  box-shadow: ${(props) => props.$shadow};
`;

export { Button };
