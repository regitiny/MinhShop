import './list-products.scss';
import React from 'react';
import { ListGroupProducts } from 'app/shared/layout/group-list-products/list-group-products-component';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import { Switch } from 'react-router-dom';
import HomeHead from 'app/modules/home/homehead';
import { ProductsIndex } from 'app/modules/do-go-noi-that/config-products';
import { ProductDetail } from 'app/modules/product/product-detail';

export const ListProducts = props => {
  return (
    <div className="list-group-products d-flex justify-content-center">
      <div className="list-products-container d-flex col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
        {/*<ListGroupProducts />*/}
        <ErrorBoundaryRoute path="/" exact component={ListGroupProducts} />
        <div className="contain-home-head col-12 s-flex col-sm-12 col-md-12 col-lg-9 col-xl-9">
          <Switch>
            <ErrorBoundaryRoute path="/" exact component={HomeHead} />
            <ErrorBoundaryRoute path="/product/product1" exact component={ProductsIndex} />
            <ErrorBoundaryRoute path="/product-detail" exact component={ProductDetail} />
          </Switch>
        </div>
      </div>
    </div>
  );
};
