import { useQuery } from "@tanstack/react-query";
import { getYourLikeMusic } from "../yourpageApi";

const useGetYourLikeMusic = (yourId: number) => {
    const { data: YourLikeMusic } = useQuery({
        queryKey: ["YourLikeMusic"],
        queryFn: () => getYourLikeMusic(yourId),
    });
    return YourLikeMusic;
};

export { useGetYourLikeMusic };
