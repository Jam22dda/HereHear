import styled, { css } from "styled-components";
import { CircleButtonProps } from "./CircleButton";
import theme from "../../../styles/theme";

const getOptionStyling = (option: Required<CircleButtonProps>["option"], props: CircleButtonProps) => {
    const style = {
        default: css`
            background: ${({ theme }) => theme.color.white2};
            color: ${({ theme }) => theme.color.main1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_play1};
        `,
        pinkActivated: css`
            background: ${({ theme }) => theme.color.pink1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
        `,
        pinkDeActivated: css`
            background: ${({ theme }) => theme.color.pink1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
        `,
        gradActivated: css`
            background: ${({ theme }) => theme.gradient.gradient1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_heart};
        `,
        gradDeActivated: css`
            background: ${({ theme }) => theme.gradient.gradient1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_play1};
            font-weight: bold;
        `,
    };
    return props.$backgroundColor
        ? css`
              background: ${props.$backgroundColor};
          `
        : style[option];
};

const getSizeStyling = (size: Required<CircleButtonProps>["size"]) => {
    const style = {
        small: css`
            height: 40px;
            width: 40px;
            border-radius: 15px;
        `,
        medium: css`
            height: 40px;
            width: 40px;
            border-radius: 20px;
        `,
        mediumplus: css`
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
    color: ${(props) => (props.color ? props.theme.color[props.color] : theme.color.main1)};
    border-radius: ${(props) => props.$borderRadius || "24px"};
    ${({ size = "large" }) => getSizeStyling(size)};
    ${({ option = "default", ...props }) => getOptionStyling(option, props)};
    font-size: ${(props) => props.$fontSize || "20px"};
    background-color: ${(props) => props.$backgroundColor};
    box-shadow: ${(props) => props.$shadow};
`;

export { Button };