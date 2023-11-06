import { useMutation } from "@tanstack/react-query";
import { postNickname } from "../mypageAPI";
import { changeNickname } from "../../../types/user";

interface postNicknameResponse {
    code: number;
    message: string;
}

const usePostNickname = () => {
    return useMutation<postNicknameResponse, Error, changeNickname>({
        mutationFn: postNickname, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
        onSuccess: (data) => {
            console.log("닉네임 변경!", data);
        },
        onError: (error: Error) => {
            console.error("닉네임 변경 실패...", error);
        },
    });
};

export { usePostNickname };
