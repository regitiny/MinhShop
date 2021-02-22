package org.regitiny.minhshop.domain;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;

class TypePostTest
{

  @Test
  void equalsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(TypePost.class);
    TypePost typePost1 = new TypePost();
    typePost1.setId(1L);
    TypePost typePost2 = new TypePost();
    typePost2.setId(typePost1.getId());
    assertThat(typePost1).isEqualTo(typePost2);
    typePost2.setId(2L);
    assertThat(typePost1).isNotEqualTo(typePost2);
    typePost1.setId(null);
    assertThat(typePost1).isNotEqualTo(typePost2);
  }
}
