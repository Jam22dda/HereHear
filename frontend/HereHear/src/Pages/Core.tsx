import { useEffect, useState } from "react";
// import './Core.styles';
import * as S from "./Core.styles";
import markImage from "../assets/Core/Union.png";
import gpsPinActImage from "../assets/Core/gpsPinActivated.png";
import gpsPinDeactImage from "../assets/Core/gpsPinDeactivated.png";
import MusicBox from "../components/molcules/MusicBox/MusicBox";
import Navbar from "../components/molcules/Navbar/Navbar";
import { useGetMapMusicList } from "../apis/Map/Queries/useGetMapMusicList";
import { useGetAroundMusicList } from "../apis/Map/Queries/useGetAroundMusicList";
import { useRecoilValue } from "recoil";
import { SignUpInfoAtom } from "../states/SignUpAtoms";

export default function Core() {
    const signUpInfoAtom = useRecoilValue(SignUpInfoAtom);
    const myId = signUpInfoAtom.memberId;

    const [isUpdate, setIsUpdate] = useState(false);
    const [lat, setLat] = useState(0); // 위도
    const [lng, setLng] = useState(0); // 경도

    // const [selectMusic, setSelectMusic] = useState(0);
    const [isSelect, setIsSelect] = useState(false);

    const [musicMap, setMusicMap] = useState({}); // 맵 전체에 있는 음악
    const [musicPin, setMusicPin] = useState({}); // 맵 전체에 있는 음악
    // const [musicAround, setMusicAround] = useState({});

    const [naverState, setNaverState] = useState();
    const [mapState, setMapState] = useState<MusicPin | undefined>(undefined);
    const [userPinState, setUserPinState] = useState();
    const [centerState, setCenterState] = useState();
    const [userSelectPin, setUserSelectPin] = useState(0);
    const [circleState, setCircleState] = useState();

    // 외부로부터 입력된 데이터
    const { musicList, refetch } = useGetMapMusicList();
    const { musicAroundList, refetch: refetchMusicAroundList } =
        useGetAroundMusicList(lat, lng);
    const [musicAroundListState, setMusicAroundListState] = useState([]);
    // const { mutate: musicAroundList } = useGetAroundMusicList();

    const [eventSource, setEventSource] = useState<EventSource | undefined>(
        undefined
    );

    useEffect(() => {
        console.log("myId : ", myId);

        // 지도 초기화
        const apiKey = import.meta.env.VITE_NAVER_MAP_API_KEY;
        const script = document.createElement("script");
        script.type = "text/javascript";
        script.src = `https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${apiKey}`;

        // 지도 초기화 완료 시 최초 1회 실행
        script.onload = async () => {
            const naver = window.naver;
            setNaverState(naver);
            console.log(naver);

            const map = new naver.maps.Map("map", {
                center: new naver.maps.LatLng(37.3595704, 127.105399),
                zoom: 15,
            });

            // 마우스 이벤트가 발생하면 자동으로 따라가기 취소하는 이벤트 추가
            // https://navermaps.github.io/maps.js.ncp/docs/tutorial-UI-Event.html
            // touchstart
            window.addEventListener("mousedown", function () {
                setIsUpdate(false);
            });

            window.addEventListener("touchstart", function () {
                setIsUpdate(false);
            });

            setMapState(map);

            const ml = await refetch();
            console.log("ml.data");
            console.log(ml.data);

            // 음악 데이터를 Map 형태로 변경하여 저장
            const musicMapIns: MusicMap = ml.data.reduce(
                (map: MusicMap, music: Music) => {
                    const { registeredMusicId, ...otherProps } = music;
                    map[registeredMusicId] = otherProps;
                    return map;
                },
                {}
            );

            setMusicMap(musicMapIns);

            // const arr: any[] = [];
            const pinIns: any = {};

            for (const key in musicMapIns) {
                // 마커 표시
                pinIns[key] = new naver.maps.Marker({
                    position: new naver.maps.LatLng(
                        musicMapIns[key].lat,
                        musicMapIns[key].lng
                    ),
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
                        origin: new naver.maps.Point(0, 0),
                        anchor: new naver.maps.Point(30, 60),
                    },
                });

                // 마커 클릭 시 발생하는 이벤트
                naver.maps.Event.addListener(
                    pinIns[key],
                    "click",
                    async function () {
                        // useGetAroundMusicList({ lat, lng });

                        const mal = await refetchMusicAroundList();
                        console.log("@@@@@@@@@@@@@@@@ mal");
                        console.log(mal);
                        setMusicAroundListState(mal.data);

                        setIsSelect(true);
                        // console.log(`marker${key} clicked`);
                        // console.log('@@@@@@@@@@@@@musicAroundList.musicAroundList');
                        // console.log(musicAroundList.musicAroundList);
                        setUserSelectPin(Number(key));

                        // alert(`marker${key} clicked`);
                    }
                );
            }
            setMusicPin(pinIns);

            if (!navigator.geolocation) {
                console.error("Geolocation is not supported by your browser");
                return;
            }

            let latitude: number;
            let longitude: number;

            // 현재 위치 가져오기
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    latitude = position.coords.latitude;
                    longitude = position.coords.longitude;
                    // const { latitude, longitude } = position.coords;

                    console.log("Latitude:", latitude, "Longitude:", longitude);
                    setLat(latitude);
                    setLng(longitude);

                    // 최초에 지도에 현재 위치 찍기
                    setUserPinState(
                        new naver.maps.Marker({
                            position: new naver.maps.LatLng(
                                latitude,
                                longitude
                            ),
                            map: map,
                            icon: {
                                content: `
                                <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; border: 4px solid white; box-shadow: 3px 3px 5px #8496B4;"></div>
                                `,
                                origin: new naver.maps.Point(0, 0),
                                anchor: new naver.maps.Point(19, 19),
                            },
                        })
                    );

                    const circle = new naver.maps.Circle({
                        strokeColor: "#0000ff",
                        strokeOpacity: 0.8,
                        strokeWeight: 1,
                        fillColor: "#0000ff",
                        fillOpacity: 0.15,
                        center: new naver.maps.LatLng(latitude, longitude),
                        radius: 600,
                        zIndex: 100,
                        clickable: true,
                        map: map,
                    });

                    setCircleState(circle);

                    // 현재 위치로 맵 가운데를 변경시키기
                    const center = new naver.maps.LatLng(latitude, longitude);
                    setCenterState(center);
                    map.panTo(center);
                },
                (error) => {
                    console.error("Error getting location:", error);
                },
                {
                    enableHighAccuracy: true,
                    maximumAge: 0,
                }
            );

            // SSE
            // const eventSource = new EventSource('http://localhost:8080/music/subscribe/1');
            const serverUrl = import.meta.env.VITE_SERVER_URL;

            setEventSource(
                new EventSource(`${serverUrl}/music/subscribe/${myId}`)
            );
        };

        document.body.appendChild(script);
        console.log("im 한번만이에요");

        // 컴포넌트 언마운트 시 스크립트 제거
        return () => {
            document.body.removeChild(script);
        };
    }, []);

    let musicPinIns = Object.assign({}, musicPin, {}); // musicPinIns를 밖으로 이동

    useEffect(() => {
        console.log("USEEFFECT");
        console.log(eventSource);
        console.log("naverState");
        console.log(naverState);

        if (eventSource && naverState && mapState) {
            // const sse = eventSource;

            // SSE 이벤트 핸들러를 등록합니다.
            eventSource.addEventListener("sse", (event) => {
                const eventData = JSON.parse(event.data);
                console.log("musicMap");
                console.log(musicMap);

                if (Array.isArray(eventData)) {
                    // 이벤트 데이터를 처리합니다.
                    console.log("Received SSE event:", eventData);
                    console.log("나 들어온다");

                    const addList = [];
                    const delList = [];

                    for (let i = 0; i < eventData.length; i++) {
                        if (eventData[i].status === 1) {
                            addList.push(eventData[i]);
                        } else {
                            delList.push(eventData[i]);
                        }
                    }

                    console.log("LLLLLLLLLLLLList");
                    console.log(addList);
                    console.log(delList);

                    // let musicPinIns = musicPin;
                    console.log("musicPinIns 인스턴스 만들기 ");
                    console.log(musicPinIns);

                    // 음악 삭제
                    const musicDelIns: MusicMap = delList.reduce(
                        (map: MusicMap, music: Music) => {
                            const { registeredMusicId, ...otherProps } = music;
                            map[registeredMusicId] = otherProps;
                            return map;
                        },
                        {}
                    );

                    console.log("musicDelIns");
                    console.log(musicDelIns);

                    for (const key in musicDelIns) {
                        console.log("key", key);
                        // key에 해당하는 객체가 존재하는 경우
                        console.log("typeof musicPinIns@@@@@@@@@@@@@@@@@@@@@@");
                        // console.log(typeof musicPinIns);
                        // console.log(musicPinIns);

                        if (key in musicPinIns) {
                            // console.log(musicPinIns[key]);

                            // (userPinState as any).setMap(null);
                            // @ts-ignore
                            (musicPinIns[key] as any).setMap(null);
                            // @ts-ignore
                            delete (musicPinIns[key] as any);

                            console.log("musicPin in DELETE");
                            console.log(musicPinIns);
                        }
                    }

                    console.log("LLLLLLLLLLLLList 이까지는 들어오는 거 같고");
                    // 음악 데이터를 Map 형태로 변경하여 저장
                    const musicMapIns: MusicMap = addList.reduce(
                        (map: MusicMap, music: Music) => {
                            const { registeredMusicId, ...otherProps } = music;
                            map[registeredMusicId] = otherProps;
                            return map;
                        },
                        {}
                    );

                    setMusicMap((prev) => Object.assign({}, prev, musicMapIns));

                    const pinIns: any = {};

                    console.log("mapState");
                    console.log(mapState);

                    for (const key in musicMapIns) {
                        // 마커 표시
                        pinIns[key] = new (naverState as any).maps.Marker({
                            position: new (naverState as any).maps.LatLng(
                                musicMapIns[key].lat,
                                musicMapIns[key].lng
                            ),
                            map: mapState,
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
                        console.log("pinIns");

                        musicPinIns = Object.assign({}, musicPinIns, pinIns);
                        console.log("AFTER add musicPinIns ", musicPinIns);
                        // setMusicPin(prev => Object.assign({}, prev, pinIns));

                        // // 함수형 업데이트를 사용하여 상태를 안전하게 업데이트
                        // setMusicPin((prevMusicPins) => {
                        //     return [...prevMusicPins, pinIns];
                        // });

                        // console.log('Hola', Object.assign({}, musicPin, pinIns));

                        // 마커 클릭 시 발생하는 이벤트
                        (naverState as any).maps.Event.addListener(
                            pinIns[key],
                            "click",
                            async function () {
                                // useGetAroundMusicList({ lat, lng });

                                const mal = await refetchMusicAroundList();
                                console.log("@@@@@@@@@@@@@@@@ mal");
                                console.log(mal);
                                setMusicAroundListState(mal.data);

                                setIsSelect(true);
                                // console.log(`marker${key} clicked`);
                                // console.log('@@@@@@@@@@@@@musicAroundList.musicAroundList');
                                // console.log(musicAroundList.musicAroundList);
                                setUserSelectPin(Number(key));

                                // alert(`marker${key} clicked`);
                            }
                        );
                    }

                    setMusicPin(musicPinIns);
                }
            });

            // SSE 에러 핸들러를 등록합니다.
            eventSource.addEventListener("error", (error) => {
                console.error("SSE error:", error);
            });

            // SSE 연결이 닫힐 때의 핸들러를 등록합니다.
            eventSource.addEventListener("close", () => {
                console.log("SSE connection closed.");
            });
        }
    }, [eventSource, naverState, mapState]);

    useEffect(() => {
        console.log("useEffect musicPin");

        console.log(musicPin);
    }, [musicPin]);

    // 지도 가운데 변경 (3초마다)
    useEffect(() => {
        if (mapState && naverState && userPinState && centerState) {
            // mapState가 설정되었을 때만 인터벌을 시작합니다.
            const intervalId = setInterval(() => {
                console.log("setMap");

                (userPinState as any).setMap(null);

                let latitude: number;
                let longitude: number;

                // 현재 위치 가져오기
                navigator.geolocation.getCurrentPosition(
                    (position) => {
                        latitude = position.coords.latitude;
                        longitude = position.coords.longitude;
                        // const { latitude, longitude } = position.coords;

                        // console.log('Latitude:', latitude, 'Longitude:', longitude);
                        setLat(latitude);
                        setLng(longitude);

                        // 변경된 현재 위치 찍기
                        const userPin = new (naverState as any).maps.Marker({
                            position: new (naverState as any).maps.LatLng(
                                latitude,
                                longitude
                            ),
                            // position: new naverState.maps.LatLng(33.3590628, 126.534361), // 에졔로 제주도 이동하게 만듦
                            map: mapState,
                            icon: {
                                content: `
                                    <div style="width: 30px; height: 30px; background-color: blue; border-radius: 100%; border: 4px solid white; box-shadow: 3px 3px 5px #8496B4;"></div>
                            `,
                            },
                        });

                        // 현재 위치를 중심으로 하는 반투명한 원 생성
                        const userCircle = new (naverState as any).maps.Circle({
                            map: mapState,
                            center: new (naverState as any).maps.LatLng(
                                latitude,
                                longitude
                            ),
                            radius: 500, // 반지름을 미터 단위로 설정
                            fillColor: "rgba(51, 153, 255, 0.3)", // 반투명한 파란색으로 채움
                            strokeColor: "rgba(51, 153, 255, 0.6)", // 테두리 색
                            strokeWeight: 2, // 테두리 두께
                        });

                        setUserPinState(userPin);
                    },
                    (error) => {
                        console.error("Error getting location:", error);
                    },
                    {
                        enableHighAccuracy: true,
                        maximumAge: 0,
                    }
                );
            }, 3000); // 3000ms = 3초

            // 컴포넌트가 언마운트될 때 인터벌을 클리어
            return () => clearInterval(intervalId);
        }
    }, []);

    // 내가 이동할 때마다 위치 업데이트 시키기
    useEffect(() => {
        let intervalId: any;

        if (isUpdate && centerState && naverState && mapState) {
            console.log("화면 자동 업데이트 활성화");
            console.log(lat + " " + lng);

            const center = new (naverState as any).maps.LatLng(lat, lng);
            // const center = new naverState.maps.LatLng(33.3590628, 126.534361); // 예제에서는 제주도 좌표 사용
            setCenterState(center);
            // (mapState as any).setZoom(15, true);
            (mapState as any).panTo(center);
        }

        // isUpdate이 변경되면 return 실행됨
        return () => {
            if (intervalId) {
                console.log("화면 자동 업데이트 비활성화");

                clearInterval(intervalId);
            }
        };
    }, [isUpdate, lat, lng]);

    function handlerBtnClick() {
        setIsUpdate((prev) => !prev);
    }

    return (
        // <div id='map__display'>
        //     <div id='map'></div>
        // </div>
        <>
            <S.MapDisplay>
                {isUpdate === true ? (
                    <S.ImgOuter>
                        <img
                            src={gpsPinActImage}
                            alt="gpsImage"
                            onClick={handlerBtnClick}
                        />
                    </S.ImgOuter>
                ) : (
                    <S.ImgOuter>
                        <img
                            src={gpsPinDeactImage}
                            alt="gpsImage"
                            onClick={handlerBtnClick}
                        />
                    </S.ImgOuter>
                )}
                <S.Map id="map"></S.Map>
                {isSelect ? (
                    <MusicBox
                        musicAroundList={musicAroundListState}
                        pinId={userSelectPin}
                        setIsSelect={setIsSelect}
                    ></MusicBox>
                ) : null}
                {/* <MusicBox></MusicBox> */}
                <Navbar active={true}></Navbar>
            </S.MapDisplay>
        </>
    );
}

// type lat = number;
// type lng = number;

declare global {
    interface Window {
        naver: any;
    }
}

interface MusicPin {
    [key: number]: any;
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
    [key: string]: Omit<Music, "registeredMusicId">;
}
