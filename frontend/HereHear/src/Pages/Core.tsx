import { useEffect, useState } from 'react';
// import './Core.styles';
import * as S from './Core.styles';
import testImage from '../assets/Core/Union.png';

export default function Core() {
    const [isUpdate, setIsUpdate] = useState(false);
    const [lat, setLat] = useState(0); // 위도
    const [lng, setLng] = useState(0); // 경도
    const [musicList, setMusicList] = useState([
        {
            registeredMusicId: 3,
            lng: 45.2,
            lat: 424.4,
            comment: 'ddd',
            subject: 'subject2',
            singer: 'singer',
            albumImg: 'albumImg',
        },
        {
            registeredMusicId: 4,
            lng: 45.2,
            lat: 424.4,
            comment: 'ddd',
            subject: 'subject2',
            singer: 'singer',
            albumImg: 'albumImg',
        },
    ]);

    let naver: any;
    let map: typeof naver;
    // let userPin: any;
    let script: any;
    const [userPin, setUserPin] = useState('');

    useEffect(() => {
        const apiKey = import.meta.env.VITE_NAVER_MAP_API_KEY;
        script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = `https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${apiKey}`;

        script.onload = () => {
            // 전역 변수 naver 사용
            naver = window.naver;

            const mapOptions = {
                center: new naver.maps.LatLng(37.3595704, 127.105399),
                zoom: 10,
            };

            map = new naver.maps.Map('map', mapOptions);

            const arr: any[] = [];

            for (let i = 0; i < 3; i++) {
                // 마커 표시
                arr[i] = new naver.maps.Marker({
                    position: new naver.maps.LatLng(37.4867995957995 + i * 0.1, 126.982211871752),
                    map: map,
                    icon: {
                        content: `
                        <div style="position: relative">
                            <img alt='img' src='${testImage}' className='pin' style="position: absolute" />
                            <img
                            src="https://image.bugsm.co.kr/album/images/500/204598/20459847.jpg"
                            alt="pin-album"
                            style="position: absolute; width: 40px; height: 40px; border-radius: 10px; left: 5.5px; top: 5.5px"
                            />
                        </div>
                        `,
                    },
                });

                // 마커 클릭 시 발생하는 이벤트
                naver.maps.Event.addListener(arr[i], 'click', function () {
                    console.log(`marker${i} clicked`);
                    alert(`marker${i} clicked`);
                });
                console.log(arr.length);
            }

            if (!navigator.geolocation) {
                console.error('Geolocation is not supported by your browser');
                return;
            }

            // 최초에 지도에 현재 위치 찍기
            navigator.geolocation.getCurrentPosition(
                position => {
                    const { latitude, longitude } = position.coords;
                    console.log('Latitude:', latitude, 'Longitude:', longitude);
                    setLat(latitude);
                    setLng(longitude);

                    setUserPin(
                        new naver.maps.Marker({
                            position: new naver.maps.LatLng(37.4867995957995, 126.983211871752),
                            map: map,
                            icon: {
                                content: `
                                <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; border: 4px solid white;"></div>
                        `,
                            },
                        })
                    );
                },
                error => {
                    console.error('Error getting location:', error);
                },
                {
                    enableHighAccuracy: true,
                    maximumAge: 0,
                }
            );

            // 지도 핀찍기 테스트
            setTimeout(() => {
                arr[0].setMap(null);

                arr[arr.length] = new naver.maps.Marker({
                    position: new naver.maps.LatLng(37.4867995957995, 126.983211871752),
                    map: map,
                    icon: {
                        content: `
                        <div style="position: relative">
                            <img alt='img' src='${testImage}' className='pin' style="position: absolute" />
                            <img
                            src="https://image.bugsm.co.kr/album/images/500/204598/20459847.jpg"
                            alt="pin-album"
                            style="position: absolute; width: 40px; height: 40px; border-radius: 10px; left: 5.5px; top: 5.5px"
                            />
                        </div>

                        `,
                    },
                });

                setUserPin(
                    new naver.maps.Marker({
                        position: new naver.maps.LatLng(37.4867995957995, 126.983211871752),
                        map: map,
                        icon: {
                            content: `
                            <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; border: 4px solid white;"></div>
                    `,
                        },
                    })
                );

                console.log(arr.length);
            }, 10000); // 10초 후에 실행
        };

        document.body.appendChild(script);

        // 컴포넌트 언마운트 시 스크립트 제거
        return () => {
            document.body.removeChild(script);
        };
    }, [isUpdate]);

    return (
        // <div id='map__display'>
        //     <div id='map'></div>
        // </div>
        <S.MapDisplay>
            <S.Map id='map'></S.Map>
        </S.MapDisplay>
    );
}

declare global {
    interface Window {
        naver: any;
    }
}
