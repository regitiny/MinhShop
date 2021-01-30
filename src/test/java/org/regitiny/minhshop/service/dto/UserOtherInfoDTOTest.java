package org.regitiny.minhshop.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

class UserOtherInfoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserOtherInfoDTO.class);
        UserOtherInfoDTO userOtherInfoDTO1 = new UserOtherInfoDTO();
        userOtherInfoDTO1.setId(1L);
        UserOtherInfoDTO userOtherInfoDTO2 = new UserOtherInfoDTO();
        assertThat(userOtherInfoDTO1).isNotEqualTo(userOtherInfoDTO2);
        userOtherInfoDTO2.setId(userOtherInfoDTO1.getId());
        assertThat(userOtherInfoDTO1).isEqualTo(userOtherInfoDTO2);
        userOtherInfoDTO2.setId(2L);
        assertThat(userOtherInfoDTO1).isNotEqualTo(userOtherInfoDTO2);
        userOtherInfoDTO1.setId(null);
        assertThat(userOtherInfoDTO1).isNotEqualTo(userOtherInfoDTO2);
    }
}
