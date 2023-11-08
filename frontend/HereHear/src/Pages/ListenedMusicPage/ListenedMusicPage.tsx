import * as S from "./ListenedMusicPage.styles";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Image } from "../../components/atoms/Image/Image";
import { Text } from "../../components/atoms/Text/Text.styles";
import Navbar from "../../components/molcules/Navbar/Navbar";
import { useGetListenedMusic } from "../../apis/Music/Quries/useGetListenedMusic";
import { useNavigate } from "react-router-dom";
import iconArrowForward from "../../../public/images/icon-arrow-forward.png";
import iconBack from "../../assets/CircleButton/icon-back.png";
interface ListenedMusicType {
    albumImg: string;
    like: boolean;
    registeredMusicId: number;
    singer: string;
    subject: string;
}

export default function ListenedMusicPage() {
    const navigate = useNavigate(); // useNavigate 훅 사용

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const ListenedMusic: ListenedMusicType[] = useGetListenedMusic();

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    최근 들은 노래 리스트
                </Text>
                {ListenedMusic &&
                    ListenedMusic.map(
                        (item: ListenedMusicType, index: number) => (
                            <S.MusicItemWrapper key={index}>
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
                                <CircleButton
                                    option="gradDeActivated"
                                    size="large"
                                >
                                    <Image
                                        src={iconArrowForward}
                                        width={24}
                                        height={20}
                                        $unit="px"
                                        onClick={() => {
                                            const subjectEncoded =
                                                encodeURIComponent(
                                                    item.subject
                                                );
                                            const singerEncoded =
                                                encodeURIComponent(item.singer);
                                            const youtubeSearchUrl = `https://www.youtube.com/results?search_query=${subjectEncoded}+${singerEncoded}`;
                                            window.location.href =
                                                youtubeSearchUrl;
                                        }}
                                    ></Image>
                                </CircleButton>
                            </S.MusicItemWrapper>
                        )
                    )}
                <Navbar></Navbar>
            </div>
        </div>
    );
}
