import * as S from "./StatisticPage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import { Image } from "../../components/atoms/Image/Image";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { useNavigate } from "react-router-dom";
import monzi from "../../../public/images/monzi-herehear.png";
// import monziSleep from "../../../public/images/monzi-sleeping.png";
import iconBack from "../../assets/CircleButton/icon-back.png";

export default function StatisticsPage() {
    const navigate = useNavigate();

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>
                <S.readyPageWrapper>
                    <Text $textAlign="center" $margin="40vw 0 10vw">
                        정확한 통계 수집을 위해
                        <br />
                        아직 준비중이에요!
                    </Text>

                    <Image width={10} src={monzi}></Image>
                    {/* <Image width={10} src={monziSleep}></Image> */}
                </S.readyPageWrapper>
            </div>
        </div>
    );
}