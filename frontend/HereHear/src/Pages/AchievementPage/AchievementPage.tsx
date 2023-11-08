import * as S from "./AchievementPage.styles";
import React, { useState } from "react";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { useNavigate } from "react-router-dom";
import { Image } from "../../components/atoms/Image/Image";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconBack from "../../assets/CircleButton/icon-back.png";
import Button from "../../components/atoms/Button/Button";
import ItemBox from "../../components/molcules/ItemBox/ItemBox";
import { useGetAchievementList } from "../../apis/Mypage/Quries/useGetAchievementList";
import { useRecoilValue } from "recoil";
import { MyAchievementAtom } from "../../states/MypageAtoms";
import { usePutAchievement } from "../../apis/Mypage/Mutations/usePutAchievement";
import { achievementId } from "../../types/user";
import Modal from "../../components/atoms/Modal/Modal";
import { ModalBg } from "../../components/atoms/Modal/Modal.styles";
import iconExit from "../../../public/images/icon-exit.png";
import iconInformation from "../../assets/Achievement/icon-information.png";

interface AchievementType {
    achievementId: number;
    badge: {
        badgeCode: number;
        badgeImg: string;
        badgeName: string;
    };
    clearTime: string | null;
    mission: string;
    title: {
        titleCode: number;
        titleName: string;
    };
    userId: number;
}

export default function Achievement() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const [isModalOpen, setIsModalOpen] = useState(false);

    const toggleModal = () => {
        setIsModalOpen((prev) => !prev);
    };

    const MyAchievementList = useGetAchievementList();
    const MyAchievement = useRecoilValue(MyAchievementAtom);
    const [modalContent, setModalContent] = useState<AchievementType | null>(
        null
    );
    console.log(MyAchievementList);
    console.log(MyAchievement);
    const { mutate: PutAchievementMutate } = usePutAchievement();

    const handlePutAchievement = (achievementId: achievementId) => {
        PutAchievementMutate(achievementId);
    };

    const [selectedItem, setSelectedItem] = useState(
        MyAchievement.achievementId
    );
    console.log(selectedItem);

    const handleItemClick = (idx: number) => {
        setSelectedItem(idx);
    };

    const handleInfoClick = (item: AchievementType) => {
        setModalContent(item);
        toggleModal();
    };

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
                    내 뱃지
                </Text>
                <S.AchievementPageWrapper>
                    <S.AchievementWrapper>
                        {MyAchievementList &&
                            MyAchievementList.map(
                                (item: AchievementType, index: number) => (
                                    <div
                                        key={index}
                                        style={{
                                            position: "relative",
                                            display: "inline-block",
                                            marginRight: "10px",
                                        }}
                                    >
                                        <Image
                                            src={iconInformation}
                                            width={24}
                                            height={24}
                                            $unit="px"
                                            style={{
                                                position: "absolute", // 절대 위치
                                                top: 10, // 상단 정렬
                                                right: 12, // 우측 정렬
                                                zIndex: 1,
                                            }}
                                            onClick={() =>
                                                handleInfoClick(item)
                                            }
                                        />
                                        <ItemBox
                                            src={item.badge.badgeImg}
                                            title={item.title.titleName}
                                            $isselected={
                                                index + 1 === selectedItem
                                            }
                                            onClick={() =>
                                                handleItemClick(index + 1)
                                            }
                                            width={68}
                                            style={{
                                                filter:
                                                    item.clearTime === null
                                                        ? "grayscale(100%)"
                                                        : "none",
                                            }}
                                        />
                                    </div>
                                )
                            )}
                    </S.AchievementWrapper>
                    <Button
                        option="save"
                        size="large"
                        $width="132px"
                        $margin="60px 0 0 0"
                        onClick={() => {
                            handlePutAchievement(selectedItem);
                            navigatePage("/mypage");
                            window.location.reload();
                        }}
                    >
                        저장하기
                    </Button>
                </S.AchievementPageWrapper>
            </div>
            {isModalOpen && (
                <ModalBg style={{ zIndex: 1000 }}>
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
                            {modalContent && (
                                <>
                                    <Text fontWeight="bold">
                                        {modalContent.badge.badgeName}
                                    </Text>
                                    <Image
                                        src={modalContent.badge.badgeImg}
                                        width={120}
                                        $unit="px"
                                        $margin="20px 0 20px 0"
                                    ></Image>
                                    <Text size="small1" $margin="0 0 20px 0">
                                        {modalContent.mission}
                                    </Text>
                                    {/* 여기에 더 많은 정보를 추가할 수 있습니다. */}
                                </>
                            )}
                        </S.TextWrapper>
                    </Modal>
                </ModalBg>
            )}
        </div>
    );
}
