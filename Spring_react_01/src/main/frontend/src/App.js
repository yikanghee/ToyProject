import React, {useEffect, useState} from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import UserComponent from "./components/UserComponent";

function App() {

    // const [hello, setHello] = useState('')

    // useEffect(() => {
    //     axios.get('/api/hello')
    //         .then(response => setHello(response.data))
    //         .catch(error => console.log(error))
    // }, []);
    // console.log(hello)

    return (
        // <div>
        //     백엔드에서 가져온 데이터입니다 : {hello}
        // </div>,
         <UserComponent />

    );
}

export default App;