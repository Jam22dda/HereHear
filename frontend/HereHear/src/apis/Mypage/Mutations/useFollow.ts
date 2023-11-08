import { useMutation, useQueryClient } from "@tanstack/react-query";
import { Follow } from "../mypageAPI";

interface FollowResponse {
    code: number;
    message: string;
}

export type memberId = number;

const useFollow = () => {
    const queryClient = useQueryClient();
    return useMutation<FollowResponse, Error, memberId>({
        mutationFn: Follow, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
        onSuccess: (data) => {
            console.log("팔로우!", data);
            queryClient.invalidateQueries({ queryKey: ["Following"] });
            queryClient.invalidateQueries({ queryKey: ["Follower"] }); // invalidateQueries 함수에 직접적으로 문자열 배열을 전달하는 대신, 쿼리 키를 나타내는 객체를 전달
        },
        onError: (error: Error) => {
            console.error("팔로우 실패...", error);
        },
    });
};

const useFollow2 = () => {
    return useMutation<FollowResponse, Error, memberId>({
        mutationFn: Follow, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
        onSuccess: (data) => {
            console.log("팔로우!", data);
        },
        onError: (error: Error) => {
            console.error("팔로우 실패...", error);
        },
    });
};

export { useFollow, useFollow2 };
