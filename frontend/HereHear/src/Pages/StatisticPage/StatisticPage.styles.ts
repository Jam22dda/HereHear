import styled, { keyframes } from "styled-components";

export const TextWrapper = styled.div`
    display: flex;
    align-items: center;
    flex-wrap: wrap; /* 이 부분을 추가합니다. */
`;

export const LabelWrapper = styled.div`
    display: flex;
    justify-content: space-around;
    margin: 16px 0 40px 0;
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

export const LikeMusicBox = styled.div`
    width: 336px;
    height: 92px;
    border-radius: 30px;
    background-image: ${({ theme }) => theme.gradient.gradient1};
    box-shadow: ${({ theme }) => theme.shadow.shadow_goback};
    display: flex;
    align-items: center;
    justify-content: center;
`;

// interface TagProps {
//     bgcolor: string;
// }

export const Tag = styled.div`
    width: 100px;
    height: 36px;
    border-radius: 40px;
    font-size: 20px;
    font-weight: bold;
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 8px 0 8px;
    box-shadow: ${({ theme }) => theme.shadow.shadow_play_selected};
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

export const AnimatedHeart = styled.img`
    position: absolute;
    top: 50%; /* 컨테이너의 중앙에 위치 */
    left: 50%; /* 컨테이너의 중앙에 위치 */
    transform: translate(-50%, -50%); /* 애니메이션 시작 전 이미지를 중앙에 정렬 */
    animation: ${floatHeart} 2s ease-in-out forwards;
    width: 25px;
`;

// 원본 이미지를 감쌀 컨테이너
export const HeartContainer = styled.div`
    position: relative;
    width: 25px;
    height: 25px;
`;
