package org.regitiny.minhshop.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

class SimplePostDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SimplePostDTO.class);
        SimplePostDTO simplePostDTO1 = new SimplePostDTO();
        simplePostDTO1.setId(1L);
        SimplePostDTO simplePostDTO2 = new SimplePostDTO();
        assertThat(simplePostDTO1).isNotEqualTo(simplePostDTO2);
        simplePostDTO2.setId(simplePostDTO1.getId());
        assertThat(simplePostDTO1).isEqualTo(simplePostDTO2);
        simplePostDTO2.setId(2L);
        assertThat(simplePostDTO1).isNotEqualTo(simplePostDTO2);
        simplePostDTO1.setId(null);
        assertThat(simplePostDTO1).isNotEqualTo(simplePostDTO2);
    }
}
