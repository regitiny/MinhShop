import './home.scss';
import React, { useState } from 'react';
import { NavLink } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { Carousel, CarouselItem, CarouselControl, CarouselIndicators, CarouselCaption } from 'reactstrap';
// export const Homehead=(props)=>{
//
//   return (
//       <div className="banner-img auto">
//         <NavLink to="/" tag={Link}>
//           <img className="img-fluid" src="content/images/banner.png" alt="banner"/>
//         </NavLink>
//       </div>
//
//   )
// }
const items = [
  {
    src: './../../../content/images/slide_bon_tam_go.png',
    // altText: 'Slide 1',
    // caption: 'Slide 1'
  },
  {
    src: './../../../content/images/slide_trong_doan_doi.png',
    // altText: 'Slide 2',
    // caption: 'Slide 2'
  },
  {
    src: './../../../content/images/slide_thung_go_soi.png',
    // altText: 'Slide 3',
    // caption: 'Slide 3'
  },
];

const HomeHead = props => {
  const [activeIndex, setActiveIndex] = useState(0);
  const [animating, setAnimating] = useState(false);

  const next = () => {
    if (animating) return;
    const nextIndex = activeIndex === items.length - 1 ? 0 : activeIndex + 1;
    setActiveIndex(nextIndex);
  };

  const previous = () => {
    if (animating) return;
    const nextIndex = activeIndex === 0 ? items.length - 1 : activeIndex - 1;
    setActiveIndex(nextIndex);
  };

  const goToIndex = newIndex => {
    if (animating) return;
    setActiveIndex(newIndex);
  };

  const slides = items.map(item => {
    return (
      <CarouselItem onExiting={() => setAnimating(true)} onExited={() => setAnimating(false)} key={item.src}>
        <div className="banner-img">
          <NavLink to="/" tag={Link}>
            <img className="image-cover img-fluid" src={item.src} />
          </NavLink>
        </div>
      </CarouselItem>
    );
  });

  return (
    <Carousel activeIndex={activeIndex} next={next} previous={previous} className="banner-img auto">
      <CarouselIndicators className="click-handle" items={items} activeIndex={activeIndex} onClickHandler={goToIndex} />
      {slides}
      <CarouselControl direction="prev" directionText="Previous" onClickHandler={previous} />
      <CarouselControl direction="next" directionText="Next" onClickHandler={next} />
    </Carousel>
  );
};

export default HomeHead;
