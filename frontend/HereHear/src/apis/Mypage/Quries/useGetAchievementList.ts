import { useQuery } from "@tanstack/react-query";
import { getAchievementList } from "../mypageAPI";

const useGetAchievementList = () => {
    const { data: AchievementList } = useQuery({
        queryKey: ["AchievementList"],
        queryFn: getAchievementList,
    });
    return AchievementList;
};

export { useGetAchievementList };
