import React from "react";

function Food() {
    return <h1>I like potato</h1>
}
function App() {
    return <div>Hello!!!!
    <Food fav="kimchi"
          something={true}
          papapapapa={["hello", 1,2,3,4, true]} />
    </div>
}

export default App;
