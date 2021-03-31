import React from 'react';
import {BreadcrumbItem} from 'reactstrap';
import {Link} from 'react-router-dom';

const base_path = '/';

const CrumbIconItem = ({to, glyph, children, ...props}) => (
  <Link to={to}>
    <BreadcrumbItem {...props}>
      <span> {children}</span>
    </BreadcrumbItem>
  </Link>
);

export default CrumbIconItem;
