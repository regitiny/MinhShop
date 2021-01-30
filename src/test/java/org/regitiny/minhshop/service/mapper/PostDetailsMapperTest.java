package org.regitiny.minhshop.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostDetailsMapperTest {

    private PostDetailsMapper postDetailsMapper;

    @BeforeEach
    public void setUp() {
        postDetailsMapper = new PostDetailsMapperImpl();
    }
}
