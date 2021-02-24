import React from 'react';
import {Switch} from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TypePost from './type-post';
import TypePostDetail from './type-post-detail';
import TypePostUpdate from './type-post-update';
import TypePostDeleteDialog from './type-post-delete-dialog';

const Routes = ({match}) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TypePostUpdate}/>
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TypePostUpdate}/>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TypePostDetail}/>
      <ErrorBoundaryRoute path={match.url} component={TypePost}/>
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TypePostDeleteDialog}/>
  </>
);

export default Routes;
