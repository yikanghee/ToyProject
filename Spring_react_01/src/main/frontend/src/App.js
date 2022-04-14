import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {

    const [hello, setHello] = useState('')

    useEffect(() => {
        axios.get('/api/hello')
            .then(response => setHello(response.data))
            .catch(error => console.log(error))
    }, []);
    console.log(hello)

    return (
        <div>
            백엔드에서 가져온 데이터입니다 : {hello}
        </div>

    );
}

export default App;