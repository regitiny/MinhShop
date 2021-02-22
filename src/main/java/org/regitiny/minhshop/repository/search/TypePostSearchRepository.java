package org.regitiny.minhshop.repository.search;

import org.regitiny.minhshop.domain.TypePost;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link TypePost} entity.
 */
public interface TypePostSearchRepository extends ElasticsearchRepository<TypePost, Long>
{
}
