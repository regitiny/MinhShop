import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Products1 from './../do-go-noi-that/do-go-noi-that-1/do-go-noi-that-1';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      <ErrorBoundaryRoute path={`${match.url}/product1`} component={Products1} />
    </Switch>
  </div>
);

export default Routes;
