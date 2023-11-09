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
import monziHerehear from "../../../public/images/monzi-herehear.png";
import { useGetCheckNickname } from "../../apis/Login/Quries/useGetCheckNickname";
import { useDebouncedCallback } from "use-debounce";
import { useRecoilState } from "recoil";
import { MyAchievementAtom } from "../../states/MypageAtoms";
import iconBack from "../../assets/CircleButton/icon-back.png";

const mypage = [
    { src: iconLikemusic, name: "좋아요한 노래", params: "/like" },
    { src: iconLp, name: "등록한 노래", params: "/myRegist" },
    { src: iconBadge, name: "뱃지", params: "/achievement" },
    { src: iconMystatistics, name: "개인 통계", params: "/myStatistics" },
];

export default function MyPage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        if (path === "/achievement") {
            if (UserInfo.achievementId === null) {
                navigate(path);
            } else {
                setMyAchievement(MyAchievement);
                navigate(path);
            }
        } else {
            navigate(path);
        }
    };

    const UserInfo = useGetUserinfo();
    const Follower = useGetFollower();
    const Following = useGetFollowing();
    const MyAchievement = useGetMyAchievement(UserInfo?.achievementId);
    const [myAchievement, setMyAchievement] = useRecoilState(MyAchievementAtom);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [nickname, setNickname] = useState("");
    const [isBlanked, setIsBlanked] = useState(false);
    const [isDuplicated, setIsDuplicated] = useState(false);
    const [debouncedNickname, setDebouncedNickname] = useState("");

    const debouncedCheck = useDebouncedCallback(
        // function
        (value) => {
            setDebouncedNickname(value);
        },
        // delay in ms
        500
    );

    const toggleModal = () => {
        setIsModalOpen((prev) => !prev);
        setIsBlanked(false);
        setIsDuplicated(false);
    };

    const handleEdit = () => {
        toggleModal();
    };

    const handleBlanked = () => {
        setIsBlanked(false);
    };

    const handleDuplicated = () => {
        setIsDuplicated(false);
    };

    const { mutate: postNicknameMutate } = usePostNickname();
    const { data: checkNicknameData } = useGetCheckNickname(debouncedNickname);
    const handleChangeNickname = (e: ChangeEvent<HTMLInputElement>) => {
        setNickname(e.target.value);
        debouncedCheck(e.target.value);
    };

    const handleSaveNickname = (nickname: changeNickname) => {
        if (nickname == "") {
            setIsBlanked(true);
        } else if (checkNicknameData?.isAvailable === false) {
            setIsDuplicated(true);
        } else {
            postNicknameMutate(nickname);
            toggleModal();
        }
    };

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>
                <S.MyPageWrapper>
                    <S.Profile>
                        <Image src={UserInfo && UserInfo.profileCharacter.characterImage} width={140} height={140} $unit="px"></Image>
                    </S.Profile>
                    <S.MydataWrapper>
                        {MyAchievement && (
                            <Image src={MyAchievement && MyAchievement.badge.badgeImg} width={24} height={24} $unit="px" $margin="0 4px 4px 0"></Image>
                        )}
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
                            <Image src={iconEdit} width={16} height={16} $unit="px" $margin="0 0 0 4px" onClick={handleEdit}></Image>
                        </S.EditWrapper>
                    </S.MydataWrapper>
                    <S.FollowWrapper>
                        <Button option="tag_plus" size="largeplus" $width="130px" onClick={() => navigatePage("/following")}>
                            팔로잉 {Following?.length ?? 0}명
                        </Button>
                        <Button option="tag_plus" size="largeplus" $width="130px" onClick={() => navigatePage("/follower")}>
                            팔로워 {Follower?.length ?? 0}명
                        </Button>
                    </S.FollowWrapper>
                    <S.MyItemWrapper>
                        {mypage.map((item, index) => (
                            <ItemBox key={index} src={item.src} title={item.name} onClick={() => navigatePage(item.params)} />
                        ))}
                    </S.MyItemWrapper>
                    <Navbar></Navbar>
                </S.MyPageWrapper>
            </div>
            {isModalOpen && (
                <ModalBg>
                    <Modal toggleModal={() => toggleModal()}>
                        {!isBlanked && !isDuplicated && (
                            <S.ExitWrapper>
                                <CircleButton option="default" size="medium" onClick={toggleModal}>
                                    <Image src={iconExit} width={20} height={20} $unit="px"></Image>
                                </CircleButton>
                            </S.ExitWrapper>
                        )}
                        {!isBlanked && !isDuplicated && (
                            <S.TextWrapper>
                                <Text size="body2" fontWeight="medium" $margin="10px 0 28px 0">
                                    변경할 닉네임을 작성해 주세요!
                                </Text>

                                <Input onChange={handleChangeNickname}></Input>
                                <Button onClick={() => handleSaveNickname(nickname)} $width="130px" $margin="32px 0 0 0">
                                    저장하기
                                </Button>
                            </S.TextWrapper>
                        )}
                        {isBlanked && (
                            <S.TextWrapper>
                                <Image src={monziHerehear} width={100} height={100} $unit="px" $margin="0 0 30px 0"></Image>
                                <h2>닉네임을 입력해주세요!</h2>
                                <Button onClick={handleBlanked} $width="130px" $margin="32px 0 0 0">
                                    다시 입력하기
                                </Button>
                            </S.TextWrapper>
                        )}
                        {isDuplicated && (
                            <S.TextWrapper>
                                <Image src={monziHerehear} width={100} height={100} $unit="px" $margin="0 0 30px 0"></Image>
                                <h2>닉네임이 중복되었습니다!</h2>
                                <Button onClick={handleDuplicated} $width="130px" $margin="32px 0 0 0">
                                    다시 입력하기
                                </Button>
                            </S.TextWrapper>
                        )}
                    </Modal>
                </ModalBg>
            )}
        </div>
    );
}
