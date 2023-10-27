import { DefaultTheme } from "styled-components";

const color = {
    // text color
    main1: "#3B4F7D",
    main2: "#6F83B1",
    grey1: "#838383",
    grey2: "#AEAEAE",
    white1: "#F6F5F6",

    // main color
    lightpurple: "#E6E7FD",
    lightblue1: "#DCE4F8",
    lightblue2: "#D1DCFE",
    pink1: "#F8E3F294",
    pink2: "#FD8C8A",
    nav: "#FFFFFF33",
    white2: "#F4F8FE",
} as const;

const fontSize = {
    heading1: "36px",
    subtitle1: "24px",
    body1: "20px",
    body2: "18px",
    small1: "16px",
    small2: "14px",
    small3: "12px",
} as const;

// 나중에 값 조정 예정
const letterSpacing = {
    narrow: "-1.4px",
    spread: "0.4px",
} as const;

const shadow = {
    shadow_btn: "-2px -2px 6px 0px rgba(255, 255, 255, 0.40), 2px 2px 6px 0px #C4C4C4;",
    shadow_box1: "-10px -10px 16px 0px #EAEFFF, 10px 10px 20px 0px #C2CCEB;",
    shadow_box2: "-10px -10px 20px 0px #EBEBFF, 10px 10px 20px 0px #BFCAE4;",
    shadow_box3: "-10px -10px 20px 0px #E2E9FF inset, 10px 10px 20px 0px #BFCAE4 inset;",
    shadow_album: "10px 15px 20px 10px #D1D1D1, -10px -7px 15px 10px #FFF;",
    shadow_goback: "4px 4px 8px 0px #D1D2F2, -4px -4px 8px 0px #F6F9FF;",
    shadow_smallbtn: "2px 2px 4px 0px #BAC3DF inset, -2px -2px 4px 0px #FFF inset;",
    shadow_heart: "-1px -1px 2px 0px #EDF2FF inset, 2px 2px 4px 0px #C2CDEF inset;",
    shadow_play1: "-1px -1px 2px 0px #EDF2FF, 2px 2px 4px 0px #CACDEE;",
    shadow_play2: "4px 4px 8px 0px #C4CEF2, -4px -4px 8px 0px #E9EDFF;",
    shadow_nav: "2px 2px 4px 0px #F4F8FF inset;",
    shadow_itembox: "-3px -3px 4px 0px #FFF, 2px 2px 4px 0px #CACDEE;",
} as const;

const gradient = {
    gradient1: "linear-gradient(180deg, rgba(231, 238, 255, 0.70) 0%, rgba(224, 234, 255, 0.70) 99.99%, rgba(224, 234, 255, 0.00) 100%)",
    gradient2: "linear-gradient(91deg, #E1DAF7 -3.9%, #D2DDFD 80.45%)",
    gradient3: "linear-gradient(89deg, #DEDBF9 -1.22%, #D2DEFD 59.57%)",
    gradient4: "linear-gradient(144deg, #5163E0 4.87%, #8893F0 93.04%)",
    gradient5: "linear-gradient(141deg, #7E8BEE 14.8%, #5E6FE4 89.51%)",
    angular:
        "conic-gradient(from 135deg at 79.6% -13.32%, rgba(98, 132, 255, 0.30) 0deg, rgba(255, 150, 200, 0.30) 47.61769652366638deg, rgba(249, 229, 232, 0.30) 99.69967246055603deg, rgba(169, 212, 255, 0.30) 186.6353988647461deg, rgba(251, 183, 212, 0.30) 234.49278831481934deg)",
};

const theme: DefaultTheme = {
    color,
    fontSize,
    letterSpacing,
    shadow,
    gradient,
};

export default theme;
