// import * as S from "./StatisticPage.styles";
// import { Text } from "../../components/atoms/Text/Text.styles";
// import { Image } from "../../components/atoms/Image/Image";
// import CircleButton from "../../components/atoms/CircleButton/CircleButton";
// import { useNavigate } from "react-router-dom";
// import monzi from "../../../public/images/monzi-herehear.png";
// import monziSleep from "../../../public/images/monzi-sleeping.png";
// import iconBack from "../../assets/CircleButton/icon-back.png";

import { Bar, Pie } from "react-chartjs-2";
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
} from "chart.js";

ChartJS.register(
    CategoryScale,
    LinearScale,
    BarElement,
    Title,
    Tooltip,
    Legend,
    ArcElement,
    PieController
);

const labels = ["January", "February", "March", "April", "May", "June", "July"];

const data = {
    labels: labels,
    datasets: [
        {
            label: "My First Dataset",
            data: [65, 59, 80, 81, 56, 55, 40],
            backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 205, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(54, 162, 235, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgba(201, 203, 207, 0.2)",
            ],
            borderColor: [
                "rgb(255, 99, 132)",
                "rgb(255, 159, 64)",
                "rgb(255, 205, 86)",
                "rgb(75, 192, 192)",
                "rgb(54, 162, 235)",
                "rgb(153, 102, 255)",
                "rgb(201, 203, 207)",
            ],
            borderWidth: 1,
        },
    ],
};

const options = {
    plugins: {
        legend: {
            display: true,
        },
        title: {
            display: true,
            text: "Chart Title",
        },
    },
    responsive: true,
    scales: {
        x: {
            // display: false, // X축 자체를 숨깁니다.
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
    // 여기에 필요한 기타 옵션 추가
};

const pieoptions = {
    responsive: true,
    plugins: {
        legend: {
            position: "top" as const, // 타입스크립트에게 이 값이 변하지 않는다고 알려주는 'as const' 사용
        },
        title: {
            display: true,
            text: "Pie Chart Example",
        },
    },
};

const piedata = {
    labels: ["Red", "Blue", "Yellow"],
    datasets: [
        {
            data: [300, 50, 100],
            backgroundColor: [
                "rgb(255, 99, 132)",
                "rgb(54, 162, 235)",
                "rgb(255, 205, 86)",
            ],
            hoverOffset: 4,
        },
    ],
};

export default function StatisticsPage() {
    // const navigate = useNavigate();

    // const navigatePage = (path: string) => {
    //     navigate(path);
    // };

    return (
        <div id="display">
            <div className="container">
                <Bar data={data} options={options} />
                <Pie data={piedata} options={pieoptions} />;
                {/* <CircleButton
                    option="default2"
                    size="medium"
                    onClick={() => {
                        navigatePage("/core");
                        window.location.reload();
                    }}
                >
                    <Image
                        src={iconBack}
                        width={10}
                        height={18}
                        $unit="px"
                    ></Image>
                </CircleButton>
                <S.readyPageWrapper>
                    <Text $textAlign="center" $margin="20px 0 ">
                        정확한 통계 수집을 위해
                        <br />
                        아직 준비중이에요!
                    </Text>

                    <Image width={10} src={monzi}></Image>
                </S.readyPageWrapper> */}
            </div>
        </div>
    );
}
