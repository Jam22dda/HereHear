import { useQuery } from "@tanstack/react-query";
import { getFollowing } from "../mypageAPI";

const useGetFollowing = () => {
    const { data: Following } = useQuery({
        queryKey: ["Following"],
        queryFn: getFollowing,
    });
    return Following;
};

export { useGetFollowing };
