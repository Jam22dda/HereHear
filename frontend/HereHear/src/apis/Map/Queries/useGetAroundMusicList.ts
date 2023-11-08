import { useMutation, useQuery, useQueryClient } from '@tanstack/react-query';
import { getAroundMusicList } from '../mapAPI';

// const useGetAroundMusicList = () => {
// const queryClient = useQueryClient();

// return useMutation<FollowResponse, Error, memberId>({
//     mutationFn: Follow, // 이 함수는 Promise<MutationResponse>를 반환해야 합니다.
//     onSuccess: (data) => {
//         console.log("팔로우!", data);
//         queryClient.invalidateQueries({ queryKey: ["Following"] });
//         queryClient.invalidateQueries({ queryKey: ["Follower"] }); // invalidateQueries 함수에 직접적으로 문자열 배열을 전달하는 대신, 쿼리 키를 나타내는 객체를 전달
//     },
//     onError: (error: Error) => {
//         console.error("주변 데이터 받아오기 실패...", error);
//     },
// });

const useGetAroundMusicList = (lat: number, lng: number) => {
    const { data: musicAroundList, refetch } = useQuery({
        queryKey: ['musicAroundList', lat, lng],
        // queryFn: getAroundMusicList,
        queryFn: () => getAroundMusicList(lat, lng),
        enabled: false, // 쿼리를 비활성화하거나 활성화하는 플래그
    });

    return { musicAroundList, refetch };
};

export { useGetAroundMusicList };
