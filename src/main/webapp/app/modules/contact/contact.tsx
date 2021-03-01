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

import React, {useState} from 'react';
import axios from 'axios'

function Contact({children})
{
  const [baseImages, setBaseImages] = useState([])
  const promises = [];

  const upload=(file)=>{
    return new Promise(
      (resolve, reject) => {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/api/fileanhs');
        // xhr.setRequestHeader('Authorization', 'Client-ID c166b3ccc22b789');
        const data = new FormData();
        data.append('img', file);
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
  function uploadImage(event) {
    const {files}=event.target;
    const newFiles = Object.keys(files).map(i => ({image: files[i]}));
    const fileData=newFiles.map(file=>promises.push(upload(file.image)))
    Promise.all(promises).then(res=>setBaseImages(res))

  }
  window.console.log(baseImages)
  return (
    <div>
      <div>Đây là thông tin của trang web</div>
      <div className="d-flex justify-content-center">
        <div>
          <div>
            <input type="file" onChange={(event) => uploadImage(event)} multiple/>
          </div>
          {baseImages &&baseImages.length>0?baseImages.map((baseImage,index)=>(
            <div key={index}>
              <img src={baseImage.link} style={{width: 300}} />
            </div>
          )):('không có ảnh nào ở đây')}
        </div>
      </div>
    </div>
  );
}

export default Contact;

