// import { useParams } from "react-router-dom";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Image } from "../../components/atoms/Image/Image";
import iconBack from "../../../public/images/icon-back.png";
import * as S from "./MyPage.styles";
import monziSleeping from "../../../public/images/monzi-sleeping.png";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconEdit from "../../assets/MyPage/icon-edit.png";
import Button from "../../components/atoms/Button/Button";
import iconHeartbeat from "../../../public/images/icon-heartbeat.png";
import iconLp from "../../assets/MyPage/icon-lp.png";
import ItemBox from "../../components/molcules/ItemBox/ItemBox";
import Navbar from "../../components/molcules/Navbar/Navbar";
import iconLikemusic from "../../assets/MyPage/icon-likemusic.png";
import iconBadge from "../../assets/MyPage/icon-badge.png";
import iconMystatistics from "../../assets/MyPage/icon-mystatistics.png";

const mypage = [
    { src: iconLikemusic, name: "좋아요한 노래" },
    { src: iconLp, name: "등록한 노래" },
    { src: iconBadge, name: "뱃지" },
    { src: iconMystatistics, name: "개인 통계" },
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
                        <S.OverlappingImageWrapper>
                            <Image
                                src={iconHeartbeat}
                                width={60}
                                height={72}
                                $unit="px"
                            ></Image>
                        </S.OverlappingImageWrapper>
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
