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
