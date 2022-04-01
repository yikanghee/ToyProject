import React from 'react'; 
import {HashRouter, Route} from 'react-router-dom'; 
import Home from "./routes/Home"; 
import Search from "./routes/Search";
import Navigation from "./components/Navigation";


function App() {
  return (
    <HashRouter>
    <Navigation />
    <Route path="/" exact={true} component={Home}></Route>
    <Route path="/search" exact={true} component={Search}></Route>
  </HashRouter>  
    );
}

export default App;
