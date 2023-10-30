import * as S from "./NicknamePage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import Input from "../../components/atoms/Input/Input";
import Button from "../../components/atoms/Button/Button";
import { useNavigate } from "react-router-dom";

export default function NicknamePage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };
    return (
        <div id="display">
            <S.NicknamePageWrapper className="container">
                <Text size="heading1" fontWeight="bold" $marginTop="150px">
                    HERE HEAR에
                </Text>
                <Text size="heading1" fontWeight="bold">
                    오신것을 환영합니다!
                </Text>
                <Text size="subtitle1" fontWeight="bold" $marginTop="108px">
                    서비스를 이용하기 전,
                </Text>
                <Text size="subtitle1" fontWeight="bold" $margin="0 0 8px 0">
                    본인의 닉네임을 정해주세요!
                </Text>
                <Input></Input>
                <Button
                    option="save"
                    size="large"
                    $width="132px"
                    $margin="150px 0 0 0"
                    onClick={() => navigatePage("/character")}
                >
                    저장하기
                </Button>
            </S.NicknamePageWrapper>
        </div>
    );
}
