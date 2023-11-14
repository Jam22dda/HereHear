import { useQuery } from "@tanstack/react-query";
import { getMyTagCount } from "../myStatisticAPI";

const useGetMyTagCount = () => {
    const { data: MyTagCount } = useQuery({
        queryKey: ["MyTagCount"],
        queryFn: getMyTagCount,
    });
    return MyTagCount;
};

export { useGetMyTagCount };
