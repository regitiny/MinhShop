import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Laptop from 'app/page-product/laptop';
import MacBook from 'app/page-product/mabook/macbook';

function PageProduct({ match }) {
  // window.console.log(`${match.url}lap-top`)
  return (
    <div>
      <Switch>
        <ErrorBoundaryRoute path={`${match.url}/lap-top`} component={Laptop} />
        <ErrorBoundaryRoute path={`${match.url}/macbook`} component={MacBook} />
      </Switch>
    </div>
  );
}

export default PageProduct;
