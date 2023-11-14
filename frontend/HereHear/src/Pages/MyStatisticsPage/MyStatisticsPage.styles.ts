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

export const MystatisticWrapper = styled.div`
    display: flex;
    align-items: center;
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
    margin: 30px 0 0 0;
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
