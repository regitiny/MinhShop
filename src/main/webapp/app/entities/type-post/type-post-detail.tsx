import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './type-post.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITypePostDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypePostDetail = (props: ITypePostDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { typePostEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="typePostDetailsHeading">
          <Translate contentKey="minhShopApp.typePost.detail.title">TypePost</Translate> [<strong>{typePostEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.typePost.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.typePost.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostEntity.uuid}</dd>
          <dt>
            <span id="typeName">
              <Translate contentKey="minhShopApp.typePost.typeName">Type Name</Translate>
            </span>
            <UncontrolledTooltip target="typeName">
              <Translate contentKey="minhShopApp.typePost.help.typeName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostEntity.typeName}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.typePost.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.typePost.help.createdDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {typePostEntity.createdDate ? <TextFormat value={typePostEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.typePost.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.typePost.help.modifiedDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {typePostEntity.modifiedDate ? <TextFormat value={typePostEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.typePost.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.typePost.help.createdBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.typePost.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.typePost.help.modifiedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{typePostEntity.modifiedBy}</dd>
        </dl>
        <Button tag={Link} to="/type-post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type-post/${typePostEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ typePost }: IRootState) => ({
  typePostEntity: typePost.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypePostDetail);
