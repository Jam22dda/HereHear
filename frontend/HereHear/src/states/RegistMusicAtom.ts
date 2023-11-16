import { atom } from "recoil";
import { MusicItemState } from "../types/music";

export const musicItemState = atom<MusicItemState>({
    key: "musicItemState", // 고유한 키
    default: {
        src: "",
        songtitle: "",
        artist: "",
        releaseTime: "",
        trackId: "",
    },
});
