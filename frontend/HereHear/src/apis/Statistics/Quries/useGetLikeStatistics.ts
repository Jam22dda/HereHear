import { useQuery } from "@tanstack/react-query";
import { getLikeStatistics } from "../statisticsApi";

const useGetLikeStatistics = () => {
    const { data: LikeStatistics } = useQuery({
        queryKey: ["LikeStatistics"],
        queryFn: getLikeStatistics,
    });
    return LikeStatistics;
};

export { useGetLikeStatistics };
