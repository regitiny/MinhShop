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
  const [cartItems, setCartItems] = useState([]);
  const cart: any = localStorage.getItem('cartItems');
  useEffect(() => {
    setCartItems(JSON.parse(cart));
  }, [cart]);
  const totalProduct = () => {
    let total = 0;
    if (cartItems && cartItems.length > 0) {
      cartItems.map(item => {
        total += item.count;
      });
    }
    return total;
  };
  return (
    <div className="warp-cart d-none d-sm-none d-md-none d-lg-none d-xl-block d-lg-flex d-xl-flex flex-wrap align-content-center col-lg-3 col-xl-4 ">
      <NavLink to="/checkout" tag={Link} className="cart">
        <span className="quantity">{totalProduct()}</span>
        <span>Giỏ hàng có {totalProduct()} sản phẩm</span>
      </NavLink>
    </div>
  );
};
