package org.regitiny.minhshop.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

class TypePostFilterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypePostFilterDTO.class);
        TypePostFilterDTO typePostFilterDTO1 = new TypePostFilterDTO();
        typePostFilterDTO1.setId(1L);
        TypePostFilterDTO typePostFilterDTO2 = new TypePostFilterDTO();
        assertThat(typePostFilterDTO1).isNotEqualTo(typePostFilterDTO2);
        typePostFilterDTO2.setId(typePostFilterDTO1.getId());
        assertThat(typePostFilterDTO1).isEqualTo(typePostFilterDTO2);
        typePostFilterDTO2.setId(2L);
        assertThat(typePostFilterDTO1).isNotEqualTo(typePostFilterDTO2);
        typePostFilterDTO1.setId(null);
        assertThat(typePostFilterDTO1).isNotEqualTo(typePostFilterDTO2);
    }
}
