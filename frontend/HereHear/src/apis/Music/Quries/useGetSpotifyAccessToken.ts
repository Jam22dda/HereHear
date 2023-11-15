import { useQuery } from "@tanstack/react-query";
import { getSpotifyAccessToken } from "../musicAPI";

const useGetSpotifyAccessToken = () => {
    const { data: spotifyAccessToken } = useQuery({
        queryKey: ["spotifyAccessToken"],
        queryFn: () => getSpotifyAccessToken(),
    });
    return spotifyAccessToken;
};

export { useGetSpotifyAccessToken };
