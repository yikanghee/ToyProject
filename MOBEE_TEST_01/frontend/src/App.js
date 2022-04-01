import logo from './logo.svg';
import './App.css';
import 'antd/dist/antd.css'
import {useEffect, useState} from "react";
import MovieSearchContainer from "./Container/MovieSearchContainer";

function App() {
    const [message, setMessage] = useState([]);
    useEffect(() => {
        fetch("/hello")
            .then((response) => {
                return response.json(); })
            .then(function (data) {
                setMessage(data);
            });
        }, []);

  return (
      <HashRouter>

  );
}

export default App;
