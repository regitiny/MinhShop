import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISimplePost } from 'app/shared/model/simple-post.model';
import { getEntities as getSimplePosts } from 'app/entities/simple-post/simple-post.reducer';
import { getEntity, updateEntity, createEntity, reset } from './post-details.reducer';
import { IPostDetails } from 'app/shared/model/post-details.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPostDetailsUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PostDetailsUpdate = (props: IPostDetailsUpdateProps) => {
  const [simplePostId, setSimplePostId] = useState('0');
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { postDetailsEntity, simplePosts, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/post-details');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getSimplePosts();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    if (errors.length === 0) {
      const entity = {
        ...postDetailsEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="minhShopApp.postDetails.home.createOrEditLabel" data-cy="PostDetailsCreateUpdateHeading">
            <Translate contentKey="minhShopApp.postDetails.home.createOrEditLabel">Create or edit a PostDetails</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : postDetailsEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="post-details-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="post-details-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="post-details-uuid">
                  <Translate contentKey="minhShopApp.postDetails.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="post-details-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="publicIdLabel" for="post-details-publicId">
                  <Translate contentKey="minhShopApp.postDetails.publicId">Public Id</Translate>
                </Label>
                <AvField
                  id="post-details-publicId"
                  data-cy="publicId"
                  type="text"
                  name="publicId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    minLength: { value: 3, errorMessage: translate('entity.validation.minlength', { min: 3 }) },
                    maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) },
                    pattern: { value: '[A-z]+[0-9]+', errorMessage: translate('entity.validation.pattern', { pattern: '[A-z]+[0-9]+' }) },
                  }}
                />
                <UncontrolledTooltip target="publicIdLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.publicId" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="contentLabel" for="post-details-content">
                  <Translate contentKey="minhShopApp.postDetails.content">Content</Translate>
                </Label>
                <AvField
                  id="post-details-content"
                  data-cy="content"
                  type="text"
                  name="content"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="contentLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.content" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="post-details-createdDate">
                  <Translate contentKey="minhShopApp.postDetails.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="post-details-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.postDetailsEntity.createdDate)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="post-details-modifiedDate">
                  <Translate contentKey="minhShopApp.postDetails.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="post-details-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.postDetailsEntity.modifiedDate)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="post-details-createdBy">
                  <Translate contentKey="minhShopApp.postDetails.createdBy">Created By</Translate>
                </Label>
                <AvField
                  id="post-details-createdBy"
                  data-cy="createdBy"
                  type="text"
                  name="createdBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="post-details-modifiedBy">
                  <Translate contentKey="minhShopApp.postDetails.modifiedBy">Modified By</Translate>
                </Label>
                <AvField
                  id="post-details-modifiedBy"
                  data-cy="modifiedBy"
                  type="text"
                  name="modifiedBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dataSizeLabel" for="post-details-dataSize">
                  <Translate contentKey="minhShopApp.postDetails.dataSize">Data Size</Translate>
                </Label>
                <AvField id="post-details-dataSize" data-cy="dataSize" type="string" className="form-control" name="dataSize" />
                <UncontrolledTooltip target="dataSizeLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.dataSize" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="post-details-comment">
                  <Translate contentKey="minhShopApp.postDetails.comment">Comment</Translate>
                </Label>
                <AvField
                  id="post-details-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="minhShopApp.postDetails.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/post-details" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  simplePosts: storeState.simplePost.entities,
  postDetailsEntity: storeState.postDetails.entity,
  loading: storeState.postDetails.loading,
  updating: storeState.postDetails.updating,
  updateSuccess: storeState.postDetails.updateSuccess,
});

const mapDispatchToProps = {
  getSimplePosts,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PostDetailsUpdate);
