import React from "react";

// // 자식 컴포넌트의 DOM 요소에 접근하려 할 때 forwardRef 사용
// // 부모 컴포넌트로부터 전달받은 ref를 자식 컴포넌트의 DOM 요소에 연결
import { forwardRef } from "react";
// import type { ForwardedRef } from "react";

// //  *는 해당 모듈에서 export된 모든 것들
import * as S from "./Button.styles";

export interface ButtonProps extends React.ButtonHTMLAttributes<HTMLButtonElement> {
    option?:
        | "tag1" // 음악 페이지 태그
        | "tag_selected" // 태그 페이지 선택된 태그
        | "tag_unselected" // 태그 페이지 선택되지 않은 태그
        | "tag_plus" // 태그 추가 버튼
        | "save" // 저장하기
        | "follow" // 팔로우
        | "unfollow"; // 언팔로우
    size?: "small" | "medium" | "mediumplus" | "large" | "largeplus";
    $fontSize?: string;
    $backgroundColor?: string;
    $borderRadius?: string;
    $textAlign?: string; // 기본으로 center으로하면 상관없?
    $color?: string;
    $width?: string;
    $hight?: string;
    $shadow?: string;
    $margin?: string;
    tag?: string | null;
}

const Button = (
    { option, size, $backgroundColor, $borderRadius, $color, $width, $hight, $shadow, $margin, tag, children, ...attributes }: ButtonProps,
    ref: React.ForwardedRef<HTMLButtonElement>
) => {
    return (
        <S.Button
            ref={ref}
            size={size}
            option={option}
            $backgroundColor={$backgroundColor}
            $color={$color}
            $borderRadius={$borderRadius}
            $width={$width}
            $hight={$hight}
            $shadow={$shadow}
            $margin={$margin}
            {...attributes}
        >
            {children}
            {tag} {/* 태그를 버튼 내용으로 출력 */}
        </S.Button>
    );
};

export default forwardRef<HTMLButtonElement, ButtonProps>(Button);
