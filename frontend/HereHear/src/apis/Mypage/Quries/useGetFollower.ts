import { useQuery } from "@tanstack/react-query";
import { getFollower } from "../mypageAPI";

const useGetFollower = () => {
    const { data: Follower } = useQuery({
        queryKey: ["Follower"],
        queryFn: getFollower,
    });
    return Follower;
};

export { useGetFollower };
