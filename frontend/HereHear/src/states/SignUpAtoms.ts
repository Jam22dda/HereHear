import { atom } from "recoil";
import { SignUpInfo } from "../types/user";

export const SignUpInfoAtom = atom<SignUpInfo>({
    key: "SignUpInfoAtom",
    default: {
        memberId: 0,
        nickname: "",
        profileCharacterCode: 0,
    },
});
