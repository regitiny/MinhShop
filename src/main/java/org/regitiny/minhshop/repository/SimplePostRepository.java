package org.regitiny.minhshop.repository;

import java.util.List;
import java.util.Optional;
import org.regitiny.minhshop.domain.SimplePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the SimplePost entity.
 */
@Repository
public interface SimplePostRepository extends JpaRepository<SimplePost, Long> {
    @Query(
        value = "select distinct simplePost from SimplePost simplePost left join fetch simplePost.typePostFilters",
        countQuery = "select count(distinct simplePost) from SimplePost simplePost"
    )
    Page<SimplePost> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct simplePost from SimplePost simplePost left join fetch simplePost.typePostFilters")
    List<SimplePost> findAllWithEagerRelationships();

    @Query("select simplePost from SimplePost simplePost left join fetch simplePost.typePostFilters where simplePost.id =:id")
    Optional<SimplePost> findOneWithEagerRelationships(@Param("id") Long id);

    List<SimplePost> findAllByTypePostId(Long ids);
}
