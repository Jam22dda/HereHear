import * as S from "./Image.styles";

const Image = ({
    width,
    height,
    $margin,
    $boxShadow,
    $unit = "rem",
    $borderRadius,
    ...attributes
}: S.ImageProps) => {
    return (
        <S.ImageConatiner
            width={width}
            height={height}
            $margin={$margin}
            $unit={$unit}
            $boxShadow={$boxShadow}
            $borderRadius={$borderRadius}
            {...attributes}
        />
    );
};

export { Image };
