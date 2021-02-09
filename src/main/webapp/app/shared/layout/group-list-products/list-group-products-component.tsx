import './list-products.scss';
import React from 'react';
import { ListGroup, ListGroupItem } from 'reactstrap';

export const ListGroupProducts = props => {
  return (
    <div className="list-products col-3 d-none d-sm-none d-md-none d-lg-block d-xl-block">
      <ListGroup>
        <ListGroupItem tag="a" href="#" action>
          ĐỒ GỖ NỘI THẤT 1
        </ListGroupItem>
        <ListGroupItem tag="a" href="#" action>
          ĐỒ GỖ NỘI THẤT 2
        </ListGroupItem>
        <ListGroupItem tag="a" href="#" action>
          ĐỒ GỖ NỘI THẤT 3
        </ListGroupItem>
        <ListGroupItem tag="a" href="#" action>
          ĐỒ GỖ NỘI THẤT 4
        </ListGroupItem>
        <ListGroupItem tag="a" href="#" action>
          ĐỒ GỖ NỘI THẤT 5
        </ListGroupItem>
      </ListGroup>
    </div>
  );
};
