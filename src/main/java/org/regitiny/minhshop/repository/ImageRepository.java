package org.regitiny.minhshop.repository;

import java.util.Optional;
import org.regitiny.minhshop.domain.Image;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Image entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findOneByNameImage(String nameImage);
}
