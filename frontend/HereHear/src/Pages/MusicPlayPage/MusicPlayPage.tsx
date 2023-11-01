import * as S from "./MusicPlayPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import AlbumCover from "../../components/atoms/AlbumCover/AlbumCover";
import Button from "../../components/atoms/Button/Button";
import Message from "../../components/atoms/Message/Message";
import PlayBtn from "../../components/molcules/Play/Play";

export default function MusicPlay() {
    const tags = [{ tag: "산책" }, { tag: "청량" }, { tag: "주말" }];
    const music = { artist: "악동뮤지션", title: "LOVELEE" };
    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium"></CircleButton>
                <S.MusicPlayWrapper>
                    <S.SelectTagWrapper>
                        {tags.map((item, index) => (
                            <Button option="unfollow" size="mediumplus" $margin="5px" $width="80px" key={index} tag={item.tag}></Button>
                        ))}
                    </S.SelectTagWrapper>
                    <AlbumCover></AlbumCover>
                    <Text size="body2" fontWeight="bold" $marginTop="40px">
                        {music.artist}
                    </Text>
                    <Text size="body2" fontWeight="medium" $marginTop="5px">
                        {music.title}
                    </Text>
                    <Message></Message>
                    <PlayBtn></PlayBtn>
                </S.MusicPlayWrapper>
            </div>
        </div>
    );
}
