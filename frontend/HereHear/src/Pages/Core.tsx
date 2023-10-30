import { useEffect, useState } from "react";
import "./Core.styles";
import testImage from "../assets/Core/Union.png";

export default function Core() {
    const [isUpdate, setIsUpdate] = useState(false);
    const [musicList, setMusicList] = useState([
        {
            registeredMusicId: 3,
            lng: 45.2,
            lat: 424.4,
            comment: "ddd",
            subject: "subject2",
            singer: "singer",
            albumImg: "albumImg",
        },
        {
            registeredMusicId: 4,
            lng: 45.2,
            lat: 424.4,
            comment: "ddd",
            subject: "subject2",
            singer: "singer",
            albumImg: "albumImg",
        },
    ]);

    let naver: any;

    useEffect(() => {
        const apiKey = import.meta.env.VITE_NAVER_MAP_API_KEY;
        const script = document.createElement("script");
        script.type = "text/javascript";
        script.src = `https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${apiKey}`;

        script.onload = () => {
            // 전역 변수 naver 사용
            naver = window.naver;

            const mapOptions = {
                center: new naver.maps.LatLng(37.3595704, 127.105399),
                zoom: 10,
            };

            const map = new naver.maps.Map("map", mapOptions);

            const arr: any[] = [];

            for (let i = 0; i < 10; i++) {
                // 마커 표시
                arr[i] = new naver.maps.Marker({
                    position: new naver.maps.LatLng(37.4867995957995 + i * 0.1, 126.982211871752),
                    map: map,
                    icon: {
                        content: `

                        <img alt='img' src='${testImage}' />
                        `,
                    },
                });

                // 마커 클릭 시 발생하는 이벤트
                naver.maps.Event.addListener(arr[i], "click", function (e: any) {
                    alert(`marker${i} clicked`);
                });
            }
            setTimeout(() => {
                const hello = new naver.maps.Marker({
                    position: new naver.maps.LatLng(37.4867995957995, 126.972211871752),
                    map: map,
                    icon: {
                        content: `

                        <img alt='img' src='${testImage}' />
                        `,
                    },
                });
                arr[0].setMap(null);
            }, 10000); // 10초 후에 실행

            setTimeout(() => {
                const hello = new naver.maps.Marker({
                    position: new naver.maps.LatLng(37.4867995957995, 126.972211871752),
                    map: map,
                    icon: {
                        content: `

                        <img alt='img' src='${testImage}' />
                        `,
                    },
                });
                arr[0].setMap(null);
            }, 11000); // 10초 후에 실행
        };

        document.body.appendChild(script);

        // 컴포넌트 언마운트 시 스크립트 제거
        return () => {
            document.body.removeChild(script);
        };
    }, [isUpdate]);

    // useEffect(() => {
    //     const apiKey = import.meta.env.VITE_NAVER_MAP_API_KEY;
    //     const script = document.createElement('script');
    //     script.type = 'text/javascript';
    //     script.src = `https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${apiKey}`;

    //     // let map: naver.maps.Map;

    //     script.onload = () => {
    //         let map = null;
    //         const initMap = () => {
    //             const map = new naver.maps.Map('map', {
    //                 center: new naver.maps.LatLng(37.511337, 127.012084),
    //                 zoom: 13,
    //             });
    //             initMap();
    //         };
    //     };
    // }, []);

    return (
        <div id="map__display">
            <div id="map"></div>
        </div>
        // <div>
        //     <img alt={`${testImage}`} src={`${testImage}`} />
        // </div>
    );
}
