import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Label, Row, UncontrolledTooltip} from 'reactstrap';
import {AvField, AvForm, AvGroup, AvInput} from 'availity-reactstrap-validation';
import {setFileData, Translate, translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {IRootState} from 'app/shared/reducers';
import {getEntities as getUserOtherInfos} from 'app/entities/user-other-info/user-other-info.reducer';
import {getEntities as getPayments} from 'app/entities/payment/payment.reducer';
import {createEntity, getEntity, reset, setBlob, updateEntity} from './bill.reducer';
import {convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime} from 'app/shared/util/date-utils';

export interface IBillUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const BillUpdate = (props: IBillUpdateProps) =>
{
  const [userOtherInfoId, setUserOtherInfoId] = useState('0');
  const [paymentId, setPaymentId] = useState('0');
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const {billEntity, userOtherInfos, payments, loading, updating} = props;

  const {searchField} = billEntity;

  const handleClose = () =>
  {
    props.history.push('/entity/bill');
  };

  useEffect(() =>
  {
    if (!isNew)
    {
      props.getEntity(props.match.params.id);
    }

    props.getUserOtherInfos();
    props.getPayments();
  }, []);

  const onBlobChange = (isAnImage, name) => event =>
  {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () =>
  {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() =>
  {
    if (props.updateSuccess)
    {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) =>
  {
    values.createdDate = convertDateTimeToServer(values.createdDate);
    values.modifiedDate = convertDateTimeToServer(values.modifiedDate);

    if (errors.length === 0)
    {
      const entity = {
        ...billEntity,
        ...values,
      };

      if (isNew)
      {
        props.createEntity(entity);
      }
      else
      {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="minhShopApp.bill.home.createOrEditLabel" data-cy="BillCreateUpdateHeading">
            <Translate contentKey="minhShopApp.bill.home.createOrEditLabel">Create or edit a Bill</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : billEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="bill-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="bill-id" type="text" className="form-control" name="id" required readOnly/>
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="bill-uuid">
                  <Translate contentKey="minhShopApp.bill.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="bill-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: {value: true, errorMessage: translate('entity.validation.required')},
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="minhShopApp.bill.help.uuid"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="billIdLabel" for="bill-billId">
                  <Translate contentKey="minhShopApp.bill.billId">Bill Id</Translate>
                </Label>
                <AvField
                  id="bill-billId"
                  data-cy="billId"
                  type="text"
                  name="billId"
                  validate={{
                    required: {value: true, errorMessage: translate('entity.validation.required')},
                    maxLength: {value: 16, errorMessage: translate('entity.validation.maxlength', {max: 16})},
                  }}
                />
                <UncontrolledTooltip target="billIdLabel">
                  <Translate contentKey="minhShopApp.bill.help.billId"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="phoneNumberLabel" for="bill-phoneNumber">
                  <Translate contentKey="minhShopApp.bill.phoneNumber">Phone Number</Translate>
                </Label>
                <AvField
                  id="bill-phoneNumber"
                  data-cy="phoneNumber"
                  type="text"
                  name="phoneNumber"
                  validate={{
                    required: {value: true, errorMessage: translate('entity.validation.required')},
                    minLength: {value: 10, errorMessage: translate('entity.validation.minlength', {min: 10})},
                    maxLength: {value: 10, errorMessage: translate('entity.validation.maxlength', {max: 10})},
                    pattern: {value: '[0-9]+', errorMessage: translate('entity.validation.pattern', {pattern: '[0-9]+'})},
                  }}
                />
                <UncontrolledTooltip target="phoneNumberLabel">
                  <Translate contentKey="minhShopApp.bill.help.phoneNumber"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="bill-email">
                  <Translate contentKey="minhShopApp.bill.email">Email</Translate>
                </Label>
                <AvField id="bill-email" data-cy="email" type="text" name="email"/>
                <UncontrolledTooltip target="emailLabel">
                  <Translate contentKey="minhShopApp.bill.help.email"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="addressDetailsLabel" for="bill-addressDetails">
                  <Translate contentKey="minhShopApp.bill.addressDetails">Address Details</Translate>
                </Label>
                <AvField id="bill-addressDetails" data-cy="addressDetails" type="text" name="addressDetails"/>
                <UncontrolledTooltip target="addressDetailsLabel">
                  <Translate contentKey="minhShopApp.bill.help.addressDetails"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="addressCodeLabel" for="bill-addressCode">
                  <Translate contentKey="minhShopApp.bill.addressCode">Address Code</Translate>
                </Label>
                <AvField id="bill-addressCode" data-cy="addressCode" type="text" name="addressCode"/>
                <UncontrolledTooltip target="addressCodeLabel">
                  <Translate contentKey="minhShopApp.bill.help.addressCode"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="productLabel" for="bill-product">
                  <Translate contentKey="minhShopApp.bill.product">Product</Translate>
                </Label>
                <AvField
                  id="bill-product"
                  data-cy="product"
                  type="text"
                  name="product"
                  validate={{
                    maxLength: {value: 65535, errorMessage: translate('entity.validation.maxlength', {max: 65535})},
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="commentLabel" for="bill-comment">
                  <Translate contentKey="minhShopApp.bill.comment">Comment</Translate>
                </Label>
                <AvField
                  id="bill-comment"
                  data-cy="comment"
                  type="text"
                  name="comment"
                  validate={{
                    maxLength: {value: 2048, errorMessage: translate('entity.validation.maxlength', {max: 2048})},
                  }}
                />
                <UncontrolledTooltip target="commentLabel">
                  <Translate contentKey="minhShopApp.bill.help.comment"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="searchFieldLabel" for="bill-searchField">
                  <Translate contentKey="minhShopApp.bill.searchField">Search Field</Translate>
                </Label>
                <AvInput id="bill-searchField" data-cy="searchField" type="textarea" name="searchField"/>
                <UncontrolledTooltip target="searchFieldLabel">
                  <Translate contentKey="minhShopApp.bill.help.searchField"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="roleLabel" for="bill-role">
                  <Translate contentKey="minhShopApp.bill.role">Role</Translate>
                </Label>
                <AvField id="bill-role" data-cy="role" type="text" name="role"/>
                <UncontrolledTooltip target="roleLabel">
                  <Translate contentKey="minhShopApp.bill.help.role"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="bill-createdDate">
                  <Translate contentKey="minhShopApp.bill.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="bill-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.billEntity.createdDate)}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="minhShopApp.bill.help.createdDate"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="bill-modifiedDate">
                  <Translate contentKey="minhShopApp.bill.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="bill-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.billEntity.modifiedDate)}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="minhShopApp.bill.help.modifiedDate"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="bill-createdBy">
                  <Translate contentKey="minhShopApp.bill.createdBy">Created By</Translate>
                </Label>
                <AvField id="bill-createdBy" data-cy="createdBy" type="text" name="createdBy"/>
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.bill.help.createdBy"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="bill-modifiedBy">
                  <Translate contentKey="minhShopApp.bill.modifiedBy">Modified By</Translate>
                </Label>
                <AvField id="bill-modifiedBy" data-cy="modifiedBy" type="text" name="modifiedBy"/>
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.bill.help.modifiedBy"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="dataSizeLabel" for="bill-dataSize">
                  <Translate contentKey="minhShopApp.bill.dataSize">Data Size</Translate>
                </Label>
                <AvField id="bill-dataSize" data-cy="dataSize" type="string" className="form-control" name="dataSize"/>
                <UncontrolledTooltip target="dataSizeLabel">
                  <Translate contentKey="minhShopApp.bill.help.dataSize"/>
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label for="bill-userOtherInfo">
                  <Translate contentKey="minhShopApp.bill.userOtherInfo">User Other Info</Translate>
                </Label>
                <AvInput id="bill-userOtherInfo" data-cy="userOtherInfo" type="select" className="form-control" name="userOtherInfo.id">
                  <option value="" key="0"/>
                  {userOtherInfos
                    ? userOtherInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/bill" replace color="info">
                <FontAwesomeIcon icon="arrow-left"/>
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save"/>
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
  userOtherInfos: storeState.userOtherInfo.entities,
  payments: storeState.payment.entities,
  billEntity: storeState.bill.entity,
  loading: storeState.bill.loading,
  updating: storeState.bill.updating,
  updateSuccess: storeState.bill.updateSuccess,
});

const mapDispatchToProps = {
  getUserOtherInfos,
  getPayments,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillUpdate);
