const DiaryItem = ({author, content, created_date, emotion, id}) => {
   return (
        <div className="DiaryItem">

            <div className="info">
                <span>
                    작성자 : {author} | 감정점수 : {emotion}
                </span>
                <span className="date">
                    {new Date(created_date).toLocaleString()}
                </span>
                <div className="content">{content}</div>
            </div>
        </div>

   );
};

export default DiaryItem