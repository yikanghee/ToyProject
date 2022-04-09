import { useRef ,useState } from "react";


const DiaryEditor = () => {

    const [state, setState] = useState({
        author: '',
        content: '',
        emotion: 1,
    });

    const authorInput = useRef();
    const contentInput = useRef();

    const handleChangeState = (e) => {
        setState({
            ...state,
            [e.target.name]: e.target.vlaue,
        });
    };

    const handleSubmit = () => {
        if(state.author.length < 1){
            authorInput.current.focus();
            return;
        }

        if(state.content.length < 5){
            contentInput.current.focus();
            return;
        }

        alert("작성 성공!");
    }

    return <div className="DiaryEditor">
        <h2>오늘의 일기</h2>
        <div>
            <input 
            ref={authorInput}
            name="author"
            value={state.author} 
            onChange={handleChangeState}
            />
        </div>
        <div>
            <textarea 
                ref={contentInput}
                name="content"
                value={state.content}
                onChange={handleChangeState}
            />
        </div>
        <div>
            <select name="emotion"
                value={state.emotion}
                onChange={handleChangeState}
            >
                <option value={1}>1</option>
                <option value={2}>2</option>
                <option value={3}>3</option>
                <option value={4}>4</option>
                <option value={5}>5</option>
            </select>
        </div>
        <div>
            <button onClick={handleSubmit}>일기 저장</button> 
        </div>
    </div>;
    
}

export default DiaryEditor;