import { useQuery } from "@tanstack/react-query";
import { getCheckNickname } from "../loginAPI";

const useGetCheckNickname = (nickname: string) => {
    return useQuery({
        queryKey: ["CheckNickname", nickname],
        queryFn: () => getCheckNickname(nickname),
        enabled: !!nickname, // nickname이 있을 때만 쿼리를 활성화합니다.
    });
};

export { useGetCheckNickname };
