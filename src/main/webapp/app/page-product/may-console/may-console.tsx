import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Storage, Translate } from 'react-jhipster';
import { Card, CardHeader, CardTitle, CardSubtitle, CardImg, CardBody, CardText, Progress, Button } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';

const MayConsole = ({ match }) => {
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const [consoles, setConsoles] = useState([]);
  const authToken = `Bearer ${Token}`;

  useEffect(() => {
    axios({
      url: 'api/_search/simple-posts',
      method: 'get',
      headers: {
        Authorization: authToken,
      },
      params: { size: 20, page: 0, query: 'typePost.id:1452' },
    }).then(res => setConsoles(res.data));
  }, []);
  window.console.log(consoles);

  return (
    <div className="d-flex justify-content-center">
      <div className="d-flex row col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
        {consoles && consoles.length > 0
          ? consoles.map(console => {
              return (
                <div className="col-4" key={console.id * 137}>
                  <Link to={`/${console.id}`}>
                    <Card className="p-1 p-sm-1 p-lg-0 ">
                      <CardHeader className="px-1 px-md-1 p-lg-2">
                        <div>
                          <CardImg top width="100%" src={console.imageUrl} alt="Card image cap" />
                        </div>
                        <div className="float-group">
                          <CardTitle tag="h4" className="float-left">
                            {console.title}
                          </CardTitle>
                        </div>
                      </CardHeader>
                      <CardBody>
                        <CardText className="">
                          <p className="float-left">Giá gốc: </p>
                          <div className="float-left text-secondary ml-1">
                            <del>{console.price.toLocaleString()}đ</del>
                          </div>
                          <br />
                        </CardText>
                        <CardText className="">
                          <p className="float-left">Chỉ còn: </p>
                          <div className="float-left text-danger ml-1">
                            <b>{console.salePrice.toLocaleString()}đ</b>
                          </div>
                          <div className="float-left badge badge-danger text-white ml-2">-{console.percentSale}%</div>
                          <br />
                        </CardText>
                        <div className="text-center" style={{ width: '200px' }}>
                          <Progress animated value={console.scores}>
                            {console.scores}
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

export default MayConsole;
