import { useRef, useState } from "react";

const DiaryItem = ({onEdit ,onRemove, author, content, created_date, emotion, id}) => {
    

    const [isEdit, setIsEdit] = useState(false);
    const toggleIsEdit = () => setIsEdit(!isEdit);

    const [localContent,setLocalContent] = useState(content);
    const localContentInput = useRef();

    const handleRemove = () => {
        if(window.confirm(`${id}번째 일기를 정말 삭제하시겠습니까?`)){
            onRemove(id);
        }
    }

    const handleQuitEdit= () => {
        setIsEdit(false);
        setLocalContent(content);
    }

    const handleEdit =() => {

        if(localContent.length < 5) {
            localContentInput.current.focus();
            return;
        }

        if(window.confirm(`${id}번 째 일기를 수정하시겠습니까?`)){
        onEdit(id,localContent);
        toggleIsEdit();
    }
    }
    
    return( 
    <div className="DiaryItem">
        <div className="info">
            <span className="author_info">
                작성자 : {author} | 감정점수 : {emotion}
            </span>
            <br />
            <span className="date">
                {new Date(created_date).toLocaleString()}</span>
        </div>
        <div className="content">{isEdit ? (
        <>
            <textarea 
            ref={localContentInput}
            value={localContent}
                onChange={(e) => setLocalContent(e.target.value)}
                />
        </>
        ) : ( 
            <>{content}</>
        )}</div>
        {isEdit ? <>
            <button onClick={handleQuitEdit}>수정 취소</button>
            <button onClick={handleEdit}>수정완료</button>
        </> : <>
        <button onClick={handleRemove}>삭제하기</button>
        <button onClick={toggleIsEdit}>수정하기</button>
        </>}
    </div>
    );
};

export default DiaryItem