package org.regitiny.minhshop.repository.search;

import org.regitiny.minhshop.domain.UserOtherInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserOtherInfo} entity.
 */
public interface UserOtherInfoSearchRepository extends ElasticsearchRepository<UserOtherInfo, Long> {}
