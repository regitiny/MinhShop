import React from 'react';
import { Breadcrumb } from 'reactstrap';
import { Link } from 'react-router-dom';

const base_path = '/';

const CrumbIconItem = ({ to, glyph, children, ...props }) => (
  <Link to={to}>
    <Breadcrumb.Item {...props}>
      <span> {children}</span>
    </Breadcrumb.Item>
  </Link>
);

export default CrumbIconItem;
