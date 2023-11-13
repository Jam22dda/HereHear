import { useQuery } from "@tanstack/react-query";
import { getMyLikeCount } from "../myStatisticAPI";

const useGetMyLikeCount = () => {
    const { data: MyLickCount } = useQuery({
        queryKey: ["MyLikeCount"],
        queryFn: getMyLikeCount,
    });
    return MyLickCount;
};

export { useGetMyLikeCount };
