import * as S from "./FollowingPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { Text } from "../../components/atoms/Text/Text.styles";
import { Image } from "../../components/atoms/Image/Image";
import { useNavigate } from "react-router-dom";
import Follow from "../../components/molcules/Follow/Follow";
import Button from "../../components/atoms/Button/Button";
import { useGetFollowing } from "../../apis/Mypage/Quries/useGetFollowing";
import { useUnFollow } from "../../apis/Mypage/Mutations/useUnFollow";

export default function Following() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    // const navigatePage = (path: string) => {
    //     navigate(path);
    // };

    interface FollowingType {
        memberId: number;
        nickname: string;
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

    const Following: FollowingType[] = useGetFollowing();
    const { mutate: unFollowUser } = useUnFollow();

    const handleUnFollowClick = (memberId: number) => {
        unFollowUser({ memberId });
    };
    console.log(Following);
    return (
        <div id="display">
            <div className="container">
                <CircleButton
                    option="default2"
                    size="medium"
                    onClick={() => navigate(-1)}
                >
                    <Image
                        src={iconBack}
                        width={10}
                        height={18}
                        $unit="px"
                    ></Image>
                </CircleButton>
                <Text
                    size="subtitle1"
                    fontWeight="bold"
                    $margin="30px 0 50px 0"
                >
                    팔로잉 목록
                </Text>
                <S.FollowingListWrapper>
                    {Following &&
                        Following.map((item: FollowingType, index: number) => (
                            <S.FollowingWrapper key={index}>
                                <Follow
                                    characterImage={
                                        item.profileCharacter.characterImage
                                    }
                                    nickname={item.nickname}
                                    titleName={
                                        item.achievement?.title?.titleName ??
                                        "뱃지가 없어용!"
                                    }
                                />
                                <Button
                                    option="unfollow"
                                    size="medium"
                                    $width="92px"
                                    onClick={() =>
                                        handleUnFollowClick(item.memberId)
                                    }
                                >
                                    팔로잉
                                </Button>
                            </S.FollowingWrapper>
                        ))}
                </S.FollowingListWrapper>
            </div>
        </div>
    );
}
