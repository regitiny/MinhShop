import './game-equipment.scss';
import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {getSortState, JhiItemCount, JhiPagination, Storage} from 'react-jhipster';
import {Card, CardBody, CardHeader, CardImg, CardText, CardTitle, Progress, Row} from 'reactstrap';
import {Link, RouteComponentProps} from 'react-router-dom';

import {connect} from 'react-redux';
import {overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';
import {ITEMS_PER_PAGE} from 'app/shared/util/pagination.constants';
import {IRootState} from 'app/shared/reducers';
import {reset} from 'app/entities/simple-post/simple-post.reducer';
import {OpenApiPathConst} from "app/page-product/OpenApiPathConst";

export interface ISimplePostProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }>
{
}

const GameEquipment = (props: ISimplePostProps) =>
{
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE, 'id'), props.location.search)
  );
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

  const [gameEquipments, setGameEquipments] = useState([]);
  const [totalGameEquipments, setTotalGameEquipments] = useState([]);
  const authToken = `Bearer ${Token}`;

  useEffect(() =>
  {
    axios({
      url: OpenApiPathConst.SIMPLE_POST_PATH,
      method: 'get',
      headers: {
        Authorization: authToken,
      },
      params: {size: 20, page: 0, typePost_id: '1451'},
    }).then(res => setGameEquipments(res.data));
  }, []);
  window.console.log(gameEquipments);
  useEffect(() =>
  {
    axios({
      method: 'get',
      url: OpenApiPathConst.SIMPLE_POST_PATH,
      headers: {Authorization: authToken},
      params: {typePost_id: '1451'},
    }).then(res => setTotalGameEquipments(res.data));
  }, []);
  const totalItems = totalGameEquipments ? totalGameEquipments.length : 1;
  const handlePagination = currentPage =>
  {
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
          {gameEquipments && gameEquipments.length > 0
            ? // ? laptops
              //     .filter(gameEquipment => gameEquipment.typePost.typeName === 'gameEquipment')
            gameEquipments.map(gameEquipment =>
            {
              // if (gameEquipment.typePost.typeName === 'gameEquipment') {}
              return (
                <div className="col-3 mt-3" key={gameEquipment.uuid + gameEquipment.id}>
                  {/*<Link to={`/${gameEquipment.id}`}>*/}
                  <Link to={`${props.match.url}/${gameEquipment.id}`}>
                    <Card className="p-1 p-sm-1 p-lg-0 ">
                      <CardHeader className="px-1 px-md-1 p-lg-2">
                        <div className='image-size'>
                          <CardImg top width="100%" src={gameEquipment.imageUrl} alt="Card image cap"/>
                        </div>
                        <div className="float-group">
                          <CardTitle tag="h4" className="float-left">
                            {gameEquipment.title}
                          </CardTitle>
                        </div>
                      </CardHeader>
                      <CardBody>
                        <CardText className="">
                          <p className="float-left">Giá gốc: </p>
                          <div className="float-left text-secondary ml-1">
                            <del>{gameEquipment.price}đ</del>
                          </div>
                          <br/>
                        </CardText>
                        <CardText className="">
                          <p className="float-left">Chỉ còn: </p>
                          <div className="float-left text-danger ml-1">
                            <b>{gameEquipment.salePrice.toLocaleString()}đ</b>
                          </div>
                          <div className="float-left badge badge-danger text-white ml-2">-{gameEquipment.percentSale}%</div>
                          <br/>
                        </CardText>
                        <div className="text-center" style={{width: '200px'}}>
                          <Progress animated value={gameEquipment.scores}>
                            {gameEquipment.scores}
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
      {gameEquipments.length ? (
        <div className={gameEquipments && gameEquipments.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems} itemsPerPage={paginationState.itemsPerPage} i18nEnabled/>
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
export default connect(null, mapDispatchToProps)(GameEquipment);
