import * as S from "./TagSelect.styles";
import React, { useState } from "react";
import { Text } from "../../atoms/Text/Text.styles";
import CircleButton from "../../atoms/CircleButton/CircleButton";
import Button from "../../atoms/Button/Button";
import { useGetTag } from "../../../apis/Music/Quries/useGetTag";
// import { useRecoilState } from "recoil";
// import { selectedTagState } from "../../../states/SelectTagAtom";

interface Tag {
    occasionCode: number;
    occasionName: string;
    category: string;
}

export default function TagSelect() {
    const getTag = useGetTag();
    console.log(getTag.tag.data);
    const occasionTags = getTag.tag.data.filter((tag: Tag) => tag.category === "occasion");
    const environmentTags = getTag.tag.data.filter((tag: Tag) => tag.category === "environment");
    const activityTags = getTag.tag.data.filter((tag: Tag) => tag.category === "activity");

    const [selectedTags, setSelectedTags] = useState<string[]>([]);

    // const [selectedTagIds, setSelectedTagIds] = useRecoilState<number[]>(selectedTagState);

    const toggleTagSelection = (selectedTag: string) => {
        if (selectedTags.includes(selectedTag)) {
            // 이미 선택된 태그를 제거합니다.
            setSelectedTags(selectedTags.filter((tag) => tag !== selectedTag));
        } else {
            // 새로운 태그를 추가하기 전에 이미 3개가 선택되었는지 확인합니다.
            if (selectedTags.length < 3) {
                setSelectedTags([...selectedTags, selectedTag]);
            }
        }
    };

    // const saveAndCloseModal = () => {
    //     const selectedIds = selectedTags
    //         .map((tagName) => {
    //             const tag = getTag.tag.data.find((t: Tag) => t.occasionName === tagName);
    //             return tag ? tag.occasionCode : null;
    //         })
    //         .filter((id) => id !== null); // null이 아닌 ID만 필터링합니다.

    //     setSelectedTagIds(selectedIds);

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
                {occasionTags.slice(0, 11).map((tag: Tag, index: number) => {
                    const isSelected = selectedTags.includes(tag.occasionName);
                    return (
                        <Button
                            size="small"
                            $width="58px"
                            option={isSelected ? "tag_selected" : "tag_unselected"}
                            onClick={() => toggleTagSelection(tag.occasionName)}
                            key={index}
                            tag={tag.occasionName}
                        ></Button>
                    );
                })}
            </S.TagBtnWrapper>

            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                환경
            </Text>
            <S.TagBtnWrapper>
                {environmentTags.slice(0, 7).map((tag: Tag, index: number) => {
                    const isSelected = selectedTags.includes(tag.occasionName);
                    return (
                        <Button
                            size="small"
                            $width="58px"
                            option={isSelected ? "tag_selected" : "tag_unselected"}
                            onClick={() => toggleTagSelection(tag.occasionName)}
                            key={index}
                            tag={tag.occasionName}
                        ></Button>
                    );
                })}
            </S.TagBtnWrapper>
            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                활동
            </Text>
            <S.TagBtnWrapper>
                {activityTags.slice(0, 9).map((tag: Tag, index: number) => {
                    const isSelected = selectedTags.includes(tag.occasionName);
                    return (
                        <Button
                            size="small"
                            $width="58px"
                            option={isSelected ? "tag_selected" : "tag_unselected"}
                            onClick={() => toggleTagSelection(tag.occasionName)}
                            key={index}
                            tag={tag.occasionName}
                        ></Button>
                    );
                })}
            </S.TagBtnWrapper>
            <Button option="save" $width="90px" size="medium" style={{ fontWeight: "normal", alignSelf: "center" }}>
                선택
            </Button>
        </S.TagSelectWrapper>
    );
}
