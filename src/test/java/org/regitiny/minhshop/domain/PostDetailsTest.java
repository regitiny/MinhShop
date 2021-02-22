package org.regitiny.minhshop.domain;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;

class PostDetailsTest
{

  @Test
  void equalsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(PostDetails.class);
    PostDetails postDetails1 = new PostDetails();
    postDetails1.setId(1L);
    PostDetails postDetails2 = new PostDetails();
    postDetails2.setId(postDetails1.getId());
    assertThat(postDetails1).isEqualTo(postDetails2);
    postDetails2.setId(2L);
    assertThat(postDetails1).isNotEqualTo(postDetails2);
    postDetails1.setId(null);
    assertThat(postDetails1).isNotEqualTo(postDetails2);
  }
}
