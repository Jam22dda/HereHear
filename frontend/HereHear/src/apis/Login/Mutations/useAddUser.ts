import { useMutation } from "@tanstack/react-query"; // 1. 리액트쿼리 기본 제공값 -> .mutate로 입력한 값 담는 곳 (?)
import { addUser } from "../loginAPI";
import { SignUpInfo } from "../../../types/user";
import { useNavigate } from "react-router-dom";

interface AddUserResponse {
    code: number;
    message: string;
    data: string;
}

const useAddUser = () => {
    const navigate = useNavigate();
    const navigatePage = (path: string) => {
        navigate(path);
    };
    return useMutation<AddUserResponse, Error, SignUpInfo>({
        mutationFn: addUser,
        onSuccess: (data) => {
            localStorage.setItem("token", data.data);
            navigatePage("/onboarding");
            // console.log("회원 등록!", data);
        },
        onError: (error) => {
            // console.log("회원 등록 실패...", error);
        },
    });
};

export { useAddUser };
