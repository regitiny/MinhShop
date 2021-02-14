import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Laptop from 'app/page-product/laptop/laptop';
import LaptopDetail from 'app/page-product/laptop/laptop-detail';
import { BreadcrumbsItem } from 'react-breadcrumbs-dynamic';

function Routes({ match }) {
  return (
    <div>
      <BreadcrumbsItem glyph="cog" to={match.url}>
        <b>Laptop</b>
      </BreadcrumbsItem>
      {/*<BreadcrumbsItem glyph='cog' to={`${match.url}/:id`}>*/}
      {/*  <b>{match.id}</b>*/}
      {/*</BreadcrumbsItem>*/}
      <Switch>
        <ErrorBoundaryRoute exact path={match.url} component={Laptop} />
        <ErrorBoundaryRoute path={`${match.url}/:id`} component={LaptopDetail} />
        {/*<ErrorBoundaryRoute  path={`${match.url}/:title`} component={LaptopDetail}/>*/}{' '}
        {/*todo path này có thể lấy giá trị id trong laptop-detail truyền từ laptop qua props.match.params.id*/}
      </Switch>
    </div>
  );
}

export default Routes;
