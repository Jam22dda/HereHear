import { atom } from "recoil";
import { SignUpInfo } from "../types/user";

export const SignUpInfoAtom = atom<SignUpInfo>({
    key: "SignUpInfoAtom",
    default: {
        memberId: parseInt(localStorage.getItem("memberId") || "0", 10),
        nickname: "",
        profileCharacterCode: 0,
    },
});
