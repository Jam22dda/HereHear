import * as S from "./SearchRegistMusicPage.styles";
import { useNavigate } from "react-router-dom";
import React, { useState, ChangeEvent } from "react";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { Text } from "../../components/atoms/Text/Text.styles";
import Input from "../../components/atoms/Input/Input";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import Button from "../../components/atoms/Button/Button";
import { useGetSearchMusic } from "../../apis/Music/Quries/useGetSearchMusic";
import { MusicData } from "../../types/music";
import iconBack from "../../assets/CircleButton/icon-back.png";
import iconMusicPlus from "../../assets/CircleButton/icon-musicPlus.png";
import { Image } from "../../components/atoms/Image/Image";
import { useRecoilState } from "recoil";
import { musicItemState } from "../../states/RegistMusicAtom";
import { MusicItemState } from "../../types/music";

export default function SearchRegistMusic() {
    const page = 1; //음악 받아오는 페이지 1로 임의 설정

    const navigate = useNavigate();

    const [searchKeyword, setSearchKeyword] = useState("");
    const [temp, setTemp] = useState("");
    const { searchMusic } = useGetSearchMusic(temp, page);
    const [, setMusicItem] = useRecoilState(musicItemState);

    // 음악 검색 버튼 클릭 시 이벤트
    const handleSearchChange = (e: ChangeEvent<HTMLInputElement>) => {
        setSearchKeyword(e.target.value);
    };
    const handleSearchClick = () => {
        setTemp(searchKeyword);
    };

    // 음악 추가 버튼 클릭 시 이벤트
    const saveMusicItemAndNavigate = (item: MusicData) => {
        const musicItemToSave: MusicItemState = {
            src: item.albumImages[0].url,
            songtitle: item.subject,
            artist: item.artists[0].name,
            releaseTime: item.releaseDate,
        };
        console.log(musicItemToSave, "musicItemToSave");
        setMusicItem(musicItemToSave); //리코일에 해당 음악정보 저장
        console.log(item.artists[0].name, "artist Name");

        navigate("/registMusicMent"); // 다른 페이지의 경로로 변경하세요
    };

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>

                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <S.InputWrapper>
                    <Input
                        $placeholder="이곳에 검색해주세요"
                        value={searchKeyword}
                        onChange={handleSearchChange}
                        // disabled={isLoading} // 로딩 중일 때 입력창 비활성화
                    ></Input>
                    <Button option="follow" $height="44px" $width="60px" $borderRadius="12px" color="white1" size="mediumplus" onClick={handleSearchClick}>
                        검색
                    </Button>
                </S.InputWrapper>

                {searchMusic &&
                    searchMusic.data?.musicInfoList?.map((item: MusicData, index: number) => (
                        <S.MusicItemWrapper key={index}>
                            <MusicItem src={item.albumImages[0].url} songtitle={item.subject} artist={item.artists[0].name} />
                            <CircleButton option="gradDeActivated" size="mediumplus" onClick={() => saveMusicItemAndNavigate(item)}>
                                <Image src={iconMusicPlus} width={24} height={24} $unit="px"></Image>
                            </CircleButton>

                            {/* TODO: 버튼 클릭이벤트- 음악 
                            저장하고 음악 등록페이지로 이동 */}
                        </S.MusicItemWrapper>
                    ))}
            </div>
        </div>
    );
}

// export default function SearchRegistMusic() {
//     const [searchPage] = useState(1);
//     const navigate = useNavigate();

//     const [searchKeyword, setSearchKeyword] = useState("");
//     // const [searchResults, setSearchResults] = useState<MusicData[] | null>(null);

//     const { data: searchMusic, isLoading, error } = useGetSearchMusic(searchKeyword, searchPage);

//     const handleSearchChange = (event: React.ChangeEvent<HTMLInputElement>) => {
//         setSearchKeyword(event.target.value);
//     };

//     if (isLoading) {
//         return <div>Loading...</div>;
//     }

//     if (error) {
//         console.error("Error fetching search music", error);
//         return <div>Error occurred</div>;
//     }

//     // const handleSearchClick = async () => {
//     //     if (searchKeyword) {
//     //         console.log(searchMusic);
//     //         setSearchResults(searchMusic.data.musicInfoList);
//     //     }
//     // };

//     return (
//         <div id="display">
//             <div className="container">
//                 <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
//                     <Image src={iconBack} width={10} height={18} $unit="px"></Image>
//                 </CircleButton>
//                 <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
//                     음악 등록
//                 </Text>
//                 <S.InputWrapper>
//                     <Input $placeholder="🔍" value={searchKeyword} onChange={handleSearchChange}></Input>
//                     <Button option="follow" $height="44px" $width="60px" $borderRadius="12px" color="white1" size="mediumplus">
//                         검색
//                     </Button>
//                 </S.InputWrapper>
//                 {searchMusic &&
//                     searchMusic.data.musicInfoList.map((item, index) => (
//                         <S.MusicItemWrapper key={index}>
//                             <MusicItem src={item.album_images[0].url} title={item.subject} artist={item.artists[0].name} />
//                             <CircleButton option="gradDeActivated" size="mediumplus">
//                                 <Image src={iconMusicPlus} width={24} height={24} $unit="px"></Image>
//                             </CircleButton>
//                             {/* TODO: 버튼 클릭이벤트- 음악 저장하고 음악 등록페이지로 이동 */}
//                         </S.MusicItemWrapper>
//                     ))}
//             </div>
//         </div>
//     );
// }
