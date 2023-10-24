import { Link } from 'react-router-dom';

export default function Landing() {
    const id = 'angel';

    return (
        <div id='display'>
            <div className='container'>
                <h1>Landing</h1>

                <Link to='app'>App</Link>
                <br />
                <Link to={`/member/${id}`}>Member</Link>
            </div>
        </div>
    );
}
