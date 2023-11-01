import React from "react";
import * as S from "./Input.styles";

interface InputProps {
    $placeholder?: string;
}

export default function Input({ $placeholder }: InputProps) {
    return (
        <div>
            <S.Input type="text" name="input" id="input" placeholder={$placeholder} />
        </div>
    );
}
