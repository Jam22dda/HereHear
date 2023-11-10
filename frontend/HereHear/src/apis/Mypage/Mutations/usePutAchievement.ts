import { useMutation, useQueryClient } from '@tanstack/react-query';
import { putAchievement } from '../mypageAPI';
import { achievementId } from '../../../types/user';

interface putAchievementResponse {
    code: number;
    message: string;
}

const usePutAchievement = () => {
    const queryClient = useQueryClient();
    return useMutation<putAchievementResponse, Error, achievementId>({
        mutationFn: putAchievement, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
        onSuccess: data => {
            // console.log("닉네임 변경!", data);
            queryClient.invalidateQueries({ queryKey: ['MyAchievementList'] }); // invalidateQueries 함수에 직접적으로 문자열 배열을 전달하는 대신, 쿼리 키를 나타내는 객체를 전달
        },
        onError: (error: Error) => {
            // console.error("닉네임 변경 실패...", error);
        },
    });
};

export { usePutAchievement };
