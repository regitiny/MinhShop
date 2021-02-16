import './scroll.scss';
import React from 'react';
import { Button } from 'reactstrap';

export const ScrollTop = props => {
  // const mybutton = document.getElementById("scroll-btn");

  const scrollFunction = () => {
    if (document.body.scrollTop > 200 || document.documentElement.scrollTop > 200) {
      document.getElementById('scroll-btn').style.display = 'block';
    } else {
      document.getElementById('scroll-btn').style.display = 'none';
    }
  };
  window.onscroll = () => scrollFunction();

  const topFuction = () => {
    document.documentElement.scrollTop = 0;
    document.body.scrollTop = 0;
  };
  return (
    <div>
      <Button id="scroll-btn" onClick={topFuction} className="myBtn-block " color="danger" title="Go to top">
        Top
      </Button>
    </div>
  );
};
