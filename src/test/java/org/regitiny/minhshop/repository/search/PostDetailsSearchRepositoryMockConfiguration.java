package org.regitiny.minhshop.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link PostDetailsSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class PostDetailsSearchRepositoryMockConfiguration
{

  @MockBean
  private PostDetailsSearchRepository mockPostDetailsSearchRepository;
}
