import React from 'react';
import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';
import {Link} from 'react-router-dom'
import HistoryView from "app/page-product/history-view";

const base_path = '/';

function Introduce({children})
{
  return (
    <div>
      <BreadcrumbsItem glyph="calendar" to="/introduce">
        Introduce
      </BreadcrumbsItem>
      {/*<Breadcrumbs*/}
      {/*  separator={<b> / </b>}*/}
      {/*  item={NavLink}*/}
      {/*  finalItem={'b'}*/}
      {/*  finalProps={{*/}
      {/*    style: {color: 'red'}*/}
      {/*  }}*/}
      {/*/>*/}
      <div>Đây là thông tin của trang web</div>
      <HistoryView/>
    </div>
  );
}

export default Introduce;
