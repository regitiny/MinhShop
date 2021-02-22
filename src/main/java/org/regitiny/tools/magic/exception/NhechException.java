package org.regitiny.tools.magic.exception;

import lombok.extern.log4j.Log4j2;

/**
 * author: @yuvytung
 * cảm hứng từ mèo Nhếch đã tạo ra ngoại lệ của nhếch (NhechException).
 * tất cả các trường hợp code bẩn chưa xử lý sẽ throw NhechException để nhếch ngồi chồm chỗm trong code
 * <p>
 * xin lỗi nhếch xuất hiện ở đâu cũng sẽ chửi
 */
@Log4j2
public class NhechException extends RuntimeException
{

  public NhechException(String defaultMessage)
  {
    log.error(defaultMessage);
  }

  public NhechException()
  {
    log.warn("đéo ổn bạn ơi . nhếch đang ngồi ở đây ");
  }
}
