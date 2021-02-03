import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { setFileData, openFile, byteSize, Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, setBlob, reset } from './file.reducer';
import { IFile } from 'app/shared/model/file.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IFileUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const FileUpdate = (props: IFileUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { fileEntity, loading, updating } = props;

  const { videoData, videoDataContentType, searchField } = fileEntity;

  const handleClose = () => {
    props.history.push('/file');
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
        ...fileEntity,
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
          <h2 id="minhShopApp.file.home.createOrEditLabel" data-cy="FileCreateUpdateHeading">
            <Translate contentKey="minhShopApp.file.home.createOrEditLabel">Create or edit a File</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : fileEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="file-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="file-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="file-uuid">
                  <Translate contentKey="minhShopApp.file.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="file-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="minhShopApp.file.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="videoDataLabel" for="videoData">
                    <Translate contentKey="minhShopApp.file.videoData">Video Data</Translate>
                  </Label>
                  <br />
                  {videoData ? (
                    <div>
                      {videoDataContentType ? (
                        <a onClick={openFile(videoDataContentType, videoData)}>
                          <Translate contentKey="entity.action.open">Open</Translate>
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {videoDataContentType}, {byteSize(videoData)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('videoData')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_videoData" data-cy="videoData" type="file" onChange={onBlobChange(false, 'videoData')} />
                  <AvInput
                    type="hidden"
                    name="videoData"
                    value={videoData}
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') },
                    }}
                  />
                </AvGroup>

                <UncontrolledTooltip target="videoDataLabel">
                  <Translate contentKey="minhShopApp.file.help.videoData" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="nameVideoLabel" for="file-nameVideo">
                  <Translate contentKey="minhShopApp.file.nameVideo">Name Video</Translate>
                </Label>
                <AvField
                  id="file-nameVideo"
                  data-cy="nameVideo"
                  type="text"
                  name="nameVideo"
                  validate={{
                    maxLength: { value: 1024, errorMessage: translate('entity.validation.maxlength', { max: 1024 }) },
                  }}
                />
                <UncontrolledTooltip target="nameVideoLabel">
                  <Translate contentKey="minhShopApp.file.help.nameVideo" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="extensionLabel" for="file-extension">
                  <Translate contentKey="minhShopApp.file.extension">Extension</Translate>
                </Label>
                <AvField
                  id="file-extension"
                  data-cy="extension"
                  type="text"
                  name="extension"
                  validate={{
                    maxLength: { value: 16, errorMessage: translate('entity.validation.maxlength', { max: 16 }) },
                  }}
                />
                <UncontrolledTooltip target="extensionLabel">
                  <Translate contentKey="minhShopApp.file.help.extension" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeFileLabel" for="file-typeFile">
                  <Translate contentKey="minhShopApp.file.typeFile">Type File</Translate>
                </Label>
                <AvField
                  id="file-typeFile"
                  data-cy="typeFile"
                  type="text"
                  name="typeFile"
                  validate={{
                    maxLength: { value: 32, errorMessage: translate('entity.validation.maxlength', { max: 32 }) },
                  }}
                />
                <UncontrolledTooltip target="typeFileLabel">
                  <Translate contentKey="minhShopApp.file.help.typeFile" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="searchFieldLabel" for="file-searchField">
                  <Translate contentKey="minhShopApp.file.searchField">Search Field</Translate>
                </Label>
                <AvInput id="file-searchField" data-cy="searchField" type="textarea" name="searchField" />
                <UncontrolledTooltip target="searchFieldLabel">
                  <Translate contentKey="minhShopApp.file.help.searchField" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="file-role">
                  <Translate contentKey="minhShopApp.file.role">Role</Translate>
                </Label>
                <AvField id="file-role" data-cy="role" type="text" name="role" />
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="minhShopApp.file.help.role" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="file-createdDate">
                  <Translate contentKey="minhShopApp.file.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="file-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.fileEntity.createdDate)}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="minhShopApp.file.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="file-modifiedDate">
                  <Translate contentKey="minhShopApp.file.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="file-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.fileEntity.modifiedDate)}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="minhShopApp.file.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="file-createdBy">
                  <Translate contentKey="minhShopApp.file.createdBy">Created By</Translate>
                </Label>
                <AvField id="file-createdBy" data-cy="createdBy" type="text" name="createdBy" />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.file.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="file-modifiedBy">
                  <Translate contentKey="minhShopApp.file.modifiedBy">Modified By</Translate>
                </Label>
                <AvField id="file-modifiedBy" data-cy="modifiedBy" type="text" name="modifiedBy" />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.file.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dataSizeLabel" for="file-dataSize">
                  <Translate contentKey="minhShopApp.file.dataSize">Data Size</Translate>
                </Label>
                <AvField id="file-dataSize" data-cy="dataSize" type="string" className="form-control" name="dataSize" />
                <UncontrolledTooltip target="dataSizeLabel">
                  <Translate contentKey="minhShopApp.file.help.dataSize" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="file-comment">
                  <Translate contentKey="minhShopApp.file.comment">Comment</Translate>
                </Label>
                <AvField
                  id="file-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="minhShopApp.file.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/file" replace color="info">
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
  fileEntity: storeState.file.entity,
  loading: storeState.file.loading,
  updating: storeState.file.updating,
  updateSuccess: storeState.file.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(FileUpdate);
