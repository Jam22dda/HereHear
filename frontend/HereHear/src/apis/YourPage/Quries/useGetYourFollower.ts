import { useQuery } from "@tanstack/react-query";
import { getYourFollower } from "../yourpageApi";

const useGetYourFollower = (yourId: number) => {
    const { data: YourFollower } = useQuery({
        queryKey: ["YourFollower"],
        queryFn: () => getYourFollower(yourId),
    });
    return YourFollower;
};

export { useGetYourFollower };
