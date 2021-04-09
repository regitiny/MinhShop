import React from 'react';
import {Switch} from 'react-router-dom';
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Laptop from 'app/page-product/laptop';
import MacBook from 'app/page-product/mabook';
import MayConsole from 'app/page-product/may-console';
import Tablet from 'app/page-product/may-tinh-bang';
import GameEquipment from 'app/page-product/thiet-bi-choi-game';
// import { Breadcrumb, BreadcrumbItem } from 'reactstrap';
const BreadcrumbLayout = props =>
{
  const {children} = props;
  return (
    <div className="d-flex justify-content-center">
      <div className="breadcrumb col-9">{children}</div>
    </div>
  );
};

function PageProduct({match})
{
  // window.console.log(`${match.url}lap-top`)
  return (
    <div>
      {/*<Breadcrumbs*/}
      {/*  separator={<b>/</b>}*/}
      {/*  item={NavLink}*/}
      {/*  finalItem={'b'} //chọn thẻ tag cho route cuối cùng*/}
      {/*  finalProps={{*/}
      {/*    style: { color: 'gray' },*/}
      {/*  }}*/}
      {/*  container={BreadcrumbLayout}*/}
      {/*  // compare={(a,b)=>a.weight-b.weight} removeProps={{weight: true}}*/}
      {/*/>*/}
      {/*<BreadcrumbsItem glyph='cog' to={match.url}>*/}
      {/*  <b>Page</b>*/}
      {/*</BreadcrumbsItem>*/}
      <Switch>
        <ErrorBoundaryRoute path={`${match.url}/lap-top`} component={Laptop}/>
        <ErrorBoundaryRoute path={`${match.url}/macbook`} component={MacBook}/>
        <ErrorBoundaryRoute path={`${match.url}/may-console`} component={MayConsole}/>
        <ErrorBoundaryRoute path={`${match.url}/may-tinh-bang`} component={Tablet}/>
        <ErrorBoundaryRoute path={`${match.url}/thiet-bi-choi-game`} component={GameEquipment}/>
      </Switch>
    </div>
  );
}

export default PageProduct;
