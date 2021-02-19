import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Tablet from 'app/page-product/may-tinh-bang/tablet';
import { BreadcrumbsItem } from 'react-breadcrumbs-dynamic';

function Routes({ match }) {
  return (
    <div>
      <BreadcrumbsItem glyph="cog" to={match.url}>
        <b>Tablet</b>
      </BreadcrumbsItem>
      <Switch>
        <ErrorBoundaryRoute exact path={match.url} component={Tablet} />
        {/*<ErrorBoundaryRoute path={`${match.url}/:id`} component={MayConsoleDetail} />*/}
        {/*<ErrorBoundaryRoute  path={`${match.url}/:title`} component={LaptopDetail}/>*/}{' '}
        {/*todo path này có thể lấy giá trị id trong laptop-detail truyền từ laptop qua props.match.params.id*/}
      </Switch>
    </div>
  );
}

export default Routes;
