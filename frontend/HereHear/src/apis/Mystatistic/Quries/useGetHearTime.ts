import { useQuery } from "@tanstack/react-query";
import { getHearTime } from "../myStatisticAPI";

const useGetHearTime = (userId: number) => {
    const { data: MyHearTime } = useQuery({
        queryKey: ["MyHearTime", userId],
        queryFn: () => getHearTime(userId),
    });
    return MyHearTime;
};

export { useGetHearTime };
