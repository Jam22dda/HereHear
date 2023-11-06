import { instance } from "../instance";

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
        return response.data;
    } catch (error) {
        console.error("Error fetching search Tag", error);
        throw error;
    }
};
export { getSearchMusic, getTag };
