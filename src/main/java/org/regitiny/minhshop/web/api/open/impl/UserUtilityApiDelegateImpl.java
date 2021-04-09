package org.regitiny.minhshop.web.api.open.impl;

import org.regitiny.minhshop.service.SimplePostService;
import org.regitiny.minhshop.web.api.open.UserUtilityApi;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.math.BigDecimal;

public class UserUtilityApiDelegateImpl implements UserUtilityApi
{
  public final SimplePostService simplePostService;

  public UserUtilityApiDelegateImpl(SimplePostService simplePostService)
  {
    this.simplePostService = simplePostService;
  }

  @Override
  public ResponseEntity<Object> userRatingProfuct(
    @Valid String productId, @Valid BigDecimal scores)
  {

    return null;
  }
}
