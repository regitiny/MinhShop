import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, UncontrolledTooltip } from 'reactstrap';
import { byteSize, openFile, TextFormat, Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './image.reducer';
import { APP_DATE_FORMAT } from 'app/config/constants';

export interface IImageDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ImageDetail = (props: IImageDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { imageEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="imageDetailsHeading">
          <Translate contentKey="minhShopApp.image.detail.title">Image</Translate> [<strong>{imageEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.image.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.image.help.uuid" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.uuid}</dd>
          <dt>
            <span id="imageData">
              <Translate contentKey="minhShopApp.image.imageData">Image Data</Translate>
            </span>
            <UncontrolledTooltip target="imageData">
              <Translate contentKey="minhShopApp.image.help.imageData" />
            </UncontrolledTooltip>
          </dt>
          <dd>
            {imageEntity.imageData ? (
              <div>
                {imageEntity.imageDataContentType ? (
                  <a onClick={openFile(imageEntity.imageDataContentType, imageEntity.imageData)}>
                    <img src={`data:${imageEntity.imageDataContentType};base64,${imageEntity.imageData}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {imageEntity.imageDataContentType}, {byteSize(imageEntity.imageData)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="nameImage">
              <Translate contentKey="minhShopApp.image.nameImage">Name Image</Translate>
            </span>
            <UncontrolledTooltip target="nameImage">
              <Translate contentKey="minhShopApp.image.help.nameImage" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.nameImage}</dd>
          <dt>
            <span id="extension">
              <Translate contentKey="minhShopApp.image.extension">Extension</Translate>
            </span>
            <UncontrolledTooltip target="extension">
              <Translate contentKey="minhShopApp.image.help.extension" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.extension}</dd>
          <dt>
            <span id="typeFile">
              <Translate contentKey="minhShopApp.image.typeFile">Type File</Translate>
            </span>
            <UncontrolledTooltip target="typeFile">
              <Translate contentKey="minhShopApp.image.help.typeFile" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.typeFile}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="minhShopApp.image.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="minhShopApp.image.help.searchField" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.searchField}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.image.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.image.help.role" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.image.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.image.help.createdDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.createdDate ? <TextFormat value={imageEntity.createdDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.image.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.image.help.modifiedDate" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.modifiedDate ? <TextFormat value={imageEntity.modifiedDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.image.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.image.help.createdBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.image.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.image.help.modifiedBy" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.modifiedBy}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="minhShopApp.image.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="minhShopApp.image.help.dataSize" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.dataSize}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.image.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.image.help.comment" />
            </UncontrolledTooltip>
          </dt>
          <dd>{imageEntity.comment}</dd>
        </dl>
        <Button tag={Link} to="/image" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/image/${imageEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ image }: IRootState) => ({
  imageEntity: image.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ImageDetail);
