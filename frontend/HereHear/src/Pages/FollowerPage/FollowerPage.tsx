import * as S from "./FollowerPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { Text } from "../../components/atoms/Text/Text.styles";
import { Image } from "../../components/atoms/Image/Image";
import { useNavigate } from "react-router-dom";
import Follow from "../../components/molcules/Follow/Follow";
import Button from "../../components/atoms/Button/Button";
import { useGetFollower } from "../../apis/Mypage/Quries/useGetFollower";

export default function Follower() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    // const navigatePage = (path: string) => {
    //     navigate(path);
    // };

    interface FollowerType {
        memberId: number;
        nickname: string;
        isFollowed: boolean;
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

    const Follower: FollowerType[] = useGetFollower();
    console.log(Follower);
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
                {Follower.map((item: FollowerType, index: number) => (
                    <S.FollowerWrapper key={index}>
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
                            option={item.isFollowed ? "follow" : "unfollow"}
                            size="medium"
                            $width="92px"
                        >
                            {item.isFollowed ? "팔로잉" : "팔로우"}
                        </Button>
                    </S.FollowerWrapper>
                ))}
            </div>
        </div>
    );
}
