import { instance } from "../instance";

const getMyLikeCount = async () => {
    try {
        const response = await instance.get("/statistics/likecount");
        return response.data.data;
    } catch (error) {
        console.error("Error getMyLikeCount", error);
        throw error;
    }
};

const getHearTime = async () => {
    try {
        const response = await instance.get("/statistics/heartime");
        return response.data.data;
    } catch (error) {
        console.error("Error getHearTime", error);
        throw error;
    }
};

const getMyTagCount = async () => {
    try {
        const response = await instance.get("/statistics/tags");
        return response.data.data.tagResDtoList;
        // console.log(response.data.data.tagResDtoList, "response.data.data.tagResDtoList");
    } catch (error) {
        console.error("Error getMyTagCount", error);
        throw error;
    }
};

export { getMyLikeCount, getHearTime, getMyTagCount };
