import './banner.scss';

import React from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { faAward, faCartArrowDown, faHandHoldingUsd, faThumbsUp, faTruck } from '@fortawesome/free-solid-svg-icons';

export const Banner = props => {
  return (
    <div className=" banner d-flex justify-content-center">
      <div className="container-center col-9 d-flex">
        <div className="logo mr-auto">
          <Link to="/">
            <img className="img-fluid" src="content/images/logo-shop.png" alt="logo" />
          </Link>
        </div>
        <div className="warp-cart d-block d-sm-none d-md-none d-lg-none d-xl-none d-flex d-sm-flex d-md-flex  flex-wrap align-content-center">
          <a href="#" title="Giỏ hàng" className="cart">
            <span className="quantity">0</span>
          </a>
        </div>
        <div className="commitment d-none d-sm-none d-md-none d-lg-none d-xl-block d-xl-flex">
          <div className="commitment-item d-flex flex-wrap align-content-center justify-content-between mr-3">
            <div className="icon mr-2">
              <FontAwesomeIcon icon={faCartArrowDown} size="2x" color="red" />
            </div>
            <div className="commit-text">
              <div className="commit-title">
                <strong>THANH TOÁN</strong>
              </div>
              <div className="commit-subtitle">&nbsp;Thuận tiện dễ dàng</div>
            </div>
          </div>
          <div className="commitment-item d-flex flex-wrap align-content-center justify-content-between mr-3">
            <div className="icon mr-2">
              <FontAwesomeIcon icon={faTruck} size="2x" color="red" />
            </div>
            <div className="commit-text">
              <div className="commit-title">
                <strong>VẬN CHUYỂN</strong>
              </div>
              <div className="commit-subtitle">&nbsp;Giao hàng toàn quốc</div>
            </div>
          </div>
          <div className="commitment-item d-flex flex-wrap align-content-center justify-content-between mr-3">
            <div className="icon mr-2">
              <FontAwesomeIcon icon={faAward} size="2x" color="red" />
            </div>
            <div className="commit-text">
              <div className="commit-title">
                <strong>CHẤT LƯỢNG HÀNG ĐẦU</strong>
              </div>
              <div className="commit-subtitle">&nbsp;Sản phẩm đa dạng</div>
            </div>
          </div>
          <div className="commitment-item d-flex flex-wrap align-content-center justify-content-between mr-3">
            <div className="icon mr-2">
              <FontAwesomeIcon icon={faThumbsUp} size="2x" color="red" />
            </div>
            <div className="commit-text">
              <div className="commit-title">
                <strong>DỊCH VỤ TỐT NHẤT</strong>
              </div>
              <div className="commit-subtitle">&nbsp;Uy tín hàng đầu</div>
            </div>
          </div>
          <div className="commitment-item d-flex flex-wrap align-content-center justify-content-between">
            <div className="icon mr-2">
              <FontAwesomeIcon icon={faHandHoldingUsd} size="2x" color="red" />
            </div>
            <div className="commit-text">
              <div className="commit-title">
                <strong>GIÁ CẢ CẠNH TRANH</strong>
              </div>
              <div className="commit-subtitle">&nbsp;Luôn ở mức giá tốt nhất</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Banner;
