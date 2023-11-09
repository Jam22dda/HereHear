export type SignUpInfo = {
    memberId: number | undefined;
    nickname: string;
    profileCharacterCode: number;
};

export type changeNickname = string;

export type memberId = number;

export type achievementId = number;

export type MyAchievement = {
    achievementId: number;
    badge: {
        badgeCode: number;
        badgeName: string;
        badgeImg: string;
    };
    mission: string;
    title: {
        titleCode: number;
        titleName: string;
    };
};

export interface FollowingType {
    memberId: number;
    nickname: string;
    isFollowed: boolean;
    isFollowing?: boolean;
    profileCharacter: {
        profileCharacterId: number;
        characterName: string;
        characterImage: string;
    };
    achievement?: {
        achievementId?: number;
        mission?: string;
        badge?: {
            badgeCode?: number;
            badgeName?: string;
            badgeImg?: string;
        };
        title?: {
            titleCode?: number;
            titleName?: string;
        };
    };
}
