import { useState ,useRef} from 'react';
import './App.css';
import DiaryEditor from './DiaryEditor';
import DiaryList from './DiaryList';


// const dummyList = [
//   {
//     id : 1,
//     author: "Butter",
//     content: "하이",
//     emotion: 5,
//     create_date: new Date().getTime()
//   },
//   {
//     id : 2,
//     author: "Tom",
//     content: "하이",
//     emotion: 5,
//     create_date: new Date().getTime()
//   },
//   {
//     id : 3,
//     author: "Jenny",
//     content: "하이",
//     emotion: 2,
//     create_date: new Date().getTime()
//   },
//   {
//     id : 4,
//     author: "Peter",
//     content: "하이",
//     emotion: 1,
//     create_date: new Date().getTime()
//   }

// ]

function App() {

const [data, setData] = useState([]);

console.log("data = {}", data)
console.log("setData = {}", setData)


const dataId = useRef(0);

const onCreate = (author, content ,emotion) => {
  const create_date = new Date().getTime();
  const newItem = {
    author,
    content,
    emotion,
    create_date,
    id: dataId.current,
  };
  dataId.current += 1;
  setData([newItem, ...data,]) 
}

  return (
    <div className="App">
      <DiaryEditor onCreate= {onCreate}/>
      <DiaryList diaryList={data} />
      </div>
  );
};

export default App;
