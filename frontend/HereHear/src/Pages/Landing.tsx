import { Link, useNavigate } from 'react-router-dom';

export default function Landing() {
    const id = 'angel';

    const navigate = useNavigate(); // useNavigate 훅 사용

    function moveAppHandler() {
        navigate('/app'); // /app 로 이동
    }

    return (
        <div id='display'>
            <div className='container'>
                <h1>Landing</h1>

                <Link to='app'>App</Link>
                <br />
                <Link to={`/member/${id}`}>Member</Link>
                <br />
                <button onClick={moveAppHandler}>Go to App</button>
            </div>
        </div>
    );
}
