import { atom } from "recoil";
export interface TagInfo {
    name: string;
    id: number;
}

export const selectedTagState = atom<TagInfo[]>({
    key: "selectedTagState",
    default: [],
});
