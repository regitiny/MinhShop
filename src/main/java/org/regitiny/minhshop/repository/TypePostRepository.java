package org.regitiny.minhshop.repository;

import org.regitiny.minhshop.domain.TypePost;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypePost entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypePostRepository extends JpaRepository<TypePost, Long> {}
