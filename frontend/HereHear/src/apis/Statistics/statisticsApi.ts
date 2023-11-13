import { instance } from "../instance";

const getLikeStatistics = async () => {
    try {
        const response = await instance.get("/totalstats/likecount");
        return response.data.data;
    } catch (error) {
        console.error("Error getLikeStatistics", error);
        throw error;
    }
};

const getTagStatistics = async () => {
    try {
        const response = await instance.get("/totalstats/tags");
        return response.data.data;
    } catch (error) {
        console.error("Error getTagStatistics", error);
        throw error;
    }
};

const getListenStatistics = async () => {
    try {
        const response = await instance.get("/totalstats/music");
        return response.data.data;
    } catch (error) {
        console.error("Error getListenStatistics", error);
        throw error;
    }
};

export { getLikeStatistics, getTagStatistics, getListenStatistics };
