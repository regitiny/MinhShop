import './history-view.scss'
import React, {useState, useRef} from 'react';
import {Link, withRouter} from 'react-router-dom';
import {
  Card, CardImg, CardText, CardBody,
  CardTitle, CardSubtitle, Button
} from 'reactstrap';
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

function SampleNextArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", background: "black"}}
      onClick={onClick}
    />
  );
}

function SamplePrevArrow(props) {
  const { className, style, onClick } = props;
  return (
    <div
      className={className}
      style={{ ...style, display: "block", background: "black"}}
      onClick={onClick}
    />
  );
}

export const HistoryView = (props) =>
{
  const jsonViews: any = localStorage.getItem('product')
  const views = JSON.parse(jsonViews)
  const settings = {
    dots: true,
    infinite: false,
    speed: 500,
    slidesToShow: 5,
    slidesToScroll: 1,
    initialSlide: 0,
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
    responsive: [
      {
        breakpoint: 1024,
        settings: {
          slidesToShow: 3,
          slidesToScroll: 1,
          infinite: true,
          dots: true
        }
      },
      {
        breakpoint: 600,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 1,
          initialSlide: 2
        }
      },
      {
        breakpoint: 480,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1
        }
      }
    ]
  };


  return (
    <div className="view mt-3 mb-3">
      <div className="d-flex justify-content-center">
        <h3 className="col-9 text-body">Sản phẩm đã xem</h3>
      </div>
      <div className='d-flex justify-content-center mt-2'>
        <div className='col-9 d-flex'>
          <div className="container">
            <Slider {...settings}>
              {views && views.length > 0 ? views.map((item) => (
                  <Link key={item.id} to={item.url} className="p-2">
                    {/*<div style={{height: 250}}>*/}
                    {/*  <img src={item.image} width='100%'/>*/}
                    {/*</div>*/}
                    {/*<h5>{item.name}</h5>*/}
                    <Card>
                      <div className="image-size">
                          <CardImg top width="100%" src={item.image} alt="Card image cap" />
                      </div>
                      <div className="float-group mt-3">
                        <CardTitle tag="h5" className="text-dark">{item.name}</CardTitle>
                        {/*<CardSubtitle tag="h6" className="mb-2 text-muted">Card subtitle</CardSubtitle>*/}
                        {/*<CardText>Some quick example text to build on the card title and make up the bulk of the card's content.</CardText>*/}
                        {/*<Button>Button</Button>*/}
                      </div>
                    </Card>
                  </Link>
                )
              ) : ''}
            </Slider>
          </div>
          {/*{views && views.length>0?views.slice(views.length-5, views.length).map((item)=>(*/}
          {/*    <Link key={item.id} to={item.url} className='col-2'>*/}
          {/*      <img src={item.image} width='100%'/>*/}
          {/*      <h5>{item.name}</h5>*/}
          {/*    </Link>*/}
          {/*  )*/}
          {/*): ''}*/}
        </div>
      </div>

    </div>
  );
}

export default HistoryView;
