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
    track_id: string;
    subject: string;
    duration: string;
    album_id: string;
    album_type: string;
    album_images: { height: number; url: string; width: number }[];
    artists: { id: string; name: string }[];
    name?: string;
    // 여기에 필요에 따라 다른 속성들을 추가할 수 있습니다.
};
