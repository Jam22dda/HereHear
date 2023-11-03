import { instance } from "../instance";

const getUserinfo = async () => {
    try {
        const response = await instance.get("/member/info");
        console.log("getUserinfo 성공");
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getYourinfo = async (id: number) => {
    try {
        const response = await instance.get(`/member/info/${id}`);
        console.log("getUserinfo 성공");
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getLikeMusic = async () => {
    try {
        const response = await instance.get("/like/list");
        console.log("getLikeMusic 성공", response);
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getRegistMusic = async () => {
    try {
        const response = await instance.get("/music/my/list");
        console.log("getRegistMusic 성공", response);
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getFollower = async () => {
    try {
        const response = await instance.get("/member/follower");
        console.log("getFollower 성공", response);
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getFollowing = async () => {
    try {
        const response = await instance.get("/member/following");
        console.log("getFollowing 성공", response);
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getAchievementList = async () => {
    try {
        const response = await instance.get("/achievement/myachievement");
        console.log("getAchievementList 성공", response);
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getMyAchievement = async (achievementId: number) => {
    try {
        const response = await instance.get(`/achievement/${achievementId}`);
        console.log("getMyAchievement 성공", response);
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

export {
    getUserinfo,
    getYourinfo,
    getLikeMusic,
    getRegistMusic,
    getFollower,
    getFollowing,
    getAchievementList,
    getMyAchievement,
};
