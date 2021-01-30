package org.regitiny.minhshop.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link SimplePostSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class SimplePostSearchRepositoryMockConfiguration {

    @MockBean
    private SimplePostSearchRepository mockSimplePostSearchRepository;
}
