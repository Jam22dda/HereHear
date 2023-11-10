import { useQuery } from "@tanstack/react-query";
import { getSearchMusic } from "../musicAPI";
import { useDebounce } from "use-debounce";

const useGetSearchMusic = (keyword: string, page: number) => {
    const [debouncedKeyword] = useDebounce(keyword, 500); // 500ms 디바운스

    const { data: searchMusic, error } = useQuery({
        queryKey: ["searchMusic", debouncedKeyword, page],
        queryFn: () => getSearchMusic(debouncedKeyword, page),
        enabled: debouncedKeyword.length > 0, // 키워드가 비어 있지 않을 때만 쿼리 실행
    });

    if (error) {
        // console.error("Error fetching search music", error);
    }

    return { searchMusic };
};

export { useGetSearchMusic };
