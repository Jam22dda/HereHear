import { instance } from "../instance";

const getMyLikeCount = async () => {
    try {
        const response = await instance.get("/statistics/likecount");
        return response.data;
    } catch (error) {
        console.error("Error getMyLikeCount", error);
        throw error;
    }
};

const getHereTime = async () => {
    try {
        const response = await instance.get("/statistics/heartime");
        return response.data;
    } catch (error) {
        console.error("Error getHereTime", error);
        throw error;
    }
};

const getMyTagCount = async () => {
    try {
        const response = await instance.get("/statistics/tags");
        return response.data;
    } catch (error) {
        console.error("Error getMyTagCount", error);
        throw error;
    }
};

export { getMyLikeCount, getHereTime, getMyTagCount };
