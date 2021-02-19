import React from 'react';
import { Switch } from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Laptop from 'app/page-product/laptop/laptop';
import LaptopDetail from 'app/page-product/laptop/laptop-detail';
import { BreadcrumbsItem } from 'react-breadcrumbs-dynamic';
import GameEquipment from 'app/page-product/thiet-bi-choi-game/game-equipment';

function Routes({ match }) {
  window.console.log(match);
  return (
    <div>
      <BreadcrumbsItem glyph="cog" to={match.url}>
        <b>Game equipment</b>
      </BreadcrumbsItem>
      <Switch>
        <ErrorBoundaryRoute exact path={match.url} component={GameEquipment} />
        {/*<ErrorBoundaryRoute path={`${match.url}/:id`} component={LaptopDetail} />*/}
        {/*<ErrorBoundaryRoute  path={`${match.url}/:title`} component={LaptopDetail}/>*/}{' '}
        {/*todo path này có thể lấy giá trị id trong laptop-detail truyền từ laptop qua props.match.params.id*/}
      </Switch>
    </div>
  );
}

export default Routes;
