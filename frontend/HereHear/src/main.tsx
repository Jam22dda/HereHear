import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
// import { RecoilRoot } from "recoil";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Landing from "./Pages/LandingPage/LandingPage.tsx";
import Achievement from "./Pages/Achievement.tsx";
import Core from "./Pages/Core.tsx";
import Follow from "./Pages/Follow.tsx";
import Following from "./Pages/Following.tsx";
import Like from "./Pages/Like.tsx";
import ListenedMusic from "./Pages/ListenedMusic.tsx";
import MyPage from "./Pages/MyPage/MyPage.tsx";
import MyRegist from "./Pages/MyRegist.tsx";
// import RegistMusic from "./Pages/RegistMusic.tsx";
import SelectMusic from "./Pages/SelectMusic.tsx";
import Stats from "./Pages/Stats.tsx";
import NicknamePage from "./Pages/NicknamePage/NicknamePage.tsx";
import CharacterPage from "./Pages/CharacterPage/CharacterPage.tsx";
import { ThemeProvider } from "styled-components";
import App from "./App.tsx";
import theme from "./styles/theme.ts";
import "../src/styles/font.css";
import GlobalStyle from "../src/styles/GlobalStyle.tsx";
import { QueryClientProvider, QueryClient } from "@tanstack/react-query";
import { RecoilRoot } from "recoil";
import SearchRegistMusic from "./Pages/SearchRegistMusic/SearchRegistMusic";
// declare global {
//     interface Window {
//         naver: any;
//     }
// }

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            retry: 1,
            suspense: true,
        },
    },
});

ReactDOM.createRoot(document.getElementById("root")!).render(
    <React.StrictMode>
        <RecoilRoot>
            <BrowserRouter>
                <ThemeProvider theme={theme}>
                    <QueryClientProvider client={queryClient}>
                        <GlobalStyle />
                        <Routes>
                            <Route path="/" element={<Landing />}></Route>
                            <Route path="/nickname" element={<NicknamePage />}></Route>
                            // 닉네임 설정
                            <Route path="/character" element={<CharacterPage />}></Route>
                            // 캐릭터 설정
                            <Route path="/core" element={<Core />}></Route> // map 화면
                            <Route path="/achievement" element={<Achievement />}></Route>
                            // 뱃지 화면
                            <Route path="/registMusic" element={<SearchRegistMusic />}></Route>
                            // 음악 등록
                            <Route path="/selectMusic" element={<SelectMusic />}></Route>
                            // 음악 상세
                            <Route path="/stats" element={<Stats />}></Route> // 통계 화면
                            <Route path="/follow" element={<Follow />}></Route>
                            // 팔로우
                            <Route path="/following" element={<Following />}></Route>
                            // 팔로잉
                            <Route path="/like" element={<Like />}></Route> // 좋아요한 음악
                            <Route path="/listenedMusic" element={<ListenedMusic />}></Route>
                            // 들었던 음악
                            <Route path="/mypage/:id" element={<MyPage />}></Route>
                            // 마이페이지
                            <Route path="/myRegist" element={<MyRegist />}></Route>
                            // 내가 등록한 음악
                            {/* 출시 시 제거해야 함, 테스트용 */}
                            <Route path="/app" element={<App />}></Route>
                        </Routes>
                    </QueryClientProvider>
                </ThemeProvider>
            </BrowserRouter>
        </RecoilRoot>
    </React.StrictMode>
);
