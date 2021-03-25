import React, {useState, useEffect} from 'react';
import {Storage} from 'react-jhipster';
import axios from 'axios';


function Contact({children})
{
  const s4 = () =>
  {
    return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
  }
  const newID = () =>
  {
    return s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4()
  }
  const [baseImages, setBaseImages] = useState([]);
  const [dataImages, setDataImages] = useState([]);
  const [images, setImages] = useState([]);
  const [abc, setAbc] = useState(null)
  // const [aaa, setAaa] = useState(null)
  const [importLinks, setImportLink]=useState([]);
  const promises = [];
  const [link, setLink] = useState({link: '', id: ''})
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
  // const fetchUrl=(url)=>{
  //
  // }
  const onUpload = (file, name) =>
  {
    const filename = name;
    const urlUpload = new Promise(
      (resolve, reject) =>
      {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/images/upload');
        xhr.setRequestHeader('Authorization', authToken);
        const data = new FormData();
        data.append('imageDataFile', file, filename);
        window.console.log(data)
        xhr.send(data);
        xhr.addEventListener('load', () =>
        {
          const response = JSON.parse(xhr.responseText);
          dataImages.push({link: response.link, id: newID()}) // todo add ảnh vào mảng dataImages
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
    urlUpload.then(response => setAbc(response)) //todo chỉ để render hiển thị hình ảnh thôi
  }
  // abc?dataImages.push({link: abc.link,id: newID()}):dataImages
  const onHandleChange = (event) =>
  {
    setLink({link: event.target.value, id: newID()})
  }


  const onSubmitInput = () =>
  {
    if (link.link) //todo link trong dataImages do server trả về luôn khác nhau và khác link nhập vào input không như push link từ input vào dataImages nên cần làm cách khác
    {
      let alreadyExist = false;
      // dataImages.map(item =>
      // {
      //   if (item.link === link.link)
      //   {
      //     alreadyExist = true;
      //     alert('Bạn đã nhập đường link này trước đó')
      //   }
      // })
      if(importLinks && importLinks.length>0){
        importLinks.map(importLink=>{
          if (link.link === importLink.link)
          {
            alreadyExist = true;
            alert('Bạn đã nhập đường link này trước đó')
          }
        })
      }
      window.console.log(alreadyExist)
      if (!alreadyExist)
      {
        if(link.link.slice(0,10)==="data:image") {
          axios.get(`${link.link}`, {
            responseType: 'blob'
          }).then(res => {
            const resType = res.headers['content-type']
            const tail=resType.split("/")[1]
            window.console.log(resType)
            if (res.status === 200 && resType.indexOf('image') >= 0)
            {
              const url=`${newID()}.${tail}`
              onUpload(res.data, url),
                // setAaa(res.data)
              window.console.log(res.status)
            }
            else alert('Link ảnh không đúng. Vui lòng kiểm tra lại')
          })
        }
        else axios.get(`https://cors-anywhere.froala.com/${link.link}`, {
          responseType: 'blob'
        })
          .then(res => {
            const resType = res.headers['content-type']
            const tail=resType.split("/")[1]
            window.console.log(resType)
            window.console.log(tail)
            if (res.status === 200 && resType.indexOf('image') >= 0)
            {
              const totalUrl = res.config.url.split('https://cors-anywhere.froala.com/');
              window.console.log(totalUrl)
              if(totalUrl[1].indexOf('.jpg')===-1 && totalUrl[1].indexOf('.jepg')===-1 && totalUrl[1].indexOf('.png')===-1 && totalUrl[1].indexOf('.gif')===-1){
                const addUrl=`${totalUrl[1]}.${tail}`
                onUpload(res.data, addUrl)
              }
              else {
                const url=totalUrl[1];
                onUpload(res.data, url)
              }
            }
            else alert('Link ảnh không đúng. Vui lòng kiểm tra lại')
          })
      }

      // fetchUrl(link.link)
      // fetch(`https://cors-anywhere.froala.com/${link.link}`)
      //   .then(response=>response.blob())
      //   .then(data=>{onUpload(data),window.console.log(data)})
    }
    importLinks.push(link);
    setLink({link: '', id: ''})
  }
  window.console.log(importLinks)

const uploadImage = async (event) =>
{
  const {files} = event.target;
  const newFiles = Object.keys(files).map(i => ({image: files[i]}));
  const fileData = newFiles.map(item => promises.push(upload(item.image)))
  await Promise.all(promises).then(
    res =>
    {
      res && res.length > 0 ? res.map(item =>
      {
        const newItem = {link: item.link, id: newID()};
        dataImages.push(newItem)
      }) : dataImages,
        setBaseImages(res)
    }
  )
}

// const dataImage=link.link?baseImages.push(link):baseImages


window.console.log(baseImages)

// const dataImage=[...baseImages, link]

// useEffect(()=>{
//   setDataImages(dataImages)
// },[dataImages])

window.console.log(dataImages)

const onDeleteImage = (id) =>
{
  const array = dataImages.slice().filter(x => x.id !== id);

  setDataImages(array)
}

const showImage = () =>
{
  let result = null;
  if (dataImages && dataImages.length > 0)
  {
    result = dataImages.map((image, index) =>
    {
      window.console.log(image.link)
      return (
        <div key={index} className='d-flex'>
          <div><img src={image.link} style={{width: 300, display: 'block'}} alt='link ảnh không đúng'/></div>
          <div className="mt-5">
            <button type="button" className="btn btn-danger" onClick={() => onDeleteImage(image.id)}>Xóa</button>
          </div>
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
          <input type="text" onChange={onHandleChange} name="link" value={link.link} placeholder="Nhập link ảnh ở đây..."/>
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

