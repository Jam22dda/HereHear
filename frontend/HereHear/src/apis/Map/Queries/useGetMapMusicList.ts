import { useQuery } from '@tanstack/react-query';
import { getMusicList } from '../mapAPI';

const useGetMapMusicList = () => {
    const { data: UserInfo } = useQuery({
        queryKey: ['UserInfo'],
        queryFn: getMusicList,
    });

    // console.log('AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA');
    // console.log(UserInfo.length);
    // const arr = [];
    // for (let i = 0; i < UserInfo.length; i++) {
    //     // console.log(UserInfo[i]);
    //     arr[i] = UserInfo[i]
    // }

    return UserInfo;
};

export { useGetMapMusicList };
