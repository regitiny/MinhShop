package org.regitiny.minhshop.repository;

import org.regitiny.minhshop.domain.HanhChinhVN;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the HanhChinhVN entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HanhChinhVNRepository extends JpaRepository<HanhChinhVN, Long> {}
