import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
// import { RecoilRoot } from "recoil";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Landing from "./Pages/LandingPage/LandingPage.tsx";
import AchievementPage from "./Pages/AchievementPage/AchievementPage.tsx";
import Core from "./Pages/Core.tsx";
import FollowerPage from "./Pages/FollowerPage/FollowerPage.tsx";
import FollowingPage from "./Pages/FollowingPage/FollowingPage.tsx";
import ListenedMusicPage from "./Pages/ListenedMusicPage/ListenedMusicPage.tsx";
import MyPage from "./Pages/MyPage/MyPage.tsx";
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
import RegistMusicMentPage from "./Pages/RegistMusicMentPage/RegistMusicMentPage.tsx";
import MusicPlayPage from "./Pages/MusicPlayPage/MusicPlayPage.tsx";
import LikePage from "./Pages/LikePage/LikePage.tsx";
import MyRegistPage from "./Pages/MyRegistPage/MyRegistPage.tsx";
import SearchRegistMusicPage from "./Pages/SearchRegistMusicPage/SearchRegistMusicPage.tsx";
import MyStatisticsPage from "./Pages/MyStatisticsPage/MyStatisticsPage.tsx";
import RedirectHandler from "./RedirectHandler.tsx";
import YourPage from "./Pages/YourPage/YourPage.tsx";
// import { Text } from "./components/atoms/Text/Text.styles.ts";

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            retry: 1,
            // suspense: true,
            // suspense: true,
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
                        {/* <Suspense fallback={<Text>로딩중</Text>}> */}
                        <RedirectHandler />
                        <Routes>
                            <Route path="/" element={<Landing />}></Route>
                            <Route
                                path="/memberInfo"
                                element={<NicknamePage />}
                            ></Route>
                            {/* // 닉네임 설정 */}
                            <Route
                                path="/character"
                                element={<CharacterPage />}
                            ></Route>
                            {/* // 캐릭터 설정 */}
                            <Route path="/core" element={<Core />}></Route>
                            {/* map 화면 */}
                            <Route
                                path="/achievement"
                                element={<AchievementPage />}
                            ></Route>
                            {/* // 뱃지 화면 */}
                            <Route
                                path="/registMusic"
                                element={<SearchRegistMusicPage />}
                            ></Route>
                            {/* // 음악 등록 */}
                            <Route
                                path="/registMusicMent"
                                element={<RegistMusicMentPage />}
                            ></Route>
                            {/* //음악 등록 후 멘트태그 작성 */}
                            <Route
                                path="/selectMusic"
                                element={<SelectMusic />}
                            ></Route>
                            {/* // 음악 상세 */}
                            <Route
                                path="/musicPlay"
                                element={<MusicPlayPage />}
                            ></Route>
                            {/* // 음악 실행 페이지 */}
                            <Route path="/stats" element={<Stats />}></Route>
                            {/* 통계 화면 */}
                            <Route
                                path="/follower"
                                element={<FollowerPage />}
                            ></Route>
                            {/* // 팔로우 */}
                            <Route
                                path="/following"
                                element={<FollowingPage />}
                            ></Route>
                            {/* // 팔로잉 */}
                            <Route
                                path="/like"
                                element={<LikePage />}
                            ></Route>{" "}
                            {/* // 좋아요한 음악 */}
                            <Route
                                path="/listenedMusic"
                                element={<ListenedMusicPage />}
                            ></Route>
                            {/* // 들었던 음악 */}
                            <Route path="/mypage" element={<MyPage />}></Route>
                            {/* // 마이페이지 */}
                            <Route
                                path="/mypage/:id"
                                element={<YourPage />}
                            ></Route>
                            {/* // 다른사람 마이페이지 */}
                            <Route
                                path="/myRegist"
                                element={<MyRegistPage />}
                            ></Route>
                            {/* // 내가 등록한 음악 */}
                            <Route
                                path="/myStatistics"
                                element={<MyStatisticsPage />}
                            ></Route>
                            {/* // 내 통계 */}
                            <Route path="/app" element={<App />}></Route>
                        </Routes>
                        {/* </Suspense> */}
                    </QueryClientProvider>
                </ThemeProvider>
            </BrowserRouter>
        </RecoilRoot>
    </React.StrictMode>
);
