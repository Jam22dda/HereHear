import * as S from "./Message.styles";
import { Text } from "../Text/Text.styles";
import { Image } from "../Image/Image";
interface MessageProps {
    comment: string;
    createTime: string;
    nickname: string;
    characterImage: string;
}

export default function Message({ comment, createTime, nickname, characterImage }: MessageProps) {
    // console.log(characterImage);
    return (
        <S.Message>
            <Text size="small1" fontWeight="medium">
                {comment}
            </Text>
            <S.WriterWrapper>
                <Text size="small3" fontWeight="medium">
                    {createTime}
                </Text>
                {/* TODO: 캐릭터 넣기, 날짜랑 작성자 묶기 */}
                <S.userWrapper>
                    <Image src={characterImage} width={2}></Image>
                    <Text size="small3" fontWeight="medium" $margin="5px 2px 0 0" $textAlign="center">
                        {nickname}님
                    </Text>
                </S.userWrapper>
            </S.WriterWrapper>
        </S.Message>
    );
}
