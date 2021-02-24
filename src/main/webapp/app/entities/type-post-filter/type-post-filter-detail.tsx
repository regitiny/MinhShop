import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './type-post-filter.reducer';
import {APP_DATE_FORMAT} from 'app/config/constants';

export interface ITypePostFilterDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const TypePostFilterDetail = (props: ITypePostFilterDetailProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const {typePostFilterEntity} = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="typePostFilterDetailsHeading">
          <Translate contentKey="minhShopApp.typePostFilter.detail.title">TypePostFilter</Translate> [
          <strong>{typePostFilterEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.typePostFilter.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.typePostFilter.help.uuid"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.uuid}</dd>
          <dt>
            <span id="typeFilterName">
              <Translate contentKey="minhShopApp.typePostFilter.typeFilterName">Type Filter Name</Translate>
            </span>
            <UncontrolledTooltip target="typeFilterName">
              <Translate contentKey="minhShopApp.typePostFilter.help.typeFilterName"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.typeFilterName}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="minhShopApp.typePostFilter.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="minhShopApp.typePostFilter.help.searchField"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.searchField}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.typePostFilter.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.typePostFilter.help.role"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.typePostFilter.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.typePostFilter.help.createdDate"/>
            </UncontrolledTooltip>
          </dt>
          <dd>
            {typePostFilterEntity.createdDate ? (
              <TextFormat value={typePostFilterEntity.createdDate} type="date" format={APP_DATE_FORMAT}/>
            ) : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.typePostFilter.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.typePostFilter.help.modifiedDate"/>
            </UncontrolledTooltip>
          </dt>
          <dd>
            {typePostFilterEntity.modifiedDate ? (
              <TextFormat value={typePostFilterEntity.modifiedDate} type="date" format={APP_DATE_FORMAT}/>
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.typePostFilter.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.typePostFilter.help.createdBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.typePostFilter.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.typePostFilter.help.modifiedBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.modifiedBy}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="minhShopApp.typePostFilter.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="minhShopApp.typePostFilter.help.dataSize"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.dataSize}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.typePostFilter.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.typePostFilter.help.comment"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostFilterEntity.comment}</dd>
        </dl>
        <Button tag={Link} to="/type-post-filter" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type-post-filter/${typePostFilterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({typePostFilter}: IRootState) => ({
  typePostFilterEntity: typePostFilter.entity,
});

const mapDispatchToProps = {getEntity};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypePostFilterDetail);
