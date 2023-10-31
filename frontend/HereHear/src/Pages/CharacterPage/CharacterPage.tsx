import * as S from "./CharacterPage.styles";
import { Text } from "../../components/atoms/Text/Text.styles";
import ItemBox from "../../components/molcules/ItemBox/ItemBox";
import monziExercising from "../../../public/images/monzi-exercising.png";
import monziFighting from "../../../public/images/monzi-fighting.png";
import monziHerehear from "../../../public/images/monzi-herehear.png";
import monziHippop from "../../../public/images/monzi-hippop.png";
import monziSleeping from "../../../public/images/monzi-sleeping.png";
import monziWorking from "../../../public/images/monzi-working.png";
import Button from "../../components/atoms/Button/Button";
import React, { useState } from "react";

const monzi = [
    { src: monziExercising, name: "운동먼지" },
    { src: monziFighting, name: "열정먼지" },
    { src: monziHerehear, name: "히어먼지" },
    { src: monziHippop, name: "힙합먼지" },
    { src: monziSleeping, name: "꿀잠먼지" },
    { src: monziWorking, name: "열일먼지" },
];

export default function CharacterPage() {
    const [selectedItem, setSelectedItem] = useState<string | null>(null);
    const handleItemClick = (name: string) => {
        setSelectedItem(name);
    };

    return (
        <div id="display">
            <S.CharacterPageWrapper className="container">
                <Text size="subtitle1" fontWeight="bold" $marginTop="72px">
                    함께 음악감상 하고 싶은
                </Text>
                <Text size="subtitle1" fontWeight="bold">
                    캐릭터를 선택 해 주세요!
                </Text>
                <S.CharacterWrapper>
                    {monzi.map((item, index) => (
                        <ItemBox
                            key={index}
                            src={item.src}
                            title={item.name}
                            isselected={item.name === selectedItem}
                            onClick={() => handleItemClick(item.name)}
                        />
                    ))}
                </S.CharacterWrapper>
                <Button
                    option="save"
                    size="large"
                    $width="132px"
                    $margin="60px 0 0 0"
                >
                    저장하기
                </Button>
            </S.CharacterPageWrapper>
        </div>
    );
}
