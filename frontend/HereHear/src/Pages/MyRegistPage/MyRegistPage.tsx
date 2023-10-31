import * as S from "./MyRegistPage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import { Text } from "../../components/atoms/Text/Text.styles";
import BTS_answer from "../../../public/images/BTS_answer.jpg";

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
                    <MusicItem
                        src={BTS_answer}
                        title="Answer : Love Myself"
                        artist="방탄소년단"
                    ></MusicItem>
                </S.MyRegistWrapper>
                <S.MyRegistWrapper>
                    <MusicItem
                        src={BTS_answer}
                        title="Answer : Love Myself"
                        artist="방탄소년단"
                    ></MusicItem>
                </S.MyRegistWrapper>
            </div>
        </div>
    );
}
