// import React from "react";
import * as S from './MusicBox.styles';
import { Image } from '../../atoms/Image/Image';
import { Text } from '../../atoms/Text/Text.styles';
import afterBtn from '../../../assets/MusicBox/icon-musicAfterBtn.png';
import beforeBtn from '../../../assets/MusicBox/icon-musicBeforeBtn.png';
import Button from '../../atoms/Button/Button';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

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
        console.log('MusicBox랍니다');
        console.log(props.musicAroundList);

        for (let i = 0; i < props.musicAroundList.length; i++) {
            // 배열의 id와 내가 눌렀던 id가 같은 경우
            console.log(props.musicAroundList[i].registeredMusicId);

            if (props.musicAroundList[i].registeredMusicId === props.pinId) {
                setPinId(props.pinId);
                setPinIndex(i);
                setIsNearListNull(false);
            }
        }

        if (props.musicAroundList.length === 0) {
            console.log('길이기 0이랍니다함소명');

            setIsListNull(true);
        } else {
            console.log('길이기 0이나립니다김주하');

            setIsListNull(false);
        }
    }, []);

    useEffect(() => {
        if (isListNull === false) {
            console.log('pinIndex');
            console.log(pinIndex);

            console.log('props.musicAroundList');
            console.log(props.musicAroundList);
            const nowList = props.musicAroundList[pinIndex];

            console.log('nowList#####################################');
            console.log(nowList);
            setImgUrl(nowList.albumImg);
            setTag1(nowList.occasionName[0]);
            setTag2(nowList.occasionName[1]);
            setTag3(nowList.occasionName[2]);

            setSinger(nowList.singer);
            setTitle(nowList.subject);
            console.log('hola');
        }
    }, [pinIndex, isListNull]);

    function nextMusicHandler(type: string) {
        let nowPinIndex = pinIndex;
        if (type === 'prev') {
            nowPinIndex -= 1;
            if (nowPinIndex < 0) nowPinIndex = props.musicAroundList.length - 1;
        } else {
            nowPinIndex += 1;
            if (nowPinIndex > props.musicAroundList.length - 1) nowPinIndex = 0;
        }
        console.log('nextMusicHandler');

        console.log(nowPinIndex);
        console.log(props.musicAroundList[nowPinIndex]);

        setPinId(props.musicAroundList[nowPinIndex].registeredMusicId);
        setPinIndex(nowPinIndex);
    }

    function OuterOnClickHandler() {
        console.log('밖만 들어감');
        props.setIsSelect(false);
    }

    function InnerOnClickHandler(event: React.MouseEvent<HTMLDivElement, MouseEvent>) {
        event.stopPropagation(); // 이벤트가 상위 DOM으로 전파되지 않도록 막음
        console.log('안 클릭 처리');
    }

    return (
        <S.Outer onClick={OuterOnClickHandler}>
            <S.MusicBox onClick={InnerOnClickHandler}>
                {!isNearNull && !isListNull ? (
                    <>
                        <Image
                            src={beforeBtn}
                            width={35}
                            height={35}
                            $unit='px'
                            onClick={() => {
                                nextMusicHandler('prev');
                            }}
                        ></Image>
                        <S.BigWrapper
                            onClick={() => {
                                navigate('/musicPlay', { state: { data: pinId } });
                            }}
                        >
                            <Image src={imgUrl} width={100} height={100} $unit='px' $borderRadius='10px'></Image>

                            <S.MidWrapper>
                                <S.MapTextrapper>
                                    <Text size='body2' fontWeight='bold' color='main1' $margin='0 0 5px'>
                                        {title}
                                    </Text>
                                    <Text size='body2' fontWeight='medium' color='main1' $margin='0 0 10px'>
                                        {singer}
                                    </Text>
                                </S.MapTextrapper>

                                <S.MapMusicTagWrapper>
                                    {/* TODO:버튼 크기 다시확인(아톰 버튼에 없음?) */}
                                    <Button option='tag1' $width='55px' $height='25px' size='small'>
                                        {tag1}
                                    </Button>
                                    <Button option='tag1' $width='55px' $height='25px' size='small'>
                                        {tag2}
                                    </Button>
                                    <Button option='tag1' $width='55px' $height='25px' size='small'>
                                        {tag3}
                                    </Button>
                                </S.MapMusicTagWrapper>
                            </S.MidWrapper>
                        </S.BigWrapper>
                        <Image
                            src={afterBtn}
                            width={35}
                            height={35}
                            $unit='px'
                            onClick={() => {
                                nextMusicHandler('next');
                            }}
                        ></Image>
                    </>
                ) : (
                    <p>주변의 음악이 아닙니다.</p>
                )}
            </S.MusicBox>
        </S.Outer>
    );
}
