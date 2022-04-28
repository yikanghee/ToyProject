import React from 'react';

// 컴포넌트
import Header from '../components/Header';
import Navigator from '../components/Navigator';
import Community from '../components/Community';
import Mobee from '../components/Mobee';
// import Slide from '../components/Slide';
// import Cards from '../components/Cards';
// import Footer from '../components/Footer';
// import SortSelect from '../components/SortSelect';

// shared
// import Paging from '../shared/Paging';





// 메인 페이지 컴포넌트
const MobeeP = (props) => {


  return (
    <div>
      <Header history={props.history} />
      <Navigator history={props.history}/>
      <Mobee />
      {/* <Slide />  */}
      {/* <SortSelect /> */}
      {/* <Cards history={props.history} /> */}
      {/* <Paging /> */}
      {/* <Footer /> */}
    </div>
  )
};

export default MobeeP;