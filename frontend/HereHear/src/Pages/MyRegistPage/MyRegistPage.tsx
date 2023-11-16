import * as S from "./MyRegistPage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import { Text } from "../../components/atoms/Text/Text.styles";
import { useNavigate } from "react-router-dom";
import iconBack from "../../assets/CircleButton/icon-back.png";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Image } from "../../components/atoms/Image/Image";
import { useGetRegistMusic } from "../../apis/Mypage/Quries/useGetRegistMusic";
import { useGetYourRegistMusic } from "../../apis/YourPage/Quries/useGetYourRegistMusic";
import { useRecoilValue } from "recoil";
import { YourIdAtom } from "../../states/MypageAtoms";

export default function MyRegistPage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const yourId = useRecoilValue(YourIdAtom);

    interface RegistMusicType {
        albumImg: string;
        registeredMusicId: number;
        singer: string;
        subject: string;
    }

    const RegistMusic: RegistMusicType[] = useGetRegistMusic();
    const YourRegistMusic: RegistMusicType[] = useGetYourRegistMusic(yourId);

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
                    등록한 노래
                </Text>
                {yourId === 0
                    ? RegistMusic &&
                      RegistMusic.map(
                          (item: RegistMusicType, index: number) => (
                              <S.MyRegistWrapper key={index}>
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
                              </S.MyRegistWrapper>
                          )
                      )
                    : YourRegistMusic &&
                      YourRegistMusic.map(
                          (item: RegistMusicType, index: number) => (
                              <S.MyRegistWrapper key={index}>
                                  <MusicItem
                                      src={item.albumImg}
                                      songtitle={item.subject}
                                      artist={item.singer}
                                  />
                              </S.MyRegistWrapper>
                          )
                      )}
            </div>
        </div>
    );
}
