package org.regitiny.minhshop.repository;

import org.regitiny.minhshop.domain.PostDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the PostDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PostDetailsRepository extends JpaRepository<PostDetails, Long> {}
