package org.regitiny.minhshop.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimplePostMapperTest {

    private SimplePostMapper simplePostMapper;

    @BeforeEach
    public void setUp() {
        simplePostMapper = new SimplePostMapperImpl();
    }
}
