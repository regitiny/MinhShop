import './product-detail.scss';
import React, { useEffect, useState } from 'react';
import { ProductDetailContent } from './product-detail-content';

export const ProductDetail = () => {
  const [count, setCount] = useState(1);
  const showSlides = () => {
    if (count) {
      const slides = document.getElementsByClassName('image-product-detail');
      const dots = document.getElementsByClassName('img-thb');
      for (let i = 0; i < slides.length; i++) {
        const slide: any = slides[i];
        slide.style.display = 'none';
      }
      for (let i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(' active', '');
      }
      const slideBlock: any = slides[count - 1];
      slideBlock.style.display = 'block';
      dots[count - 1].className += ' active';
    }
  };
  const currentSlide = n => {
    setCount(n);
    showSlides();
  };
  useEffect(() => {
    currentSlide(count);
  });
  return (
    <div className="product-detail">
      <div className=" product-detail-header d-xl-flex d-lg-flex col-12">
        <div className="image-product mt-3  col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6">
          <div className="image-product-detail">
            <img className="image-cover img-fluid" src="/content/images/do_go_san_pham_1.png" alt="product-detail" />
          </div>
          <div className="image-product-detail">
            <img
              className="image-cover img-fluid"
              src="https://trongmanhhung.com/uploads/images/product/thung-ruou-go-soi/1.-thung-g-soi-dang-nam-20lit.jpg"
              alt="product-detail"
            />
          </div>
          <div className="image-product-detail">
            <img
              className="image-cover img-fluid"
              src="https://trongmanhhung.com/uploads/images/product/thung-ruou-go-soi/mua-thung-go-soi-ngam-ruou.jpg"
              alt="product-detail"
            />
          </div>
          <div className="list-image-thumbnail row mt-2">
            <div className="image-thumbnail img-1 pr-2 col-sm-12 col-md-6 col-lg-6 col-xl-4 ">
              <img
                className="img-thb image-cover img-fluid cursor"
                onClick={() => currentSlide(1)}
                src="/content/images/do_go_san_pham_1.png"
                alt="product-detail"
              />
            </div>
            <div className="image-thumbnail img-2 px-1 col-sm-12 col-md-6 col-lg-6 col-xl-4">
              <img
                className="img-thb image-cover img-fluid cursor"
                onClick={() => currentSlide(2)}
                src="https://trongmanhhung.com/uploads/images/product/thung-ruou-go-soi/1.-thung-g-soi-dang-nam-20lit.jpg"
                alt="product-detail"
              />
            </div>
            <div className="image-thumbnail img-3 pl-2 col-sm-12 col-md-6 col-lg-6 col-xl-4">
              <img
                className="img-thb image-cover img-fluid cursor"
                onClick={() => currentSlide(3)}
                src="https://trongmanhhung.com/uploads/images/product/thung-ruou-go-soi/mua-thung-go-soi-ngam-ruou.jpg"
                alt="product-detail"
              />
            </div>
          </div>
        </div>
        <div className="product-description mt-3 col-12 col-sm-12 col-md-12 col-lg-6 col-xl-6">
          <h5>SẢN PHẨM ĐỒ GỖ 1</h5>
          <hr />
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
          <hr />
          <div>{/*todo shop-cart*/}</div>
        </div>
      </div>
      <div className="product-detail-content">
        <ProductDetailContent />
      </div>
    </div>
  );
};
