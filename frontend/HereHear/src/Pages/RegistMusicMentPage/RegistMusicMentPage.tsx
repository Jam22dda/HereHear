import * as S from "./RegistMusicMentPage.styles";
import React, { useEffect, useRef } from "react";
import { useNavigate } from "react-router-dom";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import AlbumCover from "../../components/atoms/AlbumCover/AlbumCover";
import Button from "../../components/atoms/Button/Button";
import MessagePlus from "../../components/atoms/MessagePlus/MessagePlus";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { Image } from "../../components/atoms/Image/Image";
import { useState } from "react";
import { musicItemState } from "../../states/RegistMusicAtom";
import { startTransition } from "react";
import TagSelect from "../../components/molcules/TagSelect/TagSelect";
import { useRecoilValue, useRecoilState } from "recoil";
import { selectedTagState } from "../../states/SelectTagAtom";
import { useAddMusic } from "../../apis/Music/Mutations/useAddMusic";
import { AddMusicInfo } from "../../types/music";

export default function RegistMusicMent() {
    const locationRef = useRef<{
        latitude: number | null;
        longitude: number | null;
    }>({ latitude: null, longitude: null });

    useEffect(() => {
        // 현재 위치 가져오기

        navigator.geolocation.getCurrentPosition(
            (position) => {
                locationRef.current = {
                    latitude: position.coords.latitude,
                    longitude: position.coords.longitude,
                };
                // console.log("Latitude:", position.coords.latitude, "Longitude:", position.coords.longitude);
            },
            (error) => {
                alert("사용자의 위치 정보 제공에 동의해주세요 !");
                // TODO:위치정보 제공 동의 알림 확인버튼 눌렀을 때, 위치설정하는 페이지로 가도록...앱이랑 연동 되면 ..
                console.error(
                    "Error Code = " + error.code + " - " + error.message
                );
            }
        );
    }, []);
    // const selectedTagIds = useRecoilValue(selectedTagState); // 선택한 태그 리코일에서 불러오기
    // console.log(selectedTagIds, "태그 리스트");
    const [selectedTagIds, setSelectedTagIds] =
        useRecoilState(selectedTagState);
    const musicItem = useRecoilValue(musicItemState); // 선택한 노래 리코일에서 불러오기

    const [isOpenModal, setOpenModal] = useState<boolean>(false);
    const navigate = useNavigate();

    const handleBack = () => {
        // 태그 상태를 초기화하고
        setSelectedTagIds([]);
        // console.log(selectedTagIds);
        // 이전 페이지로 이동합니다.
        navigate(-1);
    };

    const openModal = () => {
        startTransition(() => {
            // 비동기 작업의 우선순위를 낮추고, 동시에 UI가 즉각적으로 변경되지 않도록 관리
            setOpenModal(true);
        });
    };

    const messageInputRef = useRef<HTMLTextAreaElement>(null);

    const addMusicMutation = useAddMusic();
    const registerMusic = () => {
        const { latitude, longitude } = locationRef.current;
        if (latitude && longitude) {
            const musicInfo: AddMusicInfo = {
                lat: latitude, // 경도
                lng: longitude, // 위도
                comment: messageInputRef.current?.value || "", // 사용자의 코멘트
                subject: musicItem.songtitle, // 노래 제목
                singer: musicItem.artist, // 아티스트
                albumImg: musicItem.src, // 앨범 이미지 URL
                releaseTime: musicItem.releaseTime, // 발매 시간
                musicOccasionIds: selectedTagIds.map((tag) => tag.id), // 선택된 태그 ID들
            };
            // console.log(musicInfo, "musicInfo 잘 들어가?");
            addMusicMutation.mutate(musicInfo);
            setSelectedTagIds([]);
            navigate("/core");
        }
    };
    return (
        <div id="display">
            <div className="container">
                {isOpenModal && (
                    <>
                        <S.BgWrapper>
                            <TagSelect setOpenModal={setOpenModal} />
                        </S.BgWrapper>
                    </>
                )}

                <CircleButton
                    option="default2"
                    size="medium"
                    onClick={handleBack}
                >
                    <Image
                        src={iconBack}
                        width={10}
                        height={18}
                        $unit="px"
                    ></Image>
                </CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <S.RegistMusicWrapper>
                    <AlbumCover src={musicItem.src}></AlbumCover>
                    <Text
                        $textAlign="center"
                        size="body2"
                        fontWeight="bold"
                        $marginTop="40px"
                    >
                        {musicItem.songtitle}
                    </Text>
                    <Text
                        $textAlign="center"
                        size="body2"
                        fontWeight="medium"
                        $marginTop="5px"
                    >
                        {musicItem.artist}
                    </Text>

                    <Button
                        option="tag_plus"
                        size="mediumplus"
                        $width="96px"
                        $margin="20px 0 10px 0 "
                        onClick={openModal}
                    >
                        태그추가 +
                    </Button>

                    {selectedTagIds.length > 0 && (
                        <S.SelectTagWrapper>
                            {selectedTagIds.map((tag, index) => (
                                <Button
                                    option="tag1"
                                    size="medium"
                                    $margin="5px"
                                    $width="80px"
                                    key={index}
                                    tag={tag.name}
                                ></Button>
                            ))}
                        </S.SelectTagWrapper>
                    )}

                    <MessagePlus
                        ref={messageInputRef}
                        $placeholder="이곳에 멘트를 작성 해 주세요."
                        onMessageChange={(message) => {
                            message;
                        }}
                    ></MessagePlus>
                    <Button
                        option="save"
                        size="large"
                        $width="132px"
                        $margin="15px 0 0 0"
                        onClick={() => {
                            registerMusic();
                            window.location.reload();
                        }}
                    >
                        등록하기
                    </Button>
                </S.RegistMusicWrapper>
            </div>
        </div>
    );
}
