// import React from "react";

// // 자식 컴포넌트의 DOM 요소에 접근하려 할 때 forwardRef 사용
// // 부모 컴포넌트로부터 전달받은 ref를 자식 컴포넌트의 DOM 요소에 연결
import { forwardRef } from "react";
import type { ForwardedRef } from "react";

// //  *는 해당 모듈에서 export된 모든 것들
import * as S from "./CircleButton.styles";

export interface CircleButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
    option?: "default" | "default2" | "pinkActivated" | "pinkDeActivated" | "gradActivated" | "gradDeActivated" | "playNextBtn" | "playBtn" | "playBtnOuter";
    size?: "small" | "medium" | "mediumplus" | "large" | "largePlus" | "large60";

    $fontSize?: string;
    $backgroundColor?: string;
    $borderColor?: string;
    $border?: string;
    $borderRadius?: string;
    $textAlign?: string; // 기본으로 center으로하면 상관없?
    $color?: string;
    $width?: string;
    $shadow?: string;
}

const CircleButton = (
    { option, size, $backgroundColor, $borderColor, $borderRadius, $border, $color, $width, children, ...attributes }: CircleButtonProps,
    ref: ForwardedRef<HTMLButtonElement>
) => {
    return (
        <S.Button
            ref={ref}
            size={size}
            option={option}
            $border={$border}
            $backgroundColor={$backgroundColor}
            $borderColor={$borderColor}
            $color={$color}
            $borderRadius={$borderRadius}
            $width={$width}
            {...attributes}
        >
            {children}
        </S.Button>
    );
};

export default forwardRef(CircleButton);
