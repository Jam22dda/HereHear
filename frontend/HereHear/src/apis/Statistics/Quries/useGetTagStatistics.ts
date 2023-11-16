import { useQuery } from "@tanstack/react-query";
import { getTagStatistics } from "../statisticsApi";

const useGetTagStatistics = () => {
    const { data: TagStatistics } = useQuery({
        queryKey: ["TagStatistics"],
        queryFn: getTagStatistics,
    });
    return TagStatistics;
};

export { useGetTagStatistics };
