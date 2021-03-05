import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './post.reducer';

export interface IPostDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const PostDetail = (props: IPostDetailProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const {postEntity} = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="postDetailsHeading">
          <Translate contentKey="minhShopApp.post.detail.title">Post</Translate> [<strong>{postEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="title">
              <Translate contentKey="minhShopApp.post.title">Title</Translate>
            </span>
            <UncontrolledTooltip target="title">
              <Translate contentKey="minhShopApp.post.help.title"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.title}</dd>
          <dt>
            <span id="price">
              <Translate contentKey="minhShopApp.post.price">Price</Translate>
            </span>
            <UncontrolledTooltip target="price">
              <Translate contentKey="minhShopApp.post.help.price"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.price}</dd>
          <dt>
            <span id="salePrice">
              <Translate contentKey="minhShopApp.post.salePrice">Sale Price</Translate>
            </span>
            <UncontrolledTooltip target="salePrice">
              <Translate contentKey="minhShopApp.post.help.salePrice"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.salePrice}</dd>
          <dt>
            <span id="percentSale">
              <Translate contentKey="minhShopApp.post.percentSale">Percent Sale</Translate>
            </span>
            <UncontrolledTooltip target="percentSale">
              <Translate contentKey="minhShopApp.post.help.percentSale"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.percentSale}</dd>
          <dt>
            <span id="imageUrl">
              <Translate contentKey="minhShopApp.post.imageUrl">Image Url</Translate>
            </span>
            <UncontrolledTooltip target="imageUrl">
              <Translate contentKey="minhShopApp.post.help.imageUrl"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.imageUrl}</dd>
          <dt>
            <span id="scores">
              <Translate contentKey="minhShopApp.post.scores">Scores</Translate>
            </span>
            <UncontrolledTooltip target="scores">
              <Translate contentKey="minhShopApp.post.help.scores"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.scores}</dd>
          <dt>
            <span id="simpleContent">
              <Translate contentKey="minhShopApp.post.simpleContent">Simple Content</Translate>
            </span>
            <UncontrolledTooltip target="simpleContent">
              <Translate contentKey="minhShopApp.post.help.simpleContent"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.simpleContent}</dd>
          <dt>
            <span id="otherInfo">
              <Translate contentKey="minhShopApp.post.otherInfo">Other Info</Translate>
            </span>
            <UncontrolledTooltip target="otherInfo">
              <Translate contentKey="minhShopApp.post.help.otherInfo"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.otherInfo}</dd>
          <dt>
            <span id="postDetailsId">
              <Translate contentKey="minhShopApp.post.postDetailsId">Post Details Id</Translate>
            </span>
            <UncontrolledTooltip target="postDetailsId">
              <Translate contentKey="minhShopApp.post.help.postDetailsId"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.postDetailsId}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="minhShopApp.post.content">Content</Translate>
            </span>
            <UncontrolledTooltip target="content">
              <Translate contentKey="minhShopApp.post.help.content"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.content}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.post.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.post.help.role"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.role}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.post.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.post.help.createdBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.post.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.post.help.modifiedBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.modifiedBy}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.post.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.post.help.comment"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{postEntity.comment}</dd>
        </dl>
        <Button tag={Link} to="/entity/post" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/entity/post/${postEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({post}: IRootState) => ({
  postEntity: post.entity,
});

const mapDispatchToProps = {getEntity};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PostDetail);
