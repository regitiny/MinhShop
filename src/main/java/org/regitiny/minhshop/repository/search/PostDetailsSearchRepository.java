package org.regitiny.minhshop.repository.search;

import org.regitiny.minhshop.domain.PostDetails;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PostDetails} entity.
 */
public interface PostDetailsSearchRepository extends ElasticsearchRepository<PostDetails, Long>
{
}
