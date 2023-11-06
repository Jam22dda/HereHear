import { Image } from "../../components/atoms/Image/Image";
import React, { useState, ChangeEvent } from "react";
import * as S from "./MyPage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconEdit from "../../assets/MyPage/icon-edit.png";
import Button from "../../components/atoms/Button/Button";
import iconLp from "../../assets/MyPage/icon-lp.png";
import ItemBox from "../../components/molcules/ItemBox/ItemBox";
import Navbar from "../../components/molcules/Navbar/Navbar";
import iconLikemusic from "../../assets/MyPage/icon-likemusic.png";
import iconBadge from "../../assets/MyPage/badges.png";
import iconMystatistics from "../../assets/MyPage/icon-mystatistics.png";
import { useNavigate } from "react-router-dom";
import { useGetUserinfo } from "../../apis/Mypage/Quries/useGetUserInfo";
import { useGetFollower } from "../../apis/Mypage/Quries/useGetFollower";
import { useGetFollowing } from "../../apis/Mypage/Quries/useGetFollowing";
import { useGetMyAchievement } from "../../apis/Mypage/Quries/useGetMyAchievement";
import Modal from "../../components/atoms/Modal/Modal";
import { ModalBg } from "../../components/atoms/Modal/Modal.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconExit from "../../../public/images/icon-exit.png";
import Input from "../../components/atoms/Input/Input";
import { usePostNickname } from "../../apis/Mypage/Mutations/usePostNickname";
import { changeNickname } from "../../types/user";

const mypage = [
    { src: iconLikemusic, name: "좋아요한 노래", params: "/like" },
    { src: iconLp, name: "등록한 노래", params: "/myRegist" },
    { src: iconBadge, name: "뱃지", params: "/achievement" },
    { src: iconMystatistics, name: "개인 통계", params: "/myStatistics" },
];

export default function MyPage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const UserInfo = useGetUserinfo();
    console.log("왜???????????????????????????");
    console.log(UserInfo);
    const Follower = useGetFollower();
    const Following = useGetFollowing();
    const MyAchievement = useGetMyAchievement(UserInfo?.achievementId);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [nickname, setNickname] = useState("");

    const toggleModal = () => {
        setIsModalOpen((prev) => !prev);
    };

    const handleEdit = () => {
        toggleModal();
    };

    const { mutate } = usePostNickname();
    const handleChangeNickname = (e: ChangeEvent<HTMLInputElement>) => {
        setNickname(e.target.value);
    };

    const handleSaveNickname = (nickname: changeNickname) => {
        mutate(nickname);
        toggleModal();
    };

    return (
        <div id="display">
            <div className="container">
                <S.MyPageWrapper>
                    <S.Profile>
                        <Image
                            src={
                                UserInfo &&
                                UserInfo.profileCharacter.characterImage
                            }
                            width={140}
                            height={140}
                            $unit="px"
                        ></Image>
                    </S.Profile>
                    <S.MydataWrapper>
                        <Image
                            src={MyAchievement && MyAchievement.badge.badgeImg}
                            width={24}
                            height={24}
                            $unit="px"
                            $margin="0 4px 4px 0"
                        ></Image>
                        <Text size="body1" fontWeight="bold">
                            {MyAchievement && MyAchievement.title.titleName}
                        </Text>
                        <Text size="body1" $marginLeft="4px">
                            {UserInfo && UserInfo.nickname}
                        </Text>
                        <Text size="body2" $marginLeft="4px">
                            님
                        </Text>
                        <S.EditWrapper>
                            <Image
                                src={iconEdit}
                                width={16}
                                height={16}
                                $unit="px"
                                $margin="0 0 0 4px"
                                onClick={handleEdit}
                            ></Image>
                        </S.EditWrapper>
                    </S.MydataWrapper>
                    <S.FollowWrapper>
                        <Button
                            option="tag_plus"
                            size="largeplus"
                            $width="130px"
                        >
                            팔로잉 {Following.length}명
                        </Button>
                        <Button
                            option="tag_plus"
                            size="largeplus"
                            $width="130px"
                        >
                            팔로워 {Follower.length}명
                        </Button>
                    </S.FollowWrapper>
                    <S.MyItemWrapper>
                        {mypage.map((item, index) => (
                            <ItemBox
                                key={index}
                                src={item.src}
                                title={item.name}
                                onClick={() => navigatePage(item.params)}
                            />
                        ))}
                    </S.MyItemWrapper>
                    <Navbar></Navbar>
                </S.MyPageWrapper>
            </div>
            {isModalOpen && (
                <ModalBg>
                    <Modal toggleModal={() => toggleModal()}>
                        <S.ExitWrapper>
                            <CircleButton
                                option="default"
                                size="medium"
                                onClick={toggleModal}
                            >
                                <Image
                                    src={iconExit}
                                    width={20}
                                    height={20}
                                    $unit="px"
                                ></Image>
                            </CircleButton>
                        </S.ExitWrapper>
                        <S.TextWrapper>
                            <Text fontWeight="bold">변경할 닉네임을 작성</Text>
                            <Text $margin="0 0 28px 0" fontWeight="bold">
                                해주세요!
                            </Text>
                            <Input onChange={handleChangeNickname}></Input>
                            <Button
                                onClick={() => handleSaveNickname(nickname)}
                                $width="130px"
                                $margin="32px 0 0 0"
                            >
                                저장하기
                            </Button>
                        </S.TextWrapper>
                    </Modal>
                </ModalBg>
            )}
        </div>
    );
}
