import styled, { css } from "styled-components";
import { CircleButtonProps } from "./CircleButton";
import theme from "../../../styles/theme";

const getOptionStyling = (
    option: Required<CircleButtonProps>["option"],
    props: CircleButtonProps
) => {
    const style = {
        default: css`
            background: ${({ theme }) => theme.color.white2};
            color: ${({ theme }) => theme.color.main1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_play1};
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        default2: css`
            background: ${({ theme }) => theme.color.white2};
            color: ${({ theme }) => theme.color.main1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        pinkActivated: css`
            background: ${({ theme }) => theme.color.pink1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        pinkDeActivated: css`
            background: ${({ theme }) => theme.color.pink1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        gradActivated: css`
            background: ${({ theme }) => theme.gradient.gradient1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_heart};
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        gradDeActivated: css`
            background: ${({ theme }) => theme.gradient.gradient1};
            color: ${({ theme }) => theme.color.pink2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_play2};
            font-weight: bold;
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        playBtn: css`
            background: ${({ theme }) => theme.gradient.gradient4};
            color: ${({ theme }) => theme.color.white1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_play2};
        `,
        playBtnOuter: css`
            background: ${({ theme }) => theme.gradient.gradient5};
            color: ${({ theme }) => theme.color.white1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_play2};
        `,
        playNextBtn: css`
            background: ${({ theme }) => theme.color.lightblue3};
            color: ${({ theme }) => theme.color.main1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_goback};
        `,
        follow: css`
            background: ${({ theme }) => theme.gradient.gradient2};
            box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
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
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        medium: css`
            height: 40px;
            width: 40px;
            border-radius: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        mediumplus: css`
            height: 45px;
            width: 45px;
            border-radius: 22.5px;
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        large: css`
            height: 50px;
            width: 50px;
            border-radius: 25px;
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        large60: css`
            height: 60px;
            width: 60px;
            border-radius: 30px;
            display: flex;
            justify-content: center;
            align-items: center;
        `,
        largePlus: css`
            height: 68px;
            width: 68px;
            border-radius: 34px;
            display: flex;
            justify-content: center;
            align-items: center;
        `,
    };
    return style[size];
};

const Button = styled.button<CircleButtonProps>`
    width: ${(props) => props.$width || "100%"};
    text-align: ${(props) => props.$textAlign || "center"};
    color: ${(props) =>
        props.color ? props.theme.color[props.color] : theme.color.main1};
    border-radius: ${(props) => props.$borderRadius || "24px"};
    ${({ size = "large" }) => getSizeStyling(size)};
    ${({ option = "default", ...props }) => getOptionStyling(option, props)};
    font-size: ${(props) => props.$fontSize || "20px"};
    background-color: ${(props) => props.$backgroundColor};
    box-shadow: ${(props) => props.$shadow};
`;

export { Button };
