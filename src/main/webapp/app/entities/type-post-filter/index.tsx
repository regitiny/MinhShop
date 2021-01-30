import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypePostFilter from './type-post-filter';
import TypePostFilterDetail from './type-post-filter-detail';
import TypePostFilterUpdate from './type-post-filter-update';
import TypePostFilterDeleteDialog from './type-post-filter-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypePostFilterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypePostFilterUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypePostFilterDetail} />
      <ErrorBoundaryRoute path={match.url} component={TypePostFilter} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypePostFilterDeleteDialog} />
  </>
);

export default Routes;
