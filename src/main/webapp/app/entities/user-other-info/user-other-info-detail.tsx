import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, UncontrolledTooltip } from 'reactstrap';
import { TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './user-other-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IUserOtherInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const UserOtherInfoDetail = (props: IUserOtherInfoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { userOtherInfoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="userOtherInfoDetailsHeading">
          <Translate contentKey="minhShopApp.userOtherInfo.detail.title">UserOtherInfo</Translate> [
          <strong>{userOtherInfoEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.userOtherInfo.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.userOtherInfo.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.uuid}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="minhShopApp.userOtherInfo.phoneNumber">Phone Number</Translate>
            </span>
            <UncontrolledTooltip target="phoneNumber">
              <Translate contentKey="minhShopApp.userOtherInfo.help.phoneNumber" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.phoneNumber}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="minhShopApp.userOtherInfo.email">Email</Translate>
            </span>
            <UncontrolledTooltip target="email">
              <Translate contentKey="minhShopApp.userOtherInfo.help.email" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.email}</dd>
          <dt>
            <span id="wardCode">
              <Translate contentKey="minhShopApp.userOtherInfo.wardCode">Ward Code</Translate>
            </span>
            <UncontrolledTooltip target="wardCode">
              <Translate contentKey="minhShopApp.userOtherInfo.help.wardCode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.wardCode}</dd>
          <dt>
            <span id="distCode">
              <Translate contentKey="minhShopApp.userOtherInfo.distCode">Dist Code</Translate>
            </span>
            <UncontrolledTooltip target="distCode">
              <Translate contentKey="minhShopApp.userOtherInfo.help.distCode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.distCode}</dd>
          <dt>
            <span id="cityCode">
              <Translate contentKey="minhShopApp.userOtherInfo.cityCode">City Code</Translate>
            </span>
            <UncontrolledTooltip target="cityCode">
              <Translate contentKey="minhShopApp.userOtherInfo.help.cityCode" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.cityCode}</dd>
          <dt>
            <span id="addressDetails">
              <Translate contentKey="minhShopApp.userOtherInfo.addressDetails">Address Details</Translate>
            </span>
            <UncontrolledTooltip target="addressDetails">
              <Translate contentKey="minhShopApp.userOtherInfo.help.addressDetails" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.addressDetails}</dd>
          <dt>
            <span id="dateOfBirth">
              <Translate contentKey="minhShopApp.userOtherInfo.dateOfBirth">Date Of Birth</Translate>
            </span>
            <UncontrolledTooltip target="dateOfBirth">
              <Translate contentKey="minhShopApp.userOtherInfo.help.dateOfBirth" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {userOtherInfoEntity.dateOfBirth ? (
              <TextFormat value={userOtherInfoEntity.dateOfBirth} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="otherInfo">
              <Translate contentKey="minhShopApp.userOtherInfo.otherInfo">Other Info</Translate>
            </span>
            <UncontrolledTooltip target="otherInfo">
              <Translate contentKey="minhShopApp.userOtherInfo.help.otherInfo" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.otherInfo}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="minhShopApp.userOtherInfo.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="minhShopApp.userOtherInfo.help.searchField" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.searchField}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.userOtherInfo.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.userOtherInfo.help.role" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.userOtherInfo.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.userOtherInfo.help.createdDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {userOtherInfoEntity.createdDate ? (
              <TextFormat value={userOtherInfoEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.userOtherInfo.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.userOtherInfo.help.modifiedDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {userOtherInfoEntity.modifiedDate ? (
              <TextFormat value={userOtherInfoEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.userOtherInfo.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.userOtherInfo.help.createdBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.userOtherInfo.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.userOtherInfo.help.modifiedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.modifiedBy}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="minhShopApp.userOtherInfo.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="minhShopApp.userOtherInfo.help.dataSize" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.dataSize}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.userOtherInfo.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.userOtherInfo.help.comment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{userOtherInfoEntity.comment}</dd>
          <dt>
            <Translate contentKey="minhShopApp.userOtherInfo.userName">User Name</Translate>
          </dt>
          <dd>{userOtherInfoEntity.userName ? userOtherInfoEntity.userName.login : ''}</dd>
        </dl>
        <Button tag={Link} to="/user-other-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/user-other-info/${userOtherInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ userOtherInfo }: IRootState) => ({
  userOtherInfoEntity: userOtherInfo.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(UserOtherInfoDetail);
