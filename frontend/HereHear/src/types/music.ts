export type Musicinfo = {
    title: string;
    artist: string;
};

//TODO: 이거수정필요,, 하다 말았삼
export type SearchMusicResult = {
    searchMusic: {
        data: MusicData[];
        code: string;
        message: string;
    };
    error: Error | null;
};
export type MusicData = {
    src: string;
    subject: string;
    albumImages: { height: number; url: string; width: number }[];
    artists: { id: string; name: string }[];
    releaseDate: string;

    // 여기에 필요에 따라 다른 속성들을 추가할 수 있습니다.
};

export interface MusicItemState {
    src: string;
    songtitle: string;
    artist: string;
    releaseTime: string;
}
