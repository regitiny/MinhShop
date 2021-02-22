package org.regitiny.minhshop.repository;

import org.regitiny.minhshop.domain.TypePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data SQL repository for the TypePost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypePostRepository extends JpaRepository<TypePost, Long>
{
  List<TypePost> findAllByOrderByCommentDesc();
}
