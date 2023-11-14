import * as S from "./MyStatisticsPage.styles";
import React, { useState, useEffect } from "react";
import { Text } from "../../components/atoms/Text/Text.styles";
import { Image } from "../../components/atoms/Image/Image";
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
import { Pie } from "react-chartjs-2";
// import { Line } from "react-chartjs-2";
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

ChartJS.register(CategoryScale, LinearScale, BarElement, Title, Tooltip, Legend, ArcElement, PieController, ChartDataLabels);
export default function MyStatisticsPage() {
    const navigate = useNavigate();

    const tagColors = ["#FFC0EC", "#BDDDFD", "#FFF0CB", "#96ebbc", "#F9D6D5"];
    const MyTagCount = useGetMyTagCount();
    const MyLikeCount = useGetMyLikeCount();
    const HearTime = useGetHearTime();
    const UserInfo = useGetUserinfo();

    // console.log(HearTime, "HearTime나옴?");
    // console.log(MyTagCount, "마이태그 카운트");
    console.log(HearTime, "HearTime나오나요?");
    // console.log(HearTime.time["18~21시"], "HearTime나오나요?");

    // interface hearTime {
    //     mostTime: string;
    //     time: object;
    //     timeKey: any;
    // }
    // useEffect(() => {
    //     if (HearTime === null || HearTime === undefined) return;
    //     const HearTimeArr = Object.keys(HearTime?.time);

    //     if (HearTime && HearTimeArr.length) {
    //         const timeKeys = Object.keys(HearTime?.time);
    //         setUsingTime(timeKeys); // 실제 키들을 출력
    //     } else {
    //         console.log("안됐을때");
    //     }
    // }, [HearTime]);
    // const timeKey = Object.keys(HearTime?.time);
    // console.log(timeKey);
    // const [usingTime, setUsingTime] = useState([]);

    // console.log(usingTime);

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
                    <Image src={musicNote} width={2.4} $margin="0 0 0 3px"></Image>
                </S.TitleWrapper>
                <S.MystatisticWrapper>
                    <S.LikeBox>
                        <S.TextWrapper>
                            <Text size="body1" fontWeight="bold">
                                {UserInfo && UserInfo.nickname}
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
                            <Text size="body2" fontWeight="medium">
                                개의 좋아요를 받았어요.
                            </Text>
                        </S.TextWrapperbottom>
                    </S.LikeBox>
                </S.MystatisticWrapper>
                <S.TextWrapper style={{ margin: "3rem 0 0 0" }}>
                    <Text size="body1" fontWeight="bold">
                        {UserInfo && UserInfo.nickname}
                    </Text>
                    <Text size="body2" fontWeight="medium" $marginLeft="4px">
                        님은
                    </Text>
                </S.TextWrapper>
                <Text size="body2" fontWeight="medium" $margin="10px 0 0 0 ">
                    언제 음악을 들을까요?
                </Text>

                {hasValidCounts ? (
                    <>
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
                            <Text size="body2" $margin="0 0 10px 5px">
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

                {/* <S.readyPageWrapper>
                    <Text $textAlign="center" $margin="20px 0 ">
                        정확한 통계 수집을 위해
                        <br />
                        아직 준비중이에요!
                    </Text>
                    <Image width={10} src={monziSleep}></Image>
                </S.readyPageWrapper> */}
            </div>
        </div>
    );
}
