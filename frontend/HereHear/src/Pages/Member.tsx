import { useParams } from 'react-router-dom';

export default function Member() {
    const { id } = useParams();

    return (
        <div id='display'>
            <div className='container'>
                <h1>Member</h1>
                <p>{id}</p>
            </div>
        </div>
    );
}
