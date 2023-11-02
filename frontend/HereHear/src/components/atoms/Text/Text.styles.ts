import theme from "../../../styles/theme";
import styled, { css } from "styled-components";

export interface TextProps {
    size?: "heading1" | "subtitle1" | "body1" | "body2" | "small1" | "small2" | "small3";
    color?: keyof typeof theme.color;
    //width 단위는 픽셀
    width?: number;
    fontWeight?: "bold" | "medium" | "light";
    $margin?: string;
    $marginLeft?: string;
    $marginTop?: string;
}

const getSizeStyling = (size: Required<TextProps>["size"] = "heading1") => {
    const style = {
        heading1: css`
            font-size: ${({ theme }) => theme.fontSize.heading1};
        `,
        subtitle1: css`
            font-size: ${({ theme }) => theme.fontSize.subtitle1};
        `,
        body1: css`
            font-size: ${({ theme }) => theme.fontSize.body1};
        `,
        body2: css`
            font-size: ${({ theme }) => theme.fontSize.body2};
        `,
        small1: css`
            font-size: ${({ theme }) => theme.fontSize.small1};
        `,
        small2: css`
            font-size: ${({ theme }) => theme.fontSize.small2};
        `,
        small3: css`
            font-size: ${({ theme }) => theme.fontSize.small3};
        `,
    };
    return style[size];
};

const getFontWeightStyling = (weight?: TextProps["fontWeight"]) => {
    switch (weight) {
        case "bold":
            return "bold";
        case "light":
            return "300"; // 'light'의 경우 CSS에서 표준 값은 300
        default:
            return "500"; // 'medium'의 경우 CSS에서 표준 값은 500
    }
};

const Text = styled.p<TextProps>`
    margin: ${(props) => props.$margin || "0"};
    margin-left: ${(props) => props.$marginLeft};
    margin-top: ${(props) => props.$marginTop};

    ${({ size = "subtitle1" }) => getSizeStyling(size)};
    color: ${(props) => (props.color ? props.theme.color[props.color as keyof typeof theme.color] : props.theme.color.main1)};
    font-weight: ${(props) => getFontWeightStyling(props.fontWeight)};
`;

export { Text, getFontWeightStyling };
