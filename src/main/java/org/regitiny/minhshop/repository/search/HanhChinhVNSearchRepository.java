package org.regitiny.minhshop.repository.search;

import org.regitiny.minhshop.domain.HanhChinhVN;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link HanhChinhVN} entity.
 */
public interface HanhChinhVNSearchRepository extends ElasticsearchRepository<HanhChinhVN, Long>
{
}
