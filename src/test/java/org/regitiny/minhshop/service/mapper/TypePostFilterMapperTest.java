package org.regitiny.minhshop.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TypePostFilterMapperTest {

    private TypePostFilterMapper typePostFilterMapper;

    @BeforeEach
    public void setUp() {
        typePostFilterMapper = new TypePostFilterMapperImpl();
    }
}
