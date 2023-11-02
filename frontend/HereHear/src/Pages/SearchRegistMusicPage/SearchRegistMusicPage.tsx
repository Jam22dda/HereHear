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

export default function SearchRegistMusic() {
    const page = 1;
    const navigate = useNavigate();

    // startTransition을 사용하여 상태 업데이트를 비동기적으로 처리
    // const [, startTransition] = useTransition();
    const [searchKeyword, setSearchKeyword] = useState("");

    const [searchResults, setSearchResults] = useState<MusicData[] | null>(
        null
    );
    const { searchMusic } = useGetSearchMusic(searchKeyword, page);
    console.log(searchMusic);
    const handleSearchChange = (e: ChangeEvent<HTMLInputElement>) => {
        setSearchKeyword(e.target.value);
    };
    console.log(searchKeyword);

    const handleSearchClick = () => {
        if (searchKeyword) {
            if (
                searchMusic.searchMusic &&
                searchMusic.searchMusic.data &&
                searchMusic.searchMusic.data.musicInfoList
            ) {
                console.log("야 오냐?");
                setSearchResults(searchMusic.searchMusic.data.musicInfoList);
            }
        }
    };

    // const handleSearchClick = () => {
    //     if (searchKeyword) {
    //         startTransition(() => {
    //             if (
    //                 SearchMusic.searchMusic &&
    //                 SearchMusic.searchMusic.data &&
    //                 SearchMusic.searchMusic.data.musicInfoList
    //             ) {
    //                 setSearchResults(
    //                     SearchMusic.searchMusic.data.musicInfoList
    //                 );
    //             }
    //         });
    //     }
    // };
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
                <Text size="subtitle1" fontWeight="bold" $marginTop="20px">
                    음악 등록
                </Text>
                <S.InputWrapper>
                    <Input
                        $placeholder="🔍"
                        value={searchKeyword}
                        onChange={handleSearchChange}
                        // disabled={isLoading} // 로딩 중일 때 입력창 비활성화
                    ></Input>
                    <Button
                        option="follow"
                        $height="44px"
                        $width="60px"
                        $borderRadius="12px"
                        color="white1"
                        size="mediumplus"
                        onClick={handleSearchClick}
                    >
                        검색
                    </Button>
                </S.InputWrapper>

                {searchResults &&
                    searchResults.map((item, index) => (
                        <S.MusicItemWrapper key={index}>
                            <MusicItem
                                src={item.album_images[0].url}
                                title={item.subject}
                                artist={item.artists[0].name}
                            />
                            <CircleButton
                                option="gradDeActivated"
                                size="mediumplus"
                            >
                                <Image
                                    src={iconMusicPlus}
                                    width={24}
                                    height={24}
                                    $unit="px"
                                ></Image>
                            </CircleButton>
                            {/* TODO: 버튼 클릭이벤트- 음악 저장하고 음악 등록페이지로 이동 */}
                        </S.MusicItemWrapper>
                    ))}
            </div>
        </div>
    );
}

// import * as S from "./SearchRegistMusicPage.styles";
// import { useNavigate } from "react-router-dom";
// import React, { useState } from "react";
// import CircleButton from "../../components/atoms/CircleButton/CircleButton";
// import { Text } from "../../components/atoms/Text/Text.styles";
// import Input from "../../components/atoms/Input/Input";
// import MusicItem from "../../components/molcules/MusicItem/MusicItem";
// import Button from "../../components/atoms/Button/Button";
// import { useGetSearchMusic } from "../../apis/Music/Quries/useGetSearchMusic";
// import { MusicData } from "../../types/music";
// import iconBack from "../../assets/CircleButton/icon-back.png";
// import iconMusicPlus from "../../assets/CircleButton/icon-musicPlus.png";
// import { Image } from "../../components/atoms/Image/Image";

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
