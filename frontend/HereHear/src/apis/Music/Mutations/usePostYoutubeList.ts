import { useMutation } from "@tanstack/react-query";
import { postYoutubeList } from "../musicAPI";
import { youtubeMusicRequest } from "../../../types/music";

interface YoutubeListResponse {
    code: number;
    message: string;
}

const usePostYoutubeList = () => {
    return useMutation<YoutubeListResponse, Error, youtubeMusicRequest>({
        mutationFn: postYoutubeList, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
        onSuccess: (data) => {
            console.log("유튜브 리스트 추가!", data);
        },
        onError: (error: Error) => {
            console.error("유튜브 리스트 추가 실패...", error);
        },
    });
};

export { usePostYoutubeList };
