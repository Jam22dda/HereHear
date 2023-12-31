import { instance } from "../instance";
import { AddMusicInfo } from "../../types/music";
import { registeredMusicId, youtubeMusicRequest } from "../../types/music";

const getSearchMusic = async (keyword: string, page: number) => {
    try {
        const response = await instance.get(
            `/music/search?keyword=${keyword}&limit=10&page=${page}`
        );
        return response.data;
    } catch (error) {
        // console.error("Error fetching search music", error);
        console.log();
        throw error;
    }
};

const getMusicPlay = async (registeredMusicId: number) => {
    try {
        const response = await instance.get(`/music/${registeredMusicId}`);
        return response.data;
    } catch (error) {
        // console.error("Error fetching readMusicPlayer", error);
        console.log();

        throw error;
    }
};

const getTag = async () => {
    try {
        const response = await instance.get("/music/tag");
        return response.data;
    } catch (error) {
        // console.error("Error fetching search Tag", error);
        console.log();

        throw error;
    }
};

const addMusic = async (data: AddMusicInfo) => {
    try {
        const response = await instance.post("/music", data);
        return response.data;
    } catch (error) {
        // console.error("Error fetching addMusic", error);
        console.log();

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
        // console.error("Error fetching search music", error);
        console.log();

        throw error;
    }
};

const postMusicHistory = async (
    registeredMusicId: registeredMusicId
): Promise<postLikeMusicResponse> => {
    const response = await instance.post<postLikeMusicResponse>("/history", {
        registeredMusicId,
    });
    return response.data;
};

const getSpotifyAccessToken = async () => {
    const response = await instance.get("/spotify/token");
    return response.data;
};

interface YoutubeListResponse {
    code: number;
    message: string;
}

const postYoutubeList = async (
    searchName: youtubeMusicRequest
): Promise<YoutubeListResponse> => {
    const response = await instance.post<YoutubeListResponse>("/youtube", {
        searchName,
    });
    return response.data;
};

export {
    getSearchMusic,
    getMusicPlay,
    getTag,
    addMusic,
    postLikeMusic,
    getListenedMusic,
    postMusicHistory,
    getSpotifyAccessToken,
    postYoutubeList,
};
