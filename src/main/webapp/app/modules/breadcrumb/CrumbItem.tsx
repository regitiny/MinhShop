import React from 'react';
import { Breadcrumb, NavLink } from 'reactstrap';

const base_path = '/';
const CrumbItem = ({ to, glyph, ...props }) => {
  window.console.log(glyph);
  return (
    <NavLink to={to}>
      <Breadcrumb.Item {...props}></Breadcrumb.Item>
    </NavLink>
  );
};

export default CrumbItem;
