import { instance } from "../instance";
import { changeNickname, achievementId } from "../../types/user";

const getUserinfo = async () => {
    try {
        const response = await instance.get("/member/info");
        console.log(response.data);
        return response.data.data;
    } catch (error) {
        console.error("Error getUserinfo", error);
        throw error;
    }
};

const getYourinfo = async (id: number) => {
    try {
        const response = await instance.get(`/member/info/${id}`);
        return response.data.data;
    } catch (error) {
        console.error("Error getYourinfo", error);
        throw error;
    }
};

const getLikeMusic = async () => {
    try {
        const response = await instance.get("/like/list");
        return response.data.data;
    } catch (error) {
        console.error("Error getLikeMusic", error);
        throw error;
    }
};

const getRegistMusic = async () => {
    try {
        const response = await instance.get("/music/my/list");
        return response.data.data;
    } catch (error) {
        console.error("Error getRegistMusic", error);
        throw error;
    }
};

const getFollower = async () => {
    try {
        const response = await instance.get("/member/follower");
        return response.data.data;
    } catch (error) {
        console.error("Error getFollower", error);
        throw error;
    }
};

const getFollowing = async () => {
    try {
        const response = await instance.get("/member/following");
        return response.data.data;
    } catch (error) {
        console.error("Error getFollowing", error);
        throw error;
    }
};

const getAchievementList = async () => {
    try {
        const response = await instance.get("/achievement/myachievement");
        return response.data.data;
    } catch (error) {
        console.error("Error getAchievementList", error);
        throw error;
    }
};

const getMyAchievement = async (achievementId: number | undefined) => {
    try {
        const response = await instance.get(`/achievement/${achievementId}`);
        return response.data.data;
    } catch (error) {
        console.error("Error getMyAchievement", error);
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
    return response.data;
};

interface unFollowResponse {
    code: number;
    message: string;
}

const unFollow = async (memberId: number): Promise<unFollowResponse> => {
    const { data } = await instance.delete("/member/follow", {
        data: { memberId },
    });
    return data;
};

interface FollowResponse {
    code: number;
    message: string;
}

export type memberId = number;

const Follow = async (memberId: memberId): Promise<FollowResponse> => {
    const response = await instance.post<FollowResponse>("/member/follow", {
        memberId,
    });
    return response.data;
};

interface putAchievementResponse {
    code: number;
    message: string;
}

const putAchievement = async (
    achievementId: achievementId
): Promise<putAchievementResponse> => {
    const response = await instance.put<putAchievementResponse>(
        "/achievement/equip",
        {
            achievementId,
        }
    );
    return response.data;
};

interface postWearOsResponse {
    code: number;
    message: string;
    data: {
        personalCode: string;
    };
}

const postWearOs = async (): Promise<postWearOsResponse> => {
    const response = await instance.post<postWearOsResponse>(
        "/wearos/personalCode"
    );
    return response.data;
};

const getWearOs = async () => {
    try {
        const response = await instance.get("/wearos/personalCode");
        return response.data.data;
    } catch (error) {
        console.error("getWearOs", error);
        throw error;
    }
};

interface logoutResponse {
    code: number;
    message: string;
}

const logout = async (): Promise<logoutResponse> => {
    const { data } = await instance.delete<logoutResponse>("/member/logout");
    console.log(data);
    return data;
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
    unFollow,
    Follow,
    putAchievement,
    postWearOs,
    getWearOs,
    logout,
};
