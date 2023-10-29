import { useEffect, useState } from "react";
import { Map, MapDisplay } from "./Core.styles"; // 스타일을 가져옴
import testImage from "../assets/Core/test.png";

// import { Image } from "../components/atoms/Image/Image";
// import MusicBin from "../../../assets/Core/AlbumArtPin.png"

export default function Core() {
  const [isUpdate, setIsUpdate] = useState(false);

  useEffect(() => {
    const apiKey = import.meta.env.VITE_NAVER_MAP_API_KEY;
    const script = document.createElement("script");
    script.type = "text/javascript";
    script.src = `https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${apiKey}`;

    script.onload = () => {
      // 전역 변수 naver 사용
      const naver = window.naver;

      const mapOptions = {
        center: new naver.maps.LatLng(37.3595704, 127.105399),
        zoom: 10,
      };

      const map = new naver.maps.Map("map", mapOptions);

      const arr = [];
      const w = 37.4867995957995;
      for (let i = 0; i < 10000; i++) {
        arr[i] = new naver.maps.Marker({
          position: new naver.maps.LatLng(
            37.4867995957995 + i * 0.1,
            126.982211871752
          ),
          map: map,
          icon: {
            content: `
                        
                        <img alt='img' src='${testImage}' />
                        `,
          },
        });
      }
    };

    document.body.appendChild(script);

    // 컴포넌트 언마운트 시 스크립트 제거
    return () => {
      document.body.removeChild(script);
    };
  }, [isUpdate]);

  return (
    <MapDisplay>
      <Map></Map>
    </MapDisplay>
    // <div>
    //     <img alt={`${testImage}`} src={`${testImage}`} />
    // </div>
  );
}
