import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './bill.reducer';
import {APP_DATE_FORMAT} from 'app/config/constants';

export interface IBillDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const BillDetail = (props: IBillDetailProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const {billEntity} = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="billDetailsHeading">
          <Translate contentKey="minhShopApp.bill.detail.title">Bill</Translate> [<strong>{billEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.bill.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.bill.help.uuid"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.uuid}</dd>
          <dt>
            <span id="billId">
              <Translate contentKey="minhShopApp.bill.billId">Bill Id</Translate>
            </span>
            <UncontrolledTooltip target="billId">
              <Translate contentKey="minhShopApp.bill.help.billId"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.billId}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="minhShopApp.bill.phoneNumber">Phone Number</Translate>
            </span>
            <UncontrolledTooltip target="phoneNumber">
              <Translate contentKey="minhShopApp.bill.help.phoneNumber"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.phoneNumber}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="minhShopApp.bill.email">Email</Translate>
            </span>
            <UncontrolledTooltip target="email">
              <Translate contentKey="minhShopApp.bill.help.email"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.email}</dd>
          <dt>
            <span id="addressDetails">
              <Translate contentKey="minhShopApp.bill.addressDetails">Address Details</Translate>
            </span>
            <UncontrolledTooltip target="addressDetails">
              <Translate contentKey="minhShopApp.bill.help.addressDetails"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.addressDetails}</dd>
          <dt>
            <span id="addressCode">
              <Translate contentKey="minhShopApp.bill.addressCode">Address Code</Translate>
            </span>
            <UncontrolledTooltip target="addressCode">
              <Translate contentKey="minhShopApp.bill.help.addressCode"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.addressCode}</dd>
          <dt>
            <span id="product">
              <Translate contentKey="minhShopApp.bill.product">Product</Translate>
            </span>
          </dt>
          <dd>{billEntity.product}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.bill.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.bill.help.comment"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.comment}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="minhShopApp.bill.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="minhShopApp.bill.help.searchField"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.searchField}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.bill.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.bill.help.role"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.bill.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.bill.help.createdDate"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.createdDate ? <TextFormat value={billEntity.createdDate} type="date" format={APP_DATE_FORMAT}/> : null}</dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.bill.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.bill.help.modifiedDate"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.modifiedDate ? <TextFormat value={billEntity.modifiedDate} type="date" format={APP_DATE_FORMAT}/> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.bill.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.bill.help.createdBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.bill.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.bill.help.modifiedBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.modifiedBy}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="minhShopApp.bill.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="minhShopApp.bill.help.dataSize"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{billEntity.dataSize}</dd>
          <dt>
            <Translate contentKey="minhShopApp.bill.userOtherInfo">User Other Info</Translate>
          </dt>
          <dd>{billEntity.userOtherInfo ? billEntity.userOtherInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/bill" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bill/${billEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({bill}: IRootState) => ({
  billEntity: bill.entity,
});

const mapDispatchToProps = {getEntity};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BillDetail);
