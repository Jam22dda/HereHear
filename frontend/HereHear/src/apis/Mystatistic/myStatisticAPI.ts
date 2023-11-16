import { instance } from "../instance";

const getMyLikeCount = async (userId: number) => {
    try {
        const url = userId ? `/statistics/likecount/${userId}` : "/statistics/likecount";
        const response = await instance.get(url);
        return response.data.data;
    } catch (error) {
        console.error("Error getMyLikeCount", error);
        throw error;
    }
};

const getHearTime = async (userId: number) => {
    try {
        const url = userId ? `/statistics/heartime/${userId}` : "/statistics/heartime";
        const response = await instance.get(url);
        return response.data.data;
    } catch (error) {
        console.error("Error getHearTime", error);
        throw error;
    }
};

const getMyTagCount = async (userId?: number) => {
    try {
        const url = userId ? `/statistics/tags/${userId}` : "/statistics/tags";
        const response = await instance.get(url);
        return response.data.data.tagResDtoList;
        // console.log(response.data.data.tagResDtoList, "response.data.data.tagResDtoList");
    } catch (error) {
        console.error("Error getMyTagCount", error);
        throw error;
    }
};

export { getMyLikeCount, getHearTime, getMyTagCount };
