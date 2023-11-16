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
            console.log("언팔로우 성공!");
            queryClient.invalidateQueries({ queryKey: ["Follower"] });
        },
        onError: (error: Error) => {
            console.log("언팔로우 실패:", error.message);
        },
    });
};

export { useUnFollow };
