import { useQuery } from "@tanstack/react-query";
import { getSearchMusic } from "../musicAPI";
// import { SearchMusicResult } from "../../../types/music";

const useGetSearchMusic = (keyword: string, page: number) => {
    //  useQuery의 첫 번째 인자는 쿼리 키를 나타내는 배열, 두 번째 인자는 쿼리 함수
    const { data: searchMusic, error } = useQuery({
        queryKey: ["searchMusic", keyword, page],
        queryFn: () => getSearchMusic(keyword, page),
    });

    // error는 쿼리가 실패했을 때의 에러 객체를 담고 있으며, 이를 확인하여 필요한 에러 핸들링 가능
    if (error) {
        console.error("Error fetching search music", error);
    }

    return { searchMusic };
};

export { useGetSearchMusic };
