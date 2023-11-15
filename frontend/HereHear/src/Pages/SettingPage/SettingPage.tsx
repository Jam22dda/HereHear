import { useNavigate } from "react-router-dom";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Image } from "../../components/atoms/Image/Image";
import iconBack from "../../assets/CircleButton/icon-back.png";
import * as S from "./SettingPage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import Modal from "../../components/atoms/Modal/Modal";
import { ModalBg } from "../../components/atoms/Modal/Modal.styles";
import { useState } from "react";
import Button from "../../components/atoms/Button/Button";
import { usePostWearOs } from "../../apis/Mypage/Mutations/usePostWearOs";
import { useRecoilState } from "recoil";
import { GetKeyAtom } from "../../states/MypageAtoms";
import { useGetWearOs } from "../../apis/Mypage/Quries/useGetWearOs";

export default function MyRegistPage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [key, setKey] = useRecoilState(GetKeyAtom);

    const toggleModal = () => {
        setIsModalOpen((prev) => !prev);
    };

    const { mutate: postWearOsMutate } = usePostWearOs();
    const MyKey = useGetWearOs();
    console.log(key);

    const GetWearOs = () => {
        if (key === "") {
            postWearOsMutate(
                {},
                {
                    onSuccess: (data) => {
                        console.log(
                            "핀번호 받기 성공:",
                            data.data.personalCode
                        );
                        setKey(data.data.personalCode);
                        localStorage.setItem("myKey", data.data.personalCode);
                    },
                    onError: (error) => {
                        console.error("오류 발생:", error);
                    },
                }
            );
            toggleModal();
        } else {
            setKey(MyKey && MyKey.personalCode);
            toggleModal();
        }
    };
    return (
        <div id="display">
            <div className="container">
                <CircleButton
                    option="default2"
                    size="medium"
                    onClick={() => {
                        navigatePage("/mypage");
                    }}
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
                    $margin="20px 0 48px 0"
                >
                    설정
                </Text>
                <S.SettingWrapper>
                    <S.PinBox onClick={GetWearOs}>
                        <Text size="body1">핀 번호 받기</Text>
                    </S.PinBox>
                    <S.PinBox>
                        <Text size="body1">로그아웃</Text>
                    </S.PinBox>
                </S.SettingWrapper>
            </div>
            {isModalOpen && (
                <ModalBg>
                    <Modal toggleModal={() => toggleModal()}>
                        <S.SettingModalWrapper>
                            <Text fontWeight="bold" $margin="0 0 20px 0">
                                핀 번호
                            </Text>
                            <Text>{key}</Text>
                            <Button
                                $width="80px"
                                $margin="20px 0 0 0"
                                onClick={toggleModal}
                            >
                                확인
                            </Button>
                        </S.SettingModalWrapper>
                    </Modal>
                </ModalBg>
            )}
        </div>
    );
}
