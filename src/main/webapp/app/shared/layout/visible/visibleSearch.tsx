import React, { useState, useEffect } from 'react';
import InfiniteScroll from 'react-infinite-scroller';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import {
  Button,
  InputGroup,
  Col,
  Row,
  Table,
  Card,
  CardImg,
  CardText,
  CardBody,
  CardTitle,
  CardSubtitle,
  CardHeader,
  DropdownItem,
  Dropdown,
  DropdownMenu,
  DropdownToggle,
  Progress,
} from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { byteSize, Translate, translate, ICrudSearchAction, TextFormat, getSortState, IPaginationBaseState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { Input } from 'reactstrap';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities, reset } from 'app/entities/simple-post/simple-post.reducer';
import { ISimplePost } from 'app/shared/model/simple-post.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ITEMS_PER_PAGE } from 'app/shared/util/pagination.constants';
import { overridePaginationStateWithQueryParams } from 'app/shared/util/entity-utils';

import { getEntities as getTypePostFilters } from 'app/entities/type-post-filter/type-post-filter.reducer';
import axios from 'axios';
import { Storage } from 'react-jhipster';

export interface ISimplePostProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const VisibleSearch = (props: ISimplePostProps) => {
  //cái danh sách sắp xếp
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const toggle = () => setDropdownOpen(prevState => !prevState);

  const [search, setSearch] = useState('');
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );

  window.console.log(paginationState);
  window.console.log(props);
  const { simplePostList, typePostFilters, match, loading } = props;
  const [sorting, setSorting] = useState(false);

  window.console.log(simplePostList);

  const getAllEntities = () => {
    if (search) {
      props.getSearchEntities(
        search,
        paginationState.activePage - 1,
        paginationState.itemsPerPage,
        `${paginationState.sort},${paginationState.order}`
      );
    } else {
      props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, `${paginationState.sort},${paginationState.order}`);
    }
  };
  useEffect(() => {
    props.getTypePostFilters();
  }, []);
  const resetAll = () => {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };

  useEffect(() => {
    resetAll();
  }, []);

  //sử dụng để đưa giá trị mặc định của sort về ''
  useEffect(() => {
    setPaginationState({
      ...paginationState,
      sort: '',
    });
  }, []);

  const startSearching = () => {
    if (search) {
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

  const clear = () => {
    props.reset();
    setSearch('');
    setPaginationState({
      ...paginationState,
      activePage: 1,
    });
    props.getEntities();
  };

  const handleSearch = event => setSearch(event.target.value);

  useEffect(() => {
    if (props.updateSuccess) {
      resetAll();
    }
  }, [props.updateSuccess]);

  useEffect(() => {
    getAllEntities();
  }, [paginationState.activePage]);

  useEffect(() => {
    if (sorting) {
      getAllEntities();
      setSorting(false);
    }
  }, [sorting, search]);

  return (
    <div className=" d-flex justify-content-center">
      <div className="col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
        <Row>
          <Col sm="12" className="p-0">
            <AvForm onSubmit={startSearching}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={search}
                    onChange={handleSearch}
                    placeholder={translate('minhShopApp.simplePost.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
      </div>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  simplePostList: storeState.simplePost.entities,
  typePostFilters: storeState.typePostFilter.entities,
  loading: storeState.simplePost.loading,
  totalItems: storeState.simplePost.totalItems,
  links: storeState.simplePost.links,
  entity: storeState.simplePost.entity,
  updateSuccess: storeState.simplePost.updateSuccess,
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities,
  reset,
  getTypePostFilters,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(VisibleSearch);
