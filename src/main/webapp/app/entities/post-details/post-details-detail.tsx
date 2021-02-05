import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './post-details.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import FroalaEditorView from 'react-froala-wysiwyg/FroalaEditorView';

export interface IPostDetailsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PostDetailsDetail = (props: IPostDetailsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { postDetailsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="postDetailsDetailsHeading">
          <Translate contentKey="minhShopApp.postDetails.detail.title">PostDetails</Translate> [<strong>{postDetailsEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.postDetails.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.postDetails.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.uuid}</dd>
          <dt>
            <span id="postDetailsId">
              <Translate contentKey="minhShopApp.postDetails.postDetailsId">Post Details Id</Translate>
            </span>
            <UncontrolledTooltip target="postDetailsId">
              <Translate contentKey="minhShopApp.postDetails.help.postDetailsId" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.postDetailsId}</dd>
          <dt>
            <span id="content">
              <Translate contentKey="minhShopApp.postDetails.content">Content</Translate>
            </span>
            <UncontrolledTooltip target="content">
              <Translate contentKey="minhShopApp.postDetails.help.content" />
            </UncontrolledTooltip>
          </dt>
          <FroalaEditorView model={postDetailsEntity.content}></FroalaEditorView>
          {/*<dd>{postDetailsEntity.content}</dd>*/}
          <dt>
            <span id="searchField">
              <Translate contentKey="minhShopApp.postDetails.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="minhShopApp.postDetails.help.searchField" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.searchField}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.postDetails.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.postDetails.help.role" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.postDetails.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.postDetails.help.createdDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {postDetailsEntity.createdDate ? (
              <TextFormat value={postDetailsEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.postDetails.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.postDetails.help.modifiedDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {postDetailsEntity.modifiedDate ? (
              <TextFormat value={postDetailsEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.postDetails.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.postDetails.help.createdBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.postDetails.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.postDetails.help.modifiedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.modifiedBy}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="minhShopApp.postDetails.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="minhShopApp.postDetails.help.dataSize" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.dataSize}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.postDetails.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.postDetails.help.comment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{postDetailsEntity.comment}</dd>
        </dl>
        <Button tag={Link} to="/post-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/post-details/${postDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ postDetails }: IRootState) => ({
  postDetailsEntity: postDetails.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PostDetailsDetail);
