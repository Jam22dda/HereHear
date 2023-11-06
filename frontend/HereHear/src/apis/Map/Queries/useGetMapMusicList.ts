import { useQuery } from "@tanstack/react-query";
import { getMusicList } from "../mapAPI";

const useGetMapMusicList = () => {
    const { data: MapMusicList } = useQuery({
        queryKey: ["MapMusicList"],
        queryFn: getMusicList,
    });

    return MapMusicList;
};

export { useGetMapMusicList };
