import { useMutation } from '@tanstack/react-query';
import { AddMusicInfo } from '../../../types/music'; //API요청의 형태를 지정
import { addMusic } from '../musicAPI';

interface AddMusicResponse {
    // useMutation에서 사용되며, API 응답의 형태를 지정
    code: number;
    message: string;
    data: string;
}

const useAddMusic = () => {
    console.log('useAddMusic 탔다');

    return useMutation<AddMusicResponse, Error, AddMusicInfo>({
        mutationFn: addMusic,
        // onSuccess: (data) => {
        //     console.log("음악등록!", data);
        // },
        // onError: (error) => {
        //     console.log("음악 등록 실패...", error);
        // },
    });
};
export { useAddMusic };
