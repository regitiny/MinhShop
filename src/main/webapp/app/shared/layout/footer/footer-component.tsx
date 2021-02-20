import './footer.scss';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faFacebookSquare, faWeixin, faWhatsapp } from '@fortawesome/free-brands-svg-icons';
import React from 'react';

export const ChatZalo = props => {
  return (
    <div className="social-button d-none d-sm-block d-md-block d-lg-block d-xl-block">
      <div className="social-button-content">
        <a href="tel:0981481368" className="call-icon" rel="nofollow">
          <FontAwesomeIcon icon={faWhatsapp} size="3x" />
          <span>
            Hotline:
            <br /> 098 148 1368
          </span>
        </a>
        <a href="sms:0981481368" className="sms">
          <FontAwesomeIcon icon={faWeixin} size="3x" />
          <span>SMS: 098 148 1368</span>
        </a>
        <a href="https://www.facebook.com/Ngocthang.net/" className="mes">
          <FontAwesomeIcon icon={faFacebookSquare} size="3x" />
          <span>Nhắn tin Facebook</span>
        </a>
        <a href="http://zalo.me/0981481368" className="zalo">
          <img src="./../../../../content/images/zalo-icon.png" />
          <span>Zalo: 098.148.1368</span>
        </a>
      </div>
    </div>
  );
};
export default ChatZalo;
