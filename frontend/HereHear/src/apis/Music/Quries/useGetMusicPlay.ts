import { useQuery } from "@tanstack/react-query";
import { getMusicPlay } from "../musicAPI";
import { useDebounce } from "use-debounce";

const useGetMusicPlay = (registeredMusicId: number | null) => {
    const [debouncedMusicId] = useDebounce(registeredMusicId, 500);

    const {
        data: musicPlay,
        error,
        isLoading,
    } = useQuery({
        queryKey: ["musicPlay", debouncedMusicId],
        queryFn: () => getMusicPlay(debouncedMusicId!),
        enabled: debouncedMusicId !== null && typeof debouncedMusicId === "number",
    });

    if (error) {
        console.error("Error fetching search music", error);
        return { musicPlay: undefined, isLoading, isError: true };
    }

    return { musicPlay, isLoading, isError: !!error };
};

export { useGetMusicPlay };
