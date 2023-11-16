import * as S from "./LikePage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconHeart from "../../assets/CircleButton/icon-heart.png";
import iconEmptyHeart from "../../assets/CircleButton/icon-emptyheart.png";
import { Image } from "../../components/atoms/Image/Image";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { useNavigate } from "react-router-dom";
import { useGetLikeMusic } from "../../apis/Mypage/Quries/useGetLikeMusic";
import { usePostLikeMusic } from "../../apis/Music/Mutations/useLikeMusic";
import { registeredMusicId } from "../../types/music";
import { useState, useEffect } from "react";
import { useRecoilValue } from "recoil";
import { YourIdAtom } from "../../states/MypageAtoms";
import { useGetYourLikeMusic } from "../../apis/YourPage/Quries/useGetYourLikeMusic";

export default function LikePage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const yourId = useRecoilValue(YourIdAtom);

    interface LikeMusicType {
        albumImg: string;
        like: boolean;
        registeredMusicId: number;
        singer: string;
        subject: string;
    }

    const LikeMusic: LikeMusicType[] = useGetLikeMusic();
    const YourLikeMusic: LikeMusicType[] = useGetYourLikeMusic(yourId);
    const { mutate: postLikeMusicMutate } = usePostLikeMusic();

    const handleLikeMusicClick = (
        registeredMusicId: registeredMusicId,
        index: number
    ) => {
        postLikeMusicMutate(registeredMusicId);
        setLikeList((prevList) =>
            prevList.map((like, idx) =>
                idx === index ? { ...like, isLiked: !like.isLiked } : like
            )
        );
    };

    interface LikeState {
        isLiked: boolean;
    }

    const [likeList, setLikeList] = useState<LikeState[]>([]);

    useEffect(() => {
        if (LikeMusic && LikeMusic.length > 0) {
            // 타입스크립트에게 상태의 타입을 알려줍니다.
            setLikeList(LikeMusic.map(() => ({ isLiked: true })));
        }
    }, [LikeMusic]);

    return (
        <div id="display">
            <div className="container">
                <CircleButton
                    option="default2"
                    size="medium"
                    onClick={() => navigate(-1)}
                >
                    <Image
                        src={iconBack}
                        width={10}
                        height={18}
                        $unit="px"
                    ></Image>
                </CircleButton>
                <Text
                    size="subtitle1"
                    fontWeight="bold"
                    $margin="20px 0 48px 0"
                >
                    좋아요한 노래
                </Text>
                {yourId === 0
                    ? LikeMusic &&
                      LikeMusic.map((item: LikeMusicType, index: number) => (
                          <S.LikeItemWrapper key={index}>
                              <MusicItem
                                  src={item.albumImg}
                                  songtitle={item.subject}
                                  artist={item.singer}
                                  onClick={() =>
                                      navigatePage(
                                          `/musicPlay/${item.registeredMusicId}`
                                      )
                                  }
                              />
                              {likeList[index] && likeList[index].isLiked ? (
                                  <CircleButton
                                      option="gradDeActivated"
                                      size="large"
                                      onClick={() =>
                                          handleLikeMusicClick(
                                              item.registeredMusicId,
                                              index
                                          )
                                      }
                                  >
                                      <Image
                                          src={iconHeart}
                                          width={24}
                                          height={20}
                                          $unit="px"
                                      ></Image>
                                  </CircleButton>
                              ) : (
                                  <CircleButton
                                      option="gradDeActivated"
                                      size="large"
                                      onClick={() =>
                                          handleLikeMusicClick(
                                              item.registeredMusicId,
                                              index
                                          )
                                      }
                                  >
                                      <Image
                                          src={iconEmptyHeart}
                                          width={24}
                                          height={20}
                                          $unit="px"
                                      ></Image>
                                  </CircleButton>
                              )}
                          </S.LikeItemWrapper>
                      ))
                    : YourLikeMusic &&
                      YourLikeMusic.map(
                          (item: LikeMusicType, index: number) => (
                              <S.LikeYourItemWrapper key={index}>
                                  <MusicItem
                                      src={item.albumImg}
                                      songtitle={item.subject}
                                      artist={item.singer}
                                  />
                              </S.LikeYourItemWrapper>
                          )
                      )}
            </div>
        </div>
    );
}
