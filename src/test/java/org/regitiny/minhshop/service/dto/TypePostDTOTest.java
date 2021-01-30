package org.regitiny.minhshop.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

class TypePostDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypePostDTO.class);
        TypePostDTO typePostDTO1 = new TypePostDTO();
        typePostDTO1.setId(1L);
        TypePostDTO typePostDTO2 = new TypePostDTO();
        assertThat(typePostDTO1).isNotEqualTo(typePostDTO2);
        typePostDTO2.setId(typePostDTO1.getId());
        assertThat(typePostDTO1).isEqualTo(typePostDTO2);
        typePostDTO2.setId(2L);
        assertThat(typePostDTO1).isNotEqualTo(typePostDTO2);
        typePostDTO1.setId(null);
        assertThat(typePostDTO1).isNotEqualTo(typePostDTO2);
    }
}
