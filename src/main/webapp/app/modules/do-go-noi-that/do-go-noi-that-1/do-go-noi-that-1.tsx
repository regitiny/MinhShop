import React, { useEffect } from 'react';
import { NavLink } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, Button, Badge } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faAngleDoubleRight } from '@fortawesome/free-solid-svg-icons';

const Products1 = props => {
  window.console.log('hello đây là trang chứa sản phẩm 1');
  useEffect(() => {
    document.title = 'TRỐNG GỖ CAO CẤP';
  }, []);
  window.console.log(location.pathname);
  window.console.log(document.title);
  return (
    <div className=" home-content d-flex justify-content-center">
      <div className="home-container col-12 col-sm-11 col-md-10 col-lg-10 col-xl-10">
        <section className="panel1-products  mt-5">
          {/*Danh mục sản phẩm*/}
          <header className="panel1-header d-flex align-items-center justify-content-between">
            <h2>ĐỒ GỖ NỘI THẤT 1</h2>
            <NavLink to="/do-go-noi-that1" className="d-flex align-items-center">
              Xem tất cả
              <FontAwesomeIcon icon={faAngleDoubleRight} />
            </NavLink>
          </header>
          <hr />
          <div className="section-content d-flex">
            <div className="section-banner d-none d-sm-none d-md-block d-lg-block d-xl-block col-md-3 col-lg-3 col-xl-3">
              <NavLink to="/do-go-noi-that1/trong-go-cao-cap" tag={Link}>
                <img className="img-fluid" src="content/images/do_go_noi_that_1.png" alt="banner" />
              </NavLink>
            </div>
            <div className="section-main col-12col-sm-12 col-md-9 col-lg-9 col-xl-9">
              <div className="row">
                <div className="section-product col-6 col-sm-6 col-md-6 col-lg-6 col-xl-4 pb-2">
                  <NavLink to="/product-detail" tag={Link}>
                    <Card>
                      <CardImg top width="100%" src="/content/images/do_go_san_pham_1.png" alt="Card image cap" />
                      <CardBody>
                        <CardTitle tag="h5">TRỐNG GỖ CAO CẤP</CardTitle>
                        <CardSubtitle tag="h6" className="mb-2 text-muted">
                          Mã sản phẩm: TGSN20
                        </CardSubtitle>
                        <CardText className="d-xl-flex justify-content-between">
                          <span className="price-new">2.300.000₫</span>
                          <span className="price-old">3.025.000 đ</span>
                          <span>
                            <Badge color="warning">-24%</Badge>
                          </span>
                        </CardText>
                        <Button color="primary" className="btn-block d-none d-sm-none d-md-none d-lg-block d-xl-block">
                          XEM THÊM CHI TIẾT
                        </Button>
                      </CardBody>
                    </Card>
                  </NavLink>
                </div>
                <div className="section-product col-6 col-sm-6 col-md-6 col-lg-6 col-xl-4 pb-2">
                  <NavLink to="/do-go-noi-that1/trong-go-cao-cap" tag={Link}>
                    <Card>
                      <CardImg top width="100%" src="/content/images/do_go_san_pham_1.png" alt="Card image cap" />
                      <CardBody>
                        <CardTitle tag="h5">TRỐNG GỖ CAO CẤP</CardTitle>
                        <CardSubtitle tag="h6" className="mb-2 text-muted">
                          Mã sản phẩm: TGSN20
                        </CardSubtitle>
                        <CardText className=" d-xl-flex justify-content-between">
                          <span className="price-new">2.300.000₫</span>
                          <span className="price-old">3.025.000 đ</span>
                          <span>
                            <Badge color="warning">-24%</Badge>
                          </span>
                        </CardText>
                        <Button color="primary" className="btn-block d-none d-sm-none d-md-none d-lg-block d-xl-block">
                          XEM THÊM CHI TIẾT
                        </Button>
                      </CardBody>
                    </Card>
                  </NavLink>
                </div>
                <div className="section-product col-6 col-sm-6 col-md-6 col-lg-6 col-xl-4 pb-2">
                  <NavLink to="/do-go-noi-that1/trong-go-cao-cap" tag={Link}>
                    <Card>
                      <CardImg top width="100%" src="/content/images/do_go_san_pham_1.png" alt="Card image cap" />
                      <CardBody>
                        <CardTitle tag="h5">TRỐNG GỖ CAO CẤP</CardTitle>
                        <CardSubtitle tag="h6" className="mb-2 text-muted">
                          Mã sản phẩm: TGSN20
                        </CardSubtitle>
                        <CardText className="d-xl-flex justify-content-between">
                          <span className="price-new">2.300.000₫</span>
                          <span className="price-old">3.025.000 đ</span>
                          <span>
                            <Badge color="warning">-24%</Badge>
                          </span>
                        </CardText>
                        <Button color="primary" className="btn-block d-none d-sm-none d-md-none d-lg-block d-xl-block">
                          XEM THÊM CHI TIẾT
                        </Button>
                      </CardBody>
                    </Card>
                  </NavLink>
                </div>
              </div>
              <div className="row">
                <div className="section-product col-6 col-sm-6 col-md-6 col-lg-6 col-xl-4 pb-2">
                  <NavLink to="/do-go-noi-that1/trong-go-cao-cap" tag={Link}>
                    <Card>
                      <CardImg top width="100%" src="/content/images/do_go_san_pham_1.png" alt="Card image cap" />
                      <CardBody>
                        <CardTitle tag="h5">TRỐNG GỖ CAO CẤP</CardTitle>
                        <CardSubtitle tag="h6" className="mb-2 text-muted">
                          Mã sản phẩm: TGSN20
                        </CardSubtitle>
                        <CardText className="d-xl-flex justify-content-between">
                          <span className="price-new">2.300.000₫</span>
                          <span className="price-old">3.025.000 đ</span>
                          <span>
                            <Badge color="warning">-24%</Badge>
                          </span>
                        </CardText>
                        <Button color="primary" className="btn-block d-none d-sm-none d-md-none d-lg-block d-xl-block">
                          XEM THÊM CHI TIẾT
                        </Button>
                      </CardBody>
                    </Card>
                  </NavLink>
                </div>
                <div className="section-product col-6 col-sm-6 col-md-6 col-lg-6 col-xl-4 pb-2">
                  <NavLink to="/do-go-noi-that1/trong-go-cao-cap" tag={Link}>
                    <Card>
                      <CardImg top width="100%" src="/content/images/do_go_san_pham_1.png" alt="Card image cap" />
                      <CardBody>
                        <CardTitle tag="h5">TRỐNG GỖ CAO CẤP</CardTitle>
                        <CardSubtitle tag="h6" className="mb-2 text-muted">
                          Mã sản phẩm: TGSN20
                        </CardSubtitle>
                        <CardText className=" d-xl-flex justify-content-between">
                          <span className="price-new">2.300.000₫</span>
                          <span className="price-old">3.025.000 đ</span>
                          <span>
                            <Badge color="warning">-24%</Badge>
                          </span>
                        </CardText>
                        <Button color="primary" className="btn-block d-none d-sm-none d-md-none d-lg-block d-xl-block">
                          XEM THÊM CHI TIẾT
                        </Button>
                      </CardBody>
                    </Card>
                  </NavLink>
                </div>
                <div className="section-product col-6 col-sm-6 col-md-6 col-lg-6 col-xl-4 pb-2">
                  <NavLink to="/do-go-noi-that1/trong-go-cao-cap" tag={Link}>
                    <Card>
                      <CardImg top width="100%" src="/content/images/do_go_san_pham_1.png" alt="Card image cap" />
                      <CardBody>
                        <CardTitle tag="h5">TRỐNG GỖ CAO CẤP</CardTitle>
                        <CardSubtitle tag="h6" className="mb-2 text-muted">
                          Mã sản phẩm: TGSN20
                        </CardSubtitle>
                        <CardText className="d-xl-flex justify-content-between">
                          <span className="price-new">2.300.000₫</span>
                          <span className="price-old">3.025.000 đ</span>
                          <span>
                            <Badge color="warning">-24%</Badge>
                          </span>
                        </CardText>
                        <Button color="primary" className="btn-block d-none d-sm-none d-md-none d-lg-block d-xl-block">
                          XEM THÊM CHI TIẾT
                        </Button>
                      </CardBody>
                    </Card>
                  </NavLink>
                </div>
              </div>
            </div>
          </div>
          <div className="d-flex d-none d-sm-none d-md-none d-lg-block d-xl-block d-lg-flex d-xl-flex justify-content-end mt-2">
            <NavLink to="/do-go-noi-that-1" tag={Link}>
              <Badge color="primary" className="p-3">
                XEM THÊM NHIỀU SẢN PHẨM
              </Badge>
            </NavLink>
          </div>
        </section>
      </div>
    </div>
  );
};
export default Products1;
