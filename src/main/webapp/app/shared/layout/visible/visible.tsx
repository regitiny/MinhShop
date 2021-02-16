import './visible.scss';

import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faListAlt } from '@fortawesome/free-solid-svg-icons';
import { VisibleListSearch, WarpCart } from 'app/shared/layout/visible/visible-component';

const Visible = props => (
  <div className="visible d-flex justify-content-center">
    <div className="visible-container d-flex bg-main-color  col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
      <div className="visible-list  d-none d-sm-none d-md-none d-lg-none d-xl-block d-xl-flex flex-wrap align-content-center  col-lg-3 col-xl-3">
        <div className="visible-icon mr-2 d-none d-sm-none d-md-none d-lg-none d-xl-block">
          <FontAwesomeIcon icon={faListAlt} />
        </div>
        <div>DANH MỤC SẢN PHẨM</div>
      </div>
      <div className="search-target d-flex flex-wrap align-content-center ">
        <div className="input-group d-block d-sm-block d-md-none d-lg-none d-xl-none">
          <input type="text" className="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" />
        </div>
        <VisibleListSearch />
        <WarpCart />
      </div>
    </div>
  </div>
);

export default Visible;
