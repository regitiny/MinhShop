package org.regitiny.minhshop.repository;

import org.regitiny.minhshop.domain.UserOtherInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the UserOtherInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserOtherInfoRepository extends JpaRepository<UserOtherInfo, Long> {}
