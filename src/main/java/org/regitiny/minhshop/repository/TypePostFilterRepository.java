package org.regitiny.minhshop.repository;

import org.regitiny.minhshop.domain.TypePostFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the TypePostFilter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypePostFilterRepository extends JpaRepository<TypePostFilter, Long> {}
