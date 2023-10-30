import * as S from "./Message.styles";
import { Text } from "../Text/Text.styles";

export default function Message() {
    return (
        <S.Message>
            <Text size="small1" fontWeight="medium">
                사연적는 곳
            </Text>
            <Text size="small3" fontWeight="medium">
                0월 0일 00시
            </Text>
            //TODO: 캐릭터 넣기, 날짜랑 작성자 묶기
            <Text size="small3" fontWeight="medium">
                {"000"}님
            </Text>
        </S.Message>
    );
}
