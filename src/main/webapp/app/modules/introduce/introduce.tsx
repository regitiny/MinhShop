import React from 'react';
import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';
import {Link} from 'react-router-dom';
import {Storage} from 'react-jhipster';
import axios from 'axios';
import HistoryView from "app/page-product/product-history-view/history-view";

const base_path = '/';

function Introduce({children})
{
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const authToken = `Bearer ${Token}`;
  const promises=[];

  function uploadImage(file) {
    // window.console.log(event.target.value)
    // const file=event.target.value;
    return new Promise(
      (resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/images/upload');
        xhr.setRequestHeader('Authorization', authToken);
        const data = new FormData();
        data.append('imageDataFile', file);
        xhr.send(data);
        xhr.addEventListener('load', () => {
          const response = JSON.parse(xhr.responseText);
          window.console.log(response)
          resolve(response);
        });
        xhr.addEventListener('error', () => {
          const error = JSON.parse(xhr.responseText);
          window.console.log(error)
          reject(error);
        });
      }
    );
  }

  const onSubmitUrl=()=>{
    // axios({
    //   method: 'get',
    //   headers: { 'Content-Type': 'multipart/form-data' },
    //   url:'https://cors-anywhere.froala.com/https://muagame.vn/cdn1/images/202006/goods_img/may-ps5-gia-re-P1349-1591901295661.jpg',
    //   data: null,
    // }).then(res=>{uploadImage(res.data),
    //   window.console.log(new FileReader().readAsArrayBuffer(res.data))
    // })

    fetch('https://cors-anywhere.froala.com/https://muagame.vn/cdn1/images/202006/goods_img/may-ps5-gia-re-P1349-1591901295661.jpg')
      .then(response=>response.blob())
      .then(data=> {uploadImage(data), window.console.log(data)})
  }
  return (
    <div>
      <BreadcrumbsItem glyph="calendar" to="/introduce">
        Introduce
      </BreadcrumbsItem>
      {/*<Breadcrumbs*/}
      {/*  separator={<b> / </b>}*/}
      {/*  item={NavLink}*/}
      {/*  finalItem={'b'}*/}
      {/*  finalProps={{*/}
      {/*    style: {color: 'red'}*/}
      {/*  }}*/}
      {/*/>*/}
      {/*<input type='text' onChange={(event)=>uploadImage(event)} style={{margin: 200}}/>*/}
      <div>Đây là thông tin của trang web</div>
      <button type='button' onClick={onSubmitUrl} className="btn btn-danger" style={{margin: 200}}>Send</button>
      <HistoryView/>
    </div>
  );
}

export default Introduce;
