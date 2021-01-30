package org.regitiny.minhshop.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TypePostMapperTest {

    private TypePostMapper typePostMapper;

    @BeforeEach
    public void setUp() {
        typePostMapper = new TypePostMapperImpl();
    }
}
