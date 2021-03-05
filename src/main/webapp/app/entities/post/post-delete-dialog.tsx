import React, {useEffect} from 'react';
import {connect} from 'react-redux';
import {RouteComponentProps} from 'react-router-dom';
import {Button, Modal, ModalBody, ModalFooter, ModalHeader} from 'reactstrap';
import {Translate} from 'react-jhipster';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';

import {IRootState} from 'app/shared/reducers';
import {deleteEntity, getEntity} from './post.reducer';

export interface IPostDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }>
{
}

export const PostDeleteDialog = (props: IPostDeleteDialogProps) =>
{
  useEffect(() =>
  {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () =>
  {
    props.history.push('/entity/post');
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
    props.deleteEntity(props.postEntity.id);
  };

  const {postEntity} = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose} data-cy="postDeleteDialogHeading">
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="minhShopApp.post.delete.question">
        <Translate contentKey="minhShopApp.post.delete.question" interpolate={{id: postEntity.id}}>
          Are you sure you want to delete this Post?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban"/>
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-post" data-cy="entityConfirmDeleteButton" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash"/>
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({post}: IRootState) => ({
  postEntity: post.entity,
  updateSuccess: post.updateSuccess,
});

const mapDispatchToProps = {getEntity, deleteEntity};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PostDeleteDialog);
