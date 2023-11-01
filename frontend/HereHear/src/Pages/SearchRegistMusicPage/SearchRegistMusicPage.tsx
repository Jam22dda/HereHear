import * as S from "./SearchRegistMusicPage.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import Input from "../../components/atoms/Input/Input";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import Button from "../../components/atoms/Button/Button";
import { useGetSearchMusic } from "../../apis/Music/Quries/useGetSearchMusic";
import { MusicData } from "../../types/music";

export default function SearchRegistMusic() {
    const keyword = "NewJeans";

    const SearchMusic = useGetSearchMusic(keyword);
    console.log(SearchMusic.searchMusic.data, "음악검색되나여?");
    const searchregistmusicList1 = SearchMusic.searchMusic.data;

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium"></CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <S.InputWrapper>
                    <Input $placeholder="🔍"></Input>
                    <Button option="follow" $hight="44px" $width="60px" $borderRadius="12px" color="white1" size="mediumplus">
                        검색
                    </Button>
                </S.InputWrapper>

                {searchregistmusicList1.map((item: MusicData, index: number) => (
                    <S.MusicItemWrapper key={index}>
                        <MusicItem src={item.album_images[2].url} title={item.subject} artist={item.artists[0].name} />
                        <CircleButton option="gradDeActivated" size="mediumplus"></CircleButton>
                        {/* TODO: 버튼 클릭이벤트- 음악 저장하고 음악 등록페이지로 이동 */}
                    </S.MusicItemWrapper>
                ))}
            </div>
        </div>
    );
}
