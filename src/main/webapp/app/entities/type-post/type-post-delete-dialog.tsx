import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {RouteComponentProps} from 'react-router-dom';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {deleteEntity, getEntity} from './type-post.reducer';

export interface ITypePostDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const TypePostDeleteDialog = (props: ITypePostDeleteDialogProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () =>
  {
    props.history.push('/type-post');
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
    props.deleteEntity(props.typePostEntity.id);
  };

  const {typePostEntity} = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="typePostDeleteDialogHeading">
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="minhShopApp.typePost.delete.question">
        <Translate contentKey="minhShopApp.typePost.delete.question" interpolate={{id: typePostEntity.id}}>
          Are you sure you want to delete this TypePost?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban"/>
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-typePost" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash"/>
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({typePost}: IRootState) => ({
  typePostEntity: typePost.entity,
  updateSuccess: typePost.updateSuccess,
});

const mapDispatchToProps = {getEntity, deleteEntity};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TypePostDeleteDialog);
