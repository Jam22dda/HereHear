import styled, { css } from "styled-components";
import { ButtonProps } from "./Button";

const getOptionStyling = (option: Required<ButtonProps>["option"]) => {
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
            color: ${({ theme }) => theme.color.white1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_play2};
        `,
        unfollow: css`
            background: ${({ theme }) => theme.gradient.gradient1};
            color: ${({ theme }) => theme.color.main1};
            box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
        `,
    };
    return style[option];
};

const getSizeStyling = (size: Required<ButtonProps>["size"]) => {
    const style = {
        small: css`
            // 태그
            height: 20px;
            font-size: ${({ theme }) => theme.fontSize.small3};
            border-radius: 24px;
            border: none;
            line-height: 22px;
        `,
        medium: css`
            // 팔로우 팔로잉
            height: 32px;
            font-size: ${({ theme }) => theme.fontSize.small2};
            border-radius: 32px;
            border: none;
            line-height: 34px;
        `,
        mediumplus: css`
            // 큰 태그
            height: 36px;
            font-size: ${({ theme }) => theme.fontSize.small1};
            border-radius: 20px;
            border: none;
            line-height: 38px;
        `,
        large: css`
            // 저장하기, 태그추가
            height: 40px;
            font-size: ${({ theme }) => theme.fontSize.body2};
            font-weight: bold;
            border-radius: 28px;
            border: none;
            line-height: 42px;
        `,
        largeplus: css`
            // 마이페이지 팔로우 팔로잉
            height: 42px;
            font-size: ${({ theme }) => theme.fontSize.small1};
            border-radius: 30px;
            border: none;
            line-height: 44px;
        `,
    };
    return style[size];
};

const Button = styled.button<ButtonProps>`
    ${({ size = "large" }) => getSizeStyling(size)};
    ${({ option = "save" }) => getOptionStyling(option)};
    width: ${(props) => props.$width};
    height: ${(props) => props.$height};
    text-align: ${(props) => props.$textAlign || "center"};
    font-size: ${(props) => props.$fontSize};
    background-color: ${(props) => props.$backgroundColor};
    box-shadow: ${(props) => props.$shadow};
    margin: ${(props) => props.$margin};
    border-radius: ${(props) => props.$borderRadius}; //순서 중요
`;

export { Button };
