package org.regitiny.minhshop.service.dto;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;

class PostDetailsDTOTest
{

  @Test
  void dtoEqualsVerifier() throws Exception
  {
    TestUtil.equalsVerifier(PostDetailsDTO.class);
    PostDetailsDTO postDetailsDTO1 = new PostDetailsDTO();
    postDetailsDTO1.setId(1L);
    PostDetailsDTO postDetailsDTO2 = new PostDetailsDTO();
    assertThat(postDetailsDTO1).isNotEqualTo(postDetailsDTO2);
    postDetailsDTO2.setId(postDetailsDTO1.getId());
    assertThat(postDetailsDTO1).isEqualTo(postDetailsDTO2);
    postDetailsDTO2.setId(2L);
    assertThat(postDetailsDTO1).isNotEqualTo(postDetailsDTO2);
    postDetailsDTO1.setId(null);
    assertThat(postDetailsDTO1).isNotEqualTo(postDetailsDTO2);
  }
}
