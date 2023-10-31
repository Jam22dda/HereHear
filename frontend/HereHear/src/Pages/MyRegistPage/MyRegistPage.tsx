import * as S from "./MyRegistPage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import { Text } from "../../components/atoms/Text/Text.styles";

export default function MyRegistPage() {
    return (
        <div id="display">
            <div className="container">
                <Text
                    size="subtitle1"
                    fontWeight="bold"
                    $margin="50px 0 30px 0"
                >
                    내가 등록한 노래
                </Text>
                <S.MyRegistWrapper>
                    <MusicItem></MusicItem>
                </S.MyRegistWrapper>
                <S.MyRegistWrapper>
                    <MusicItem></MusicItem>
                </S.MyRegistWrapper>
            </div>
        </div>
    );
}
