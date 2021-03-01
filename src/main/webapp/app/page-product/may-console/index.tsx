import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import MayConsole from 'app/page-product/may-console/may-console';
import { BreadcrumbsItem } from 'react-breadcrumbs-dynamic';
import MayConsoleDetail from "app/page-product/may-console/may-console-detail";

function Routes({ match }) {
  return (
    <div>
      <BreadcrumbsItem glyph="cog" to={match.url}>
        <b>Máy Console</b>
      </BreadcrumbsItem>
      <Switch>
        <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MayConsoleDetail} />
        <ErrorBoundaryRoute exact path={match.url} component={MayConsole} />
        {/*<ErrorBoundaryRoute  path={`${match.url}/:title`} component={LaptopDetail}/>*/}{' '}
        {/*todo path này có thể lấy giá trị id trong laptop-detail truyền từ laptop qua props.match.params.id*/}
      </Switch>
    </div>
  );
}

export default Routes;
