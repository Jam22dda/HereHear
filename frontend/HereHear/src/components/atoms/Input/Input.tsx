import React from "react";
import * as S from "./Input.styles";

interface InputProps {
    $placeholder?: string;
    value?: string;
    onChange?: React.ChangeEventHandler<HTMLInputElement>;
}

export default function Input({ $placeholder, value, onChange }: InputProps) {
    return (
        <div>
            <S.Input
                type="text"
                name="input"
                id="input"
                placeholder={$placeholder}
                value={value}
                onChange={onChange}
            />
        </div>
    );
}
