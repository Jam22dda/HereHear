import * as S from "./TagSelect.styles";
import React, { useState } from "react";
import { Text } from "../../atoms/Text/Text.styles";
import CircleButton from "../../atoms/CircleButton/CircleButton";
import Button from "../../atoms/Button/Button";
import { useGetTag } from "../../../apis/Music/Quries/useGetTag";
import { Image } from "../../atoms/Image/Image";
import deleteBtn from "../../../assets/CircleButton/icon-delete.png";
import { useRecoilState } from "recoil";
import { TagInfo, selectedTagState } from "../../../states/SelectTagAtom";

interface TagSelectProps {
    setOpenModal: (open: boolean) => void;
}
interface TagData {
    occasionCode?: number;
    occasionName?: string;
    category?: string;
}

export default function TagSelect({ setOpenModal }: TagSelectProps) {
    const { data: tagsData, isLoading, isError, error } = useGetTag();
    const [selectedTags, setSelectedTags] = useState<string[]>([]);
    const [, setSelectedTagIds] = useRecoilState(selectedTagState);

    if (isLoading) {
        return <div>Loading...</div>;
    }

    if (isError) {
        return <div>Error: {error.message}</div>;
    }

    const occasionTags =
        tagsData.data?.filter((tag: TagData) => tag.category === "occasion") ||
        [];
    const environmentTags =
        tagsData.data?.filter(
            (tag: TagData) => tag.category === "environment"
        ) || [];
    const activityTags =
        tagsData.data?.filter((tag: TagData) => tag.category === "activity") ||
        [];

    const toggleTagSelection = (selectedTag: string) => {
        if (selectedTags.includes(selectedTag)) {
            // 이미 선택된 태그를 제거
            setSelectedTags(selectedTags.filter((tag) => tag !== selectedTag));
        } else {
            // 새로운 태그를 추가하기 전에 이미 3개가 선택되었는지 확인
            if (selectedTags.length < 3) {
                setSelectedTags([...selectedTags, selectedTag]);
            }
        }
    };

    const saveSelectedTags = () => {
        const selectedIds = selectedTags
            .map((tagName) => {
                const tag = tagsData.data.find(
                    (t: TagData) => t.occasionName === tagName
                );
                return tag ? { name: tagName, id: tag.occasionCode } : null;
            })
            .filter((tagInfo): tagInfo is TagInfo => tagInfo !== null); // null이 아닌 ID만 필터링
        // 선택된 태그의 ID들을 Recoil 상태에 저장
        setSelectedTagIds(selectedIds);
        setOpenModal(false);
    };

    return (
        <S.TagSelectWrapper>
            <S.TagTopWrapper>
                <Text size="body1" fontWeight="bold">
                    태그 등록
                </Text>

                <CircleButton size="medium" onClick={() => setOpenModal(false)}>
                    <Image src={deleteBtn} width={0.9}></Image>
                </CircleButton>
            </S.TagTopWrapper>
            <Text
                color="main2"
                size="small2"
                fontWeight="medium"
                $marginTop="20px"
            >
                태그 등록은 최대 3개까지 가능합니다.
            </Text>
            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                상황
            </Text>
            <S.TagBtnWrapper>
                {occasionTags
                    .slice(0, 11)
                    .map((tag: TagData, index: number) => {
                        const isSelected = selectedTags.includes(
                            tag.occasionName ?? ""
                        );
                        return (
                            <Button
                                size="small"
                                $width="58px"
                                option={
                                    isSelected
                                        ? "tag_selected"
                                        : "tag_unselected"
                                }
                                onClick={() =>
                                    tag.occasionName &&
                                    toggleTagSelection(tag.occasionName)
                                }
                                key={index}
                                tag={tag.occasionName ?? ""}
                            ></Button>
                        );
                    })}
            </S.TagBtnWrapper>

            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                환경
            </Text>
            <S.TagBtnWrapper>
                {environmentTags
                    .slice(0, 7)
                    .map((tag: TagData, index: number) => {
                        const isSelected = selectedTags.includes(
                            tag.occasionName ?? ""
                        );
                        return (
                            <Button
                                size="small"
                                $width="58px"
                                option={
                                    isSelected
                                        ? "tag_selected"
                                        : "tag_unselected"
                                }
                                onClick={() =>
                                    tag.occasionName &&
                                    toggleTagSelection(tag.occasionName)
                                }
                                key={index}
                                tag={tag.occasionName ?? ""}
                            ></Button>
                        );
                    })}
            </S.TagBtnWrapper>
            <Text size="small2" fontWeight="medium" $margin="25px 0 15px 0">
                활동
            </Text>
            <S.TagBtnWrapper>
                {activityTags.slice(0, 9).map((tag: TagData, index: number) => {
                    const isSelected = selectedTags.includes(
                        tag.occasionName ?? ""
                    );
                    return (
                        <Button
                            size="small"
                            $width="58px"
                            option={
                                isSelected ? "tag_selected" : "tag_unselected"
                            }
                            onClick={() =>
                                tag.occasionName &&
                                toggleTagSelection(tag.occasionName)
                            }
                            key={index}
                            tag={tag.occasionName ?? ""}
                        ></Button>
                    );
                })}
            </S.TagBtnWrapper>
            <Button
                option="save"
                $width="90px"
                size="medium"
                style={{ fontWeight: "normal", alignSelf: "center" }}
                onClick={saveSelectedTags}
            >
                선택
            </Button>
        </S.TagSelectWrapper>
    );
}
