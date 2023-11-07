import * as S from "./MusicPlayPage.styles";
import { useNavigate } from "react-router-dom";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { Image } from "../../components/atoms/Image/Image";
import AlbumCover from "../../components/atoms/AlbumCover/AlbumCover";
import Button from "../../components/atoms/Button/Button";
import Message from "../../components/atoms/Message/Message";
import PlayBtn from "../../components/molcules/Play/Play";
import { useLocation } from "react-router-dom";
import { useGetMusicPlay } from "../../apis/Music/Quries/useGetMusicPlay";

export default function MusicPlay() {
    const navigate = useNavigate();

    const occasionName = [{ tag: "산책" }, { tag: "청량" }, { tag: "주말" }];
    const music = { singer: "악동뮤지션", subject: "LOVELEE", albumImg: ".jpg" };

    const location = useLocation();
    const musicId = location.state?.data;
    // =================================== 데이터 연결 아이디 이거 쓰세욤 ==========================================
    console.log("musicId");
    console.log(musicId);
    // =================================== 데이터 연결 아이디 이거 쓰세욤 ==========================================
    const getMusicPlay = useGetMusicPlay(musicId);
    console.log(getMusicPlay, "getMusicPlay");

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>
                <S.MusicPlayWrapper>
                    <S.SelectTagWrapper>
                        {/* TODO:음악들어온거 안에 있는 태그 map으로 뿌리기 */}
                        {occasionName.map((item, index) => (
                            <Button option="unfollow" $shadow="" size="mediumplus" $margin="5px" $width="80px" key={index} tag={item.tag}></Button>
                        ))}
                    </S.SelectTagWrapper>
                    {/* TODO: getMusicPlay가 잘 들어오는지 확인하고 이미지,가수,제목 바꾸기 */}
                    <AlbumCover src={music.albumImg}></AlbumCover>
                    <CircleButton option="pinkDeActivated" style={{ marginLeft: "17rem" }}></CircleButton>
                    <Text size="body2" fontWeight="bold" $marginTop="10px">
                        {music.singer}
                    </Text>
                    <Text size="body2" fontWeight="medium" $marginTop="5px">
                        {music.subject}
                    </Text>
                    {/* TODO: 메세지 불러올 때 작성자 comment,createTime,nickname //근데 캐릭터는 어케 앎?*/}
                    <Message></Message>
                    <PlayBtn></PlayBtn>
                </S.MusicPlayWrapper>
            </div>
        </div>
    );
}
