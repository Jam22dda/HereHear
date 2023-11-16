import { useQuery } from "@tanstack/react-query";
import { getYourRegistMusic } from "../yourpageApi";

const useGetYourRegistMusic = (yourId: number) => {
    const { data: YourRegistMusic } = useQuery({
        queryKey: ["YourRegistMusic"],
        queryFn: () => getYourRegistMusic(yourId),
    });
    return YourRegistMusic;
};

export { useGetYourRegistMusic };
