import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { setFileData, openFile, byteSize, Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './image.reducer';
import { IImage } from 'app/shared/model/image.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IImageUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ImageUpdate = (props: IImageUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { imageEntity, loading, updating } = props;

  const { imageData, imageDataContentType } = imageEntity;

  const handleClose = () => {
    props.history.push('/image');
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
        ...imageEntity,
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
          <h2 id="minhShopApp.image.home.createOrEditLabel" data-cy="ImageCreateUpdateHeading">
            <Translate contentKey="minhShopApp.image.home.createOrEditLabel">Create or edit a Image</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : imageEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="image-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="image-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="image-uuid">
                  <Translate contentKey="minhShopApp.image.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="image-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="minhShopApp.image.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="imageDataLabel" for="imageData">
                    <Translate contentKey="minhShopApp.image.imageData">Image Data</Translate>
                  </Label>
                  <br />
                  {imageData ? (
                    <div>
                      {imageDataContentType ? (
                        <a onClick={openFile(imageDataContentType, imageData)}>
                          <img src={`data:${imageDataContentType};base64,${imageData}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {imageDataContentType}, {byteSize(imageData)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('imageData')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_imageData" data-cy="imageData" type="file" onChange={onBlobChange(true, 'imageData')} accept="image/*" />
                  <AvInput
                    type="hidden"
                    name="imageData"
                    value={imageData}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                    }}
                  />
                </AvGroup>

                <UncontrolledTooltip target="imageDataLabel">
                  <Translate contentKey="minhShopApp.image.help.imageData" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="nameImageLabel" for="image-nameImage">
                  <Translate contentKey="minhShopApp.image.nameImage">Name Image</Translate>
                </Label>
                <AvField
                  id="image-nameImage"
                  data-cy="nameImage"
                  type="text"
                  name="nameImage"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
                <UncontrolledTooltip target="nameImageLabel">
                  <Translate contentKey="minhShopApp.image.help.nameImage" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="extensionLabel" for="image-extension">
                  <Translate contentKey="minhShopApp.image.extension">Extension</Translate>
                </Label>
                <AvField
                  id="image-extension"
                  data-cy="extension"
                  type="text"
                  name="extension"
                  validate={{
                    maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) },
                  }}
                />
                <UncontrolledTooltip target="extensionLabel">
                  <Translate contentKey="minhShopApp.image.help.extension" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeFileLabel" for="image-typeFile">
                  <Translate contentKey="minhShopApp.image.typeFile">Type File</Translate>
                </Label>
                <AvField
                  id="image-typeFile"
                  data-cy="typeFile"
                  type="text"
                  name="typeFile"
                  validate={{
                    maxLength: { value: 32, errorMessage: translate('entity.validation.maxlength', { max: 32 }) },
                  }}
                />
                <UncontrolledTooltip target="typeFileLabel">
                  <Translate contentKey="minhShopApp.image.help.typeFile" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="image-role">
                  <Translate contentKey="minhShopApp.image.role">Role</Translate>
                </Label>
                <AvField
                  id="image-role"
                  data-cy="role"
                  type="text"
                  name="role"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="minhShopApp.image.help.role" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="image-createdDate">
                  <Translate contentKey="minhShopApp.image.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="image-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.imageEntity.createdDate)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="minhShopApp.image.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="image-modifiedDate">
                  <Translate contentKey="minhShopApp.image.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="image-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.imageEntity.modifiedDate)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="minhShopApp.image.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="image-createdBy">
                  <Translate contentKey="minhShopApp.image.createdBy">Created By</Translate>
                </Label>
                <AvField
                  id="image-createdBy"
                  data-cy="createdBy"
                  type="text"
                  name="createdBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.image.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="image-modifiedBy">
                  <Translate contentKey="minhShopApp.image.modifiedBy">Modified By</Translate>
                </Label>
                <AvField
                  id="image-modifiedBy"
                  data-cy="modifiedBy"
                  type="text"
                  name="modifiedBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.image.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dataSizeLabel" for="image-dataSize">
                  <Translate contentKey="minhShopApp.image.dataSize">Data Size</Translate>
                </Label>
                <AvField id="image-dataSize" data-cy="dataSize" type="string" className="form-control" name="dataSize" />
                <UncontrolledTooltip target="dataSizeLabel">
                  <Translate contentKey="minhShopApp.image.help.dataSize" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="image-comment">
                  <Translate contentKey="minhShopApp.image.comment">Comment</Translate>
                </Label>
                <AvField
                  id="image-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="minhShopApp.image.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup check>
                <Label id="deletedLabel">
                  <AvInput id="image-deleted" data-cy="deleted" type="checkbox" className="form-check-input" name="deleted" />
                  <Translate contentKey="minhShopApp.image.deleted">Deleted</Translate>
                </Label>
                <UncontrolledTooltip target="deletedLabel">
                  <Translate contentKey="minhShopApp.image.help.deleted" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/image" replace color="info">
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
  imageEntity: storeState.image.entity,
  loading: storeState.image.loading,
  updating: storeState.image.updating,
  updateSuccess: storeState.image.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(ImageUpdate);
