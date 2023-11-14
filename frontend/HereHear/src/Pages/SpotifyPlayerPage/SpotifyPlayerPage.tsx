import axios from "axios";
import { useEffect, useState } from "react";

export default function SpotifyPlayer() {
  const track = {
    name: "",
    album: {
      images: [{ url: "" }],
    },
    artists: [{ name: "" }],
  };

  const token =
    "BQDvlRtU6JYX0-Ciw35uIiR0aiU-ooqPJgiuYY-Lp58Iui3J28kGvI_GbCAJyRVeRebUjQDsKpCuHPuh2_SUZwWllJBdbAcdBxe0as7OPVsmncpZhU0iOlXQPEQS0swtSKIejGxHs8uINoceoXCv1UTduuDWP10O2JAD8WvE3-IK0R-wsZgMAocwImHB2PGvPAFfE0uVJQQkjEUkLDpbJR1sm7lBbzNv";

  const [paused, setPaused] = useState(true);
  const [active, setActive] = useState(false);
  const [player, setPlayer] = useState(undefined);
  const [current_track, setTrack] = useState(track);
  const [deviceId, setDeviceId] = useState("");

  useEffect(() => {
    const script = document.createElement("script");
    script.src = "https://sdk.scdn.co/spotify-player.js";
    script.async = true;

    document.body.appendChild(script);

    window.onSpotifyWebPlaybackSDKReady = () => {
      const inPlayer = new window.Spotify.Player({
        name: "HereHear! Spotify Player",
        getOAuthToken: (cb) => {
          cb(token);
        },
        volume: 0.5,
      });

      setPlayer(inPlayer);

      inPlayer.addListener("ready", ({ device_id }) => {
        console.log("Ready with Device ID", device_id);
        setDeviceId(device_id);
        playMusic(device_id);
      });

      inPlayer.addListener("not_ready", ({ device_id }) => {
        console.log("Device ID has gone offline", device_id);
      });

      inPlayer.connect();
    };
  }, []);

  const playMusic = (device_id) => {
    axios({
      method: "put",
      url: "https://api.spotify.com/v1/me/player/play?device_id=" + device_id,
      data: {
        uris: ["spotify:track:4tEbrFRsptk67AYGcukhfV"],
      },
      headers: {
        Authorization: "Bearer " + token,
      },
    })
      .then(function (response) {
        console.log("play SUCCESS");
        console.log(response.data);
      })
      .catch(function (error) {
        console.log("play ERROR");
        console.log(error);
      });
  };

  return (
    <>
      <div className="container">
        <div className="main-wrapper">
          <img src={current_track.album.images[0].url} className="now-playing__cover" alt="" />

          <div className="now-playing__side">
            <div className="now-playing__name">{current_track.name}</div>
            <div className="now-playing__artist">{current_track.artists[0].name}</div>

            <button
              className="btn-spotify"
              onClick={() => {
                player.previousTrack();
              }}
            >
              &lt;&lt;
            </button>

            <button
              className="btn-spotify"
              onClick={() => {
                // player.togglePlay();
                playMusic(deviceId);
                player.togglePlay();
                setPaused(!paused);
              }}
            >
              {paused ? "PLAY" : "PAUSE"}
            </button>

            <button
              className="btn-spotify"
              onClick={() => {
                player.nextTrack();
              }}
            >
              &gt;&gt;
            </button>
          </div>
        </div>
      </div>
    </>
  );
}
