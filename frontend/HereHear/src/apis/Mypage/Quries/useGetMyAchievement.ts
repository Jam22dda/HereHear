import { useQuery } from "@tanstack/react-query";
import { getMyAchievement } from "../mypageAPI";

const useGetMyAchievement = (achievementId: number) => {
    const { data: MyAchievement } = useQuery({
        queryKey: ["MyAchievement"],
        queryFn: () => getMyAchievement(achievementId),
    });
    return MyAchievement;
};

export { useGetMyAchievement };
