import * as S from "./MessagePlus.styles";
import React, { forwardRef } from "react";
interface MessagePlusProps {
    $placeholder?: string;
    onMessageChange?: (message: string) => void;
}

const MessagePlus = forwardRef<HTMLTextAreaElement, MessagePlusProps>(({ $placeholder, onMessageChange }, ref) => {
    const handleTextChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
        const message = event.target.value;
        if (message.length > 100) {
            alert("멘트는 100자를 초과할 수 없습니다."); // 사용자에게 알림
            return; // 여기서 함수 실행을 멈추고 더 이상의 상태 변경을 방지
        }
        // 부모 컴포넌트로 변경된 메시지를 전달
        if (onMessageChange) onMessageChange(message);
    };

    return (
        <div>
            <S.MessagePlus name="message" id="message" placeholder={$placeholder} maxLength={100} onChange={handleTextChange} ref={ref} />
        </div>
    );
});

export default MessagePlus;
