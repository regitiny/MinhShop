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
import { getEntity, updateEntity, createEntity, reset } from './type-post-filter.reducer';
import { ITypePostFilter } from 'app/shared/model/type-post-filter.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITypePostFilterUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TypePostFilterUpdate = (props: ITypePostFilterUpdateProps) => {
  const [simplePostId, setSimplePostId] = useState('0');
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { typePostFilterEntity, simplePosts, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/type-post-filter');
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
        ...typePostFilterEntity,
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
          <h2 id="minhShopApp.typePostFilter.home.createOrEditLabel" data-cy="TypePostFilterCreateUpdateHeading">
            <Translate contentKey="minhShopApp.typePostFilter.home.createOrEditLabel">Create or edit a TypePostFilter</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : typePostFilterEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="type-post-filter-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="type-post-filter-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="uuidLabel" for="type-post-filter-uuid">
                  <Translate contentKey="minhShopApp.typePostFilter.uuid">Uuid</Translate>
                </Label>
                <AvField
                  id="type-post-filter-uuid"
                  data-cy="uuid"
                  type="text"
                  name="uuid"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="uuidLabel">
                  <Translate contentKey="minhShopApp.typePostFilter.help.uuid" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="typeFilterNameLabel" for="type-post-filter-typeFilterName">
                  <Translate contentKey="minhShopApp.typePostFilter.typeFilterName">Type Filter Name</Translate>
                </Label>
                <AvField
                  id="type-post-filter-typeFilterName"
                  data-cy="typeFilterName"
                  type="text"
                  name="typeFilterName"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="typeFilterNameLabel">
                  <Translate contentKey="minhShopApp.typePostFilter.help.typeFilterName" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="type-post-filter-createdDate">
                  <Translate contentKey="minhShopApp.typePostFilter.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="type-post-filter-createdDate"
                  data-cy="createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.typePostFilterEntity.createdDate)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="createdDateLabel">
                  <Translate contentKey="minhShopApp.typePostFilter.help.createdDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedDateLabel" for="type-post-filter-modifiedDate">
                  <Translate contentKey="minhShopApp.typePostFilter.modifiedDate">Modified Date</Translate>
                </Label>
                <AvInput
                  id="type-post-filter-modifiedDate"
                  data-cy="modifiedDate"
                  type="datetime-local"
                  className="form-control"
                  name="modifiedDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.typePostFilterEntity.modifiedDate)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="modifiedDateLabel">
                  <Translate contentKey="minhShopApp.typePostFilter.help.modifiedDate" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="createdByLabel" for="type-post-filter-createdBy">
                  <Translate contentKey="minhShopApp.typePostFilter.createdBy">Created By</Translate>
                </Label>
                <AvField
                  id="type-post-filter-createdBy"
                  data-cy="createdBy"
                  type="text"
                  name="createdBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="createdByLabel">
                  <Translate contentKey="minhShopApp.typePostFilter.help.createdBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <AvGroup>
                <Label id="modifiedByLabel" for="type-post-filter-modifiedBy">
                  <Translate contentKey="minhShopApp.typePostFilter.modifiedBy">Modified By</Translate>
                </Label>
                <AvField
                  id="type-post-filter-modifiedBy"
                  data-cy="modifiedBy"
                  type="text"
                  name="modifiedBy"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
                <UncontrolledTooltip target="modifiedByLabel">
                  <Translate contentKey="minhShopApp.typePostFilter.help.modifiedBy" />
                </UncontrolledTooltip>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/type-post-filter" replace color="info">
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
  typePostFilterEntity: storeState.typePostFilter.entity,
  loading: storeState.typePostFilter.loading,
  updating: storeState.typePostFilter.updating,
  updateSuccess: storeState.typePostFilter.updateSuccess,
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

export default connect(mapStateToProps, mapDispatchToProps)(TypePostFilterUpdate);
