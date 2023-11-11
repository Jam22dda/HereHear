import * as S from "./Message.styles";
import { Text } from "../Text/Text.styles";
import { Image } from "../Image/Image";
import { SignUpInfoAtom } from "../../../states/SignUpAtoms";
import { useRecoilValue } from "recoil";
import { useNavigate } from "react-router-dom";

interface MessageProps {
    comment: string;
    createTime: string;
    nickname: string;
    characterImage: string;
    musicRegistId: number;
}

export default function Message({
    comment,
    createTime,
    nickname,
    characterImage,
    musicRegistId,
}: MessageProps) {
    const navigate = useNavigate();
    const navigatePage = (path: string) => {
        navigate(path);
    };
    const mySignUpInfo = useRecoilValue(SignUpInfoAtom);
    const myId = mySignUpInfo.memberId;
    const musicRegistIdNumber = parseInt(musicRegistId, 10);

    return (
        <S.Message>
            <Text size="small1" fontWeight="medium">
                {comment}
            </Text>
            <S.WriterWrapper>
                <Text size="small3" fontWeight="medium">
                    {createTime}
                </Text>
                <S.userWrapper
                    onClick={() => {
                        myId === musicRegistIdNumber
                            ? navigatePage("/mypage")
                            : navigatePage(`/mypage/${musicRegistIdNumber}`);
                    }}
                >
                    <Image src={characterImage} width={2}></Image>
                    <Text
                        size="small3"
                        fontWeight="medium"
                        $margin="5px 2px 0 0"
                        $textAlign="center"
                    >
                        {nickname}님
                    </Text>
                </S.userWrapper>
            </S.WriterWrapper>
        </S.Message>
    );
}
