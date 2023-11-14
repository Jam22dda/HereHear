const sCacheName: string = 'react-pwa'; // 캐시제목 선언
const aFilesToCache: string[] = [
    // 캐싱할 파일 선언
    './',
    './index.html',
    './manifest.json',
];

// install event
// 서비스워커를 설치
self.addEventListener('install', (pEvent: any) => {
    console.log('서비스워커 설치');
    // 캐싱할 파일 저장
    pEvent.waitUntil(
        caches.open(sCacheName).then((pCache: Cache) => {
            // console.log('파일을 캐시에 저장');
            return pCache.addAll(aFilesToCache);
        })
    );
});

// activate event
// 서비스 워커 동작 시작
self.addEventListener('activate', (pEvent: any) => {
    // console.log('서비스워커 동작 시작');
});

// fetch event
// 데이터 요청시 수행
self.addEventListener('fetch', (pEvent: any) => {
    // pEvent.respondWith(
    //     // 오프라인에서 데이터 색인
    //     caches
    //         .match(pEvent.request)
    //         .then((response: Response | undefined) => {
    //             // 캐시에 없는 데이터인 경우
    //             if (!response) {
    //                 // console.log('네트워크에서 데이터 요청', pEvent.request);
    //                 return fetch(pEvent.request, { mode: 'cors' });
    //             }
    //             // console.log('캐시에서 데이터 요청', pEvent.request);
    //             return response;
    //         })
    //         .catch((err: Error) => console.log(err))
    // );
});
