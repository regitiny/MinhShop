package org.regitiny.minhshop.repository;

import org.regitiny.minhshop.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the Image entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ImageRepository extends JpaRepository<Image, Long>
{
  Optional<Image> findOneByNameImage(String nameImage);
}
