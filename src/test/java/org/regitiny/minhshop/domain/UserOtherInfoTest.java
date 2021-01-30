package org.regitiny.minhshop.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.regitiny.minhshop.web.rest.TestUtil;

class UserOtherInfoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UserOtherInfo.class);
        UserOtherInfo userOtherInfo1 = new UserOtherInfo();
        userOtherInfo1.setId(1L);
        UserOtherInfo userOtherInfo2 = new UserOtherInfo();
        userOtherInfo2.setId(userOtherInfo1.getId());
        assertThat(userOtherInfo1).isEqualTo(userOtherInfo2);
        userOtherInfo2.setId(2L);
        assertThat(userOtherInfo1).isNotEqualTo(userOtherInfo2);
        userOtherInfo1.setId(null);
        assertThat(userOtherInfo1).isNotEqualTo(userOtherInfo2);
    }
}
