import * as S from "./NicknamePage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import Input from "../../components/atoms/Input/Input";
import Button from "../../components/atoms/Button/Button";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { SignUpInfoAtom } from "../../states/SignUpAtoms";
import React, { ChangeEvent, useState } from "react";
import Modal from "../../components/atoms/Modal/Modal";
import { ModalBg } from "../../components/atoms/Modal/Modal.styles";
import { Image } from "../../components/atoms/Image/Image";
import monziHerehear from "../../../public/images/monzi-herehear.png";
import { useSearchParams } from "react-router-dom";
import { useGetCheckNickname } from "../../apis/Login/Quries/useGetCheckNickname";
import { useDebouncedCallback } from "use-debounce";

export default function NicknamePage() {
    const navigate = useNavigate(); // useNavigate 훅 사용
    const [signUpInfo, setSignUpInfo] = useRecoilState(SignUpInfoAtom);
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [searchParams] = useSearchParams();
    const id = searchParams.get("id");
    const memberId = id ? Number(id) : undefined;
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

    if (memberId === undefined || isNaN(memberId)) {
        console.error("Invalid member ID");
    }

    const { data: checkNicknameData } = useGetCheckNickname(debouncedNickname);

    const navigatePage = (path: string) => {
        if (signUpInfo.nickname === "") {
            toggleModal();
            setIsBlanked(true);
        } else if (checkNicknameData?.isAvailable === false) {
            toggleModal();
            setIsDuplicated(true);
        } else {
            navigate(path);
        }
    };

    const handleInputChange = (e: ChangeEvent<HTMLInputElement>) => {
        const newNickname = e.target.value;
        setSignUpInfo({
            ...signUpInfo,
            memberId: memberId,
            nickname: newNickname,
        });
        debouncedCheck(newNickname);
    };

    const toggleModal = () => {
        setIsModalOpen((prev) => !prev);
    };

    const handleBlanked = () => {
        toggleModal();
        setIsBlanked(false);
    };

    const handleDuplicated = () => {
        toggleModal();
        setIsDuplicated(false);
    };

    return (
        <div id="display">
            <div className="container">
                <S.NicknamePageWrapper>
                    <Text size="heading1" fontWeight="bold" $marginTop="150px">
                        HERE HEAR에
                    </Text>
                    <Text size="heading1" fontWeight="bold">
                        오신것을 환영합니다!
                    </Text>
                    <div>
                        <Text
                            size="subtitle1"
                            fontWeight="bold"
                            $marginTop="108px"
                        >
                            서비스를 이용하기 전,
                        </Text>
                        <Text
                            size="subtitle1"
                            fontWeight="bold"
                            $margin="0 0 12px 0"
                        >
                            본인의 닉네임을 정해주세요!
                        </Text>
                    </div>
                    <Input
                        value={signUpInfo.nickname}
                        onChange={handleInputChange}
                        $placeholder="닉네임을 입력하세요"
                    />
                    <Button
                        $width="132px"
                        $margin="150px 0 0 0"
                        onClick={() => navigatePage("/character")}
                    >
                        저장하기
                    </Button>
                </S.NicknamePageWrapper>
            </div>
            {isModalOpen && (
                <ModalBg>
                    <Modal toggleModal={() => toggleModal()}>
                        {isBlanked && (
                            <S.NicknamePageWrapper>
                                <Image
                                    src={monziHerehear}
                                    width={100}
                                    height={100}
                                    $unit="px"
                                    $margin="0 0 30px 0"
                                ></Image>
                                <h2>닉네임을 입력해주세요!</h2>
                                <Button
                                    onClick={handleBlanked}
                                    $width="130px"
                                    $margin="32px 0 0 0"
                                >
                                    다시 입력하기
                                </Button>
                            </S.NicknamePageWrapper>
                        )}
                        {isDuplicated && (
                            <S.NicknamePageWrapper>
                                <Image
                                    src={monziHerehear}
                                    width={100}
                                    height={100}
                                    $unit="px"
                                    $margin="0 0 30px 0"
                                ></Image>
                                <h2>닉네임이 중복되었습니다!</h2>
                                <Button
                                    onClick={handleDuplicated}
                                    $width="130px"
                                    $margin="32px 0 0 0"
                                >
                                    다시 입력하기
                                </Button>
                            </S.NicknamePageWrapper>
                        )}
                    </Modal>
                </ModalBg>
            )}
        </div>
    );
}
