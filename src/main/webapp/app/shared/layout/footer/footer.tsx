import './footer.scss';

import React from 'react';
import {NavLink} from 'reactstrap';
import {NavLink as Link} from 'react-router-dom';
import ChatZalo from './footer-component';
import {ScrollTop} from 'app/shared/layout/scroll/scroll';

const Footer = props =>
{
  return (
    <div>
      <div className="d-flex justify-content-center">
        <div className="footer page-content col-9">
          <div className="row">
            <div className="col-lg-4 ">
              <div className="mb-2">
                <NavLink to="/" tag={Link} className="d-flex justify-content-center">
                  <img className="img-footer img-fluid" width="100%" src="content/images/logo-shop.png" alt="Card image cap"/>
                </NavLink>
                <div>
                  <span>
                    Trống Mạnh Hùng có cơ ở sản xuất tại Làng nghề Đọi Tam - Hà Nam chuyên sản xuất các loại trống tường học,trống đội,
                    trống chùa, thùng rượu gỗ sồi, thùng gỗ trang trí, chậu gỗ ngâm chân, bồn tắm gỗ.. và các sản phẩm liên quan uy tín,
                    chất lượng.
                  </span>
                </div>
              </div>
            </div>
            <div className="col-lg-4">
              <div className="mt-5 mb-2">
                <div className="title ml-3 mb-2">ĐỨC HÒA SHOP</div>
                <div className="info ml-3">
                  <p>
                    <span>
                      <strong>1.Xưởng sản xuất</strong>
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>Địa chỉ:</strong> Thôn Đọi Tam, Đọi Sơn, Duy Tiên, Hà Nam.
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>Hoiline:</strong> 123456789
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>2.Cơ sở 1</strong>
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>Địa chỉ:</strong> Thôn Đọi Tam, Đọi Sơn, Duy Tiên, Hà Nam.
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>Hoiline:</strong> 123456789
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>3.Cơ sở 2</strong>
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>Địa chỉ:</strong> Thôn Đọi Tam, Đọi Sơn, Duy Tiên, Hà Nam.
                    </span>
                  </p>
                  <p>
                    <span>
                      <strong>Hoiline:</strong> 123456789
                    </span>
                  </p>
                </div>
              </div>
            </div>
            <div className="col-lg-4 mt-5 mb-2">
              <iframe
                src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15166.769750411268!2d105.87571617925717!3d21.229681325805107!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135047bc0614a7d%3A0x8df4c0918b0d724d!2zxJDhu6ljIEhvw6AsIFPDs2MgU8ahbiwgSMOgIE7hu5lpLCBWaeG7h3QgTmFt!5e1!3m2!1svi!2sus!4v1605716708504!5m2!1svi!2sus"
                width="100%"
                height="100%"
                frameBorder="0"
                style={{border: 0}}
                allowFullScreen
                aria-hidden="false"
              ></iframe>
            </div>
          </div>
        </div>
      </div>
      <div className="chat-zalo">
        <ChatZalo/>
      </div>
      <div className="scroll-top">
        <ScrollTop/>
      </div>
    </div>
  );
};

export default Footer;
