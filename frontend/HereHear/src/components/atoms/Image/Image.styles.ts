import { ComponentPropsWithRef } from "react";
import styled from "styled-components";

export interface ImageProps extends ComponentPropsWithRef<"img"> {
    width?: number;
    height?: number;
    $margin?: string;
    $boxShadow?: string;
    $unit?: "rem" | "px" | "em" | "%";
    alignItems?: string;
    $borderRadius?: string;
}

const ImageConatiner = styled.img<ImageProps>`
    width: ${(props) => `${props.width}${props.$unit}` || "auto"};
    height: ${(props) => `${props.height}${props.$unit}` || "auto"};
    margin: ${(props) => props.$margin || "0"};
    &.invert {
        filter: invert(1);
    }
    box-shadow: ${(props) => (props.$boxShadow ? props.theme.shadow[props.$boxShadow] : "none")};
    border-radius: ${(props) => props.$borderRadius};
`;

export { ImageConatiner };
