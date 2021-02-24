import React, {useEffect, useState} from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, InputGroup, Row, Table} from 'reactstrap';
import {AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {getSortState, TextFormat, Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntities, getSearchEntities, reset} from './user-other-info.reducer';
import {APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT} from 'app/config/constants';
import {ITEMS_PER_PAGE} from 'app/shared/util/pagination.constants';
import {overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';

export interface IUserOtherInfoProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }>
{
}

export const UserOtherInfo = (props: IUserOtherInfoProps) =>
{
  const [search, setSearch] = useState('');
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );
  const [sorting, setSorting] = useState(false);

  const getAllEntities = () =>
  {
    if (search)
    {
      props.getSearchEntities(
        search,
        paginationState.activePage - 1,
        paginationState.itemsPerPage,
        `${paginationState.sort},${paginationState.order}`
      );
    }
    else
    {
      props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
    }
  };

  const resetAll = () =>
  {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };

  useEffect(() =>
  {
    resetAll();
  }, []);

  const startSearching = () =>
  {
    if (search)
    {
      props.reset();
      setPaginationState({
        ...paginationState,
        activePage: 1,
      });
      props.getSearchEntities(
        search,
        paginationState.activePage - 1,
        paginationState.itemsPerPage,
        `${paginationState.sort},${paginationState.order}`
      );
    }
  };

  const clear = () =>
  {
    props.reset();
    setSearch('');
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };

  const handleSearch = event => setSearch(event.target.value);

  useEffect(() =>
  {
    if (props.updateSuccess)
    {
      resetAll();
    }
  }, [props.updateSuccess]);

  useEffect(() =>
  {
    getAllEntities();
  }, [paginationState.activePage]);

  const handleLoadMore = () =>
  {
    if ((window as any).pageYOffset > 0)
    {
      setPaginationState({
        ...paginationState,
        activePage: paginationState.activePage + 1,
      });
    }
  };

  useEffect(() =>
  {
    if (sorting)
    {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting, search]);

  const sort = p => () =>
  {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
      order: paginationState.order === 'asc' ? 'desc' : 'asc',
      sort: p,
    });
    setSorting(true);
  };

  const handleSyncList = () =>
  {
    resetAll();
  };

  const {userOtherInfoList, match, loading} = props;
  return (
    <div>
      <h2 id="user-other-info-heading" data-cy="UserOtherInfoHeading">
        <Translate contentKey="minhShopApp.userOtherInfo.home.title">User Other Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="mr-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading}/>{' '}
            <Translate contentKey="minhShopApp.userOtherInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to={`${match.url}/new`} className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus"/>
            &nbsp;
            <Translate contentKey="minhShopApp.userOtherInfo.home.createLabel">Create new User Other Info</Translate>
          </Link>
        </div>
      </h2>
      <Row>
        <Col sm="12">
          <AvForm onSubmit={startSearching}>
            <AvGroup>
              <InputGroup>
                <AvInput
                  type="text"
                  name="search"
                  value={search}
                  onChange={handleSearch}
                  placeholder={translate('minhShopApp.userOtherInfo.home.search')}
                />
                <Button className="input-group-addon">
                  <FontAwesomeIcon icon="search"/>
                </Button>
                <Button type="reset" className="input-group-addon" onClick={clear}>
                  <FontAwesomeIcon icon="trash"/>
                </Button>
              </InputGroup>
            </AvGroup>
          </AvForm>
        </Col>
      </Row>
      <div className="table-responsive">
        <InfiniteScroll
          pageStart={paginationState.activePage}
          loadMore={handleLoadMore}
          hasMore={paginationState.activePage - 1 < props.links.next}
          loader={<div className="loader">Loading ...</div>}
          threshold={0}
          initialLoad={false}
        >
          {userOtherInfoList && userOtherInfoList.length > 0 ? (
            <Table responsive>
              <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="global.field.id">ID</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('uuid')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.uuid">Uuid</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('phoneNumber')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.phoneNumber">Phone Number</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('email')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.email">Email</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('wardCode')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.wardCode">Ward Code</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('distCode')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.distCode">Dist Code</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('cityCode')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.cityCode">City Code</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('addressDetails')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.addressDetails">Address Details</Translate>{' '}
                  <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('dateOfBirth')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.dateOfBirth">Date Of Birth</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('otherInfo')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.otherInfo">Other Info</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('searchField')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.searchField">Search Field</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('role')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.role">Role</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('createdDate')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.createdDate">Created Date</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('modifiedDate')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.modifiedDate">Modified Date</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.createdBy">Created By</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('modifiedBy')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.modifiedBy">Modified By</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('dataSize')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.dataSize">Data Size</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th className="hand" onClick={sort('comment')}>
                  <Translate contentKey="minhShopApp.userOtherInfo.comment">Comment</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th>
                  <Translate contentKey="minhShopApp.userOtherInfo.userName">User Name</Translate> <FontAwesomeIcon icon="sort"/>
                </th>
                <th/>
              </tr>
              </thead>
              <tbody>
              {userOtherInfoList.map((userOtherInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`${match.url}/${userOtherInfo.id}`} color="link" size="sm">
                      {userOtherInfo.id}
                    </Button>
                  </td>
                  <td>{userOtherInfo.uuid}</td>
                  <td>{userOtherInfo.phoneNumber}</td>
                  <td>{userOtherInfo.email}</td>
                  <td>{userOtherInfo.wardCode}</td>
                  <td>{userOtherInfo.distCode}</td>
                  <td>{userOtherInfo.cityCode}</td>
                  <td>{userOtherInfo.addressDetails}</td>
                  <td>
                    {userOtherInfo.dateOfBirth ? (
                      <TextFormat type="date" value={userOtherInfo.dateOfBirth} format={APP_LOCAL_DATE_FORMAT}/>
                    ) : null}
                  </td>
                  <td>{userOtherInfo.otherInfo}</td>
                  <td>{userOtherInfo.searchField}</td>
                  <td>{userOtherInfo.role}</td>
                  <td>
                    {userOtherInfo.createdDate ? (
                      <TextFormat type="date" value={userOtherInfo.createdDate} format={APP_DATE_FORMAT}/>
                    ) : null}
                  </td>
                  <td>
                    {userOtherInfo.modifiedDate ? (
                      <TextFormat type="date" value={userOtherInfo.modifiedDate} format={APP_DATE_FORMAT}/>
                    ) : null}
                  </td>
                  <td>{userOtherInfo.createdBy}</td>
                  <td>{userOtherInfo.modifiedBy}</td>
                  <td>{userOtherInfo.dataSize}</td>
                  <td>{userOtherInfo.comment}</td>
                  <td>{userOtherInfo.userName ? userOtherInfo.userName.login : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${userOtherInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye"/>{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${userOtherInfo.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt"/>{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`${match.url}/${userOtherInfo.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash"/>{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
              </tbody>
            </Table>
          ) : (
            !loading && (
              <div className="alert alert-warning">
                <Translate contentKey="minhShopApp.userOtherInfo.home.notFound">No User Other Infos found</Translate>
              </div>
            )
          )}
        </InfiniteScroll>
      </div>
    </div>
  );
};

const mapStateToProps = ({userOtherInfo}: IRootState) => ({
  userOtherInfoList: userOtherInfo.entities,
  loading: userOtherInfo.loading,
  totalItems: userOtherInfo.totalItems,
  links: userOtherInfo.links,
  entity: userOtherInfo.entity,
  updateSuccess: userOtherInfo.updateSuccess,
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserOtherInfo);
