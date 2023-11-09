import { instance } from "../instance";
import { AddMusicInfo } from "../../types/music";
import { registeredMusicId } from "../../types/music";

const getSearchMusic = async (keyword: string, page: number) => {
    try {
        const response = await instance.get(
            `/music/search?keyword=${keyword}&limit=10&page=${page}`
        );
        return response.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getMusicPlay = async (registeredMusicId: number) => {
    try {
        const response = await instance.get(`/music/${registeredMusicId}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching readMusicPlayer", error);
        throw error;
    }
};

const getTag = async () => {
    try {
        const response = await instance.get("/music/tag");
        return response.data;
    } catch (error) {
        console.error("Error fetching search Tag", error);
        throw error;
    }
};

const addMusic = async (data: AddMusicInfo) => {
    try {
        const response = await instance.post("/music", data);
        return response.data;
    } catch (error) {
        console.error("Error fetching addMusic", error);
        throw error;
    }
};

interface postLikeMusicResponse {
    code: number;
    message: string;
}

const postLikeMusic = async (
    registeredMusicId: registeredMusicId
): Promise<postLikeMusicResponse> => {
    const response = await instance.post<postLikeMusicResponse>("/like", {
        registeredMusicId,
    });
    return response.data;
};

const getListenedMusic = async () => {
    try {
        const response = await instance.get("/history/list");
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

export {
    getSearchMusic,
    getMusicPlay,
    getTag,
    addMusic,
    postLikeMusic,
    getListenedMusic,
};
