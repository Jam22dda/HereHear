import * as S from "./StatisticPage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import { Image } from "../../components/atoms/Image/Image";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { useNavigate } from "react-router-dom";
// import monzi from "../../../public/images/monzi-herehear.png";
// import iconBack from "../../assets/CircleButton/icon-back.png";
import MusicItem from "../../components/molcules/MusicItem/MusicItem";
import LoveMyself from "../../../public/images/BTS_answer.jpg";
import Heart from "../../assets/CircleButton/icon-heart.png";
import { useGetLikeStatistics } from "../../apis/Statistics/Quries/useGetLikeStatistics";
import { useGetTagStatistics } from "../../apis/Statistics/Quries/useGetTagStatistics";
import { useGetListenStatistics } from "../../apis/Statistics/Quries/useGetListentatistics";
import { useMusicHistory } from "../../apis/Music/Mutations/useMusicHistory";

import { Bar, Pie } from "react-chartjs-2";
import ChartDataLabels from "chartjs-plugin-datalabels";
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    PieController,
    // Plugin,
} from "chart.js";

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    PieController,
    ChartDataLabels
);

// const shadowPlugin: Plugin<"bar"> = {
//     // 'bar' 타입을 명시적으로 제공합니다.
//     id: "shadowPlugin",
//     afterDraw: (chart) => {
//         const ctx = chart.ctx;
//         chart.data.datasets.forEach((dataset, i) => {
//             const meta = chart.getDatasetMeta(i);
//             if (!meta.hidden) {
//                 meta.data.forEach((element) => {
//                     // 그림자 스타일 설정
//                     ctx.save(); // 현재 상태를 저장합니다.
//                     ctx.shadowColor = "rgba(0,0,0,0.2)";
//                     ctx.shadowBlur = 7;
//                     ctx.shadowOffsetX = 5;
//                     ctx.shadowOffsetY = 5;
//                     ctx.fillStyle = dataset.backgroundColor[index]; // 원본 데이터셋의 배경색을 사용합니다.
//                     ctx.fillRect(
//                         element.x,
//                         element.base,
//                         element.width,
//                         element.y - element.base
//                     ); // element의 사각형을 그립니다.
//                     ctx.restore(); // 저장된 상태를 복구하여 그림자를 다른 요소에 영향이 가지 않도록 합니다.
//                 });
//             }
//         });
//     },
// };

