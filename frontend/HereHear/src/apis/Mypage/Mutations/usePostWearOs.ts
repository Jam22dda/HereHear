import { useMutation } from "@tanstack/react-query";
import { postWearOs } from "../mypageAPI";

interface postWearOsResponse {
    code: number;
    message: string;
    data: {
        personalCode: string;
    };
}

const usePostWearOs = () => {
    // const queryClient = useQueryClient();
    return useMutation<postWearOsResponse, Error>({
        mutationFn: postWearOs, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
        onSuccess: (data) => {
            console.log("핀번호 받기!");
            return data.data.personalCode;
            // queryClient.invalidateQueries({ queryKey: ["UserInfo"] }); // invalidateQueries 함수에 직접적으로 문자열 배열을 전달하는 대신, 쿼리 키를 나타내는 객체를 전달
        },
        onError: (error: Error) => {
            console.error("핀번호 받기 실패...", error);
        },
    });
};

export { usePostWearOs };
