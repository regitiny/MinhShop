import React,{useState} from 'react';
import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';

const base_path = '/home';

function Contact({children})
{
  const promises=[];
  const convertBase64 = (file) => {
    return new Promise((resolve, reject) => {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(file.image);
        fileReader.onload = () => {
          file.image.data=fileReader.result
          resolve(file.image);
        };
        window.console.log(file.image)
        fileReader.onerror = (error) => {
          reject(error);
        };
    });
  }
  const uploadImage = (event) => {
    const {files}=event.target;
    const newFiles=Object.keys(files).map(i=>({image:files[i]}));
    const base64 = newFiles.map(file=>promises.push(convertBase64(file)));
   ;
    Promise.all(promises)
      .then(response =>window.console.log(response))
  }

  return (
    <div>
      <BreadcrumbsItem glyph="calendar" to="/contact">
        Liên hệ
      </BreadcrumbsItem>
      {/*<Breadcrumbs*/}
      {/*  separator={<b> / </b>}*/}
      {/*  item={NavLink}*/}
      {/*  finalItem={'b'}*/}
      {/*  finalProps={{*/}
      {/*    style: {color: 'red'}*/}
      {/*  }}*/}
      {/*/>*/}
      <div>Đây là thông tin của trang web</div>
      <div className="d-flex justify-content-center">
        <input type="file" onChange={(event)=>uploadImage(event)}multiple/>
      </div>
    </div>
  );
}

export default Contact;
