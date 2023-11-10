import { useQuery } from "@tanstack/react-query";
import { getYourFollowing } from "../yourpageApi";

const useGetYourFollowing = (yourId: number) => {
    const { data: YourFollowing } = useQuery({
        queryKey: ["YourFollowing"],
        queryFn: () => getYourFollowing(yourId),
    });
    return YourFollowing;
};

export { useGetYourFollowing };
