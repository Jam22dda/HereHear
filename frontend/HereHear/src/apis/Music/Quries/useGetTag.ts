import { useQuery } from "@tanstack/react-query";
import { getTag } from "../musicAPI";
// import { useDebounce } from "use-debounce";

const useGetTag = () => {
    // const [debouncedKeyword] = useDebounce(500); // 500ms 디바운스

    const { data: tag, error } = useQuery({
        queryKey: ["getTag"],
        queryFn: () => getTag(),
    });

    if (error) {
        console.error("Error fetching get Tag", error);
    }

    return { tag };
};

export { useGetTag };
