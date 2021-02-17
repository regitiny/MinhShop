import './visible.scss';
import React, { useState, useEffect } from 'react';
import { Dropdown, DropdownToggle, DropdownMenu, DropdownItem, Form, FormGroup, Input, Button, NavLink } from 'reactstrap';
import { NavLink as Link, RouteComponentProps } from 'react-router-dom';

{
  /*export const VisibleListSearch = (props) => {*/
}
{
  /*  const [dropdownOpen, setDropdownOpen] = useState(false);*/
}
{
  /*//test*/
}
{
  /*//test*/
}
{
  /*  const toggle = () => setDropdownOpen(prevState => !prevState);*/
}
{
  /*  return (*/
}
{
  /*    <Form className=" form-visible col">*/
}
{
  /*      <div className="col d-flex">*/
}
{
  /*        <FormGroup className="col-auto">*/
}
//           <div className="d-none d-sm-none d-md-block d-lg-block d-xl-block">
//             <Input type="select" name="select" id="visibleSelect" className="my-2 mr-1">
{
  /*              <option>Chọn danh mục</option>*/
}
{
  /*              <option>Đồ gỗ nội thất 1</option>*/
}
{
  /*              <option>Đồ gỗ nội thất 2</option>*/
}
{
  /*              <option>Đồ gỗ nội thất 3</option>*/
}
//               <option>Đồ gỗ nội thất 4</option>
//               <option>Đồ gỗ nội thất 5</option>
//             </Input>
{
  /*          </div>*/
}
{
  /*        </FormGroup>*/
}
//         <FormGroup className="col align-self-center">
//           <Input
{
  /*            type="index"*/
}
//             name="search"
//             id="txtSearch"
{
  /*            placeholder="Nhập từ khóa tìm kiếm..."*/
}
{
  /*            className="d-none d-sm-none d-md-block d-lg-block d-xl-block "*/
}
{
  /*          />*/
}
{
  /*        </FormGroup>*/
}
{
  /*        <Button type="submit" className="btn-search d-none d-sm-none d-md-block d-lg-block d-xl-block col-auto ">*/
}
{
  /*          Tìm kiếm*/
}
{
  /*        </Button>*/
}
{
  /*      </div>*/
}
{
  /*      <div className="result-search col">*/
}
{
  /*        {' '}*/
}
{
  /*        /!*todo cần sủa lại bằng reactstrap thay cho bootstrap*!/*/
}
{
  /*        <div className="row mt-2">*/
}
{
  /*          <div className="col">*/
}
{
  /*            <ResultSearch />*/
}
{
  /*          </div>*/
}
{
  /*          <div className="col">*/
}
{
  /*            <ResultSearch />*/
}
{
  /*          </div>*/
}
{
  /*        </div>*/
}
{
  /*        <div className="row mt-2">*/
}
{
  /*          <div className="col">*/
}
//             <ResultSearch />
//           </div>
{
  /*          <div className="col">*/
}
//             <ResultSearch />
//           </div>
//         </div>
//         <div className="row mt-2">
//           <div className="col">
//             <ResultSearch />
//           </div>
//           <div className="col">
//             <ResultSearch />
//           </div>
//         </div>
//         <div className="float-right m-2">
//           <NavLink tag={Link} to="/products">
//             Xem tất cả
//           </NavLink>
//         </div>
//       </div>
//     </Form>
//   );
// };

export const WarpCart = props => {
  return (
    <div className="warp-cart d-none d-sm-none d-md-none d-lg-none d-xl-block d-lg-flex d-xl-flex flex-wrap align-content-center col-3">
      <a href="#" title="Giỏ hàng" className="cart">
        <span className="quantity">0</span>
        <span>Giỏ hàng</span>
        <span>có 0 sản phẩm</span>
      </a>
    </div>
  );
};

export const ResultSearch = () => {
  return (
    <div className="media border p-3">
      <img src="./../../../../content/images/do_go_san_pham_1.png" alt="John Doe" className="mr-3 mt-3" width="80px" />
      <div className="media-body">
        <h6>THÙNG GỖ SỒI MỘC</h6>
        <p>2000000đ</p>
      </div>
    </div>
  );
};
