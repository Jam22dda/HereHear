import * as S from "./RegistMusicMentPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import AlbumCover from "../../components/atoms/AlbumCover/AlbumCover";
import Button from "../../components/atoms/Button/Button";
import MessagePlus from "../../components/atoms/MessagePlus/MessagePlus";
import { useState } from "react";
import { musicItemState } from "../../states/RegistMusicAtom";
import { startTransition } from "react";
import TagSelect from "../../components/molcules/TagSelect/TagSelect";
import { useRecoilValue } from "recoil";
import { selectedTagState } from "../../states/SelectTagAtom";

export default function RegistMusicMent() {
    const selectedTagIds = useRecoilValue(selectedTagState); // 선택한 태그 리코일에서 불러오기
    console.log(selectedTagIds, "태그 리스트");
    const musicItem = useRecoilValue(musicItemState); // 선택한 노래 리코일에서 불러오기

    const [isOpenModal, setOpenModal] = useState<boolean>(false);

    const openModal = () => {
        startTransition(() => {
            // 비동기 작업의 우선순위를 낮추고, 동시에 UI가 즉각적으로 변경되지 않도록 관리
            console.log("모달열림?");
            setOpenModal(true);
        });
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

                <CircleButton option="default2" size="medium"></CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <S.RegistMusicWrapper>
                    <AlbumCover src={musicItem.src}></AlbumCover>
                    <Text size="body2" fontWeight="bold" $marginTop="40px">
                        {musicItem.songtitle}
                    </Text>
                    <Text size="body2" fontWeight="medium" $marginTop="5px">
                        {musicItem.artist}
                    </Text>

                    <Button option="tag_plus" size="mediumplus" $width="96px" $margin="20px 0 10px 0 " onClick={openModal}>
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
                                    // 여기서 tag.name이 태그의 이름이라고 가정했습니다.
                                    // 실제 사용하는 객체 구조에 맞춰야 합니다.
                                    tag={tag.name}
                                ></Button>
                            ))}
                        </S.SelectTagWrapper>
                    )}

                    <MessagePlus></MessagePlus>
                    <Button option="save" size="large" $width="132px" $margin="15px 0 0 0">
                        등록하기
                    </Button>
                </S.RegistMusicWrapper>
            </div>
        </div>
    );
}
