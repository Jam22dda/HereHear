import { useQuery } from '@tanstack/react-query';
import { getAroundMusicList } from '../mapAPI';

const useGetAroundMusicList = (lat: number, lng: number) => {
    const { data: musicAroundList } = useQuery({
        queryKey: ['musicAroundList', lat, lng],
        // queryFn: getAroundMusicList,
        queryFn: () => getAroundMusicList(lat, lng),
        // enabled: false, // 쿼리를 비활성화하거나 활성화하는 플래그
    });

    return { musicAroundList };
};

export { useGetAroundMusicList };
