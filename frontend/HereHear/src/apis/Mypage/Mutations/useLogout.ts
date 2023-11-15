import { useMutation } from "@tanstack/react-query";
import { logout } from "../mypageAPI";

interface logoutResponse {
    code: number;
    message: string;
}

const useLogout = () => {
    return useMutation<logoutResponse, Error>({
        mutationFn: logout,
        onSuccess: () => {
            console.log("로그아웃 성공!");
            localStorage.removeItem("token");
            localStorage.removeItem("yourId");
            localStorage.removeItem("memberId");
            localStorage.removeItem("myKey");
        },
        onError: (error: Error) => {
            console.log("로그아웃 실패:", error.message);
        },
    });
};

export { useLogout };
