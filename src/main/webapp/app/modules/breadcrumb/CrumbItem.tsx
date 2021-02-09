import React, { Component } from 'react';
import { NavLink, Breadcrumb } from 'reactstrap';

import { NavLink as Link } from 'react-router-dom';

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
