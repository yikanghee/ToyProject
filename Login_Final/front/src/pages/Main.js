import React from 'react';
import Community from '../components/Community';

// 컴포넌트
import Header from '../components/Header';
import Navigator from '../components/Navigator';
// import Slide from '../components/Slide';
// import Cards from '../components/Cards';
// import Footer from '../components/Footer';
// import SortSelect from '../components/SortSelect';

// shared
// import Paging from '../shared/Paging';





// 메인 페이지 컴포넌트
const Main = (props) => {


  return (
    <div>
      <Header history={props.history} />
      <Navigator history={props.history}/>
    <div>
      <Community />
      {/* <Slide />  */}
      {/* <SortSelect /> */}
      {/* <Cards history={props.history} /> */}
      {/* <Paging /> */}
      {/* <Footer /> */}
    </div>
    </div>
  )
};

export default Main;