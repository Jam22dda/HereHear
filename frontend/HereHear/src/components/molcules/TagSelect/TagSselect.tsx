import * as S from "./TagSelect.styles";
import { Text } from "../../atoms/Text/Text.styles";
import CircleButton from "../../atoms/CircleButton/CircleButton";
import Button from "../../atoms/Button/Button";
// import Button from "../../atoms/Button/Button";

const taglist1 = ["청량", "감성", "집중", "신나는", "우울", "이별", "힐링", "열정", "출근", "퇴근", "주말"];
const taglist2 = ["봄", "여름", "가을", "겨울", "눈", "비", "맑음"];
const taglist3 = ["운동", "산책", "수면", "독서", "공부", "운전", "샤워", "여행", "업무"];

export default function TagSelect() {
    return (
        <S.TagSelectWrapper>
            <S.TagTopWrapper>
                <Text size="body1" fontWeight="bold">
                    태그 등록
                </Text>

                <CircleButton size="medium"></CircleButton>
            </S.TagTopWrapper>
            <Text color="main2" size="small2" fontWeight="medium" $marginTop="20px">
                태그 등록은 최대 3개까지 가능합니다.
            </Text>
            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                상황
            </Text>
            <S.TagBtnWrapper>
                {taglist1.slice(0, 11).map((tag, index) => {
                    // return numberButton(btn, index);
                    return <Button size="small" $width="58px" option="tag_unselected" key={index} tag={tag}></Button>;
                })}
            </S.TagBtnWrapper>

            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                환경
            </Text>
            <S.TagBtnWrapper>
                {taglist2.slice(0, 7).map((tag, index) => {
                    // return numberButton(btn, index);
                    return <Button size="small" $width="58px" option="tag_unselected" key={index} tag={tag}></Button>;
                })}
            </S.TagBtnWrapper>
            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                활동
            </Text>
            <S.TagBtnWrapper>
                {taglist3.slice(0, 9).map((tag, index) => {
                    // return numberButton(btn, index);
                    return <Button size="small" $width="58px" option="tag_unselected" key={index} tag={tag}></Button>;
                })}
            </S.TagBtnWrapper>
            <Button option="save" $width="90px" size="medium" style={{ fontWeight: "normal", alignSelf: "center" }}>
                선택
            </Button>
        </S.TagSelectWrapper>
    );
}
