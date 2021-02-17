import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { setFileData, byteSize, Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './type-post.reducer';
import { ITypePost } from 'app/shared/model/type-post.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITypePostUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypePostUpdate = (props: ITypePostUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { typePostEntity, loading, updating } = props;

  const { searchField } = typePostEntity;

  const handleClose = () => {
    props.history.push('/entity/type-post');
  };

  useEffect(() => {
    if (!isNew) {
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
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    if (errors.length === 0) {
      const entity = {
        ...typePostEntity,
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
          <h2 id="minhShopApp.typePost.home.createOrEditLabel" data-cy="TypePostCreateUpdateHeading">
            <Translate contentKey="minhShopApp.typePost.home.createOrEditLabel">Create or edit a TypePost</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : typePostEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="type-post-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="type-post-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="type-post-uuid">
                  <Translate contentKey="minhShopApp.typePost.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="type-post-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="minhShopApp.typePost.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeNameLabel" for="type-post-typeName">
                  <Translate contentKey="minhShopApp.typePost.typeName">Type Name</Translate>
                </Label>
                <AvField
                  id="type-post-typeName"
                  data-cy="typeName"
                  type="text"
                  name="typeName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="typeNameLabel">
                  <Translate contentKey="minhShopApp.typePost.help.typeName" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="searchFieldLabel" for="type-post-searchField">
                  <Translate contentKey="minhShopApp.typePost.searchField">Search Field</Translate>
                </Label>
                <AvInput id="type-post-searchField" data-cy="searchField" type="textarea" name="searchField" />
                <UncontrolledTooltip target="searchFieldLabel">
                  <Translate contentKey="minhShopApp.typePost.help.searchField" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="type-post-role">
                  <Translate contentKey="minhShopApp.typePost.role">Role</Translate>
                </Label>
                <AvField id="type-post-role" data-cy="role" type="text" name="role" />
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="minhShopApp.typePost.help.role" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="type-post-createdDate">
                  <Translate contentKey="minhShopApp.typePost.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="type-post-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.typePostEntity.createdDate)}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="minhShopApp.typePost.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="type-post-modifiedDate">
                  <Translate contentKey="minhShopApp.typePost.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="type-post-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.typePostEntity.modifiedDate)}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="minhShopApp.typePost.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="type-post-createdBy">
                  <Translate contentKey="minhShopApp.typePost.createdBy">Created By</Translate>
                </Label>
                <AvField id="type-post-createdBy" data-cy="createdBy" type="text" name="createdBy" />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.typePost.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="type-post-modifiedBy">
                  <Translate contentKey="minhShopApp.typePost.modifiedBy">Modified By</Translate>
                </Label>
                <AvField id="type-post-modifiedBy" data-cy="modifiedBy" type="text" name="modifiedBy" />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.typePost.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dataSizeLabel" for="type-post-dataSize">
                  <Translate contentKey="minhShopApp.typePost.dataSize">Data Size</Translate>
                </Label>
                <AvField id="type-post-dataSize" data-cy="dataSize" type="string" className="form-control" name="dataSize" />
                <UncontrolledTooltip target="dataSizeLabel">
                  <Translate contentKey="minhShopApp.typePost.help.dataSize" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="type-post-comment">
                  <Translate contentKey="minhShopApp.typePost.comment">Comment</Translate>
                </Label>
                <AvField
                  id="type-post-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="minhShopApp.typePost.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/type-post" replace color="info">
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
  typePostEntity: storeState.typePost.entity,
  loading: storeState.typePost.loading,
  updating: storeState.typePost.updating,
  updateSuccess: storeState.typePost.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(TypePostUpdate);
