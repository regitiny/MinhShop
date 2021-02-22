package org.regitiny.minhshop.repository.search;

import org.regitiny.minhshop.domain.TypePostFilter;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TypePostFilter} entity.
 */
public interface TypePostFilterSearchRepository extends ElasticsearchRepository<TypePostFilter, Long>
{
}
