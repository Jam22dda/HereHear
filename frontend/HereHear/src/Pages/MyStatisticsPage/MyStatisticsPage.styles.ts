import styled, { keyframes } from "styled-components";

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
    height: 120px;
    width: 360px;
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

export const chartSize = styled.div`
    width: 200px;
    display: flex;
    justify-content: center;
    align-items: center;
`;

export const HeartContainer = styled.div`
    position: relative;
    width: 25px;
    height: 25px;
`;

const floatHeart = keyframes`
  0% {
    transform: translate(-50%, -50%); /* 중심에서 시작 */
    opacity: 1;
  }
  100% {
    transform: translate(-50%, calc(-50px - 100%)); /* 위로 50px 만큼 + 이미지 높이만큼 추가 이동 */
    opacity: 0;
  }
`;
interface AnimatedHeartProps {
    delay: number;
}
export const AnimatedHeart = styled.img<AnimatedHeartProps>`
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    animation: ${floatHeart} 2s ease-in-out infinite; /* infinite를 추가하여 무한 반복 */
    animation-delay: ${(props) => props.delay}s; /* delay를 설정하여 각 하트가 다른 시간에 시작하도록 함 */
    width: 25px;
`;
