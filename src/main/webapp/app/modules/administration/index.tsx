import React from 'react';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import UserManagement from './user-management';
import Logs from './logs/logs';
import Health from './health/health';
import Metrics from './metrics/metrics';
import Configuration from './configuration/configuration';
import Docs from './docs/docs';
import Tracker from './tracker/tracker';
import ServerManagement from "app/modules/administration/server-management";

const Routes = ({match}) => (
  <div>
    <ErrorBoundaryRoute path={`${match.url}/user-management`} component={UserManagement}/>
    <ErrorBoundaryRoute exact path={`${match.url}/tracker`} component={Tracker}/>
    <ErrorBoundaryRoute exact path={`${match.url}/health`} component={Health}/>
    <ErrorBoundaryRoute exact path={`${match.url}/metrics`} component={Metrics}/>
    <ErrorBoundaryRoute exact path={`${match.url}/configuration`} component={Configuration}/>
    <ErrorBoundaryRoute exact path={`${match.url}/logs`} component={Logs}/>
    <ErrorBoundaryRoute exact path={`${match.url}/docs`} component={Docs}/>
    <ErrorBoundaryRoute exact path={`${match.url}/server-management`} component={ServerManagement}/>
  </div>
);

export default Routes;
