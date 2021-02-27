import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import ServerManagement from "app/modules/administration/server-management/server-management";

const Routes = ({match}) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}`} component={ServerManagement}/>
    </Switch>
  </>
);

export default Routes;
