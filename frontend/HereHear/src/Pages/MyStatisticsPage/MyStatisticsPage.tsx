import * as S from "./MyStatisticsPage.styles";
import React, { useState, useEffect } from "react";
import { Text } from "../../components/atoms/Text/Text.styles";
import { Image } from "../../components/atoms/Image/Image";
import Heart from "../../assets/CircleButton/icon-heart.png";
import musicNote from "../../assets/Statistic/icon-musicNote.png";
import CircleButton from "../../components/atoms/CircleButton/CircleButton";
import { useNavigate } from "react-router-dom";
import monzi from "../../../public/images/monzi-herehear.png";
// import monziSleep from "../../../public/images/monzi-sleeping.png";
import iconBack from "../../assets/CircleButton/icon-back.png";
import { useGetMyLikeCount } from "../../apis/Mystatistic/Quries/useGetMyLickCount";
import { useGetHearTime } from "../../apis/Mystatistic/Quries/useGetHearTime";
import { useGetMyTagCount } from "../../apis/Mystatistic/Quries/useGetMyTagCount";
import { useGetUserinfo } from "../../apis/Mypage/Quries/useGetUserInfo";
import { useGetYourinfo } from "../../apis/Mypage/Quries/useGetYourInfo";
import { Pie } from "react-chartjs-2";
import { Line } from "react-chartjs-2";
import ChartDataLabels from "chartjs-plugin-datalabels";
import { useRecoilValue } from "recoil";
import { YourIdAtom } from "../../states/MypageAtoms";

import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    LineElement, //라인차트를 위한 설정
    PointElement, //라인차트를 위한 설정
    BarElement,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    PieController,
    // Plugin,
} from "chart.js";

ChartJS.register(CategoryScale, LineElement, LinearScale, BarElement, PointElement, Title, Tooltip, Legend, ArcElement, PieController, ChartDataLabels);

