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
import { useGetYourinfo } from "../../apis/Mypage/Quries/useGetYourInfo";
import { YourIdAtom } from "../../states/MypageAtoms";
import { useRecoilState } from "recoil";
import { useGetYourFollowing } from "../../apis/YourPage/Quries/useGetYourFollowing";
import { useGetYourFollower } from "../../apis/YourPage/Quries/useGetYourFollower";
import { FollowingType } from "../../types/user";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { useGetFollowing } from "../../apis/Mypage/Quries/useGetFollowing";
import { useEffect, useState } from "react";
import { useUnFollow } from "../../apis/Mypage/Mutations/useUnFollow";
import { useFollow } from "../../apis/Mypage/Mutations/useFollow";
import { memberId } from "../../types/user";
import { useGetYourAchievement } from "../../apis/Mypage/Quries/useGetMyAchievement";

const mypage = [
    { src: iconLikemusic, name: "좋아요한 노래", params: "/like" },
    { src: iconLp, name: "등록한 노래", params: "/myRegist" },
    { src: iconBadge, name: "뱃지", params: "/achievement" },
    { src: iconMystatistics, name: "개인 통계", params: "/myStatistics" },
];

export default function YourPage() {
    const [yourId, setYourId] = useRecoilState(YourIdAtom);
    const navigate = useNavigate();

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const YourInfo = useGetYourinfo(Number(yourId));
    console.log(YourInfo?.achievementId);
    const YourFollowing: FollowingType[] = useGetYourFollowing(yourId);
    const YourFollower: FollowingType[] = useGetYourFollower(yourId);
    const Following: FollowingType[] = useGetFollowing();
    const [isFollowing, setIsFollowing] = useState(false);
    const { mutate: FollowUser } = useFollow();
    const { mutate: unFollowUser } = useUnFollow();
    const YourAchievement = useGetYourAchievement(YourInfo?.achievementId);
    console.log(YourAchievement);
    useEffect(() => {
        if (Following && yourId) {
            // 팔로잉 목록에서 yourId와 일치하는 memberId 찾기
            const isFollowing = Following.some((following) => following.memberId === yourId);
            setIsFollowing(isFollowing);
        }
    }, [Following, yourId]);

    const handleFollowClick = (memberId: memberId) => {
        setIsFollowing((prev) => !prev);
        FollowUser(memberId);
    };

    const handleUnFollowClick = (memberId: memberId) => {
        setIsFollowing((prev) => !prev);
        unFollowUser({ memberId });
    };

    return (
        <div id="display">
            <div className="container">
                <S.FollowBtnWrapper>
                    <CircleButton
                        option="default2"
                        size="medium"
                        onClick={() => {
                            navigate(-1);
                            setYourId(0);
                        }}
                    >
                        <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                    </CircleButton>
                    {isFollowing && isFollowing ? (
                        <Button option="unfollow" size="medium" $width="70px" onClick={() => handleUnFollowClick(yourId)}>
                            팔로잉
                        </Button>
                    ) : (
                        <Button option="follow" size="medium" $width="70px" onClick={() => handleFollowClick(yourId)}>
                            팔로우
                        </Button>
                    )}
                </S.FollowBtnWrapper>
                <S.YourPageWrapper>
                    <S.Profile>
                        <Image
                            src={YourInfo && YourInfo.profileCharacter && YourInfo.profileCharacter.characterImage}
                            width={140}
                            height={140}
                            $unit="px"
                        ></Image>
                    </S.Profile>
                    <S.YourdataWrapper>
                        {YourAchievement && YourAchievement.badge && (
                            <Image src={YourAchievement.badge.badgeImg} width={24} height={24} $unit="px" $margin="0 4px 4px 0"></Image>
                        )}
                        <Text size="body1" fontWeight="bold">
                            {YourAchievement && YourAchievement.title && YourAchievement.title.titleName}
                        </Text>

                        <Text size="body2" $marginLeft="4px">
                            {YourInfo && YourInfo.nickname}
                        </Text>
                        <Text size="body2" $marginLeft="4px">
                            님
                        </Text>
                    </S.YourdataWrapper>
                    <S.FollowWrapper>
                        <Button option="tag_plus" size="largeplus" $width="130px" onClick={() => navigatePage("/following")}>
                            팔로잉 {YourFollowing?.length ?? 0}명
                        </Button>
                        <Button option="tag_plus" size="largeplus" $width="130px" onClick={() => navigatePage("/follower")}>
                            팔로워 {YourFollower?.length ?? 0}명
                        </Button>
                    </S.FollowWrapper>
                    <S.YourItemWrapper>
                        {mypage.map((item, index) => (
                            <ItemBox key={index} src={item.src} title={item.name} onClick={() => navigatePage(item.params)} />
                        ))}
                    </S.YourItemWrapper>
                </S.YourPageWrapper>
                <S.NavbarWrapper>
                    <Navbar></Navbar>
                </S.NavbarWrapper>
            </div>
        </div>
    );
}
