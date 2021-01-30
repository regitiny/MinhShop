package org.regitiny.minhshop.repository.search;

import org.regitiny.minhshop.domain.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link File} entity.
 */
public interface FileSearchRepository extends ElasticsearchRepository<File, Long> {}
