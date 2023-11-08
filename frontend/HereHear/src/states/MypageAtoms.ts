import { atom } from "recoil";
import { MyAchievement, FollowingType } from "../types/user";

export const MyAchievementAtom = atom<MyAchievement>({
    key: "MyAchievementAtom",
    default: {
        achievementId: 0,
        badge: {
            badgeCode: 0,
            badgeName: "",
            badgeImg: "",
        },
        mission: "",
        title: {
            titleCode: 0,
            titleName: "",
        },
    },
});

export const FollowingListAtom = atom<FollowingType[]>({
    key: "FollowingListAtom",
    default: [],
});
