package org.regitiny.minhshop.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

class TypePostFilterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypePostFilter.class);
        TypePostFilter typePostFilter1 = new TypePostFilter();
        typePostFilter1.setId(1L);
        TypePostFilter typePostFilter2 = new TypePostFilter();
        typePostFilter2.setId(typePostFilter1.getId());
        assertThat(typePostFilter1).isEqualTo(typePostFilter2);
        typePostFilter2.setId(2L);
        assertThat(typePostFilter1).isNotEqualTo(typePostFilter2);
        typePostFilter1.setId(null);
        assertThat(typePostFilter1).isNotEqualTo(typePostFilter2);
    }
}
