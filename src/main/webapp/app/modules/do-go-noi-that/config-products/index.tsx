import React from 'react';
import { SearchProduct } from 'app/modules/do-go-noi-that/config-products/search-product';
import BreadCrumb from 'app/modules/do-go-noi-that/config-products/bread-crumb';
import { ProductsCarousel } from 'app/modules/do-go-noi-that/config-products/products-carousel';

export const ProductsIndex = props => {
  return (
    <div>
      <BreadCrumb />
      <SearchProduct />
      <ProductsCarousel />
    </div>
  );
};
