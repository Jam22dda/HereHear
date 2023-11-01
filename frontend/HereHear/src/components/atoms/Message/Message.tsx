import * as S from "./Message.styles";
import { Text } from "../Text/Text.styles";

export default function Message() {
    return (
        <S.Message>
            <Text size="small1" fontWeight="medium">
                사연적는 곳에 어쩌고저쩌고 내가 좋아하는노래인데 헤어졌어요..
            </Text>
            <S.WriterWrapper>
                <Text size="small3" fontWeight="medium">
                    0월 0일 00시
                </Text>
                {/* TODO: 캐릭터 넣기, 날짜랑 작성자 묶기 */}
                <Text size="small3" fontWeight="medium">
                    {"000"}님
                </Text>
            </S.WriterWrapper>
        </S.Message>
    );
}
