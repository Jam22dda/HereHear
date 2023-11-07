import * as S from "./MessagePlus.styles";
import React, { forwardRef } from "react";
interface MessagePlusProps {
    $placeholder?: string;
}

const MessagePlus = forwardRef<HTMLTextAreaElement, MessagePlusProps>(({ $placeholder }, ref) => {
    return (
        <div>
            <S.MessagePlus name="message" id="message" placeholder={$placeholder} ref={ref} />
        </div>
    );
});

export default MessagePlus;
