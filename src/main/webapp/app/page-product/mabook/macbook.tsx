import './macbook.scss'
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Storage, Translate, getSortState, JhiPagination, JhiItemCount } from 'react-jhipster';
import { Card, CardHeader, CardTitle, CardSubtitle, CardImg, CardBody, CardText, Progress, Button, Row } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link, RouteComponentProps } from 'react-router-dom';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { IRootState } from 'app/shared/reducers';
import { reset } from 'app/entities/simple-post/simple-post.reducer';
import { connect } from 'react-redux';
export interface ISimplePostProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}
const MacBook = (props: ISimplePostProps) => {
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const [macbooks, setmacbooks] = useState([]);
  const [totalMacbooks, setTotalMacbooks] = useState([]);
  const authToken = `Bearer ${Token}`;

  useEffect(() => {
    axios({
      url: 'api/_search/simple-posts',
      method: 'get',
      headers: {
        Authorization: authToken,
      },
      params: { page: paginationState.activePage - 1, size: paginationState.itemsPerPage, query: 'typePost.id:1052' },
    }).then(res => setmacbooks(res.data));
  }, []);
  window.console.log(macbooks);
  useEffect(() => {
    axios({
      method: 'get',
      url: 'api/_search/simple-posts',
      headers: { Authorization: authToken },
      params: { query: 'typePost.id:1052' },
    }).then(res => setTotalMacbooks(res.data));
  }, []);
  const totalItems = totalMacbooks ? totalMacbooks.length : 1;
  const handlePagination = currentPage => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });
  };
  return (
    <div>
      <div className="d-flex justify-content-center">
        <div className="d-flex row col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
          {macbooks && macbooks.length > 0
            ? macbooks.map(macbook => {
                return (
                  <div className="col-3 mt-3" key={macbook.id * 137}>
                    <Link to={`${props.match.url}/${macbook.id}`}>
                    {/*<Link to={`/${macbook.id}`}>*/}
                      <Card className="p-1 p-sm-1 p-lg-0 ">
                        <CardHeader className="px-1 px-md-1 p-lg-2">
                          <div className='image-size'>
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
              })
            : null}
        </div>
      </div>
      {macbooks.length ? (
        <div className={macbooks && macbooks.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled />
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};
const mapStateToProps = (storeState: IRootState) => ({});

const mapDispatchToProps = {
  reset,
};
type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;
export default connect(null, mapDispatchToProps)(MacBook);
