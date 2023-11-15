import styled from "styled-components";

// export const Profile = styled.div`
//     position: relative;
//     width: 200px;
//     height: 200px;
//     border-radius: 120px;
//     box-shadow: ${({ theme }) => theme.shadow.shadow_smallbtn};
//     margin-top: 16px;
//     display: flex;
//     align-items: center;
//     justify-content: center;
// `;
export const TitleWrapper = styled.div`
    display: flex;
    align-items: end;
    margin: 20px 0 56px;
    /* flex-direction: column; */
`;
export const MystatisticWrapper = styled.div`
    display: flex;
    /* align-items: center; */
    flex-direction: column;
`;
export const readyPageWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 80vh;
    justify-content: center;
`;

export const Tag = styled.div`
    width: 100px;
    height: 36px;
    border-radius: 40px;
    font-size: 20px;
    font-weight: bold;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 8px 0 0;
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
`;

export const TextWrapper = styled.div`
    display: flex;
    align-items: center;
    /* flex-wrap: wrap; */
`;
export const TextWrapperbottom = styled.div`
    display: flex;
    align-items: end;
    /* flex-wrap: wrap; */
`;

export const LabelWrapper = styled.div`
    display: flex;
    justify-content: space-around;
    margin: 42px 0 0 0;
    align-items: center;
`;

export const Label = styled.div`
    padding: 6px 10px 6px 10px;
    border-radius: 20px;
    font-size: 12px;
    font-weight: bold;
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    width: 60px;
    height: 30px;
    white-space: nowrap; /* 텍스트를 줄 바꿈 없이 한 줄로 설정 */
    overflow: hidden; /* 내용이 넘칠 경우 숨김 처리 */
    text-overflow: ellipsis; /* 넘치는 텍스트를 말줄임표로 표시 */
    line-height: 20px;
    text-align: center;
`;

export const NoRecordWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 4rem;
`;

export const LikeBox = styled.div`
    height: 100px;
    width: 320px;
    display: flex;
    flex-direction: column;
    border-radius: 30px;
    background-color: ${({ theme }) => theme.color.Linear};
    box-shadow: ${({ theme }) => theme.shadow.shadow_btn};
    padding: 16px;
    align-items: center;
    justify-content: space-around;
`;

export const chartWrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
`;

// export const chartSize = styled.div`;
//     width: 200px;
//     display: flex;
//     justify-content: center;
//     align-items: center;
// `;

// const usingTimeValue = [0, 2, 5, 4, 3];
// const usingTimeKey = ["0~3시", "3~6시", "6~9시", "9~12시", "12~15시"];

// const lineData = {
//     labels: usingTimeKey, // x축 라벨
//     datasets: [{
//         label: 'Dataset 1',
//         data: usingTimeValue, // y축 데이터
//         fill: false,
//         borderColor: 'rgb(75, 192, 192)',
//         tension: 0.1
//     }]
// };

// const lineOptions = {
//     responsive: true,
//     // 추가 옵션 설정
// };

// // 컴포넌트에서 Line 차트 렌더링
// <Line data={lineData} options={lineOptions} />
