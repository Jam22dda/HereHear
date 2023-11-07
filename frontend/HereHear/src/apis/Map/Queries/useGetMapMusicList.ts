import { useQuery } from '@tanstack/react-query';
import { getMusicList } from '../mapAPI';

const useGetMapMusicList = () => {
    const { data: musicList, refetch } = useQuery({
        queryKey: ['MusicList'],
        queryFn: getMusicList,
        enabled: false,
    });

    return { musicList, refetch };
};

export { useGetMapMusicList };
