import React from 'react';
import {Switch} from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import MacBook from 'app/page-product/mabook/macbook';
import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';
import MacbookDetail from "app/page-product/mabook/macbook-detail";

function Routes({match})
{
  return (
    <div>
      <BreadcrumbsItem glyph="cog" to={match.url}>
        <b>Macbook</b>
      </BreadcrumbsItem>
      <Switch>
        <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MacbookDetail}/>
        <ErrorBoundaryRoute exact path={match.url} component={MacBook}/>
        {/*<ErrorBoundaryRoute  path={`${match.url}/:title`} component={LaptopDetail}/>*/}{' '}
        {/*todo path này có thể lấy giá trị id trong laptop-detail truyền từ laptop qua props.match.params.id*/}
      </Switch>
    </div>
  );
}

export default Routes;
