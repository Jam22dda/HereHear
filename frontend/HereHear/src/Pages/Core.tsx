import { useEffect, useState } from 'react';
// import './Core.styles';
import * as S from './Core.styles';
import markImage from '../assets/Core/Union.png';
import gpsPinActImage from '../assets/Core/gpsPinActivated.png';
import gpsPinDeactImage from '../assets/Core/gpsPinDeactivated.png';
import MusicBox from '../components/molcules/MusicBox/MusicBox';
import Navbar from '../components/molcules/Navbar/Navbar';

export default function Core() {
    const [isUpdate, setIsUpdate] = useState(false);
    const [lat, setLat] = useState(0); // 위도
    const [lng, setLng] = useState(0); // 경도

    const [selectMusic, setSelectMusic] = useState(0); // 경도
    const [isSelect, setIsSelect] = useState(false); // 경도

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
    // let userPin: any;

    // 최초 1회 실행
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
            console.log('naver');
            console.log(naver);

            const map = new naver.maps.Map('map', {
                center: new naver.maps.LatLng(37.3595704, 127.105399),
                zoom: 15,
            });

            // naver.maps.Event.addListener(map, 'mousedown', function () {
            //     console.log(setIsUpdate(false));
            // });

            // 마우스 이벤트가 발생하면 자동으로 따라가기 취소하는 이벤트 추가
            // https://navermaps.github.io/maps.js.ncp/docs/tutorial-UI-Event.html
            // touchstart
            window.addEventListener('mousedown', function () {
                console.log('화면 자동 업데이트 비활성화');
                console.log(setIsUpdate(false));
            });
            // naver.maps.Event.addListener(map, 'click', function () {
            //     console.log(setIsUpdate(false));
            // });
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
                // 마커 표시
                pinIns[key] = new naver.maps.Marker({
                    position: new naver.maps.LatLng(musicMapIns[key].lat, musicMapIns[key].lng),
                    map: map,
                    icon: {
                        content: `
                        <div style="position: relative">
                            <img alt='img' src='${markImage}' className='pin' style="position: absolute" />
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
                    setUserPinState(
                        new naver.maps.Marker({
                            position: new naver.maps.LatLng(latitude, longitude),
                            map: map,
                            icon: {
                                content: `
                                <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; border: 4px solid white; box-shadow: 3px 3px 5px #8496B4;"></div>
                        `,
                            },
                        })
                    );

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
        console.log('im 한번만이에요');

        // 컴포넌트 언마운트 시 스크립트 제거
        return () => {
            document.body.removeChild(script);
        };
    }, []);

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

    // 지도 가운데 변경 (3초마다)
    useEffect(() => {
        if (mapState && naverState && userPinState && centerState) {
            // mapState가 설정되었을 때만 인터벌을 시작합니다.
            const intervalId = setInterval(() => {
                console.log('setMap');

                (userPinState as any).setMap(null);

                let latitude: number;
                let longitude: number;

                // 현재 위치 가져오기
                navigator.geolocation.getCurrentPosition(
                    position => {
                        latitude = position.coords.latitude;
                        longitude = position.coords.longitude;
                        // const { latitude, longitude } = position.coords;

                        // console.log('Latitude:', latitude, 'Longitude:', longitude);
                        setLat(latitude);
                        setLng(longitude);

                        // 변경된 현재 위치 찍기
                        const userPin = new (naverState as any).maps.Marker({
                            position: new (naverState as any).maps.LatLng(latitude, longitude),
                            // position: new naverState.maps.LatLng(33.3590628, 126.534361), // 에졔로 제주도 이동하게 만듦
                            map: mapState,
                            icon: {
                                content: `
                                    <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; border: 4px solid white; box-shadow: 3px 3px 5px #8496B4;"></div>
                            `,
                            },
                        });

                        setUserPinState(userPin);
                    },
                    error => {
                        console.error('Error getting location:', error);
                    },
                    {
                        enableHighAccuracy: true,
                        maximumAge: 0,
                    }
                );
            }, 500); // 3000ms = 3초

            // 컴포넌트가 언마운트될 때 인터벌을 클리어
            return () => clearInterval(intervalId);
        }
    }, [mapState, naverState, userPinState, centerState]);

    // 내가 이동할 때마다 위치 업데이트 시키기
    useEffect(() => {
        let intervalId: any;

        if (isUpdate && centerState && naverState && mapState) {
            console.log('화면 자동 업데이트 활성화');
            console.log(lat + ' ' + lng);

            const center = new (naverState as any).maps.LatLng(lat, lng);
            // const center = new naverState.maps.LatLng(33.3590628, 126.534361); // 예제에서는 제주도 좌표 사용
            setCenterState(center);
            (mapState as any).setZoom(15, true);
            (mapState as any).panTo(center);

            intervalId = setInterval(() => {
                // 현재 위치로 맵 가운데를 변경시키기
                const center = new (naverState as any).maps.LatLng(lat, lng);
                // const center = new naverState.maps.LatLng(33.3590628, 126.534361); // 예제에서는 제주도 좌표 사용
                setCenterState(center);
                (mapState as any).panTo(center);
            }, 3000); // 3초마다 실행
        }

        // isUpdate이 변경되면 return 실행됨
        return () => {
            if (intervalId) {
                console.log('화면 자동 업데이트 비활성화');

                clearInterval(intervalId);
            }
        };
    }, [isUpdate]);

    function handlerBtnClick() {
        setIsUpdate(prev => !prev);
    }

    return (
        // <div id='map__display'>
        //     <div id='map'></div>
        // </div>
        <>
            <S.MapDisplay>
                {isUpdate === true ? (
                    <S.ImgOuter>
                        <img src={gpsPinActImage} alt='gpsImage' onClick={handlerBtnClick} />
                    </S.ImgOuter>
                ) : (
                    <S.ImgOuter>
                        <img src={gpsPinDeactImage} alt='gpsImage' onClick={handlerBtnClick} />
                    </S.ImgOuter>
                )}
                <S.Map id='map'></S.Map>
                {isSelect ? <MusicBox></MusicBox> : null}
                <MusicBox></MusicBox>
                <Navbar></Navbar>
            </S.MapDisplay>
        </>
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
