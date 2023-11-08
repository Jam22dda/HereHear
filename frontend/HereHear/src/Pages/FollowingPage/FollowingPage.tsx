import * as S from "./FollowingPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { Text } from "../../components/atoms/Text/Text.styles";
import { Image } from "../../components/atoms/Image/Image";
import { useNavigate } from "react-router-dom";
import Follow from "../../components/molcules/Follow/Follow";
import Button from "../../components/atoms/Button/Button";
import { useGetFollowing } from "../../apis/Mypage/Quries/useGetFollowing";
import { useUnFollow2 } from "../../apis/Mypage/Mutations/useUnFollow";
import { useFollow2 } from "../../apis/Mypage/Mutations/useFollow";
import { memberId } from "../../types/user";
// import React, { useState } from "react";
import { FollowingType } from "../../types/user";
// import { useRecoilState } from "recoil";
// import { FollowingListAtom } from "../../states/MypageAtoms";

export default function Following() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    // const navigatePage = (path: string) => {
    //     navigate(path);
    // };
    // const [followingList, setFollowingList] = useRecoilState(FollowingListAtom);
    // console.log(followingList);
    // const [followingList, setFollowingList] = useState();

    const Following: FollowingType[] = useGetFollowing();
    const { mutate: FollowUser } = useFollow2();
    const { mutate: unFollowUser } = useUnFollow2();

    // useEffect(() => {
    //     setFollowingList(Following);
    // }, [Following]);

    const handleFollowClick = (memberId: memberId) => {
        FollowUser(memberId);
    };

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
                                {item.isFollowed && (
                                    <Button
                                        option="follow"
                                        size="medium"
                                        $width="92px"
                                        onClick={() =>
                                            handleFollowClick(item.memberId)
                                        }
                                    >
                                        팔로우
                                    </Button>
                                )}
                                {!item.isFollowed && (
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
                                )}
                            </S.FollowingWrapper>
                        ))}
                </S.FollowingListWrapper>
            </div>
        </div>
    );
}
