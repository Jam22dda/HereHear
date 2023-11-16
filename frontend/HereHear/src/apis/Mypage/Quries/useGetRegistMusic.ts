import { useQuery } from "@tanstack/react-query";
import { getRegistMusic } from "../mypageAPI";

const useGetRegistMusic = () => {
    const { data: RegistMusic } = useQuery({
        queryKey: ["RegistMusic"],
        queryFn: getRegistMusic,
    });
    return RegistMusic;
};

export { useGetRegistMusic };
