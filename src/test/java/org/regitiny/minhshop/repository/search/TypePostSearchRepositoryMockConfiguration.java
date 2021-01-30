package org.regitiny.minhshop.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link TypePostSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class TypePostSearchRepositoryMockConfiguration {

    @MockBean
    private TypePostSearchRepository mockTypePostSearchRepository;
}
