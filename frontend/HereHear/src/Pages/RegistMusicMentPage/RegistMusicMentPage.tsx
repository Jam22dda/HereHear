import * as S from "./RegistMusicMentPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import AlbumCover from "../../components/atoms/AlbumCover/AlbumCover";
import Button from "../../components/atoms/Button/Button";
import MessagePlus from "../../components/atoms/MessagePlus/MessagePlus";
import { useCallback, useState } from "react";
// import TagSelect from "../../components/molcules/TagSelect/TagSselect";

export default function RegistMusicMent() {
    // const [tags, setTags] = useState([{ tag: "산책" }, { tag: "청량" }, { tag: "주말" }]);
    // const [newTags, setNewTags] = useState("");

    // const addTag = () => {
    //     if (newTags) {
    //         setTags([...tags, { tag: newTags }]);
    //         setNewTags("" );
    //     }
    // };
    const [isOpenModal, setOpenModal] = useState<boolean>(false);

    const onClickToggleModal = useCallback(() => {
        setOpenModal(!isOpenModal);
    }, [isOpenModal]);

    return (
        <div id="display">
            <div className="container">
                {/* {isOpenModal && <TagSelect onClickToggleModal={onClickToggleModal}>이곳에 children이 들어갑니다.</TagSelect>} */}

                <CircleButton option="default2" size="medium"></CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <S.RegistMusicWrapper>
                    <AlbumCover></AlbumCover>
                    <Text size="body2" fontWeight="bold" $marginTop="40px">
                        가수
                    </Text>
                    <Text size="body2" fontWeight="medium" $marginTop="5px">
                        제목
                    </Text>

                    <Button option="tag_plus" size="mediumplus" $width="96px" $margin="20px 0 0 0 " onClick={onClickToggleModal}>
                        태그추가 +
                    </Button>
                    {/* TODO: 태그 선택전에는 안보이게, 태그 선택하면 그 값을 여기로 가져오기 */}
                    {/* <S.SelectTagWrapper>
                        {tags.map((item, index) => (
                            <Button option="tag1" size="medium" $margin="5px" $width="80px" key={index} tag={item.tag}></Button>
                        ))}
                    </S.SelectTagWrapper> */}

                    <MessagePlus></MessagePlus>
                    <Button option="save" size="large" $width="132px" $margin="15px 0 0 0">
                        등록하기
                    </Button>
                </S.RegistMusicWrapper>
            </div>
        </div>
    );
}
