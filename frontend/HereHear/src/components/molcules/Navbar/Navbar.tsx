import React from "react";
import * as S from "./Navbar.styles";
import { useNavigate } from "react-router-dom";
import { Image } from "../../../components/atoms/Image/Image";

// 네비바 비활성화 이미지
import iconMap from "../../../assets/Navbar/icon-map.png";
import iconMusiclist from "../../../assets/Navbar/icon-musiclist.png";
import iconMusicplus from "../../../assets/Navbar/icon-musicplus.png";
import iconStatistics from "../../../assets/Navbar/icon-statistics.png";
import iconMypage from "../../../assets/Navbar/icon-mypage.png";

const navBarInfo = [
    { src: iconMap, path: "/core" },
    { src: iconMusiclist, path: "/listenedMusic" },
    { src: iconMusicplus, path: "/registMusic" },
    { src: iconStatistics, path: "/selectMusic" },
    { src: iconMypage, path: "/mypage" },
];

export default function Navbar() {
    const navigate = useNavigate();

    const navigatePage = (path: string) => {
        navigate(path);
    };

    return (
        <S.StyledNavBarBackground>
            <S.StyledNavBar>
                {navBarInfo.map(({ src, path }) => {
                    return (
                        <Image
                            key={path}
                            src={src}
                            onClick={() => navigatePage(path)}
                            width={path === "/registMusic" ? 64 : 52}
                            $unit="px"
                        />
                    );
                })}
            </S.StyledNavBar>
        </S.StyledNavBarBackground>
    );
}
