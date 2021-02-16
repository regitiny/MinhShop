import './configProducts.scss';
import React, { Component } from 'react';
import Slider from 'react-slick';
// import "slick-carousel/slick/slick.css";
// import "slick-carousel/slick/slick-theme.css";

export const ProductsCarousel = () => {
  const settings = {
    dots: true,
    infinite: true,
    slidesToShow: 3,
    slidesToScroll: 1,
    autoplay: true,
    speed: 2000,
    autoplaySpeed: 2000,
    cssEase: 'linear',
  };
  return (
    <div className="container-fluid p0">
      <div className="container">
        <h2>Auto Play</h2>
        <Slider {...settings}>
          <div className="item">
            <div className="tile">
              <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
            </div>
          </div>
          <div className="item">
            <div className="tile">
              <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
          <div>
            <div className="item">
              <div className="tile">
                <img src="./../../../../content/images/do_go_san_pham_1.png" alt="image product" />
              </div>
            </div>
          </div>
        </Slider>
      </div>
    </div>
  );
};
