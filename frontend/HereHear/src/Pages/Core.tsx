import { useEffect, useState } from 'react';
// import './Core.styles';
import * as S from './Core.styles';
import testImage from '../assets/Core/Union.png';

export default function Core() {
    const [isUpdate, setIsUpdate] = useState(false);
    const [lat, setLat] = useState(0); // 위도
    const [lng, setLng] = useState(0); // 경도

    // 외부로부터 입력된 데이터
    const [musicList, setMusicList] = useState([
        {
            registeredMusicId: 3,
            lat: 36.3528192,
            lng: 127.3102336,
            comment: 'ddd',
            subject: 'subject2',
            singer: 'singer',
            albumImg: 'https://image.bugsm.co.kr/album/images/500/204598/20459847.jpg',
        },
        {
            registeredMusicId: 4,
            lat: 36.3528192,
            lng: 127.3202336,
            comment: 'ddd',
            subject: 'subject2',
            singer: 'singer',
            albumImg: 'https://i.namu.wiki/i/T74peTI9G97pbLNLGK7H8XawCFQl9-0-GmJPIlrX7h1pg7N9C6Tm0QfKtTcd5XXEGCyUBjIqNgCYzfGyJj0lwA.webp',
        },
    ]);

    const [musicMap, setMusicMap] = useState({});
    const [musicPin, setMusicPin] = useState({});

    const [naverState, setNaverState] = useState();
    const [mapState, setMapState] = useState();
    const [userPinState, setUserPinState] = useState();
    const [centerState, setCenterState] = useState();

    useEffect(() => {
        // 지도 초기화
        const apiKey = import.meta.env.VITE_NAVER_MAP_API_KEY;
        const script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = `https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${apiKey}`;

        // 지도 초기화 완료 시 최초 1회 실행
        script.onload = () => {
            const naver = window.naver;
            setNaverState(naver);

            // const mapOptions = {
            //     center: new naver.maps.LatLng(37.3595704, 127.105399),
            //     zoom: 10,
            // };

            // map = new naver.maps.Map('map', mapOptions);

            const map = new naver.maps.Map('map', {
                center: new naver.maps.LatLng(37.3595704, 127.105399),
                zoom: 10,
            });
            setMapState(map);

            // 음악 데이터를 Map 형태로 변경하여 저장
            const musicMapIns: MusicMap = musicList.reduce((map: MusicMap, music: Music) => {
                const { registeredMusicId, ...otherProps } = music;
                map[registeredMusicId] = otherProps;
                return map;
            }, {});

            setMusicMap(musicMapIns);

            const arr: any[] = [];
            const pinIns: any = {};

            for (const key in musicMapIns) {
                console.log('KEEEEEEEEEY');
                console.log(key);
                console.log('MMMAAAAAAAAAPP');
                console.log(musicMapIns);
                console.log(musicMapIns[key].lat);

                // 마커 표시
                pinIns[key] = new naver.maps.Marker({
                    position: new naver.maps.LatLng(musicMapIns[key].lat, musicMapIns[key].lng),
                    map: map,
                    icon: {
                        content: `
                        <div style="position: relative">
                            <img alt='img' src='${testImage}' className='pin' style="position: absolute" />
                            <img
                            src="${musicMapIns[key].albumImg}"
                            alt="pin-album"
                            style="position: absolute; width: 40px; height: 40px; border-radius: 10px; left: 5.5px; top: 5.5px"
                            />
                        </div>
                        `,
                    },
                });

                // 마커 클릭 시 발생하는 이벤트
                naver.maps.Event.addListener(pinIns[key], 'click', function () {
                    console.log(`marker${key} clicked`);
                    alert(`marker${key} clicked`);
                });
                console.log(arr.length);
            }

            if (!navigator.geolocation) {
                console.error('Geolocation is not supported by your browser');
                return;
            }

            let latitude: number;
            let longitude: number;

            // 현재 위치 가져오기
            navigator.geolocation.getCurrentPosition(
                position => {
                    latitude = position.coords.latitude;
                    longitude = position.coords.longitude;
                    // const { latitude, longitude } = position.coords;

                    console.log('Latitude:', latitude, 'Longitude:', longitude);
                    setLat(latitude);
                    setLng(longitude);

                    // 최초에 지도에 현재 위치 찍기
                    const userPin = new naver.maps.Marker({
                        position: new naver.maps.LatLng(latitude, longitude),
                        map: map,
                        icon: {
                            content: `
                                <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; border: 4px solid white;"></div>
                        `,
                        },
                    });

                    setUserPinState(userPin);

                    // 현재 위치로 맵 가운데를 변경시키기
                    const center = new naver.maps.LatLng(latitude, longitude);
                    setCenterState(center);
                    map.panTo(center);
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
            // setTimeout(() => {
            //     arr[0].setMap(null);

            //     arr[arr.length] = new naver.maps.Marker({
            //         position: new naver.maps.LatLng(37.4867995957995, 126.983211871752),
            //         map: map,
            //         icon: {
            //             content: `
            //             <div style="position: relative">
            //                 <img alt='img' src='${testImage}' className='pin' style="position: absolute" />
            //                 <img
            //                 src="https://image.bugsm.co.kr/album/images/500/204598/20459847.jpg"
            //                 alt="pin-album"
            //                 style="position: absolute; width: 40px; height: 40px; border-radius: 10px; left: 5.5px; top: 5.5px"
            //                 />
            //             </div>

            //             `,
            //         },
            //     });

            //     console.log(arr.length);

            //     userPin.setMap(null);

            //     userPin = new naver.maps.Marker({
            //         position: new naver.maps.LatLng(37.4867995957995, 126.983211871752),
            //         map: map,
            //         icon: {
            //             content: `
            //                 <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; z-index: 999; border: 4px solid white;"></div>
            //         `,
            //         },
            //     });
            // }, 3000); // 10초 후에 실행
        };

        document.body.appendChild(script);

        // 컴포넌트 언마운트 시 스크립트 제거
        return () => {
            document.body.removeChild(script);
        };
    }, [isUpdate]);

    // 정해진 시간마다 내 위치 정보 받아오는 기능
    // useEffect(() => {
    //     if (!navigator.geolocation) {
    //         console.error('Geolocation is not supported by your browser');
    //         return;
    //     }

    //     // 최초에 지도에 현재 위치 찍기
    //     navigator.geolocation.getCurrentPosition(
    //         position => {
    //             const { latitude, longitude } = position.coords;
    //             console.log('Latitude:', latitude, 'Longitude:', longitude);
    //             setLat(latitude);
    //             setLng(longitude);

    //             userPin = new naver.maps.Marker({
    //                 position: new naver.maps.LatLng(37.4867995957995, 126.983211871752),
    //                 map: map,
    //                 icon: {
    //                     content: `
    //                     <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; z-index: 999;"></div>
    //                     `,
    //                 },
    //             });
    //         },
    //         error => {
    //             console.error('Error getting location:', error);
    //         },
    //         {
    //             enableHighAccuracy: true,
    //             maximumAge: 0,
    //         }
    //     );

    //     const intervalId = setInterval(() => {
    //         navigator.geolocation.getCurrentPosition(
    //             position => {
    //                 const { latitude, longitude } = position.coords;
    //                 console.log('Latitude:', latitude, 'Longitude:', longitude);
    //                 setLat(latitude);
    //                 setLng(longitude);
    //             },
    //             error => {
    //                 console.error('Error getting location:', error);
    //             },
    //             // 위치 정보 업데이트 관련 설정 (건들일 필요 x)
    //             {
    //                 enableHighAccuracy: true,
    //                 maximumAge: 0,
    //             }
    //         );
    //     }, 3000);

    //     return () => {
    //         clearInterval(intervalId);
    //     };
    // }, []);

    // 지정한 시간마다 실행할 함수
    useEffect(() => {
        if (mapState) {
            // mapState가 설정되었을 때만 인터벌을 시작합니다.
            const intervalId = setInterval(() => {
                console.log('naverState', naverState);
                console.log('mapState', mapState);
                console.log('userPinState', userPinState);
                console.log('centerState', centerState);
            }, 3000); // 3000ms = 3초

            // 컴포넌트가 언마운트될 때 인터벌을 클리어
            return () => clearInterval(intervalId);
        }
    }, [mapState, naverState, userPinState, centerState]);

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

interface Music {
    registeredMusicId: number;
    lng: number;
    lat: number;
    comment: string;
    subject: string;
    singer: string;
    albumImg: string;
}

interface MusicMap {
    [key: number]: Omit<Music, 'registeredMusicId'>;
}
