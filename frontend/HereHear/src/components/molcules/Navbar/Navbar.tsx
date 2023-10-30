import React from "react";
import * as S from "./Navbar.styles";
import { useLocation, useNavigate } from "react-router-dom";
import { Image } from "../../../components/atoms/Image/Image";

// 네비바 비활성화 이미지
import iconMap from "../../../assets/Navbar/icon-map.png";
import iconMusiclist from "../../../assets/Navbar/icon-musiclist.png";
import iconMusicplus from "../../../assets/Navbar/icon-musicplus.png";
import iconStatistics from "../../../assets/Navbar/icon-statistics.png";
import iconMypage from "../../../assets/Navbar/icon-mypage.png";

// 네비바 활성화 이미지
import iconClickedMap from "../../../assets/Navbar/icon-clicked-map.png";
import iconClickedMusiclist from "../../../assets/Navbar/icon-clicked-musiclist.png";
import iconClickedStatistics from "../../../assets/Navbar/icon-clicked-statistics.png";
import iconClickedMypage from "../../../assets/Navbar/icon-clicked-mypage.png";

const navBarInfo = [
    { src: [iconMap, iconClickedMap], path: "/core" },
    { src: [iconMusiclist, iconClickedMusiclist], path: "/achievement" },
    { src: [iconMusicplus, iconMusicplus], path: "/registMusic" },
    { src: [iconStatistics, iconClickedStatistics], path: "/selectMusic" },
    { src: [iconMypage, iconClickedMypage], path: "/stats" },
];

export default function ItemBox() {
    const navigate = useNavigate();
    const location = useLocation();

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const nowPath = location.pathname;

    return (
        <nav>
            <S.StyledNavBarBackground>
                <S.StyledNavBar>
                    {navBarInfo.map(({ src, path }) => {
                        return (
                            <Image
                                key={path}
                                src={path === "/" ? (nowPath === path ? src[1] : src[0]) : nowPath.substring(0, path.length) === path ? src[1] : src[0]}
                                onClick={() => navigatePage(path)}
                                width={path === "/registMusic" ? 20 : 15}
                                $unit="%"
                            />
                        );
                    })}
                </S.StyledNavBar>
            </S.StyledNavBarBackground>
        </nav>
    );
}
