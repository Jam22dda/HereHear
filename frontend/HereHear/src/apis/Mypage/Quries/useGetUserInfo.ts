import { useQuery } from "@tanstack/react-query";
import { getUserinfo } from "../mypageAPI";

const useGetUserinfo = () => {
    const { data: UserInfo } = useQuery({
        queryKey: ["UserInfo"],
        queryFn: getUserinfo,
    });
    console.log(UserInfo, "타니?????????????????????????????");
    return UserInfo;
};

export { useGetUserinfo };
