package org.regitiny.tools.magic.exception;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class AuthException extends RuntimeException
{

  public AuthException(String roleName)
  {
    log.warn("you not has the role : {} ", roleName);
  }
}
