import * as S from "../../../components/atoms/Image/Image.styles";

const Image = ({ width, height, $margin, $boxShadow, $unit = "rem", ...attributes }: S.ImageProps) => {
    return (
        <S.ImageConatiner
            width={width}
            height={height}
            $margin={$margin}
            $unit={$unit}
            $boxShadow={$boxShadow}
            // $justifyContent={$justifyContent}
            {...attributes}
        />
    );
};

export { Image };

// 활용 예시
// <Image src={backArrow} width={1} />
