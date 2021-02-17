package org.regitiny.tools.magic.exception;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.web.rest.errors.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;

@Log4j2
public class NhecException extends AbstractThrowableProblem {

    public NhecException(String defaultMessage) {
        log.error(defaultMessage);
    }

    public NhecException() {
        log.warn("đéo ổn bạn ơi . nhếch đang ngồi ở đây ");
    }
}
