import { useQuery } from "@tanstack/react-query";
import { getMusicList } from "../mapAPI";

const useGetMapMusicList = () => {
    const { data: MusicList } = useQuery({
        queryKey: ['MusicList'],
        queryFn: getMusicList,
    });

    return { MusicList };
};

export { useGetMapMusicList };
