import { instance } from "../instance";

const getYourLikeMusic = async (id: number) => {
    try {
        const response = await instance.get(`/like/list/${id}`);
        return response.data.data;
    } catch (error) {
        console.error("다른 유저의 좋아요한 음악 리스트 가져오기 실패", error);
        throw error;
    }
};

const getYourRegistMusic = async (id: number) => {
    try {
        const response = await instance.get(`/music/list/${id}`);
        return response.data.data;
    } catch (error) {
        console.error("다른 유저가 등록한 음악 리스트 가져오기 실패", error);
        throw error;
    }
};

const getYourFollowing = async (id: number) => {
    try {
        const response = await instance.get(`/member/following/${id}`);
        return response.data.data;
    } catch (error) {
        console.error("다른 유저의 팔로잉 목록 가져오기 실패", error);
        throw error;
    }
};

const getYourFollower = async (id: number) => {
    try {
        const response = await instance.get(`/member/follower/${id}`);
        return response.data.data;
    } catch (error) {
        console.error("다른 유저의 팔로워 목록 가져오기 실패", error);
        throw error;
    }
};

const getYourAchievement = async (id: number) => {
    try {
        const response = await instance.get(`/achievement/list/${id}`);
        return response.data.data;
    } catch (error) {
        console.error("다른 유저의 업적 목록 가져오기 실패", error);
        throw error;
    }
};

export {
    getYourLikeMusic,
    getYourRegistMusic,
    getYourFollowing,
    getYourFollower,
    getYourAchievement,
};
