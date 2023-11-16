import { instance } from '../../apis/instance';
import { SignUpInfo } from '../../types/user';

interface AddUserResponse {
    code: number;
    message: string;
    data: string;
}

// 2. 사용할 함수 이름을 지정하고, 어떤 값을 가지고 전달할 건지 타입 지정 -> 어떤 형식
const addUser = async (data: SignUpInfo): Promise<AddUserResponse> => {
    // 어떤 형식으로 인스턴스를 사용할건지 작성 , 뒤에 올 url 값을 써준다.
    const response = await instance.post<AddUserResponse>('/member/signup', data); // json의 값 body도 객체로 묶여져있기 때문에 넘길 정보도 {}로 묶어준다.
    return response.data;
};

const getCheckNickname = async (nickname: string) => {
    try {
        const response = await instance.get(`/member/check/nickname/${nickname}`);
        // console.log("getCheckNickname 성공");
        return response.data.data;
    } catch (error) {
        console.error(' ', error);
        throw error;
    }
};

export { addUser, getCheckNickname };
