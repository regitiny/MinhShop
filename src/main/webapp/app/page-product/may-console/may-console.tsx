import './may-console-detail'
import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {getSortState, JhiItemCount, JhiPagination, Storage} from 'react-jhipster';
import {Card, CardBody, CardHeader, CardImg, CardText, CardTitle, Progress, Row} from 'reactstrap';
import {Link, RouteComponentProps} from 'react-router-dom';
import {overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';
import {ITEMS_PER_PAGE} from 'app/shared/util/pagination.constants';
import {IRootState} from 'app/shared/reducers';
import {reset} from 'app/entities/simple-post/simple-post.reducer';
import {connect} from 'react-redux';
import {OpenApiPathConst} from '../OpenApiPathConst';

export interface ISimplePostProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }>
{
}

const MayConsole = (props: ISimplePostProps) =>
{
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );

  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const [consoles, setConsoles] = useState([]);
  const [totalConsoles, setTotalConsoles] = useState([]);
  const authToken = `Bearer ${Token}`;

  useEffect(() => {
    axios({
      url: OpenApiPathConst.SIMPLE_POST_PATH,
      method: 'get',
      headers: {
        Authorization: authToken,
      },
      params: {page: paginationState.activePage - 1, size: paginationState.itemsPerPage, typePost_id: '1452'},
    }).then(res => setConsoles(res.data));
  }, []);
  window.console.log(consoles);
  useEffect(() => {
    axios({
      method: 'get',
      url: OpenApiPathConst.SIMPLE_POST_PATH,
      headers: {Authorization: authToken},
      params: {typePost_id: '1452'},
    }).then(res => setTotalConsoles(res.data));
  }, []);
  const totalItems = totalConsoles ? totalConsoles.length : 1;
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
          {consoles && consoles.length > 0
            ? consoles.map(console => {
                return (
                  <div className="col-3 mt-3" key={console.id * 137}>
                    <Link to={`${props.match.url}/${console.id}`}>
                    {/*<Link to={`/${console.id}`}>*/}
                      <Card className="p-1 p-sm-1 p-lg-0 ">
                        <CardHeader className="px-1 px-md-1 p-lg-2">
                          <div className='image-size'>
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
      {consoles.length ? (
        <div className={consoles && consoles.length > 0 ? '' : 'd-none'}>
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
export default connect(null, mapDispatchToProps)(MayConsole);
