import { useQuery } from "@tanstack/react-query";
import { getMyLikeCount } from "../myStatisticAPI";

const useGetMyLikeCount = (userId: number) => {
    const { data: MyLickCount } = useQuery({
        queryKey: ["MyLikeCount", userId],
        queryFn: () => getMyLikeCount(userId),
    });
    return MyLickCount;
};

export { useGetMyLikeCount };
