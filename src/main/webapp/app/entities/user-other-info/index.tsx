import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import UserOtherInfo from './user-other-info';
import UserOtherInfoDetail from './user-other-info-detail';
import UserOtherInfoUpdate from './user-other-info-update';
import UserOtherInfoDeleteDialog from './user-other-info-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={UserOtherInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={UserOtherInfoUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={UserOtherInfoDetail} />
      <ErrorBoundaryRoute path={match.url} component={UserOtherInfo} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={UserOtherInfoDeleteDialog} />
  </>
);

export default Routes;
