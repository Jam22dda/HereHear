import { useMutation } from '@tanstack/react-query';
import { postMusicHistory } from '../musicAPI';
import { registeredMusicId } from '../../../types/music';

interface postMusicHistoryResponse {
    code: number;
    message: string;
}

const useMusicHistory = () => {
    // const queryClient = useQueryClient();
    return useMutation<postMusicHistoryResponse, Error, registeredMusicId>({
        mutationFn: postMusicHistory, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
        onSuccess: (data) => {
            console.log("좋아요!", data);
            // queryClient.invalidateQueries({ queryKey: ["LikeMusic"] }); // invalidateQueries 함수에 직접적으로 문자열 배열을 전달하는 대신, 쿼리 키를 나타내는 객체를 전달
        },
        onError: (error: Error) => {
            console.error("좋아요 실패", error);
        },
    });
};

export { useMusicHistory };
