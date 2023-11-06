import { atom } from 'recoil';

export const LatSearchAtom = atom<number>({
    key: 'LatSearchAtom',
    default: 0,
});

export const LngSearchAtom = atom<number>({
    key: 'LngSearchAtom',
    default: 0,
});
