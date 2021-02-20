import React, { useEffect, useState } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Label, Row, UncontrolledTooltip } from 'reactstrap';
import { AvField, AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
import { setFileData, Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';
import { getUsers } from 'app/modules/administration/user-management/user-management.reducer';
import { createEntity, getEntity, reset, setBlob, updateEntity } from './user-other-info.reducer';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';

export interface IUserOtherInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserOtherInfoUpdate = (props: IUserOtherInfoUpdateProps) => {
  const [userNameId, setUserNameId] = useState('0');
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { userOtherInfoEntity, users, loading, updating } = props;

  const { searchField } = userOtherInfoEntity;

  const handleClose = () => {
    props.history.push('/entity/user-other-info');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }

    props.getUsers();
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
        ...userOtherInfoEntity,
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
          <h2 id="minhShopApp.userOtherInfo.home.createOrEditLabel" data-cy="UserOtherInfoCreateUpdateHeading">
            <Translate contentKey="minhShopApp.userOtherInfo.home.createOrEditLabel">Create or edit a UserOtherInfo</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : userOtherInfoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="user-other-info-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="user-other-info-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="user-other-info-uuid">
                  <Translate contentKey="minhShopApp.userOtherInfo.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="user-other-info-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="phoneNumberLabel" for="user-other-info-phoneNumber">
                  <Translate contentKey="minhShopApp.userOtherInfo.phoneNumber">Phone Number</Translate>
                </Label>
                <AvField
                  id="user-other-info-phoneNumber"
                  data-cy="phoneNumber"
                  type="text"
                  name="phoneNumber"
                  validate={{
                    minLength: { value: 10, errorMessage: translate('entity.validation.minlength', { min: 10 }) },
                    maxLength: { value: 10, errorMessage: translate('entity.validation.maxlength', { max: 10 }) },
                    pattern: { value: '[0-9]+', errorMessage: translate('entity.validation.pattern', { pattern: '[0-9]+' }) },
                  }}
                />
                <UncontrolledTooltip target="phoneNumberLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.phoneNumber" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="user-other-info-email">
                  <Translate contentKey="minhShopApp.userOtherInfo.email">Email</Translate>
                </Label>
                <AvField id="user-other-info-email" data-cy="email" type="text" name="email" />
                <UncontrolledTooltip target="emailLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.email" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="wardCodeLabel" for="user-other-info-wardCode">
                  <Translate contentKey="minhShopApp.userOtherInfo.wardCode">Ward Code</Translate>
                </Label>
                <AvField id="user-other-info-wardCode" data-cy="wardCode" type="text" name="wardCode" />
                <UncontrolledTooltip target="wardCodeLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.wardCode" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="distCodeLabel" for="user-other-info-distCode">
                  <Translate contentKey="minhShopApp.userOtherInfo.distCode">Dist Code</Translate>
                </Label>
                <AvField id="user-other-info-distCode" data-cy="distCode" type="text" name="distCode" />
                <UncontrolledTooltip target="distCodeLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.distCode" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="cityCodeLabel" for="user-other-info-cityCode">
                  <Translate contentKey="minhShopApp.userOtherInfo.cityCode">City Code</Translate>
                </Label>
                <AvField id="user-other-info-cityCode" data-cy="cityCode" type="text" name="cityCode" />
                <UncontrolledTooltip target="cityCodeLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.cityCode" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="addressDetailsLabel" for="user-other-info-addressDetails">
                  <Translate contentKey="minhShopApp.userOtherInfo.addressDetails">Address Details</Translate>
                </Label>
                <AvField id="user-other-info-addressDetails" data-cy="addressDetails" type="text" name="addressDetails" />
                <UncontrolledTooltip target="addressDetailsLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.addressDetails" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="user-other-info-dateOfBirth">
                  <Translate contentKey="minhShopApp.userOtherInfo.dateOfBirth">Date Of Birth</Translate>
                </Label>
                <AvField id="user-other-info-dateOfBirth" data-cy="dateOfBirth" type="date" className="form-control" name="dateOfBirth" />
                <UncontrolledTooltip target="dateOfBirthLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.dateOfBirth" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="otherInfoLabel" for="user-other-info-otherInfo">
                  <Translate contentKey="minhShopApp.userOtherInfo.otherInfo">Other Info</Translate>
                </Label>
                <AvField id="user-other-info-otherInfo" data-cy="otherInfo" type="text" name="otherInfo" />
                <UncontrolledTooltip target="otherInfoLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.otherInfo" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="searchFieldLabel" for="user-other-info-searchField">
                  <Translate contentKey="minhShopApp.userOtherInfo.searchField">Search Field</Translate>
                </Label>
                <AvInput id="user-other-info-searchField" data-cy="searchField" type="textarea" name="searchField" />
                <UncontrolledTooltip target="searchFieldLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.searchField" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="user-other-info-role">
                  <Translate contentKey="minhShopApp.userOtherInfo.role">Role</Translate>
                </Label>
                <AvField id="user-other-info-role" data-cy="role" type="text" name="role" />
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.role" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="user-other-info-createdDate">
                  <Translate contentKey="minhShopApp.userOtherInfo.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="user-other-info-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.userOtherInfoEntity.createdDate)}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="user-other-info-modifiedDate">
                  <Translate contentKey="minhShopApp.userOtherInfo.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="user-other-info-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.userOtherInfoEntity.modifiedDate)}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="user-other-info-createdBy">
                  <Translate contentKey="minhShopApp.userOtherInfo.createdBy">Created By</Translate>
                </Label>
                <AvField id="user-other-info-createdBy" data-cy="createdBy" type="text" name="createdBy" />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="user-other-info-modifiedBy">
                  <Translate contentKey="minhShopApp.userOtherInfo.modifiedBy">Modified By</Translate>
                </Label>
                <AvField id="user-other-info-modifiedBy" data-cy="modifiedBy" type="text" name="modifiedBy" />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dataSizeLabel" for="user-other-info-dataSize">
                  <Translate contentKey="minhShopApp.userOtherInfo.dataSize">Data Size</Translate>
                </Label>
                <AvField id="user-other-info-dataSize" data-cy="dataSize" type="string" className="form-control" name="dataSize" />
                <UncontrolledTooltip target="dataSizeLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.dataSize" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="user-other-info-comment">
                  <Translate contentKey="minhShopApp.userOtherInfo.comment">Comment</Translate>
                </Label>
                <AvField
                  id="user-other-info-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: { value: 2048, errorMessage: translate('entity.validation.maxlength', { max: 2048 }) },
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="minhShopApp.userOtherInfo.help.comment" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label for="user-other-info-userName">
                  <Translate contentKey="minhShopApp.userOtherInfo.userName">User Name</Translate>
                </Label>
                <AvInput id="user-other-info-userName" data-cy="userName" type="select" className="form-control" name="userName.id">
                  <option value="" key="0" />
                  {users
                    ? users.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.login}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/user-other-info" replace color="info">
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
  users: storeState.userManagement.users,
  userOtherInfoEntity: storeState.userOtherInfo.entity,
  loading: storeState.userOtherInfo.loading,
  updating: storeState.userOtherInfo.updating,
  updateSuccess: storeState.userOtherInfo.updateSuccess,
});

const mapDispatchToProps = {
  getUsers,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserOtherInfoUpdate);
