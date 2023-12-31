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

export const YourIdAtom = atom({
    key: "YourIdAtom",
    default: parseInt(localStorage.getItem("yourId") || "0", 10),
});

export const BackCheckAtom = atom({
    key: "BackCheckAtom",
    default: "",
});

export const IsWhosPageAtom = atom({
    key: "IsWhosPageAtom",
    default: "",
});

export const GetKeyAtom = atom({
    key: "GetKeyAtom",
    default: localStorage.getItem("myKey") || "",
});
