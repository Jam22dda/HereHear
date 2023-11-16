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

import iconClickedMap from "../../../assets/Navbar/icon-clicked-map.png";
import iconClickedMusiclist from "../../../assets/Navbar/icon-clicked-musiclist.png";
import iconClickedMusicplus from "../../../assets/Navbar/icon-musicplus.png";
import iconClickedStatistics from "../../../assets/Navbar/icon-clicked-statistics.png";
import iconClickedMypage from "../../../assets/Navbar/icon-clicked-mypage.png";

const navBarInfo = [
    { src: [iconMap, iconClickedMap], path: "/core" },
    { src: [iconMusiclist, iconClickedMusiclist], path: "/listenedMusic" },
    { src: [iconMusicplus, iconClickedMusicplus], path: "/registMusic" },
    { src: [iconStatistics, iconClickedStatistics], path: "/statistic" },
    { src: [iconMypage, iconClickedMypage], path: "/mypage" },
];

interface NavbarProps {
    active?: boolean;
}

export default function Navbar({ active }: NavbarProps) {
    const navigate = useNavigate();
    const location = useLocation();
    const nowPath = location.pathname;

    const navigatePage = (path: string) => {
        navigate(path);
    };

    return (
        <S.StyledNavBarBackground active={active}>
            <S.StyledNavBar>
                {navBarInfo.map(({ src, path }) => {
                    return (
                        <Image
                            key={path}
                            src={
                                path === "/core"
                                    ? nowPath === path
                                        ? src[1]
                                        : src[0]
                                    : nowPath.substring(0, path.length) === path
                                    ? src[1]
                                    : src[0]
                            }
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
