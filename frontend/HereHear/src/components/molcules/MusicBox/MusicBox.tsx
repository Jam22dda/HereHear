// import React from "react";
import * as S from './MusicBox.styles';
import { Image } from '../../atoms/Image/Image';
// import { Text } from "../../atoms/Text/Text.styles";
import afterBtn from '../../../assets/MusicBox/icon-musicAfterBtn.png';
import beforeBtn from '../../../assets/MusicBox/icon-musicBeforeBtn.png';
import Button from '../../atoms/Button/Button';
import { useNavigate } from 'react-router-dom';
import React, { useRef, useState, useEffect } from 'react';

// {isSelect ? <MusicBox musicAroundList={musicAroundList} pinId={userSelectPin} setIsSelect={setIsSelect}></MusicBox> : null}
interface MusicItem {
    registeredMusicId: number;
    albumImg: string;
    occasionName: string[];
    singer: string;
    subject: string;
    // 여기에 추가적인 음악과 관련된 속성들이 더 있을 수 있습니다.
}

interface MusicBoxProps {
    musicAroundList: MusicItem[];
    pinId: number;
    setIsSelect: (isSelected: boolean) => void;
}

export default function MusicBox(props: MusicBoxProps) {
    const [title, setTitle] = useState('title');
    const [singer, setSinger] = useState('singer');
    const [pinId, setPinId] = useState(0);
    const [pinIndex, setPinIndex] = useState(0);
    const [tag1, setTag1] = useState('tag1');
    const [tag2, setTag2] = useState('tag2');
    const [tag3, setTag3] = useState('tag3');
    const [imgUrl, setImgUrl] = useState('');
    const [isListNull, setIsListNull] = useState(true);
    const [isNearNull, setIsNearListNull] = useState(true);

    const navigate = useNavigate();

    // 최초 컴포넌트 접근 시
    useEffect(() => {
        for (let i = 0; i < props.musicAroundList.length; i++) {
            // 배열의 id와 내가 눌렀던 id가 같은 경우

            if (props.musicAroundList[i].registeredMusicId === props.pinId) {
                setPinId(props.pinId);
                setPinIndex(i);
                setIsNearListNull(false);
            }
        }

        if (props.musicAroundList.length === 0) {
            setIsListNull(true);
        } else {
            setIsListNull(false);
        }
    }, []);

    useEffect(() => {
        if (isListNull === false) {
            const nowList = props.musicAroundList[pinIndex];

            setImgUrl(nowList.albumImg);
            setTag1(nowList.occasionName[0]);
            setTag2(nowList.occasionName[1]);
            setTag3(nowList.occasionName[2]);

            setSinger(nowList.singer);
            setTitle(nowList.subject);
        }
    }, [pinIndex, isListNull]);

    // 이 함수는 각 텍스트가 overflow 상태인지를 체크하고, 애니메이션 상태를 업데이트합니다.
    const checkAndAnimateText = () => {
        if (titleRef.current) {
            const isTitleOverflow = checkOverflow(titleRef.current);
            setIsTitleOverflowing(isTitleOverflow);
            setAnimateTitle(isTitleOverflow);
        }

        if (artistRef.current) {
            const isArtistOverflow = checkOverflow(artistRef.current);
            setIsArtistOverflowing(isArtistOverflow);
            setAnimateArtist(isArtistOverflow);
        }
    };

    function nextMusicHandler(type: string) {
        let nowPinIndex = pinIndex;
        if (type === 'prev') {
            nowPinIndex -= 1;
            if (nowPinIndex < 0) nowPinIndex = props.musicAroundList.length - 1;
        } else {
            nowPinIndex += 1;
            if (nowPinIndex > props.musicAroundList.length - 1) nowPinIndex = 0;
        }

        setPinId(props.musicAroundList[nowPinIndex].registeredMusicId);
        setPinIndex(nowPinIndex);

        // 새로운 텍스트 로드를 위해 이미지 URL 상태를 업데이트합니다.
        setImgUrl(props.musicAroundList[nowPinIndex].albumImg);
        // 상태 업데이트 후 적절한 시간이 지난 후에 애니메이션 상태를 검사합니다.
        setTimeout(() => {
            checkAndAnimateText();
        }, 0);
    }

    function OuterOnClickHandler() {
        props.setIsSelect(false);
    }

    function InnerOnClickHandler(event: React.MouseEvent<HTMLDivElement, MouseEvent>) {
        event.stopPropagation(); // 이벤트가 상위 DOM으로 전파되지 않도록 막음
    }

    const titleRef = useRef<HTMLDivElement>(null);
    const artistRef = useRef<HTMLDivElement>(null);
    const [isTitleOverflowing, setIsTitleOverflowing] = useState(false);
    const [isArtistOverflowing, setIsArtistOverflowing] = useState(false);
    const [animateTitle, setAnimateTitle] = useState(false);
    const [animateArtist, setAnimateArtist] = useState(false);

    const checkOverflow = (element: HTMLDivElement | null): boolean => {
        if (element) {
            return element.scrollWidth > element.clientWidth;
        }
        return false;
    };

    // 이미지 로드 완료 후 각각의 텍스트에 대해 오버플로우 및 애니메이션 상태를 업데이트합니다.
    const imageOnLoad = () => {
        checkAndAnimateText();
    };

    // 윈도우 크기가 변경될 때 오버플로우와 애니메이션 상태를 업데이트합니다.
    const handleResize = () => {
        imageOnLoad(); // 이미지 로드 함수를 호출하여 상태를 업데이트합니다.
        checkAndAnimateText();
    };

    // Resize 이벤트 리스너를 설정합니다.
    useEffect(() => {
        window.addEventListener('resize', handleResize);

        // 컴포넌트가 언마운트될 때 이벤트 리스너를 정리합니다.
        return () => {
            window.removeEventListener('resize', handleResize);
        };
    }, []);

    // 애니메이션 상태를 설정하는 useEffect
    useEffect(() => {
        if (isTitleOverflowing) setAnimateTitle(true);
        if (isArtistOverflowing) setAnimateArtist(true);
    }, [isTitleOverflowing, isArtistOverflowing]); // 오버플로우 상태가 변경될 때마다 실행됩니다.

    return (
        <S.Outer onClick={OuterOnClickHandler}>
            <S.MusicBox onClick={InnerOnClickHandler}>
                {!isNearNull && !isListNull ? (
                    <S.MusicBoxInner>
                        <S.ImageLeftOuter>
                            <Image
                                src={beforeBtn}
                                width={35}
                                height={35}
                                $unit='px'
                                onClick={() => {
                                    nextMusicHandler('prev');
                                }}
                            ></Image>
                        </S.ImageLeftOuter>
                        <S.BigWrapper
                            onClick={() => {
                                navigate(`/musicPlay/${pinId}`);
                            }}
                        >
                            <Image className='album_img' src={imgUrl} width={100} height={100} $unit='px' $borderRadius='10px' onLoad={imageOnLoad} />
                            <S.MidWrapper>
                                <S.MapTextrapper>
                                    <S.MarqueeText
                                        key={title + pinIndex} // 이 부분을 추가합니다.
                                        ref={titleRef}
                                        size='body2'
                                        fontWeight='bold'
                                        color='main2'
                                        $margin='0 0 5px'
                                        animate={isTitleOverflowing && animateTitle}
                                        isOverflowing={isTitleOverflowing}
                                    >
                                        {title}
                                    </S.MarqueeText>

                                    <S.MarqueeText
                                        key={singer + pinIndex}
                                        ref={artistRef}
                                        size='body2'
                                        fontWeight='medium'
                                        color='main2'
                                        $margin='0 0 10px'
                                        animate={isTitleOverflowing && animateArtist}
                                        isOverflowing={isArtistOverflowing}
                                    >
                                        {singer}
                                    </S.MarqueeText>
                                </S.MapTextrapper>

                                <S.MapMusicTagWrapper>
                                    {/* TODO:버튼 크기 다시확인(아톰 버튼에 없음?) */}
                                    {!tag1 ? null : (
                                        <Button option='tag1' $width='55px' $height='25px' size='small'>
                                            {tag1}
                                        </Button>
                                    )}
                                    {!tag2 ? null : (
                                        <Button option='tag1' $width='55px' $height='25px' size='small'>
                                            {tag2}
                                        </Button>
                                    )}
                                    {!tag3 ? null : (
                                        <Button option='tag1' $width='55px' $height='25px' size='small'>
                                            {tag3}
                                        </Button>
                                    )}
                                </S.MapMusicTagWrapper>
                            </S.MidWrapper>
                        </S.BigWrapper>
                        <S.ImageRightOuter>
                            <Image
                                src={afterBtn}
                                width={35}
                                height={35}
                                $unit='px'
                                onClick={() => {
                                    nextMusicHandler('next');
                                }}
                            ></Image>
                        </S.ImageRightOuter>
                    </S.MusicBoxInner>
                ) : (
                    <p>반경 2km 이내 음악만 조회 가능합니다.</p>
                )}
            </S.MusicBox>
        </S.Outer>
    );
}
