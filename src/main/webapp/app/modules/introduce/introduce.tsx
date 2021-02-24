import React from 'react';
import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';

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
    </div>
  );
}

export default Introduce;
