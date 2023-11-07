import { useQuery } from "@tanstack/react-query";
import { getMusicPlay } from "../musicAPI";
import { useDebounce } from "use-debounce";

const useGetMusicPlay = (registeredMusicId: number) => {
    const [debouncedMusic] = useDebounce(registeredMusicId, 500);
    const { data: musicPlay, error } = useQuery({
        queryKey: ["musicPlay", debouncedMusic],
        queryFn: () => getMusicPlay(debouncedMusic),
        enabled: !!debouncedMusic, // 불린값
    });

    if (error) {
        console.error("Error fetching search music", error);
    }

    return { musicPlay };
};

export { useGetMusicPlay };
