package org.regitiny.minhshop.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserOtherInfoMapperTest {

    private UserOtherInfoMapper userOtherInfoMapper;

    @BeforeEach
    public void setUp() {
        userOtherInfoMapper = new UserOtherInfoMapperImpl();
    }
}
