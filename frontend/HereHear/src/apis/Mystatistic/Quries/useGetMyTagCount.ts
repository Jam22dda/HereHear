import { useQuery } from "@tanstack/react-query";
import { getMyTagCount } from "../myStatisticAPI";

const useGetMyTagCount = (userId?: number) => {
    const queryKey = userId ? ["MyTagCount", userId] : ["MyTagCount"];

    const { data: MyTagCount } = useQuery({
        queryKey: queryKey, // queryKey를 객체의 속성으로 정의
        queryFn: () => getMyTagCount(userId),
    });
    return MyTagCount;
};

export { useGetMyTagCount };
