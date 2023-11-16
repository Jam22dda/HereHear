import { useQuery } from "@tanstack/react-query";
import { getListenedMusic } from "../musicAPI";

const useGetListenedMusic = () => {
    const { data: ListenedMusic } = useQuery({
        queryKey: ["ListenedMusic"],
        queryFn: getListenedMusic,
    });
    return ListenedMusic;
};

export { useGetListenedMusic };
