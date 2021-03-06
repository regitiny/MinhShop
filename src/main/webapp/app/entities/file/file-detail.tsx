import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {Link, RouteComponentProps} from 'react-router-dom';
import {Button, Col, Row, UncontrolledTooltip} from 'reactstrap';
import {byteSize, openFile, TextFormat, Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {getEntity} from './file.reducer';
import {APP_DATE_FORMAT} from 'app/config/constants';

export interface IFileDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const FileDetail = (props: IFileDetailProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const {fileEntity} = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fileDetailsHeading">
          <Translate contentKey="minhShopApp.file.detail.title">File</Translate> [<strong>{fileEntity.id}</strong>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="minhShopApp.file.uuid">Uuid</Translate>
            </span>
            <UncontrolledTooltip target="uuid">
              <Translate contentKey="minhShopApp.file.help.uuid"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.uuid}</dd>
          <dt>
            <span id="fileData">
              <Translate contentKey="minhShopApp.file.fileData">File Data</Translate>
            </span>
            <UncontrolledTooltip target="fileData">
              <Translate contentKey="minhShopApp.file.help.fileData"/>
            </UncontrolledTooltip>
          </dt>
          <dd>
            {fileEntity.fileData ? (
              <div>
                {fileEntity.fileDataContentType ? (
                  <a onClick={openFile(fileEntity.fileDataContentType, fileEntity.fileData)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {fileEntity.fileDataContentType}, {byteSize(fileEntity.fileData)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="nameFile">
              <Translate contentKey="minhShopApp.file.nameFile">Name File</Translate>
            </span>
            <UncontrolledTooltip target="nameFile">
              <Translate contentKey="minhShopApp.file.help.nameFile"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.nameFile}</dd>
          <dt>
            <span id="extension">
              <Translate contentKey="minhShopApp.file.extension">Extension</Translate>
            </span>
            <UncontrolledTooltip target="extension">
              <Translate contentKey="minhShopApp.file.help.extension"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.extension}</dd>
          <dt>
            <span id="typeFile">
              <Translate contentKey="minhShopApp.file.typeFile">Type File</Translate>
            </span>
            <UncontrolledTooltip target="typeFile">
              <Translate contentKey="minhShopApp.file.help.typeFile"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.typeFile}</dd>
          <dt>
            <span id="searchField">
              <Translate contentKey="minhShopApp.file.searchField">Search Field</Translate>
            </span>
            <UncontrolledTooltip target="searchField">
              <Translate contentKey="minhShopApp.file.help.searchField"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.searchField}</dd>
          <dt>
            <span id="role">
              <Translate contentKey="minhShopApp.file.role">Role</Translate>
            </span>
            <UncontrolledTooltip target="role">
              <Translate contentKey="minhShopApp.file.help.role"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.role}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="minhShopApp.file.createdDate">Created Date</Translate>
            </span>
            <UncontrolledTooltip target="createdDate">
              <Translate contentKey="minhShopApp.file.help.createdDate"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.createdDate ? <TextFormat value={fileEntity.createdDate} type="date" format={APP_DATE_FORMAT}/> : null}</dd>
          <dt>
            <span id="modifiedDate">
              <Translate contentKey="minhShopApp.file.modifiedDate">Modified Date</Translate>
            </span>
            <UncontrolledTooltip target="modifiedDate">
              <Translate contentKey="minhShopApp.file.help.modifiedDate"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.modifiedDate ? <TextFormat value={fileEntity.modifiedDate} type="date" format={APP_DATE_FORMAT}/> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="minhShopApp.file.createdBy">Created By</Translate>
            </span>
            <UncontrolledTooltip target="createdBy">
              <Translate contentKey="minhShopApp.file.help.createdBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.createdBy}</dd>
          <dt>
            <span id="modifiedBy">
              <Translate contentKey="minhShopApp.file.modifiedBy">Modified By</Translate>
            </span>
            <UncontrolledTooltip target="modifiedBy">
              <Translate contentKey="minhShopApp.file.help.modifiedBy"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.modifiedBy}</dd>
          <dt>
            <span id="dataSize">
              <Translate contentKey="minhShopApp.file.dataSize">Data Size</Translate>
            </span>
            <UncontrolledTooltip target="dataSize">
              <Translate contentKey="minhShopApp.file.help.dataSize"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.dataSize}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="minhShopApp.file.comment">Comment</Translate>
            </span>
            <UncontrolledTooltip target="comment">
              <Translate contentKey="minhShopApp.file.help.comment"/>
            </UncontrolledTooltip>
          </dt>
          <dd>{fileEntity.comment}</dd>
        </dl>
        <Button tag={Link} to="/file" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/file/${fileEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt"/>{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({file}: IRootState) => ({
  fileEntity: file.entity,
});

const mapDispatchToProps = {getEntity};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(FileDetail);
