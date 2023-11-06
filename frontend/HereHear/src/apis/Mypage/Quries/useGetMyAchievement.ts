import { useQuery } from "@tanstack/react-query";
import { getMyAchievement } from "../mypageAPI";

const useGetMyAchievement = (achievementId: number | undefined) => {
    const { data: MyAchievement } = useQuery({
        queryKey: ["MyAchievement"],
        queryFn: () => getMyAchievement(achievementId),
        enabled: !!achievementId,
    });
    return MyAchievement;
};

// const useGetMyAchievement = (achievementId: number | undefined) => {
//     return useQuery({
//         queryKey: ['MyAchievement', achievementId],
//         queryFn: () => getMyAchievement(achievementId),
//         enabled: !!achievementId, // achievementId가 truthy 값일 때만 쿼리를 활성화합니다.
//     });
// };

export { useGetMyAchievement };
