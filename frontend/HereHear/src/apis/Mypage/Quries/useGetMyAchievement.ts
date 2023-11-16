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

const useGetYourAchievement = (achievementId: number | undefined) => {
    const { data: YourAchievement } = useQuery({
        queryKey: ["YourAchievement"],
        queryFn: () => getMyAchievement(achievementId),
        enabled: !!achievementId,
    });
    return YourAchievement;
};

export { useGetMyAchievement, useGetYourAchievement };
