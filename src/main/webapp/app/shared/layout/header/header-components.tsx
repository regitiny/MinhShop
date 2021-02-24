import React from 'react';
import {Translate} from 'react-jhipster';

import {NavbarBrand, NavLink} from 'reactstrap';
import {NavLink as Link} from 'react-router-dom';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <span className="brand-title">
      <Translate contentKey="global.title">DucHoaShop</Translate>
    </span>
  </NavbarBrand>
);

export const Home = props => (
  // <NavItem>
  <NavLink tag={Link} to="/" className="d-flex align-items-center">
    <FontAwesomeIcon icon="home"/>
    <span>
      <Translate contentKey="global.menu.home">Home</Translate>
    </span>
  </NavLink>
  // </NavItem>
);
//test
export const Introduction = props => (
  // <NavItem>
  <NavLink tag={Link} to="/introduce" className="d-flex align-items-center">
    {/*<Translate contentKey="global.menu.introduce">Introduce</Translate>*/}
    Thông tin
  </NavLink>
  // </NavItem>
);
export const Contact = props => (
  // <NavItem>
  <NavLink tag={Link} to="/contact" className="d-flex align-items-center">
    {/*<Translate contentKey="global.menu.contact">Contact</Translate>*/}
    Liên hệ
  </NavLink>
  // </NavItem>
);
