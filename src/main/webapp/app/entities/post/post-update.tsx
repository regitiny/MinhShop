import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { setFileData, byteSize, Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './post.reducer';
import { IPost } from 'app/shared/model/post.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPostUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PostUpdate = (props: IPostUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { postEntity, loading, updating } = props;

  const { content } = postEntity;

  const handleClose = () => {
    props.history.push('/post');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...postEntity,
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
          <h2 id="minhShopApp.post.home.createOrEditLabel" data-cy="PostCreateUpdateHeading">
            <Translate contentKey="minhShopApp.post.home.createOrEditLabel">Create or edit a Post</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : postEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="post-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="post-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="titleLabel" for="post-title">
                  <Translate contentKey="minhShopApp.post.title">Title</Translate>
                </Label>
                <AvField
                  id="post-title"
                  data-cy="title"
                  type="text"
                  name="title"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="titleLabel">
                  <Translate contentKey="minhShopApp.post.help.title" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="priceLabel" for="post-price">
                  <Translate contentKey="minhShopApp.post.price">Price</Translate>
                </Label>
                <AvField id="post-price" data-cy="price" type="string" className="form-control" name="price" />
                <UncontrolledTooltip target="priceLabel">
                  <Translate contentKey="minhShopApp.post.help.price" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="salePriceLabel" for="post-salePrice">
                  <Translate contentKey="minhShopApp.post.salePrice">Sale Price</Translate>
                </Label>
                <AvField id="post-salePrice" data-cy="salePrice" type="string" className="form-control" name="salePrice" />
                <UncontrolledTooltip target="salePriceLabel">
                  <Translate contentKey="minhShopApp.post.help.salePrice" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="percentSaleLabel" for="post-percentSale">
                  <Translate contentKey="minhShopApp.post.percentSale">Percent Sale</Translate>
                </Label>
                <AvField
                  id="post-percentSale"
                  data-cy="percentSale"
                  type="string"
                  className="form-control"
                  name="percentSale"
                  validate={{
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 100, errorMessage: translate('entity.validation.max', { max: 100 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
                <UncontrolledTooltip target="percentSaleLabel">
                  <Translate contentKey="minhShopApp.post.help.percentSale" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="imageUrlLabel" for="post-imageUrl">
                  <Translate contentKey="minhShopApp.post.imageUrl">Image Url</Translate>
                </Label>
                <AvField
                  id="post-imageUrl"
                  data-cy="imageUrl"
                  type="text"
                  name="imageUrl"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="imageUrlLabel">
                  <Translate contentKey="minhShopApp.post.help.imageUrl" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="scoresLabel" for="post-scores">
                  <Translate contentKey="minhShopApp.post.scores">Scores</Translate>
                </Label>
                <AvField
                  id="post-scores"
                  data-cy="scores"
                  type="string"
                  className="form-control"
                  name="scores"
                  validate={{
                    min: { value: 0, errorMessage: translate('entity.validation.min', { min: 0 }) },
                    max: { value: 100, errorMessage: translate('entity.validation.max', { max: 100 }) },
                    number: { value: true, errorMessage: translate('entity.validation.number') },
                  }}
                />
                <UncontrolledTooltip target="scoresLabel">
                  <Translate contentKey="minhShopApp.post.help.scores" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="simpleContentLabel" for="post-simpleContent">
                  <Translate contentKey="minhShopApp.post.simpleContent">Simple Content</Translate>
                </Label>
                <AvField
                  id="post-simpleContent"
                  data-cy="simpleContent"
                  type="text"
                  name="simpleContent"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="simpleContentLabel">
                  <Translate contentKey="minhShopApp.post.help.simpleContent" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="otherInfoLabel" for="post-otherInfo">
                  <Translate contentKey="minhShopApp.post.otherInfo">Other Info</Translate>
                </Label>
                <AvField
                  id="post-otherInfo"
                  data-cy="otherInfo"
                  type="text"
                  name="otherInfo"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="otherInfoLabel">
                  <Translate contentKey="minhShopApp.post.help.otherInfo" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="postDetailsIdLabel" for="post-postDetailsId">
                  <Translate contentKey="minhShopApp.post.postDetailsId">Post Details Id</Translate>
                </Label>
                <AvField
                  id="post-postDetailsId"
                  data-cy="postDetailsId"
                  type="text"
                  name="postDetailsId"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    minLength: { value: 3, errorMessage: translate('entity.validation.minlength', { min: 3 }) },
                    maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) },
                    pattern: { value: '[A-z]+[0-9]+', errorMessage: translate('entity.validation.pattern', { pattern: '[A-z]+[0-9]+' }) },
                  }}
                />
                <UncontrolledTooltip target="postDetailsIdLabel">
                  <Translate contentKey="minhShopApp.post.help.postDetailsId" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="contentLabel" for="post-content">
                  <Translate contentKey="minhShopApp.post.content">Content</Translate>
                </Label>
                <AvInput id="post-content" data-cy="content" type="textarea" name="content" />
                <UncontrolledTooltip target="contentLabel">
                  <Translate contentKey="minhShopApp.post.help.content" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="post-role">
                  <Translate contentKey="minhShopApp.post.role">Role</Translate>
                </Label>
                <AvField id="post-role" data-cy="role" type="text" name="role" />
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="minhShopApp.post.help.role" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="post-createdBy">
                  <Translate contentKey="minhShopApp.post.createdBy">Created By</Translate>
                </Label>
                <AvField id="post-createdBy" data-cy="createdBy" type="text" name="createdBy" />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.post.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="post-modifiedBy">
                  <Translate contentKey="minhShopApp.post.modifiedBy">Modified By</Translate>
                </Label>
                <AvField id="post-modifiedBy" data-cy="modifiedBy" type="text" name="modifiedBy" />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.post.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="post-comment">
                  <Translate contentKey="minhShopApp.post.comment">Comment</Translate>
                </Label>
                <AvField
                  id="post-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="minhShopApp.post.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/post" replace color="info">
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
  postEntity: storeState.post.entity,
  loading: storeState.post.loading,
  updating: storeState.post.updating,
  updateSuccess: storeState.post.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PostUpdate);
