import { useQuery } from "@tanstack/react-query";
import { getHearTime } from "../myStatisticAPI";

const useGetHearTime = () => {
    const { data: MyHearTime } = useQuery({
        queryKey: ["MyHearTime"],
        queryFn: getHearTime,
    });
    return MyHearTime;
};

export { useGetHearTime };
