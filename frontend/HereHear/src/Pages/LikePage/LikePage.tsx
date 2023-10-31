import * as S from "./LikePage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconHeart from "../../../public/images/icon-heart.png";
import iconEmptyheart from "../../../public/images/icon-emptyheart.png";
import { Image } from "../../components/atoms/Image/Image";
import { Text } from "../../components/atoms/Text/Text.styles";

export default function LikePage() {
    return (
        <div id="display">
            <div className="container">
                <Text
                    size="subtitle1"
                    fontWeight="bold"
                    $margin="50px 0 30px 0"
                >
                    내가 좋아요한 노래
                </Text>
                <S.LikeItemWrapper>
                    <MusicItem></MusicItem>
                    <CircleButton option="gradDeActivated" size="large">
                        <Image
                            src={iconHeart}
                            width={24}
                            height={20}
                            $unit="px"
                        ></Image>
                    </CircleButton>
                </S.LikeItemWrapper>
                <S.LikeItemWrapper>
                    <MusicItem></MusicItem>
                    <CircleButton option="gradActivated" size="large">
                        <Image
                            src={iconEmptyheart}
                            width={24}
                            height={20}
                            $unit="px"
                        ></Image>
                    </CircleButton>
                </S.LikeItemWrapper>
            </div>
        </div>
    );
}
