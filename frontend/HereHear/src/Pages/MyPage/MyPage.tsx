// import { useParams } from "react-router-dom";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Image } from "../../components/atoms/Image/Image";
import iconBack from "../../../public/images/icon-back.png";
import * as S from "./MyPage.styles";
import monziSleeping from "../../../public/images/monzi-sleeping.png";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconEdit from "../../assets/MyPage/icon-edit.png";
import Button from "../../components/atoms/Button/Button";
import monziExercising from "../../../public/images/monzi-exercising.png";
import monziFighting from "../../../public/images/monzi-fighting.png";
import monziHerehear from "../../../public/images/monzi-herehear.png";
import monziHippop from "../../../public/images/monzi-hippop.png";
import ItemBox from "../../components/molcules/ItemBox/ItemBox";
import Navbar from "../../components/molcules/Navbar/Navbar";

const mypage = [
    { src: monziExercising, name: "좋아요한 노래" },
    { src: monziFighting, name: "등록한 노래" },
    { src: monziHerehear, name: "뱃지" },
    { src: monziHippop, name: "개인 통계" },
];

export default function MyPage() {
    // const { id } = useParams();

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium">
                    <Image
                        src={iconBack}
                        width={10}
                        height={18}
                        $unit="px"
                    ></Image>
                </CircleButton>
                <S.MyPageWrapper>
                    <S.Profile>
                        <Image
                            src={monziSleeping}
                            width={140}
                            height={140}
                            $unit="px"
                        ></Image>
                    </S.Profile>
                    <S.MydataWrapper>
                        <Text size="body1" fontWeight="bold">
                            신규 DJ
                        </Text>
                        <Text size="body1" $marginLeft="4px">
                            뾰롱뾰
                        </Text>
                        <Text size="body2" $marginLeft="4px">
                            님
                        </Text>
                        <Image
                            src={iconEdit}
                            width={16}
                            height={16}
                            $unit="px"
                            $margin="0 0 0 4px"
                        ></Image>
                    </S.MydataWrapper>
                    <S.FollowWrapper>
                        <Button
                            option="tag_plus"
                            size="largeplus"
                            $width="130px"
                        >
                            팔로잉
                        </Button>
                        <Button
                            option="tag_plus"
                            size="largeplus"
                            $width="130px"
                        >
                            팔로워
                        </Button>
                    </S.FollowWrapper>
                    <S.MyItemWrapper>
                        {mypage.map((item, index) => (
                            <ItemBox
                                key={index}
                                src={item.src}
                                title={item.name}
                            />
                        ))}
                    </S.MyItemWrapper>
                </S.MyPageWrapper>
                <Navbar></Navbar>
            </div>
        </div>
    );
}
