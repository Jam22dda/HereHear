// import { useParams } from "react-router-dom";
import { Image } from "../../components/atoms/Image/Image";
import * as S from "./YourPage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import Button from "../../components/atoms/Button/Button";
import iconLp from "../../assets/MyPage/icon-lp.png";
import ItemBox from "../../components/molcules/ItemBox/ItemBox";
import Navbar from "../../components/molcules/Navbar/Navbar";
import iconLikemusic from "../../assets/MyPage/icon-likemusic.png";
import iconBadge from "../../assets/MyPage/badges.png";
import iconMystatistics from "../../assets/MyPage/icon-mystatistics.png";
import { useNavigate } from "react-router-dom";
import icon1102DJ from "../../../public/images/icon-1102dj.png";
import { useGetYourinfo } from "../../apis/Mypage/Quries/useGetYourInfo";

const mypage = [
    { src: iconLikemusic, name: "좋아요한 노래", params: "/like" },
    { src: iconLp, name: "등록한 노래", params: "/myRegist" },
    { src: iconBadge, name: "뱃지", params: "/achievement" },
    { src: iconMystatistics, name: "개인 통계", params: "/myStatistics" },
];

export default function MyPage() {
    // const { id } = useParams();

    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const YourInfo = useGetYourinfo(14);

    return (
        <div id="display">
            <div className="container">
                <S.FollowBtnWrapper>
                    <Button option="follow" size="medium" $width="70px">
                        팔로우
                    </Button>
                </S.FollowBtnWrapper>
                <S.YourPageWrapper>
                    <S.Profile>
                        <Image
                            src={
                                YourInfo &&
                                YourInfo.profileCharacter.characterImage
                            }
                            width={140}
                            height={140}
                            $unit="px"
                        ></Image>
                    </S.Profile>
                    <S.YourdataWrapper>
                        <Image
                            src={icon1102DJ}
                            width={24}
                            height={24}
                            $unit="px"
                            $margin="0 4px 4px 0"
                        ></Image>
                        <Text size="body1" fontWeight="bold">
                            신규 DJ
                        </Text>
                        <Text size="body1" $marginLeft="4px">
                            {YourInfo && YourInfo.nickname}
                        </Text>
                        <Text size="body2" $marginLeft="4px">
                            님
                        </Text>
                    </S.YourdataWrapper>
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
                    <S.YourItemWrapper>
                        {mypage.map((item, index) => (
                            <ItemBox
                                key={index}
                                src={item.src}
                                title={item.name}
                                onClick={() => navigatePage(item.params)}
                            />
                        ))}
                    </S.YourItemWrapper>
                </S.YourPageWrapper>
                <Navbar></Navbar>
            </div>
        </div>
    );
}
