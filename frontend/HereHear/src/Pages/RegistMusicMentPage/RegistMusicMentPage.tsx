import * as S from "./RegistMusicMentPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import AlbumCover from "../../components/atoms/AlbumCover/AlbumCover";
import Button from "../../components/atoms/Button/Button";
import MessagePlus from "../../components/atoms/MessagePlus/MessagePlus";

const selecttag = [{ tag: "산책" }, { tag: "청량" }, { tag: "주말" }];

export default function RegistMusicMent() {
    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" style={{ marginTop: "20px" }}></CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <S.RegistMusicWrapper>
                    <AlbumCover></AlbumCover>
                    <Text size="body2" fontWeight="bold" $marginTop="44px">
                        가수
                    </Text>
                    <Text size="body2" fontWeight="medium" $marginTop="16px">
                        제목
                    </Text>

                    <Button option="tag_plus" size="mediumplus" $width="96px" $margin="30px 0 10px 0 ">
                        태그추가 +
                    </Button>
                    {/* TODO: 태그 선택전에는 안보이게, 태그 선택하면 그 값을 여기로 가져오기 */}
                    {selecttag.map((item, index) => (
                        <Button key={index}></Button>
                    ))}

                    <MessagePlus></MessagePlus>
                    <Button option="save" size="large" $width="132px" $margin="150px 0 0 0">
                        등록하기
                    </Button>
                </S.RegistMusicWrapper>
            </div>
        </div>
    );
}
