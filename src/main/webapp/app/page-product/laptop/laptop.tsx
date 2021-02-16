import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Storage, Translate } from 'react-jhipster';
import { Card, CardHeader, CardTitle, CardSubtitle, CardImg, CardBody, CardText, Progress, Button } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link, NavLink } from 'react-router-dom';
import _ from 'lodash';
import { BreadcrumbsItem } from 'react-breadcrumbs-dynamic';

const Laptop = ({ match }) => {
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

  const [laptops, setLaptops] = useState([]);
  const authToken = `Bearer ${Token}`;
  const typeNameFil = { typeNameFilter: 'Laptop chơi Game' };

  useEffect(() => {
    axios({
      url: 'api/simple-posts',
      method: 'get',
      headers: {
        Authorization: authToken,
      },
      // data:typeNameFil
    }).then(res => setLaptops(res.data));
  }, []);
  return (
    <div className="d-flex justify-content-center">
      <div className="d-flex row col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
        {laptops && laptops.length > 0
          ? laptops
              .filter(laptop => laptop.typePost.typeName === 'Laptop')
              .map(laptop => {
                // if (laptop.typePost.typeName === 'Laptop') {}
                return (
                  <div className="col-4" key={laptop.uuid + laptop.id}>
                    <Link to={`/${laptop.id}`}>
                      {/*<Link to={`${match.url}/${laptop.id}`}>*/}
                      <Card className="p-1 p-sm-1 p-lg-0 ">
                        <CardHeader className="px-1 px-md-1 p-lg-2">
                          <div>
                            <CardImg top width="100%" src={laptop.imageUrl} alt="Card image cap" />
                          </div>
                          <div className="float-group">
                            <CardTitle tag="h4" className="float-left">
                              {laptop.title}
                            </CardTitle>
                          </div>
                        </CardHeader>
                        <CardBody>
                          <CardText className="">
                            <p className="float-left">Giá gốc: </p>
                            <div className="float-left text-secondary ml-1">
                              <del>{laptop.price}đ</del>
                            </div>
                            <br />
                          </CardText>
                          <CardText className="">
                            <p className="float-left">Chỉ còn: </p>
                            <div className="float-left text-danger ml-1">
                              <b>{laptop.salePrice.toLocaleString()}đ</b>
                            </div>
                            <div className="float-left badge badge-danger text-white ml-2">-{laptop.percentSale}%</div>
                            <br />
                          </CardText>
                          <div className="text-center" style={{ width: '200px' }}>
                            <Progress animated value={laptop.scores}>
                              {laptop.scores}
                            </Progress>
                          </div>
                        </CardBody>
                      </Card>
                    </Link>
                  </div>
                );
              })
          : null}
      </div>
    </div>
  );
};

export default Laptop;
