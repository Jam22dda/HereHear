import { atom } from "recoil";

export const selectedTagState = atom({
    key: "selectedTagState", // 고유한 키
    default: [], // 기본값
});
