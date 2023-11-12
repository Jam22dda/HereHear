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
import { useGetYourAchievement } from "../../apis/YourPage/Quries/useGetAchievement";
import { useRecoilValue } from "recoil";
import { MyAchievementAtom, YourIdAtom } from "../../states/MypageAtoms";
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

    const yourId = useRecoilValue(YourIdAtom);
    const [isModalOpen, setIsModalOpen] = useState(false);

    const toggleModal = () => {
        setIsModalOpen((prev) => !prev);
    };

    const MyAchievementList = useGetAchievementList();
    const YourAchievement = useGetYourAchievement(yourId);
    const MyAchievement = useRecoilValue(MyAchievementAtom);
    const [modalContent, setModalContent] = useState<AchievementType | null>(
        null
    );
    const { mutate: PutAchievementMutate } = usePutAchievement();

    const handlePutAchievement = (achievementId: achievementId) => {
        PutAchievementMutate(achievementId);
    };

    const [selectedItem, setSelectedItem] = useState(
        MyAchievement.achievementId
    );

    const handleItemClick = (idx: number) => {
        if (idx === selectedItem) {
            setSelectedItem(0);
        } else {
            setSelectedItem(idx);
        }
    };

    const handleInfoClick = (item: AchievementType) => {
        setModalContent(item);
        toggleModal();
    };

    const itemsPerPage = 6;
    const [currentPage, setCurrentPage] = useState(1);
    const totalPages = Math.ceil(MyAchievementList?.length / itemsPerPage);
    const goToPage = (pageNumber: number) => setCurrentPage(pageNumber);

    const currentItems = MyAchievementList?.slice(
        (currentPage - 1) * itemsPerPage,
        currentPage * itemsPerPage
    );

    const yourCurrentItems = YourAchievement?.slice(
        (currentPage - 1) * itemsPerPage,
        currentPage * itemsPerPage
    );

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
                    뱃지
                </Text>
                <S.AchievementPageWrapper>
                    <S.AchievementWrapper>
                        {yourId === 0
                            ? currentItems &&
                              currentItems.map(
                                  (item: AchievementType, index: number) => {
                                      // Calculate the index for display, starting at 1 and adjusting for the current page
                                      const displayIndex =
                                          (currentPage - 1) * itemsPerPage +
                                          index +
                                          1;
                                      return (
                                          <div
                                              key={item.achievementId} // Assuming achievementId is unique
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
                                                      position: "absolute",
                                                      top: 10,
                                                      right: 12,
                                                      zIndex: 1,
                                                  }}
                                                  onClick={() =>
                                                      handleInfoClick(item)
                                                  }
                                              />
                                              <ItemBox
                                                  src={item.badge.badgeImg}
                                                  title={item.title.titleName} // Display the index within the title
                                                  $isselected={
                                                      displayIndex ===
                                                      selectedItem
                                                  }
                                                  onClick={() => {
                                                      if (
                                                          item.clearTime !==
                                                          null
                                                      ) {
                                                          handleItemClick(
                                                              displayIndex
                                                          );
                                                      }
                                                  }}
                                                  width={68}
                                                  style={{
                                                      filter:
                                                          item.clearTime ===
                                                          null
                                                              ? "grayscale(100%)"
                                                              : "none",
                                                      cursor:
                                                          item.clearTime ===
                                                          null
                                                              ? "default"
                                                              : "pointer", // Add this line to change the cursor
                                                  }}
                                              />
                                          </div>
                                      );
                                  }
                              )
                            : yourCurrentItems &&
                              yourCurrentItems.map(
                                  (item: AchievementType, index: number) => {
                                      // Calculate the index for display, starting at 1 and adjusting for the current page
                                      const displayIndex =
                                          (currentPage - 1) * itemsPerPage +
                                          index +
                                          1;
                                      return (
                                          <div
                                              key={item.achievementId} // Assuming achievementId is unique
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
                                                      position: "absolute",
                                                      top: 10,
                                                      right: 12,
                                                      zIndex: 1,
                                                  }}
                                                  onClick={() =>
                                                      handleInfoClick(item)
                                                  }
                                              />
                                              <ItemBox
                                                  src={item.badge.badgeImg}
                                                  title={item.title.titleName} // Display the index within the title
                                                  onClick={() => {
                                                      if (
                                                          item.clearTime !==
                                                          null
                                                      ) {
                                                          handleItemClick(
                                                              displayIndex
                                                          );
                                                      }
                                                  }}
                                                  width={68}
                                                  style={{
                                                      filter:
                                                          item.clearTime ===
                                                          null
                                                              ? "grayscale(100%)"
                                                              : "none",
                                                      cursor:
                                                          item.clearTime ===
                                                          null
                                                              ? "default"
                                                              : "pointer", // Add this line to change the cursor
                                                  }}
                                              />
                                          </div>
                                      );
                                  }
                              )}
                    </S.AchievementWrapper>
                    <S.PaginationContainer>
                        {Array.from({ length: totalPages }, (_, index) => (
                            <S.PaginationButton
                                key={index}
                                onClick={() => goToPage(index + 1)}
                                disabled={index + 1 === currentPage}
                                active={index + 1 === currentPage}
                            >
                                {index + 1}
                            </S.PaginationButton>
                        ))}
                    </S.PaginationContainer>
                    {yourId === 0 && (
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
                    )}
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
                                </>
                            )}
                        </S.TextWrapper>
                    </Modal>
                </ModalBg>
            )}
        </div>
    );
}
