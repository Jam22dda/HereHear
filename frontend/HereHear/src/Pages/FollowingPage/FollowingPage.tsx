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
import { useFollow } from "../../apis/Mypage/Mutations/useFollow";
import { memberId } from "../../types/user";
import React, { useState, useEffect } from "react";
import { FollowingType } from "../../types/user";
import { YourIdAtom } from "../../states/MypageAtoms";
import { useRecoilState, useRecoilValue } from "recoil";
import { useGetYourFollowing } from "../../apis/YourPage/Quries/useGetYourFollowing";
import { SignUpInfoAtom } from "../../states/SignUpAtoms";

export default function Following() {
    const navigate = useNavigate();
    const navigatePage = (path: string) => {
        navigate(path);
    };
    const [followingList, setFollowingList] = useState<FollowState[]>([]);
    const [yourId, setYourId] = useRecoilState(YourIdAtom);
    const Following: FollowingType[] = useGetFollowing();
    const YourFollowing: FollowingType[] = useGetYourFollowing(yourId);
    const { mutate: FollowUser } = useFollow();
    const { mutate: unFollowUser } = useUnFollow();
    const mySignUpInfo = useRecoilValue(SignUpInfoAtom);
    const myId = mySignUpInfo.memberId;
    // console.log(myId);

    interface FollowState {
        isFollowing: boolean;
    }

    useEffect(() => {
        if (Following && Following.length > 0) {
            // 타입스크립트에게 상태의 타입을 알려줍니다.
            setFollowingList(Following.map(() => ({ isFollowing: true })));
        }
    }, [Following]);

    const handleSetYourId = (yourId: memberId) => {
        localStorage.setItem("yourId", yourId.toString());
    };

    const handleFollowClick = (memberId: memberId, index: number) => {
        FollowUser(memberId);
        setFollowingList((prevList) => prevList.map((follow, idx) => (idx === index ? { ...follow, isFollowing: true } : follow)));
    };

    const handleUnFollowClick = (memberId: memberId, index: number) => {
        unFollowUser({ memberId });
        // 상태 업데이트
        setFollowingList((prevList) => prevList.map((follow, idx) => (idx === index ? { ...follow, isFollowing: false } : follow)));
    };

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>
                <Text size="subtitle1" fontWeight="bold" $margin="30px 0 50px 0">
                    팔로잉 목록
                </Text>
                <S.FollowingListWrapper>
                    {yourId === 0
                        ? Following &&
                          Following.map((item: FollowingType, index: number) => (
                              <S.FollowingWrapper key={index}>
                                  <Follow
                                      onClick={() => {
                                          navigatePage(`/mypage/${item.memberId}`);
                                          setYourId(item.memberId);
                                          handleSetYourId(item.memberId);
                                      }}
                                      characterImage={item.profileCharacter.characterImage}
                                      nickname={item.nickname}
                                      titleName={item.achievement?.title?.titleName ?? "뱃지가 없어용!"}
                                  />
                                  {followingList[index] && followingList[index].isFollowing ? (
                                      <Button option="unfollow" size="medium" $width="92px" onClick={() => handleUnFollowClick(item.memberId, index)}>
                                          팔로잉
                                      </Button>
                                  ) : (
                                      <Button option="follow" size="medium" $width="92px" onClick={() => handleFollowClick(item.memberId, index)}>
                                          팔로우
                                      </Button>
                                  )}
                              </S.FollowingWrapper>
                          ))
                        : YourFollowing &&
                          YourFollowing.map((item: FollowingType, index: number) => (
                              <S.FollowingWrapper key={index}>
                                  <Follow
                                      onClick={() => {
                                          navigatePage(item.memberId === myId ? "/mypage" : `/mypage/${item.memberId}`);
                                          setYourId(item.memberId);
                                      }}
                                      characterImage={item.profileCharacter.characterImage}
                                      nickname={item.nickname}
                                      titleName={item.achievement?.title?.titleName ?? "뱃지가 없어용!"}
                                  />
                              </S.FollowingWrapper>
                          ))}
                </S.FollowingListWrapper>
            </div>
        </div>
    );
}
