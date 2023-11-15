import { useQuery } from "@tanstack/react-query";
import { getWearOs } from "../mypageAPI";

const useGetWearOs = () => {
    const { data: WearOsKey } = useQuery({
        queryKey: ["WearOsKey"],
        queryFn: getWearOs,
    });
    return WearOsKey;
};

export { useGetWearOs };
