import * as S from "./Message.styles";
import { Text } from "../Text/Text.styles";
import { Image } from "../Image/Image";
import { SignUpInfoAtom } from "../../../states/SignUpAtoms";
import { useRecoilValue, useRecoilState } from "recoil";
import { YourIdAtom } from "../../../states/MypageAtoms";
import { useNavigate } from "react-router-dom";

interface MessageProps {
    comment: string;
    createTime: string;
    nickname: string;
    characterImage: string;
    musicRegistId: number;
}

export default function Message({ comment, createTime, nickname, characterImage, musicRegistId }: MessageProps) {
    const navigate = useNavigate();
    const navigatePage = (path: string) => {
        navigate(path);
    };
    const mySignUpInfo = useRecoilValue(SignUpInfoAtom);
    const [, setYourId] = useRecoilState(YourIdAtom);
    const myId = mySignUpInfo.memberId;
    const musicRegistIdNumber = musicRegistId;

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
                        {
                            myId == musicRegistIdNumber ? navigatePage("/mypage") : navigatePage(`/mypage/${musicRegistIdNumber}`);
                            myId == musicRegistIdNumber ? setYourId(0) : setYourId(musicRegistIdNumber);
                        }
                    }}
                >
                    <Image src={characterImage} width={1.8}></Image>
                    <Text size="small3" fontWeight="medium" $margin="5px 2px 0 5px" $textAlign="center">
                        {nickname}님
                    </Text>
                </S.userWrapper>
            </S.WriterWrapper>
        </S.Message>
    );
}
