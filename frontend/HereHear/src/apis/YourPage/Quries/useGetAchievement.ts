import { useQuery } from "@tanstack/react-query";
import { getYourAchievement } from "../yourpageApi";

const useGetYourAchievement = (yourId: number) => {
    const { data: YourAchievement } = useQuery({
        queryKey: ["YourAchievement"],
        queryFn: () => getYourAchievement(yourId),
    });
    return YourAchievement;
};

export { useGetYourAchievement };
