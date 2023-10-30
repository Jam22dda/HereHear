import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
// import * as S from "./SearchRegistMusic.styles";
import Input from "../../components/atoms/Input/Input";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";

const searchregistmusic = [{ name: "안녕" }, { name: "하녕" }, { name: "가녕" }, { name: "바녕" }];
export default function SearchRegistMusic() {
    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium"></CircleButton>
                <Text>음악 등록</Text>
                <Input>돋보기</Input>
                {searchregistmusic.map((item, index) => (
                    <MusicItem key={index} title={item.name} />
                ))}
            </div>
        </div>
    );
}
