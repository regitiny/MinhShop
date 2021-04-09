import './laptop.scss';
import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {Storage} from 'react-jhipster';
import Cart from 'app/modules/shopcart/cart';
import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';
import {ProductViews} from 'app/page-product/product-history-view/product-view'
import HistoryView from "app/page-product/product-history-view/history-view";
import {Link} from 'react-router-dom';

export const LaptopDetail = props =>
{
  const [laptop, setLaptop] = useState(null);
  const [productView, setProductView] = useState(null);
  const page_path = props.match.url;
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
  const authToken = `Bearer ${Token}`;
  const urlPage = props.location.pathname
  window.console.log(props.match);
  useEffect(() =>
  {
    axios({
      url: `api/simple-posts/${props.match.params.id}`,
      method: 'get',
      headers: {
        Authorization: authToken,
      },
      // data:typeNameFil
    }).then(res => setLaptop(res.data));
  }, [urlPage]);
  window.console.log(props.match.params.id);
  const [count, setCount] = useState(1);
  const showSlides = () =>
  {
    if (count)
    {
      const slides = document.getElementsByClassName('image-laptop-detail');
      const dots = document.getElementsByClassName('img-thb');
      for (let i = 0; i < slides.length; i++)
      {
        const slide: any = slides[i];
        slide.style.display = 'none';
      }
      for (let i = 0; i < dots.length; i++)
      {
        dots[i].className = dots[i].className.replace(' active', '');
      }
      const slideBlock: any = slides[count - 1];
      slideBlock.style.display = 'block';
      dots[count - 1].className += ' active';
    }
  };
  const currentSlide = n =>
  {
    setCount(n);
    showSlides();
  };
  useEffect(() =>
  {
    currentSlide(count);
  });
  useEffect(() =>
  {
    if (laptop)
    {
      setProductView({
        id: laptop.id,
        name: laptop.title,
        image: laptop.imageUrl,
        url: location.pathname
      })
    }
  }, [laptop])
  const ec = encodeURIComponent(JSON.stringify(productView));
  const dc = decodeURIComponent('%5B%22424%22%2C%22307%22%2C%22426%22%5D')
  window.console.log(productView)
  window.console.log(ec)
  window.console.log(dc)

  // productViews.forEach(x =>
  // {
  //   if (x && x.id === laptopView.id)
  //   {
  //     alreadyExist = true;
  //   }
  // });
  // if (!alreadyExist && laptopView)
  // {
  //   window.console.log(!alreadyExist);
  //   productViews.push(laptopView);
  // }
  // window.console.log(productViews)
  // localStorage.setItem('product',JSON.stringify(productViews))
  useEffect(() =>
  {
    if (productView)
    {
      ProductViews(productView)
    }
  }, [productView])

  return (
    <div>
      {/*<div>*/}
      <BreadcrumbsItem to={location.pathname}>{laptop ? laptop.title : ''}</BreadcrumbsItem>
      {/*</div>*/}
      <div className="laptop-detail d-flex justify-content-center ">
        <div className=" laptop-detail-header d-xl-flex d-lg-flex col-9 ">
          <div className="image-laptop mt-3  col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6">
            <div className="image-laptop-detail">
              {laptop ? <img className="image-cover img-fluid" src={laptop.imageUrl} alt="product-detail"/> :
                <div></div>}
            </div>
            <div className="image-laptop-detail">
              <img
                className="image-cover img-fluid"
                src="https://www.asus.com/media/global/products/HYBtGIeaoACCt3lM/P_setting_fff_1_90_end_600.png"
                alt="product-detail"
              />
            </div>
            <div className="image-laptop-detail">
              <img
                className="image-cover img-fluid"
                src="https://www.asus.com/media/global/gallery/jg7rlmrw0kmdyrrj_setting_fff_1_90_end_500.png"
                alt="product-detail"
              />
            </div>
            <div className="list-image-thumbnail row mt-2">
              <div className="image-thumbnail img-1 pr-2 col-4 ">
                {laptop ? (
                  <img
                    className="img-thb image-cover img-fluid cursor"
                    onClick={() => currentSlide(1)}
                    src={laptop.imageUrl}
                    alt="product-detail"
                  />
                ) : (
                  <div></div>
                )}
              </div>
              <div className="image-thumbnail img-2 px-1 col-4">
                <img
                  className="img-thb image-cover img-fluid cursor"
                  onClick={() => currentSlide(2)}
                  src="https://www.asus.com/media/global/products/HYBtGIeaoACCt3lM/P_setting_fff_1_90_end_600.png"
                  alt="product-detail"
                />
              </div>
              <div className="image-thumbnail img-3 pl-2 col-4">
                <img
                  className="img-thb image-cover img-fluid cursor"
                  onClick={() => currentSlide(3)}
                  src="https://www.asus.com/media/global/gallery/jg7rlmrw0kmdyrrj_setting_fff_1_90_end_500.png"
                  alt="product-detail"
                />
              </div>
            </div>
          </div>
          <div className="product-description mt-3 col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6">
            {laptop ? <h2>{laptop.title}</h2> : ''}
            <hr/>
            <table className="col-12">
              <tbody>
              <tr>
                <th>
                  <strong>TÊN SẢN PHẨM</strong>
                </th>
                <td>Thùng gố đựng rượu 20 lít</td>
              </tr>
              <tr>
                <th rowSpan={2}>
                  <strong>KÍCH THƯỚC</strong>
                </th>
                <td>+ Mặt thùng: 30 cm</td>
              </tr>
              <tr>
                <td>+Thân thùng: 40 cm</td>
              </tr>
              <tr>
                <th>
                  <strong>PHỤ KIỆN</strong>
                </th>
                <td>01 Chân thùng (chưa có vòi triết rượu)</td>
              </tr>
              <tr>
                <th>
                  <strong>CHẤT LIỆU</strong>
                </th>
                <td>Gỗ sồi nhập khẩu</td>
              </tr>
              <tr>
                <th>
                  <strong>BẢO HÀNH</strong>
                </th>
                <td>12 tháng (lỗi nhà sản xuất)</td>
              </tr>
              <tr>
                <th>
                  <strong>PHỤ KIỆN</strong>
                </th>
                <td>01 Chân thùng (chưa có vòi triết rượu)</td>
              </tr>
              <tr>
                <th>
                  <strong>ĐỊA CHỈ</strong>
                </th>
                <td></td>
              </tr>
              <tr>
                <td>- Hà Nội</td>
                <td>Số 63/96 phố Đại Từ - Hoàng Mai</td>
              </tr>
              <tr>
                <td>- TPHCM</td>
                <td>Số 250 Lê Văn Khương - P. Thới An - Q.12</td>
              </tr>
              <tr>
                <td> - Xưởng SX</td>
                <td>Làng nghề Đọi Tam, Duy Tiên, Hà Nam</td>
              </tr>
              <tr>
                <th>
                  <strong>Hotline</strong>
                </th>
                <td>0327.247.999</td>
              </tr>
              </tbody>
            </table>
            <hr/>
            <div>
              {/*todo shop-cart*/}
              {laptop !== undefined && laptop !== null ? (
                <div className="laptop-detail-price">
                  <div className="price">
                    Giá gốc: <span className="text-danger">{laptop.price.toLocaleString()}đ</span>
                  </div>
                  <div className="sale-price">
                    Giá khuyến mãi: <span className="text-success">{laptop.salePrice.toLocaleString()}đ</span>
                  </div>
                  <Cart cartProductDetail={laptop} page_path={page_path}/>
                  {/*<Cart productestEntity={laptopDetail}/>*/}
                </div>
              ) : (
                '...loading'
              )}
            </div>
          </div>
        </div>
      </div>
      {/*<div className="product-detail-content">/!*<ProductDetailContent/>*!/</div>*/}
      <Link to='/page/lap-top/1102'>intro</Link>
      <HistoryView/>
    </div>
  );
};
export default LaptopDetail;

// const LaptopDetail=(props)=> {
//   const Token=Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');
//
//   const [laptops, setLaptops]=useState([]);
//   const authToken= `Bearer ${Token}`;
//   const typeNameFil={typeNameFilter:"Laptop chơi Game"}
//   const base_url =props.match.params.id; // todo đổi lại path trong router thành /:id mới lấy dc giá trị id
//   const paramUrl={title: props.match.params.title}
//
//   window.console.log(paramUrl)
//   useEffect(()=>{
//     axios({
//       url:`api/simple-posts/${base_url}`,
//       method:'get',
//       headers: {
//         Authorization: authToken,
//       },
//       // params: paramUrl,
//       data:null
//     })
//       .then(res=>setLaptops(res.data))
//   },[])
//   window.console.log(laptops)
//   return (
//     <div className="d-flex justify-content-center">
//       <div className="d-flex row col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
//         <h1>{props.match.params.id}</h1>
//         <SlideImage/>
//         {/*<img src={props.match.params.imageUrl} alt="máy tính"/>*/}
//       </div>
//
//     </div>
//   );
// }
//
// export default LaptopDetail;
