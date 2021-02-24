import './list-products.scss';
import React from 'react';
import {ListGroup} from 'reactstrap';
import {NavLink} from 'react-router-dom';

export const ListGroupProducts = props =>
{
  return (
    <div className="list-products col-3 d-none d-sm-none d-md-none d-lg-block d-xl-block">
      <ListGroup>
        {/*<ListGroupItem tag="a" href="#" action>*/}
        {/*  ĐỒ GỖ NỘI THẤT 1*/}
        {/*</ListGroupItem>*/}
        {/*<ListGroupItem tag="a" href="#" action>*/}
        {/*  ĐỒ GỖ NỘI THẤT 2*/}
        {/*</ListGroupItem>*/}
        {/*<ListGroupItem tag="a" href="#" action>*/}
        {/*  ĐỒ GỖ NỘI THẤT 3*/}
        {/*</ListGroupItem>*/}
        {/*<ListGroupItem tag="a" href="#" action>*/}
        {/*  ĐỒ GỖ NỘI THẤT 4*/}
        {/*</ListGroupItem>*/}
        {/*<ListGroupItem tag="a" href="#" action>*/}
        {/*  ĐỒ GỖ NỘI THẤT 5*/}
        {/*</ListGroupItem>*/}
        {/*todo thêm active cho list-group-product 10/2/2021*/}
        <NavLink to="/page/may-tinh-bang" className="list-group-item-action list-group-item" activeStyle={{backgroundColor: '#dd5600'}}>
          <span>MÁY TÍNH BẢNG</span>
        </NavLink>
        <NavLink to="/page/lap-top" className="list-group-item-action list-group-item" activeStyle={{backgroundColor: '#dd5600'}}>
          <span>LAPTOP</span>
        </NavLink>
        <NavLink to="/page/may-console" className="list-group-item-action list-group-item" activeStyle={{backgroundColor: '#dd5600'}}>
          <span>MÁY CONSOLE</span>
        </NavLink>
        <NavLink to="/page/macbook" className="list-group-item-action list-group-item" activeStyle={{backgroundColor: '#dd5600'}}>
          <span>MACBOOK</span>
        </NavLink>
        <NavLink
          to="/page/thiet-bi-choi-game"
          className="list-group-item-action list-group-item"
          activeStyle={{backgroundColor: '#dd5600'}}
        >
          <span>THIẾT BỊ CHƠI GAME</span>
        </NavLink>
      </ListGroup>
    </div>
  );
};
