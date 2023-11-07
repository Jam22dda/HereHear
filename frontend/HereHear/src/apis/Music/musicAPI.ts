import { instance } from "../instance";
import { AddMusicInfo } from "../../types/music";

const getSearchMusic = async (keyword: string, page: number) => {
    try {
        const response = await instance.get(`/music/search?keyword=${keyword}&limit=10&page=${page}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getTag = async () => {
    try {
        const response = await instance.get("/music/tag");
        // console.log("태그 오니?", response.data);
        return response.data;
    } catch (error) {
        console.error("Error fetching search Tag", error);
        throw error;
    }
};

const addMusic = async (data: AddMusicInfo) => {
    try {
        const response = await instance.post("/music", data);
        console.log(response.data, "음악등록 가능?");
        return response.data;
    } catch (error) {
        console.error("Error fetching addMusic", error);
        throw error;
    }
};

export { getSearchMusic, getTag, addMusic };
