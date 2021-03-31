import React from 'react';
import {Button} from 'reactstrap';
import {Storage} from 'react-jhipster';
import axios from 'axios';

export const ServerManagement = (props) =>
{
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const authToken = `Bearer ${Token}`;
  const onResetElasticSearch = () =>
  {
    axios({
      method: 'put',
      url: ' /api/management/admin/database/elasticsearch/syncs/reindex-all',
      headers: {Authorization: authToken}
    })
      .then(res => window.console.log(res.data))
  }
  return (
    <div className="d-flex justify-content-center">
      <div className="col-9">
        <h3>Quản lý server</h3>
        <div>
          <b>Reset Elastic Search:</b>
          <Button type="button" onClick={onResetElasticSearch} className='btn btn-warning text-light ml-3'>Reset</Button>
        </div>
      </div>
    </div>
  );
}

export default ServerManagement;