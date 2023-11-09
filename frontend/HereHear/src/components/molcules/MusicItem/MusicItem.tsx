import React, { useRef, useState, useEffect } from "react";
import * as S from "./MusicItem.styles";
import { Image } from "../../atoms/Image/Image";

interface MusicItemProps {
    src: string;
    songtitle: string;
    artist: string;
    onClick?: () => void;
}

function MusicItem({
    src,
    songtitle,
    artist,
    onClick = () => {},
}: MusicItemProps) {
    const titleRef = useRef<HTMLDivElement>(null);
    const artistRef = useRef<HTMLDivElement>(null);
    const [isTitleOverflowing, setIsTitleOverflowing] = useState(false);
    const [isArtistOverflowing, setIsArtistOverflowing] = useState(false);
    const [animateTitle, setAnimateTitle] = useState(false);
    const [animateArtist, setAnimateArtist] = useState(false);

    const checkOverflow = (
        elementRef: React.RefObject<HTMLDivElement>,
        setIsOverflowing: React.Dispatch<React.SetStateAction<boolean>>
    ) => {
        const element = elementRef.current;
        if (element) {
            setIsOverflowing(element.scrollWidth > element.clientWidth);
        }
    };

    useEffect(() => {
        checkOverflow(titleRef, setIsTitleOverflowing);
        checkOverflow(artistRef, setIsArtistOverflowing);

        const handleResize = () => {
            checkOverflow(titleRef, setIsTitleOverflowing);
            checkOverflow(artistRef, setIsArtistOverflowing);
        };

        window.addEventListener("resize", handleResize);

        return () => {
            window.removeEventListener("resize", handleResize);
        };
    }, []); // 의존성 배열에서 titleRef와 artistRef를 제거합니다.

    useEffect(() => {
        checkOverflow(titleRef, setIsTitleOverflowing);
        checkOverflow(artistRef, setIsArtistOverflowing);

        // ... 기존의 resize event handler 코드 ...

        // 제목과 아티스트 텍스트의 오버플로우 상태를 체크하고, 필요한 경우에만 애니메이션을 설정합니다.
        const timer = setTimeout(() => {
            if (isTitleOverflowing) setAnimateTitle(true);
            if (isArtistOverflowing) setAnimateArtist(true);
        }, 3000);

        return () => clearTimeout(timer); // 컴포넌트 언마운트 시 타이머 정리
    }, [isTitleOverflowing, isArtistOverflowing]); // 의존성 배열 업데이트

    return (
        <S.MusicItemWrapper onClick={onClick}>
            <Image
                src={src}
                width={50}
                height={50}
                $unit="px"
                $boxShadow="shadow_goback"
                $borderRadius="10px"
            ></Image>
            <S.MusicTextWrapper>
                <S.MarqueeText
                    ref={titleRef}
                    size="small2"
                    fontWeight="bold"
                    color="main2"
                    animate={animateTitle}
                    isOverflowing={isTitleOverflowing}
                >
                    {songtitle}
                </S.MarqueeText>
                <S.MarqueeText
                    ref={artistRef}
                    size="small2"
                    fontWeight="bold"
                    color="main2"
                    animate={animateArtist}
                    isOverflowing={isArtistOverflowing}
                >
                    {artist}
                </S.MarqueeText>
            </S.MusicTextWrapper>
        </S.MusicItemWrapper>
    );
}

export default MusicItem;
