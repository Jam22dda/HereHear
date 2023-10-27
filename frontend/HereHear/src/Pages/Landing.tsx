import { Link, useNavigate } from 'react-router-dom';
import Button from '../components/atoms/Button/Button';

export default function Landing() {
    const id = 'angel';

    const navigate = useNavigate(); // useNavigate 훅 사용

    function moveAppHandler() {
        navigate('/app'); // /app 로 이동
    }

    return (
        <div id='display'>
            <div className='container'>
                <h1>제목 : 700</h1>
                <h2>부제목 : 500</h2>
                <h3>부제목 : 300</h3>

                <p className='p1 b'>p1 b</p>
                <p className='p1 m'>p1 m</p>
                <p className='p1 l'>p1 l</p>
                <p className='p2'>p2 m</p>
                <p className='s1'>s1 m</p>
                <p className='s2'>s2 m</p>
                <p className='s3'>s3 m</p>
                <Link to='app'>App</Link>
                <br />
                <Link to={`/member/${id}`}>Member</Link>
                <br />
                <button onClick={moveAppHandler}>Go to App</button>
                <Button>버튼</Button>
            </div>
            <div className='container'>
                <h1>제목 : 700</h1>
                <h2>부제목 : 500</h2>
                <h3>부제목 : 300</h3>

                <p className='p1 b'>p1 b</p>
                <p className='p1 m'>p1 m</p>
                <p className='p1 l'>p1 l</p>
                <p className='p2'>p2 m</p>
                <p className='s1'>s1 m</p>
                <p className='s2'>s2 m</p>
                <p className='s3'>s3 m</p>
                <Link to='app'>App</Link>
                <br />
                <Link to={`/member/${id}`}>Member</Link>
                <br />
                <button onClick={moveAppHandler}>Go to App</button>
            </div>
        </div>
    );
}
