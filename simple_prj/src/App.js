import './App.css';
import DiaryEditor from './DiaryEditor';
import DiaryList from './DiaryList';
import React, { useState, useRef } from "react";
import Lifecycle from './Lifecycle';

const App= () => {

  const [data,setData] = useState([]);

  const dataId = useRef(0)

  const onCreate = (author, content, emotion) => {
    const created_date = new Date().getTime();
    const newItem = {
      author,
      content,
      emotion,
      created_date,
      id : dataId.current
    }
    dataId.current += 1;
    setData([newItem, ...data]);
  }

  const onRemove = (targetId) => {
    console.log(`${targetId}가 삭제되었습니다`);
    const newDiaryList = data.filter((it) => it.id !== targetId);
    setData(newDiaryList);
  };

  const onEdit = (targetId, newContent) => {
    setData( 
      data.map( (it) => 
        it.id === targetId ? {...it, content:newContent} : it
    )
    );
  }

  return (
    <div className="App">
      <Lifecycle />
      <DiaryEditor onCreate={onCreate}/>
      <DiaryList onEdit={onEdit} onRemove={onRemove} diaryList={data}/>
      </div>
  );
};

export default App;