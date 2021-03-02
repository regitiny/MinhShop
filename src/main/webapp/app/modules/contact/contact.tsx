// import React, {useState} from 'react';
// import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';
// import axios from 'axios'
//
// const base_path = '/home';
//
// function Contact({children})
// {
//   const [baseImage, setBaseImage] = useState([])
//   const promises = [];
//   const convertBase64 = (file) =>
//   {
//     return new Promise((resolve, reject) =>
//     {
//       const fileReader = new FileReader();
//       fileReader.readAsDataURL(file.image);
//       fileReader.onload = () =>
//       {
//         file.image.data = fileReader.result
//         resolve(file.image);
//       };
//       window.console.log(file.image)
//       fileReader.onerror = (error) =>
//       {
//         reject(error);
//       };
//     });
//   }
//
//   const UploadImage = (event) =>
//   {
//     const {files} = event.target;
//     const newFiles = Object.keys(files).map(i => ({image: files[i]}));
//     const base64 = newFiles.map(file => promises.push(convertBase64(file)));
//     Promise.all(promises)
//       .then(response => {
//         axios({
//           method: 'post',
//           url: 'https://api/fileanhs',
//           headers: {Authorization: 'Client-ID c166b3ccc22b789'},
//           data: JSON.stringify(response)
//         }).then(res => window.console.log(res.data));
//         setBaseImage(response)
//       })
//     window.console.log(baseImage)
//   }
//   return (
//     <div>
//       <BreadcrumbsItem glyph="calendar" to="/contact">
//         Liên hệ
//       </BreadcrumbsItem>
//       {/*<Breadcrumbs*/}
//       {/*  separator={<b> / </b>}*/}
//       {/*  item={NavLink}*/}
//       {/*  finalItem={'b'}*/}
//       {/*  finalProps={{*/}
//       {/*    style: {color: 'red'}*/}
//       {/*  }}*/}
//       {/*/>*/}
//       <div>Đây là thông tin của trang web</div>
//       <div className="d-flex justify-content-center">
//         <div>
//           <div>
//             <input type="file" onChange={(event) => UploadImage(event)} multiple/>
//           </div>
//           {baseImage.map(item => (
//             <div key={item.name}>
//               <img src={item.data} style={{width: 300}}/>
//             </div>
//           ))}
//         </div>
//       </div>
//     </div>
//   );
// }
//
// export default Contact;

import React, {useState, useEffect} from 'react';
import {Storage} from 'react-jhipster';
import axios from 'axios';


function Contact({children})
{
  const s4=()=>{
    return Math.floor((1+Math.random()) *0x10000).toString(16).substring(1);
  }
  const newID=()=>{
    return s4() + '-' + s4() + '-' + s4()  + '-' + s4() + s4() + '-' + s4() + '-' + s4()  + '-' + s4()
  }
  const [baseImages, setBaseImages] = useState([]);
  const [dataImages, setDataImages] = useState([]);
  const [images, setImages]=useState([]);
  const promises = [];
  const [link, setLink] = useState({link: '',id: ''})
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const authToken = `Bearer ${Token}`;

  // const onHandleChange=(event)=>{
  //     setLink({link: event.target.value})
  // }
  const upload = (file) =>
  {
    return new Promise(
      (resolve, reject) =>
      {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/images/upload');
        xhr.setRequestHeader('Authorization', authToken);
        const data = new FormData();
        data.append('imageDataFile', file);
        xhr.send(data);
        xhr.addEventListener('load', () =>
        {
          const response = JSON.parse(xhr.responseText);
          window.console.log(response)
          resolve(response);
        });
        xhr.addEventListener('error', () =>
        {
          const error = JSON.parse(xhr.responseText);
          window.console.log(error)
          reject(error);
        });
      }
    );
  }
  const uploadImage = async (event) =>
  {
    const {files} = event.target;
    const newFiles = Object.keys(files).map(i => ({image: files[i]}));
    const fileData = newFiles.map(file => promises.push(upload(file.image)))
    await Promise.all(promises).then(
      res =>
      {
        res && res.length > 0 ? res.map(item => { const newItem={link: item.link, id: newID()};dataImages.push(newItem)}) : dataImages,
          setBaseImages(res)
      }
    )
  }
  // const dataImage=link.link?baseImages.push(link):baseImages
  const onHandleChange = (event) =>
  {
    setLink({link: event.target.value, id: newID()})
  }

  const onSubmitInput = () =>
  {
    if (link.link)
    {
      let alreadyExist = false;
      dataImages.map(item =>
      {
        if (item.link === link.link)
        {
          alreadyExist = true;
          alert('Bạn đã nhập đường link này trước đó')
        }
      })
      window.console.log(alreadyExist)
      if (!alreadyExist)
      {
        dataImages.push(link)
      }
    }
    setLink({link:'', id:''})
  }

  window.console.log(baseImages)

  // const dataImage=[...baseImages, link]

  // useEffect(()=>{
  //   setDataImages(dataImages)
  // },[dataImages])

  window.console.log(dataImages)

  const onDeleteImage=(id)=>{
    const array=dataImages.slice().filter(x => x.id !== id);

    setDataImages(array)
  }

  const showImage = () =>
  {
    let result = null;
    if (dataImages && dataImages.length > 0)
    {
      result = dataImages.map((image, index) =>{
        window.console.log(image.link)
        return(
          <div key={index} className='d-flex'>
            <div><img src={image.link} style={{width: 300, display:'block'}}/></div>
            <div className="mt-5"><button type="button" className="btn btn-danger" onClick={()=>onDeleteImage(image.id)}>Xóa</button></div>
          </div>
        )
      })
    }
    return result
  }

  const onSubmit = (event) =>
  {
    event.preventDefault()
    axios({
      method: "post",
      url: "http://localhost:4001/messages",
      data: dataImages
    }).then(res => setImages(res.data))
  }

  window.console.log(images)
return (
  <div>
    <div>Đây là thông tin của trang web</div>
    <div className="d-flex justify-content-center">
      <form onSubmit={onSubmit}>
        <div>
          <input type="text" onChange={onHandleChange} name="link" value={link.link}/>
          <button type='button' onClick={onSubmitInput}>Insert</button>
        </div>
        <div>
          <input type="file" onChange={(event) => uploadImage(event)} multiple/>
        </div>
        {/*{dataImages &&dataImages.length>0?baseImages.map((dataImage,index)=>(*/}
        {/*  <div key={index}>*/}
        {/*    <img src={dataImage.link} style={{width: 300}} />*/}
        {/*  </div>*/}
        {/*)):('không có ảnh nào ở đây')}*/}
        {showImage()}
        {/*<button type="button" onClick={showImage}>showImage</button>*/}
        <button type="submit">SEND</button>
      </form>
    </div>
  </div>
);
}

export default Contact;

