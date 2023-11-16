import React from "react";
import * as S from "./Input.styles";

interface InputProps {
    $placeholder?: string;
    value?: string;
    onChange?: React.ChangeEventHandler<HTMLInputElement>;
    onKeyPress?: React.KeyboardEventHandler<HTMLInputElement>;
}

export default function Input({ $placeholder, value, onChange, onKeyPress }: InputProps) {
    return (
        <div>
            <S.Input type="text" name="input" id="input" placeholder={$placeholder} value={value} onChange={onChange} onKeyPress={onKeyPress} />
        </div>
    );
}
