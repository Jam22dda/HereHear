import { useEffect } from 'react';
import './Core.css';

export default function Core() {
    useEffect(() => {
        const apiKey = import.meta.env.VITE_NAVER_MAP_API_KEY;
        const script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = `https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${apiKey}`;

        script.onload = () => {
            // 전역 변수 naver 사용
            const naver = window.naver;

            const mapOptions = {
                center: new naver.maps.LatLng(37.3595704, 127.105399),
                zoom: 10,
            };

            const map = new naver.maps.Map('map', mapOptions);
        };

        document.body.appendChild(script);

        // 컴포넌트 언마운트 시 스크립트 제거
        return () => {
            document.body.removeChild(script);
        };
    }, []);

    return (
        <div id='map__display'>
            <div id='map'></div>
        </div>
    );
}
