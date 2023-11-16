import { useQuery } from "@tanstack/react-query";
import { getListenStatistics } from "../statisticsApi";

const useGetListenStatistics = () => {
    const { data: ListenStatistics } = useQuery({
        queryKey: ["ListenStatistics"],
        queryFn: getListenStatistics,
    });
    return ListenStatistics;
};

export { useGetListenStatistics };