export default function StatisticsPage() {
    const navigate = useNavigate();

    const navigatePage = (path: string) => {
        navigate(path);
    };

    const LikeStatistics = useGetLikeStatistics();
    const TagStatistics = useGetTagStatistics();
    const ListenStatistics = useGetListenStatistics();
    console.log(LikeStatistics);
    console.log(TagStatistics);
    console.log(ListenStatistics);

    function getCurrentMonthAndWeek(): { month: string; week: number } {
        const now = new Date();
        const month = now.toLocaleString("default", { month: "long" }); // 현재 월을 문자열로 가져옵니다.
        const startOfMonth = new Date(now.getFullYear(), now.getMonth(), 1);
        const weekNumber = Math.ceil(
            (now.getDate() + startOfMonth.getDay()) / 7
        ); // 현재 주차를 계산합니다.

        return { month, week: weekNumber };
    }

    // 사용 예:
    const { month, week } = getCurrentMonthAndWeek();

    interface likemusic {
        albumImg: string;
        likeCount: number;
        registeredMusicId: number;
        singer: string;
        subject: string;
    }

    const musicLabels: string[] =
        LikeStatistics && LikeStatistics.length > 0
            ? LikeStatistics.map((record: likemusic) => record.subject)
            : [];
    const artistLabels: string[] =
        LikeStatistics && LikeStatistics.length > 0
            ? LikeStatistics.map((record: likemusic) => record.singer)
            : [];
    const likeCount: string[] =
        LikeStatistics && LikeStatistics.length > 0
            ? LikeStatistics.map((record: likemusic) => record.likeCount)
            : [];

    const likeColors = ["#FFC0EC", "#FFF0CB", "#BDDDFD", "#F9D6D5"];
    const tagColors = ["#FFC0EC", "#BDDDFD", "#FFF0CB", "#96ebbc", "#F9D6D5"];

    const data = {
        labels: musicLabels,
        datasets: [
            {
                label: "LikeCount",
                data: likeCount,
                backgroundColor: ["#FA9CDD", "#FFE090", "#85C2FF", "#FAA09F"],
                borderRadius: 10,
                // boxShadow: "4px 4px 8px 0px #C4CEF2, -4px -4px 8px 0px #E9EDFF",
            },
        ],
    };

    const options = {
        plugins: {
            datalabels: {
                color: "#6F83B1",
                font: {
                    weight: "bold" as const, // 글자 두께
                    size: 18, // 글자 크기
                },
            },
            legend: {
                display: false,
            },
            title: {
                display: false, // 차트 제목을 숨깁니다.
            },
        },
        responsive: true,
        scales: {
            x: {
                display: false, // X축 자체를 숨깁니다.
                grid: {
                    display: false, // X축 그리드 선을 숨깁니다.
                },
            },
            y: {
                display: false, // Y축 자체를 숨깁니다.
                grid: {
                    display: false, // Y축 그리드 선을 숨깁니다.
                },
            },
        },
    };

    interface musicTag {
        tagCount: number;
        tagName: string;
    }

    const tagNameLabels: string[] =
        TagStatistics && TagStatistics.length > 0
            ? TagStatistics.map((record: musicTag) => record.tagName)
            : [];

    const tagCount: string[] =
        TagStatistics && TagStatistics.length > 0
            ? TagStatistics.map((record: musicTag) => record.tagCount)
            : [];

    const pieoptions = {
        responsive: true,
        plugins: {
            datalabels: {
                color: "#6F83B1",
                font: {
                    weight: "bold" as const, // 글자 두께
                    size: 18, // 글자 크기
                },
            },
            legend: {
                display: false,
            },
            title: {
                display: true,
            },
        },
    };

    const piedata = {
        labels: tagNameLabels,
        datasets: [
            {
                label: "TagCount",
                data: tagCount,
                backgroundColor: [
                    "#FF65D0",
                    "#85C2FF",
                    "#FFDD84",
                    "#75DFA4",
                    "#FAA09F",
                ],
                hoverOffset: 6,
                // borderWidth: 0,
            },
        ],
    };

    const { mutate: postMusicHostiryMutate } = useMusicHistory();

    const registLikeMusicHistory = () => {
        if (LikeStatistics[0].registeredMusicId !== null) {
            postMusicHostiryMutate(LikeStatistics[0].registeredMusicId);
        }
    };

    const registListenedMusicHistory = () => {
        if (ListenStatistics.registeredMusicId !== null) {
            postMusicHostiryMutate(ListenStatistics.registeredMusicId);
        }
    };

    return (
        <div id="display">
            <div className="container">
                <CircleButton
                    option="default2"
                    size="medium"
                    onClick={() => navigatePage("/core")}
                >
                    <Image
                        src={iconBack}
                        width={10}
                        height={18}
                        $unit="px"
                    ></Image>
                </CircleButton>
                <Text
                    size="subtitle1"
                    fontWeight="bold"
                    $margin="20px 0 48px 0"
                >
                    {month} {week}주차 차트
                </Text>
                <Text size="body2" $margin="0 0 8px 0">
                    가장 많이 공감을 받은 노래는
                </Text>
                <S.TextWrapper>
                    <Text size="subtitle1" fontWeight="bold">
                        {artistLabels[0]}
                    </Text>
                    <Text size="body2" $marginLeft="4px">
                        의
                    </Text>
                </S.TextWrapper>
                <S.TextWrapper>
                    <Text size="subtitle1" fontWeight="bold">
                        '{musicLabels[0]}'
                    </Text>
                    <Text size="body2" $marginLeft="4px">
                        이예요
                    </Text>
                </S.TextWrapper>
                <Bar
                    style={{ marginTop: "30px" }}
                    data={data}
                    options={options}
                />
                <S.LabelWrapper>
                    {musicLabels &&
                        musicLabels.map((music, index) => (
                            <S.Label
                                key={index}
                                style={{
                                    backgroundColor:
                                        likeColors[index % likeColors.length],
                                }}
                            >
                                {music}
                            </S.Label>
                        ))}
                </S.LabelWrapper>
                <S.BoxWrapper>
                    <S.LikeMusicBox
                        onClick={() => {
                            registLikeMusicHistory();
                            const subjectEncoded = encodeURIComponent(
                                LikeStatistics && LikeStatistics[0].subject
                            );
                            const singerEncoded = encodeURIComponent(
                                LikeStatistics && LikeStatistics[0].singer
                            );
                            const youtubeSearchUrl = `https://www.youtube.com/results?search_query=${subjectEncoded}+${singerEncoded}`;
                            window.location.href = youtubeSearchUrl;
                        }}
                    >
                        <MusicItem
                            src={LikeStatistics && LikeStatistics[0].albumImg}
                            songtitle={musicLabels[0]}
                            artist={artistLabels[0]}
                        ></MusicItem>
                        <CircleButton option="gradActivated" size="large">
                            <S.HeartContainer>
                                <Image
                                    src={Heart}
                                    width={25}
                                    $unit="px"
                                    style={{ position: "relative" }}
                                />
                                <S.AnimatedHeart
                                    src={Heart}
                                    alt="Heart"
                                    delay={0}
                                />
                                <S.AnimatedHeart
                                    src={Heart}
                                    alt="Heart"
                                    delay={0.2}
                                />{" "}
                                <S.AnimatedHeart
                                    src={Heart}
                                    alt="Heart"
                                    delay={0.4}
                                />{" "}
                            </S.HeartContainer>
                        </CircleButton>
                    </S.LikeMusicBox>
                </S.BoxWrapper>
                <S.TextWrapper style={{ margin: "60px 0 10px 0" }}>
                    <Text size="body2" style={{ lineHeight: "36px" }}>
                        많은 사람들이
                    </Text>
                    <S.Tag style={{ backgroundColor: tagColors[0] }}>
                        {tagNameLabels[0]}
                    </S.Tag>
                    <Text size="body2" style={{ lineHeight: "36px" }}>
                        때
                    </Text>
                </S.TextWrapper>
                <Text size="body2">음악과 함께 해요</Text>
                <Pie data={piedata} options={pieoptions} />
                <S.LabelWrapper style={{ margin: "30px 0 120px 0" }}>
                    {tagNameLabels &&
                        tagNameLabels.map((music, index) => (
                            <S.Label
                                key={index}
                                style={{
                                    backgroundColor:
                                        tagColors[index % tagColors.length],
                                }}
                            >
                                {music}
                            </S.Label>
                        ))}
                </S.LabelWrapper>
                <Text size="body2" $margin="0 0 8px 0">
                    가장 많은 사람들이 들은 노래는
                </Text>
                <S.TextWrapper>
                    <Text size="subtitle1" fontWeight="bold">
                        {ListenStatistics && ListenStatistics.singer}
                    </Text>
                    <Text size="body2" $marginLeft="4px">
                        의
                    </Text>
                </S.TextWrapper>
                <S.TextWrapper>
                    <Text size="subtitle1" fontWeight="bold">
                        '{ListenStatistics && ListenStatistics.subject}'
                    </Text>
                    <Text size="body2" $marginLeft="4px">
                        이예요
                    </Text>
                </S.TextWrapper>
                <S.BoxWrapper>
                    <S.LikeMusicBox
                        style={{ marginTop: "50px" }}
                        onClick={() => {
                            registListenedMusicHistory();
                            const subjectEncoded = encodeURIComponent(
                                ListenStatistics && ListenStatistics.subject
                            );
                            const singerEncoded = encodeURIComponent(
                                ListenStatistics && ListenStatistics.singer
                            );
                            const youtubeSearchUrl = `https://www.youtube.com/results?search_query=${subjectEncoded}+${singerEncoded}`;
                            window.location.href = youtubeSearchUrl;
                        }}
                    >
                        <MusicItem
                            src={ListenStatistics && ListenStatistics.albumImg}
                            songtitle={
                                ListenStatistics && ListenStatistics.subject
                            }
                            artist={ListenStatistics && ListenStatistics.singer}
                        ></MusicItem>
                        <CircleButton option="gradActivated" size="large">
                            <S.HeartContainer>
                                <Image
                                    src={Heart}
                                    width={25}
                                    $unit="px"
                                    style={{ position: "relative" }}
                                />
                                <S.AnimatedHeart
                                    src={Heart}
                                    alt="Heart"
                                    delay={0}
                                />
                                <S.AnimatedHeart
                                    src={Heart}
                                    alt="Heart"
                                    delay={0.2}
                                />{" "}
                                <S.AnimatedHeart
                                    src={Heart}
                                    alt="Heart"
                                    delay={0.4}
                                />{" "}
                            </S.HeartContainer>
                        </CircleButton>
                    </S.LikeMusicBox>
                </S.BoxWrapper>
            </div>
        </div>
    );
}
