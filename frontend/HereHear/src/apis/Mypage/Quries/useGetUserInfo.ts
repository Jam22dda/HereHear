import { useQuery } from "@tanstack/react-query";
import { getUserinfo } from "../mypageAPI";

const useGetUserinfo = () => {
    const { data: UserInfo } = useQuery({
        queryKey: ["UserInfo"],
        queryFn: getUserinfo,
    });
    return UserInfo;
};

export { useGetUserinfo };
