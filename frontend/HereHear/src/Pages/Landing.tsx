// import { Link, useNavigate } from "react-router-dom";
// import React from "react";
import Button from "../components/atoms/Button/Button";
import Input from "../components/atoms/Input/Input";
import MusicItem from "../components/molcules/MusicItem/MusicItem";
import ItemBox from "../components/molcules/ItemBox/ItemBox";
import CircleButton from "../components/atoms/CircleButton/CircleButton";
export default function Landing() {
    // const id = "angel";

    // const navigate = useNavigate(); // useNavigate 훅 사용

    // function moveAppHandler() {
    //     navigate("/app"); // /app 로 이동
    // }

    return (
        <div id="display">
            <div className="container">
                {/* <h1>제목 : 700</h1>
                <h2>부제목 : 500</h2>
                <h3>부제목 : 300</h3>

                <p className="p1 b">p1 b</p>
                <p className="p1 m">p1 m</p>
                <p className="p1 l">p1 l</p>
                <p className="p2">p2 m</p>
                <p className="s1">s1 m</p>
                <p className="s2">s2 m</p>
                <p className="s3">s3 m</p>
                <Link to="app">App</Link>
                <br />
                <Link to={`/member/${id}`}>Member</Link>
                <br />
                <button onClick={moveAppHandler}>Go to App</button> */}
                <Button $width="130px">저장하기</Button>
                <CircleButton></CircleButton>

                <Button option="tag_selected" size="small" $width="56px">
                    감성
                </Button>
                <Button option="tag_unselected" size="small" $width="56px">
                    출근
                </Button>
                <Button option="tag_plus" size="large" $width="108px">
                    태그추가+
                </Button>
                <Button option="follow" size="medium" $width="92px">
                    팔로우
                </Button>
                <Button option="unfollow" size="medium" $width="92px">
                    팔로잉
                </Button>
                <Button option="tag1" size="mediumplus" $width="80px">
                    산책
                </Button>
                <Input></Input>
                <MusicItem></MusicItem>
                <ItemBox></ItemBox>
            </div>
        </div>
    );
}
