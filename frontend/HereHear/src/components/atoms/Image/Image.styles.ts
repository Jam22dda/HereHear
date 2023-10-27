import { ComponentPropsWithRef } from "react";
import styled from "styled-components";

export interface ImageProps extends ComponentPropsWithRef<"img"> {
    width?: number;
    height?: number;
    $margin?: string;
    $boxShadow?: boolean;
    $unit?: "rem" | "px" | "em" | "%";
    alignItems?: string;
}

const ImageConatiner = styled.img<ImageProps>`
    width: ${(props) => `${props.width}${props.$unit}` || "auto"};
    height: ${(props) => `${props.height}${props.$unit}` || "auto"};
    margin: ${(props) => props.$margin || "0"};
    &.invert {
        filter: invert(1);
    }
    box-shadow: ${(props) => (props.$boxShadow ? props.theme.shadow.shadow1 : "none")};
`;

export { ImageConatiner };
