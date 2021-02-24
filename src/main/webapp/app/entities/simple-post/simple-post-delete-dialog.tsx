import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {RouteComponentProps} from 'react-router-dom';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {deleteEntity, getEntity} from './simple-post.reducer';

export interface ISimplePostDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const SimplePostDeleteDialog = (props: ISimplePostDeleteDialogProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () =>
  {
    props.history.push('/simple-post');
  };

  useEffect(() =>
  {
    if (props.updateSuccess)
    {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () =>
  {
    props.deleteEntity(props.simplePostEntity.id);
  };

  const {simplePostEntity} = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="simplePostDeleteDialogHeading">
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="minhShopApp.simplePost.delete.question">
        <Translate contentKey="minhShopApp.simplePost.delete.question" interpolate={{id: simplePostEntity.id}}>
          Are you sure you want to delete this SimplePost?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban"/>
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-simplePost" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash"/>
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({simplePost}: IRootState) => ({
  simplePostEntity: simplePost.entity,
  updateSuccess: simplePost.updateSuccess,
});

const mapDispatchToProps = {getEntity, deleteEntity};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SimplePostDeleteDialog);
