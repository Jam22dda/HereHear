import { useQuery } from "@tanstack/react-query";
import { getLikeMusic } from "../mypageAPI";

const useGetLikeMusic = () => {
    const { data: LikeMusic } = useQuery({
        queryKey: ["LikeMusic"],
        queryFn: getLikeMusic,
    });
    return LikeMusic;
};

export { useGetLikeMusic };
