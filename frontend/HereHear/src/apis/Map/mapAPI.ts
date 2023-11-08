// 1. 인스턴스에서 기본 요청 형식을 사용하기 위해 불러온다.
import { instance } from "../instance";

// 2. 사용할 함수 이름을 지정하고, 어떤 값을 가지고 전달할 건지 타입 지정 -> 어떤 형식
const getMusicList = async () => {
    try {
        const response = await instance.get("/music/list");
        return response.data.data; //get은 리턴으로 값을 줘야 함.
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

const getAroundMusicList = async (lat: number, lng: number) => {
    try {
        const response = await instance.get(
            `/music/around/list?lat=${lat}&lng=${lng}`
        );
        return response.data.data; //get은 리턴으로 값을 줘야 함.
    } catch (error) {
        console.error("Error fetching search music", error);
        throw error;
    }
};

export { getMusicList, getAroundMusicList };
