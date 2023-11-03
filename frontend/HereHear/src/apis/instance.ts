import axios, { AxiosInstance } from "axios";

// const APP_SERVER_URL = "https://k9b202.p.ssafy.io/api/";
const APP_SERVER_URL = "http://localhost:8080";

const instance: AxiosInstance = axios.create({
    baseURL: `${APP_SERVER_URL}/`,
    headers: {
        "Content-Type": "application/json",
    },
});

instance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("token");
        console.log(token);
        if (token) {
            // 직접 속성을 할당하는 대신 이 방법을 사용
            config.headers["Authorization"] = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export { instance };

// Authorization:
//             "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm92aWRlciI6Imtha2FvIiwibWVtYmVySWQiOiIxNCIsInN1YiI6ImF1dGgiLCJpYXQiOjE2OTg4ODY2NjAsImV4cCI6MTY5ODk3MzA2MH0.jM200UVHBqfIfO8vtw8TTTfFMh8iMVxOAoJ5oqpuBFX8QzB3FxAbpQracD2BHYKTGTHB08lY9_SZtZvqTvZpyw",

// import axios, { Axios } from "axios";

// // const APP_SERVER_URL = "http://localhost:3000";
// const APP_SERVER_URL = "https://k9b202.p.ssafy.io";

// const instance: Axios = axios.create({
//     baseURL: `${APP_SERVER_URL}/api`,
//     // withCredentials: true,
//     headers: {
//         "Content-Type": "application/json",
//     },
// });

// // 요청 인터셉터
// instance.interceptors.request.use(
//     (config) => {
//         // const userIdFromLocalStorage = localStorage.getItem("userId");
//         // const userId = userIdFromLocalStorage ? parseInt(userIdFromLocalStorage, 10) : 1;
//         // config.headers.userId = userId;
//         return config;
//     },
//     (error) => {
//         return Promise.reject(error);
//     }
// );

// export { instance };
