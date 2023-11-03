import { useQuery } from "@tanstack/react-query";
import { getYourinfo } from "../mypageAPI";

const useGetYourinfo = (userId: number) => {
    const { data: YourInfo } = useQuery({
        queryKey: ["YourInfo"],
        queryFn: () => getYourinfo(userId),
    });
    return YourInfo;
};

export { useGetYourinfo };
