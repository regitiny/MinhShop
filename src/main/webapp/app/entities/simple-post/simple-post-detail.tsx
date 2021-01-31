import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './simple-post.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISimplePostDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SimplePostDetail = (props: ISimplePostDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { simplePostEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="simplePostDetailsHeading">
          <Translate contentKey="minhShopApp.simplePost.detail.title">SimplePost</Translate> [<strong>{simplePostEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.simplePost.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.simplePost.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.uuid}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="minhShopApp.simplePost.title">Title</Translate>
            </span>
            <UncontrolledTooltip target="title">
              <Translate contentKey="minhShopApp.simplePost.help.title" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.title}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="minhShopApp.simplePost.price">Price</Translate>
            </span>
            <UncontrolledTooltip target="price">
              <Translate contentKey="minhShopApp.simplePost.help.price" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.price}</dd>
          <dt>
            <span id="salePrice">
              <Translate contentKey="minhShopApp.simplePost.salePrice">Sale Price</Translate>
            </span>
            <UncontrolledTooltip target="salePrice">
              <Translate contentKey="minhShopApp.simplePost.help.salePrice" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.salePrice}</dd>
          <dt>
            <span id="percentSale">
              <Translate contentKey="minhShopApp.simplePost.percentSale">Percent Sale</Translate>
            </span>
            <UncontrolledTooltip target="percentSale">
              <Translate contentKey="minhShopApp.simplePost.help.percentSale" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.percentSale}</dd>
          <dt>
            <span id="imageUrl">
              <Translate contentKey="minhShopApp.simplePost.imageUrl">Image Url</Translate>
            </span>
            <UncontrolledTooltip target="imageUrl">
              <Translate contentKey="minhShopApp.simplePost.help.imageUrl" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.imageUrl}</dd>
          <dt>
            <span id="scores">
              <Translate contentKey="minhShopApp.simplePost.scores">Scores</Translate>
            </span>
            <UncontrolledTooltip target="scores">
              <Translate contentKey="minhShopApp.simplePost.help.scores" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.scores}</dd>
          <dt>
            <span id="simpleContent">
              <Translate contentKey="minhShopApp.simplePost.simpleContent">Simple Content</Translate>
            </span>
            <UncontrolledTooltip target="simpleContent">
              <Translate contentKey="minhShopApp.simplePost.help.simpleContent" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.simpleContent}</dd>
          <dt>
            <span id="otherInfo">
              <Translate contentKey="minhShopApp.simplePost.otherInfo">Other Info</Translate>
            </span>
            <UncontrolledTooltip target="otherInfo">
              <Translate contentKey="minhShopApp.simplePost.help.otherInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.otherInfo}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.simplePost.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.simplePost.help.role" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.simplePost.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.simplePost.help.createdDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {simplePostEntity.createdDate ? <TextFormat value={simplePostEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.simplePost.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.simplePost.help.modifiedDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {simplePostEntity.modifiedDate ? (
              <TextFormat value={simplePostEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.simplePost.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.simplePost.help.createdBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.simplePost.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.simplePost.help.modifiedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.modifiedBy}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="minhShopApp.simplePost.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="minhShopApp.simplePost.help.dataSize" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.dataSize}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.simplePost.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.simplePost.help.comment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{simplePostEntity.comment}</dd>
          <dt>
            <Translate contentKey="minhShopApp.simplePost.postDetails">Post Details</Translate>
          </dt>
          <dd>{simplePostEntity.postDetails ? simplePostEntity.postDetails.postDetailsId : ''}</dd>
          <dt>
            <Translate contentKey="minhShopApp.simplePost.typePost">Type Post</Translate>
          </dt>
          <dd>{simplePostEntity.typePost ? simplePostEntity.typePost.typeName : ''}</dd>
          <dt>
            <Translate contentKey="minhShopApp.simplePost.typePostFilter">Type Post Filter</Translate>
          </dt>
          <dd>
            {simplePostEntity.typePostFilters
              ? simplePostEntity.typePostFilters.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.typeFilterName}</a>
                    {simplePostEntity.typePostFilters && i === simplePostEntity.typePostFilters.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/simple-post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/simple-post/${simplePostEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ simplePost }: IRootState) => ({
  simplePostEntity: simplePost.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SimplePostDetail);
