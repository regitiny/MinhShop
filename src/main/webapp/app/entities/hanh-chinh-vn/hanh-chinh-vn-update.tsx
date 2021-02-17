import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label, UncontrolledTooltip } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './hanh-chinh-vn.reducer';
import { IHanhChinhVN } from 'app/shared/model/hanh-chinh-vn.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IHanhChinhVNUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const HanhChinhVNUpdate = (props: IHanhChinhVNUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { hanhChinhVNEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/entity/hanh-chinh-vn');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...hanhChinhVNEntity,
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
          <h2 id="minhShopApp.hanhChinhVN.home.createOrEditLabel" data-cy="HanhChinhVNCreateUpdateHeading">
            <Translate contentKey="minhShopApp.hanhChinhVN.home.createOrEditLabel">Create or edit a HanhChinhVN</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : hanhChinhVNEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="hanh-chinh-vn-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="hanh-chinh-vn-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="hanh-chinh-vn-name">
                  <Translate contentKey="minhShopApp.hanhChinhVN.name">Name</Translate>
                </Label>
                <AvField
                  id="hanh-chinh-vn-name"
                  data-cy="name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="nameLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.name" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="slugLabel" for="hanh-chinh-vn-slug">
                  <Translate contentKey="minhShopApp.hanhChinhVN.slug">Slug</Translate>
                </Label>
                <AvField
                  id="hanh-chinh-vn-slug"
                  data-cy="slug"
                  type="text"
                  name="slug"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="slugLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.slug" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeLabel" for="hanh-chinh-vn-type">
                  <Translate contentKey="minhShopApp.hanhChinhVN.type">Type</Translate>
                </Label>
                <AvField
                  id="hanh-chinh-vn-type"
                  data-cy="type"
                  type="text"
                  name="type"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="typeLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.type" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="nameWithTypeLabel" for="hanh-chinh-vn-nameWithType">
                  <Translate contentKey="minhShopApp.hanhChinhVN.nameWithType">Name With Type</Translate>
                </Label>
                <AvField
                  id="hanh-chinh-vn-nameWithType"
                  data-cy="nameWithType"
                  type="text"
                  name="nameWithType"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="nameWithTypeLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.nameWithType" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="codeLabel" for="hanh-chinh-vn-code">
                  <Translate contentKey="minhShopApp.hanhChinhVN.code">Code</Translate>
                </Label>
                <AvField
                  id="hanh-chinh-vn-code"
                  data-cy="code"
                  type="text"
                  name="code"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="codeLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.code" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="parentCodeLabel" for="hanh-chinh-vn-parentCode">
                  <Translate contentKey="minhShopApp.hanhChinhVN.parentCode">Parent Code</Translate>
                </Label>
                <AvField
                  id="hanh-chinh-vn-parentCode"
                  data-cy="parentCode"
                  type="text"
                  name="parentCode"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="parentCodeLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.parentCode" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="pathLabel" for="hanh-chinh-vn-path">
                  <Translate contentKey="minhShopApp.hanhChinhVN.path">Path</Translate>
                </Label>
                <AvField id="hanh-chinh-vn-path" data-cy="path" type="text" name="path" />
                <UncontrolledTooltip target="pathLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.path" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="pathWithTypeLabel" for="hanh-chinh-vn-pathWithType">
                  <Translate contentKey="minhShopApp.hanhChinhVN.pathWithType">Path With Type</Translate>
                </Label>
                <AvField id="hanh-chinh-vn-pathWithType" data-cy="pathWithType" type="text" name="pathWithType" />
                <UncontrolledTooltip target="pathWithTypeLabel">
                  <Translate contentKey="minhShopApp.hanhChinhVN.help.pathWithType" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/hanh-chinh-vn" replace color="info">
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
  hanhChinhVNEntity: storeState.hanhChinhVN.entity,
  loading: storeState.hanhChinhVN.loading,
  updating: storeState.hanhChinhVN.updating,
  updateSuccess: storeState.hanhChinhVN.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(HanhChinhVNUpdate);
