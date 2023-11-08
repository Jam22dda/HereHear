import { useMutation, useQueryClient } from "@tanstack/react-query";
import { unFollow } from "../mypageAPI";

interface unFollowResponse {
    code: number;
    message: string;
}

interface UnFollowRequest {
    memberId: number;
}

const useUnFollow = () => {
    const queryClient = useQueryClient();
    return useMutation<unFollowResponse, Error, UnFollowRequest>({
        mutationFn: (UnFollowRequest) => unFollow(UnFollowRequest.memberId),
        onSuccess: () => {
            // 성공 시 실행될 함수
            console.log("언팔로우 성공!");
            queryClient.invalidateQueries({ queryKey: ["Follower"] });
            queryClient.invalidateQueries({ queryKey: ["Following"] });
        },
        onError: (error: Error) => {
            // 에러 발생 시 실행될 함수
            console.log("언팔로우 실패:", error.message);
        },
    });
};

export { useUnFollow };
