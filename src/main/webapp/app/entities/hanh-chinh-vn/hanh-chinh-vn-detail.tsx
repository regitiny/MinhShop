import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './hanh-chinh-vn.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IHanhChinhVNDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HanhChinhVNDetail = (props: IHanhChinhVNDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { hanhChinhVNEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="hanhChinhVNDetailsHeading">
          <Translate contentKey="minhShopApp.hanhChinhVN.detail.title">HanhChinhVN</Translate> [<strong>{hanhChinhVNEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="minhShopApp.hanhChinhVN.name">Name</Translate>
            </span>
            <UncontrolledTooltip target="name">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.name" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.name}</dd>
          <dt>
            <span id="slug">
              <Translate contentKey="minhShopApp.hanhChinhVN.slug">Slug</Translate>
            </span>
            <UncontrolledTooltip target="slug">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.slug" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.slug}</dd>
          <dt>
            <span id="type">
              <Translate contentKey="minhShopApp.hanhChinhVN.type">Type</Translate>
            </span>
            <UncontrolledTooltip target="type">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.type" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.type}</dd>
          <dt>
            <span id="nameWithType">
              <Translate contentKey="minhShopApp.hanhChinhVN.nameWithType">Name With Type</Translate>
            </span>
            <UncontrolledTooltip target="nameWithType">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.nameWithType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.nameWithType}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="minhShopApp.hanhChinhVN.code">Code</Translate>
            </span>
            <UncontrolledTooltip target="code">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.code" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.code}</dd>
          <dt>
            <span id="parentCode">
              <Translate contentKey="minhShopApp.hanhChinhVN.parentCode">Parent Code</Translate>
            </span>
            <UncontrolledTooltip target="parentCode">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.parentCode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.parentCode}</dd>
          <dt>
            <span id="path">
              <Translate contentKey="minhShopApp.hanhChinhVN.path">Path</Translate>
            </span>
            <UncontrolledTooltip target="path">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.path" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.path}</dd>
          <dt>
            <span id="pathWithType">
              <Translate contentKey="minhShopApp.hanhChinhVN.pathWithType">Path With Type</Translate>
            </span>
            <UncontrolledTooltip target="pathWithType">
              <Translate contentKey="minhShopApp.hanhChinhVN.help.pathWithType" />
            </UncontrolledTooltip>
          </dt>
          <dd>{hanhChinhVNEntity.pathWithType}</dd>
        </dl>
        <Button tag={Link} to="/hanh-chinh-vn" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/hanh-chinh-vn/${hanhChinhVNEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ hanhChinhVN }: IRootState) => ({
  hanhChinhVNEntity: hanhChinhVN.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HanhChinhVNDetail);