export default function MyStatisticsPage() {
    const navigate = useNavigate();

    const tagColors = ["#FFC0EC", "#BDDDFD", "#FFF0CB", "#96ebbc", "#F9D6D5"];

    // console.log(HearTime);
    const UserInfo = useGetUserinfo();
    const yourId = useRecoilValue(YourIdAtom);
    const YourInfo = useGetYourinfo(Number(yourId));
    // console.log(YourInfo.nickname, "YourInfo?");

    //userId가 있으면넣어서 호출, 없으면 그냥 출력
    const MyTagCount = useGetMyTagCount(yourId);
    const MyLikeCount = useGetMyLikeCount(yourId);
    const HearTime = useGetHearTime(yourId);
    // console.log(HearTime, "너의 HearTime??");

    const [yourInfo, setYourInfo] = useState(null); // YourInfo 데이터 상태
    const [isLoadingYourInfo, setIsLoadingYourInfo] = useState(true); // 로딩 상태

    useEffect(() => {
        if (YourInfo && yourId && yourId !== 0) {
            setYourInfo(YourInfo);
            setIsLoadingYourInfo(false);
        } else {
            setIsLoadingYourInfo(true);
        }
    }, [YourInfo, yourId]);

    const [timeLabels, setTimeLabels] = useState<string[]>([]); //시간대별
    const [timeValues, setTimeValues] = useState<number[]>([]); //시간대별
    const mostTime = HearTime ? HearTime.mostTime : "";
    // console.log(mostTime, "mostTime 가장 많은 시간");
    const isAllZeros = timeValues.every((value) => value === 0);

    useEffect(() => {
        if (HearTime && HearTime.time) {
            const timeEntries = Object.entries(HearTime.time)
                .map(([key, value]) => ({ time: key, count: Number(value) }))
                .sort((a, b) => a.time.localeCompare(b.time)); // 시간대를 정렬

            const timeLabels = timeEntries.map((entry) => entry.time);
            const timeValues = timeEntries.map((entry) => entry.count);
            // console.log(timeLabels, "timeLabels 라벨");
            // console.log(timeValues, "timeValues 벨류");
            setTimeLabels(timeLabels); // 새로 추가된 상태
            setTimeValues(timeValues); // 새로 추가된 상태
        } else {
            // console.log("데이터가 없습니다");
        }
    }, [HearTime]);

    const lineData = {
        labels: timeLabels, // x축 라벨
        datasets: [
            {
                label: "usingTime",
                data: timeValues, // y축 데이터
                fill: false,
                borderColor: "RGB(143, 139, 213)",
                tension: 0.4,
            },
        ],
    };

    const lineOptions = {
        responsive: true,
        plugins: {
            datalabels: {
                color: "rgb(247, 11, 141)",
                font: {
                    color: "rgb(240, 31, 122)",
                    weight: "bold" as const,
                    size: 16, // 글자 크기
                    family: "Arial", // 글꼴
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

    // 태그 개수 API
    interface musicTag {
        occasionCount: number;
        occasionName: string;
    }
    const [validTagCounts, setValidTagCounts] = useState([]);
    const tagNameLabels: string[] = validTagCounts.map((record: musicTag) => record.occasionName);
    const tagCount: number[] = validTagCounts.map((record: musicTag) => record.occasionCount);
    const hasValidCounts = validTagCounts.length > 0;
    useEffect(() => {
        if (MyTagCount && MyTagCount.length > 0) {
            const filteredTags = MyTagCount.filter((tag: musicTag) => tag.occasionCount > 0).slice(0, 5);
            setValidTagCounts(filteredTags);
        }
    }, [MyTagCount]);

    const pieoptions = {
        responsive: true,
        plugins: {
            datalabels: {
                color: "#6F83B1",
                font: {
                    weight: "bold" as const,
                    size: 18, // 글자 크기
                    family: "Arial", // 글꼴
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
                backgroundColor: ["#FF65D0", "#85C2FF", "#FFDD84", "#75DFA4", "#FAA09F"],
                hoverOffset: 6,
                // borderWidth: 0,
            },
        ],
    };

    return (
        <div id="display">
            <div className="container">
                <CircleButton option="default2" size="medium" onClick={() => navigate(-1)}>
                    <Image src={iconBack} width={10} height={18} $unit="px"></Image>
                </CircleButton>

                <S.TitleWrapper>
                    <Text size="subtitle1" fontWeight="bold">
                        나의 음악 노트
                    </Text>
                    <Image src={musicNote} width={2.4} $margin="0 0 0 6px"></Image>
                </S.TitleWrapper>
                <S.chartWrapper>
                    <S.LikeBox>
                        <S.TextWrapper>
                            <Text size="body2" fontWeight="bold">
                                {yourId && yourId !== 0 ? (YourInfo ? YourInfo.nickname : "") : UserInfo ? UserInfo.nickname : ""}
                            </Text>
                            <Text size="body2" fontWeight="medium" $marginLeft="4px">
                                님이 올린 노래가
                            </Text>
                        </S.TextWrapper>
                        <S.TextWrapperbottom>
                            <Text size="body2" fontWeight="medium">
                                총
                            </Text>
                            <Text size="subtitle1" fontWeight="bold" $margin="0 4px">
                                {MyLikeCount}
                            </Text>
                            <Text size="body2" fontWeight="medium" $margin="0 3px">
                                개의 좋아요를 받았어요
                            </Text>
                            <S.HeartContainer>
                                <Image src={Heart} width={25} $unit="px" style={{ position: "relative" }} />
                                <S.AnimatedHeart src={Heart} alt="Heart" delay={0} />
                                <S.AnimatedHeart src={Heart} alt="Heart" delay={0.2} /> <S.AnimatedHeart src={Heart} alt="Heart" delay={0.4} />{" "}
                            </S.HeartContainer>
                        </S.TextWrapperbottom>
                    </S.LikeBox>
                </S.chartWrapper>

                <S.TextWrapper style={{ margin: "4rem 0 0 0" }}>
                    <Text size="body1" fontWeight="bold">
                        {yourId && yourId !== 0 ? (YourInfo ? YourInfo.nickname : "") : UserInfo ? UserInfo.nickname : ""}
                    </Text>
                    <Text size="body2" fontWeight="medium" $marginLeft="4px">
                        님은
                    </Text>
                </S.TextWrapper>
                <Text size="body2" fontWeight="medium" $margin="10px 0 0 0 ">
                    언제 음악을 들을까요?
                </Text>

                {!isAllZeros && hasValidCounts ? (
                    <>
                        <S.chartWrapper style={{ marginTop: "PX" }}>
                            <Line data={lineData} options={lineOptions} />
                        </S.chartWrapper>
                        <S.TextWrapperbottom style={{ margin: "2rem 0 10px 0" }}>
                            <Text size="subtitle1" fontWeight="bold" $margin="0 4px">
                                {mostTime}
                            </Text>
                            <Text size="body2">사이에</Text>
                        </S.TextWrapperbottom>
                        <Text size="body2" $margin="0 0 64px 0">
                            음악을 많이 듣고 계시네요.
                        </Text>
                        <S.LabelWrapper>
                            {tagNameLabels &&
                                tagNameLabels.map((tagname, index) => (
                                    <S.Label
                                        key={index}
                                        style={{
                                            backgroundColor: tagColors[index % tagColors.length],
                                        }}
                                    >
                                        {tagname}
                                    </S.Label>
                                ))}
                        </S.LabelWrapper>
                        <S.chartWrapper>
                            <Pie data={piedata} options={pieoptions} style={{ maxWidth: "270px", maxHeight: "270px" }} />
                        </S.chartWrapper>
                        <S.MystatisticWrapper>
                            <S.TextWrapperbottom style={{ margin: "3rem 0 10px 0" }}>
                                <S.Tag style={{ backgroundColor: tagColors[0] }}>{tagNameLabels[0]}</S.Tag>

                                <Text size="body2">태그가 달린 음악을</Text>
                            </S.TextWrapperbottom>
                            <Text size="body2" $margin="0 0 36px 5px">
                                가장 많이 들었어요.
                            </Text>
                        </S.MystatisticWrapper>
                    </>
                ) : (
                    // 데이터가 없을 때 표시할 메시지
                    <>
                        <S.NoRecordWrapper>
                            <Image src={monzi} width={6}></Image>
                            <Text size="body2" $margin="10px 0 10px 0">
                                아직 활동 기록이 없어
                            </Text>
                            <Text size="body2" $margin="0 0 10px 0">
                                정확한 분석이 어려워요.
                            </Text>
                        </S.NoRecordWrapper>
                    </>
                )}
            </div>
        </div>
    );
}
