package org.regitiny.minhshop.repository;

import java.util.List;
import org.regitiny.minhshop.domain.TypePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypePost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypePostRepository extends JpaRepository<TypePost, Long> {
    List<TypePost> findAllByOrderByCommentDesc();
}
