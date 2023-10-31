import * as S from "./SearchRegistMusic.styles";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
// import * as S from "./SearchRegistMusic.styles";
import Input from "../../components/atoms/Input/Input";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import albumart1 from "../../../public/images/album1.png";
import albumart2 from "../../../public/images/album2.png";
import albumart3 from "../../../public/images/album3.png";

const searchregistmusic = [
    { src: albumart1, artist: "악동뮤지션", title: "LOVELEE" },
    { src: albumart2, artist: "KOREANGROOVE", title: "BLACK GROOVE" },
    { src: albumart3, artist: "권은비", title: "UnderWater" },
];

export default function SearchRegistMusic() {
    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium"></CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <Input></Input>

                {searchregistmusic.map((item, index) => (
                    <S.MusicItemWrapper>
                        <MusicItem key={index} src={item.src} title={item.title} artist={item.artist} />
                        <CircleButton option="gradDeActivated" size="mediumplus"></CircleButton>
                        {/* TODO: 버튼 클릭이벤트- 음악 저장하고 음악 등록페이지로 이동 */}
                    </S.MusicItemWrapper>
                ))}
            </div>
        </div>
    );
}
