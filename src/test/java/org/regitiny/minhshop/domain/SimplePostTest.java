package org.regitiny.minhshop.domain;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;

class SimplePostTest
{

  @Test
  void equalsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(SimplePost.class);
    SimplePost simplePost1 = new SimplePost();
    simplePost1.setId(1L);
    SimplePost simplePost2 = new SimplePost();
    simplePost2.setId(simplePost1.getId());
    assertThat(simplePost1).isEqualTo(simplePost2);
    simplePost2.setId(2L);
    assertThat(simplePost1).isNotEqualTo(simplePost2);
    simplePost1.setId(null);
    assertThat(simplePost1).isNotEqualTo(simplePost2);
  }
}
