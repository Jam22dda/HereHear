import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
// import { RecoilRoot } from "recoil";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Landing from "./Pages/Landing.tsx";
import Achievement from "./Pages/Achievement.tsx";
import Core from "./Pages/Core.tsx";
import Follow from "./Pages/Follow.tsx";
import Following from "./Pages/Following.tsx";
import Like from "./Pages/Like.tsx";
import ListenedMusic from "./Pages/ListenedMusic.tsx";
import Member from "./Pages/Member.tsx";
import MyRegist from "./Pages/MyRegist.tsx";
import RegistMusic from "./Pages/RegistMusic.tsx";
import SelectMusic from "./Pages/SelectMusic.tsx";
import Stats from "./Pages/Stats.tsx";
import { ThemeProvider } from "styled-components";
import App from "./App.tsx";
import theme from "./styles/theme.ts";
import "../src/styles/font.css";
import GlobalStyle from "../src/styles/GlobalStyle.tsx";
import { QueryClientProvider, QueryClient } from "@tanstack/react-query";
import { RecoilRoot } from "recoil";

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

                            <Route path="/core" element={<Core />}></Route>
                            <Route path="/achievement" element={<Achievement />}></Route>
                            <Route path="/registMusic" element={<RegistMusic />}></Route>
                            <Route path="/selectMusic" element={<SelectMusic />}></Route>
                            <Route path="/stats" element={<Stats />}></Route>

                            <Route path="/follow" element={<Follow />}></Route>
                            <Route path="/following" element={<Following />}></Route>
                            <Route path="/like" element={<Like />}></Route>
                            <Route path="/listenedMusic" element={<ListenedMusic />}></Route>
                            <Route path="/member/:id" element={<Member />}></Route>
                            <Route path="/myRegist" element={<MyRegist />}></Route>

                            {/* 출시 시 제거해야 함, 테스트용 */}
                            <Route path="/app" element={<App />}></Route>
                        </Routes>
                    </QueryClientProvider>
                </ThemeProvider>
            </BrowserRouter>
        </RecoilRoot>
    </React.StrictMode>
);
