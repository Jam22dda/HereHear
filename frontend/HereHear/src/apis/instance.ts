import axios, { AxiosInstance } from "axios";

// const APP_SERVER_URL = "https://k9b202.p.ssafy.io/api/";
const APP_SERVER_URL = "http://localhost:8080";

const instance: AxiosInstance = axios.create({
    baseURL: `${APP_SERVER_URL}/`,
    headers: {
        Authorization:
            "Bearer eyJhbGciOiJIUzUxMiJ9.eyJwcm92aWRlciI6Imtha2FvIiwibWVtYmVySWQiOiIxNCIsInN1YiI6ImF1dGgiLCJpYXQiOjE2OTg5NzM0MjksImV4cCI6MTY5OTA1OTgyOX0.qqeheQwSgD-HDhNg0uWt75DTfjkfRNun03uwixVmrt9sOdSMgPJFHHsLWFNSihFj3rGEcGIohOWBeAZmBb2iAg",
        "Content-Type": "application/json",
    },
});

instance.interceptors.request.use(
    (config) => {
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export { instance };
