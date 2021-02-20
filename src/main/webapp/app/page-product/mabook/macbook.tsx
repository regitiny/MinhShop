import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Storage } from 'react-jhipster';
import { Card, CardBody, CardHeader, CardImg, CardText, CardTitle, Progress } from 'reactstrap';
import { Link } from 'react-router-dom';

const MacBook = ({ match }) => {
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const [macbooks, setmacbooks] = useState([]);
  const authToken = `Bearer ${Token}`;
  const typeNameFil = { typeNameFilter: 'macbook chơi Game' };

  useEffect(() => {
    axios({
      url: 'api/simple-posts',
      method: 'get',
      headers: {
        Authorization: authToken,
      },
      // data:typeNameFil
    }).then(res => setmacbooks(res.data));
  }, []);
  window.console.log(macbooks);
  return (
    <div className="d-flex justify-content-center">
      <div className="d-flex row col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
        {macbooks && macbooks.length > 0
          ? macbooks.map(macbook => {
              if (macbook.typePost.typeName === 'MacBook') {
                return (
                  <div className="col-4" key={macbook.id * 137}>
                    <Link to={`${match.url}/${macbook.id}`}>
                      <Card className="p-1 p-sm-1 p-lg-0 ">
                        <CardHeader className="px-1 px-md-1 p-lg-2">
                          <div>
                            <CardImg top width="100%" src={macbook.imageUrl} alt="Card image cap" />
                          </div>
                          <div className="float-group">
                            <CardTitle tag="h4" className="float-left">
                              {macbook.title}
                            </CardTitle>
                          </div>
                        </CardHeader>
                        <CardBody>
                          <CardText className="">
                            <p className="float-left">Giá gốc: </p>
                            <div className="float-left text-secondary ml-1">
                              <del>{macbook.price.toLocaleString()}đ</del>
                            </div>
                            <br />
                          </CardText>
                          <CardText className="">
                            <p className="float-left">Chỉ còn: </p>
                            <div className="float-left text-danger ml-1">
                              <b>{macbook.salePrice.toLocaleString()}đ</b>
                            </div>
                            <div className="float-left badge badge-danger text-white ml-2">-{macbook.percentSale}%</div>
                            <br />
                          </CardText>
                          <div className="text-center" style={{ width: '200px' }}>
                            <Progress animated value={macbook.scores}>
                              {macbook.scores}
                            </Progress>
                          </div>
                        </CardBody>
                      </Card>
                    </Link>
                  </div>
                );
              }
            })
          : null}
      </div>
    </div>
  );
};

export default MacBook;
