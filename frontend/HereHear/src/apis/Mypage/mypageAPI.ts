import { instance } from "../instance";
import { changeNickname } from "../../types/user";

const getUserinfo = async () => {
    try {
        const response = await instance.get("/member/info");
        console.log("getUserinfo 성공");
        console.log(
            response.data.data,
            "오냐고??????????????????????????????????????"
        );
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

const getMyAchievement = async (achievementId: number | undefined) => {
    try {
        const response = await instance.get(`/achievement/${achievementId}`);
        console.log("getMyAchievement 성공", response);
        return response.data.data;
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

interface postNicknameResponse {
    code: number;
    message: string;
}

const postNickname = async (
    nickname: changeNickname
): Promise<postNicknameResponse> => {
    const response = await instance.post<postNicknameResponse>(
        "/member/update/nickname",
        {
            nickname,
        }
    );
    console.log(response);
    return response.data;
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
    postNickname,
};