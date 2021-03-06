package org.regitiny.minhshop.repository.search;

import org.regitiny.minhshop.domain.SimplePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link SimplePost} entity.
 */
public interface SimplePostSearchRepository extends ElasticsearchRepository<SimplePost, Long>
{
  Page<SimplePost> findBySearchFieldOrSimpleContentOrPostDetailsContent(String searchField, Pageable pageable);
}
