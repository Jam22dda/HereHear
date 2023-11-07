import { useMutation } from "@tanstack/react-query";
import { unFollow } from "../mypageAPI";

interface unFollowResponse {
    code: number;
    message: string;
}

const useUnFollow = () => {
    return useMutation<unFollowResponse, Error>({
        mutationFn: unFollow,
        onSuccess: () => {
            // 성공 시 실행될 함수
            console.log("언팔로우 성공!");
        },
        onError: (error: Error) => {
            // 에러 발생 시 실행될 함수
            console.log("언팔로우 실패:", error.message);
        },
    });
};

export { useUnFollow };
