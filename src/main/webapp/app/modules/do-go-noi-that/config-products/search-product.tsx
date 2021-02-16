import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { Button, Form, FormGroup, Label, Input, FormText } from 'reactstrap';

export const SearchProduct = props => {
  return (
    <div>
      <Form className="form-select d-flex align-item-center">
        <FormGroup className="form-group-title mr-2 col">
          <Label for="exampleEmail">Tìm kiếm nâng cao</Label>
        </FormGroup>
        <FormGroup className="mr-2 col">
          <Input type="select" name="select" id="exampleSelect">
            <option>CHỌN SẢN PHẨM</option>
            <option>Sản phẩm 1</option>
            <option>Sản phẩm 2</option>
            <option>Sản phẩm 3</option>
            <option>Sản phẩm 4</option>
          </Input>
        </FormGroup>
        <FormGroup className="mr-2 col">
          <Input type="select" name="select" id="exampleSelect">
            <option>CHỌN MỨC GIÁ</option>
            <option>Dưới 500000đ</option>
            <option>500000-1000000đ</option>
            <option>1000000-20000000đ</option>
            <option>Trên 2000000đ</option>
          </Input>
        </FormGroup>
        <FormGroup className="mr-2 col">
          <Input type="select" name="select" id="exampleSelect">
            <option>CHỌN CHẤT LIỆU</option>
            <option>làm bằng a</option>
            <option>làm bằng b</option>
            <option>làm bằng c</option>
          </Input>
        </FormGroup>
        <Button className="col-2">Tìm kiếm</Button>
      </Form>
    </div>
  );
};
