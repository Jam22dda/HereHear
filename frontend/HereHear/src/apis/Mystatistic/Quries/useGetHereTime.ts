import { useQuery } from "@tanstack/react-query";
import { getHereTime } from "../myStatisticAPI";

const useGetHereTime = () => {
    const { data: MyLickCount } = useQuery({
        queryKey: ["MyHereTime"],
        queryFn: getHereTime,
    });
    return MyLickCount;
};

export { useGetHereTime };
