package org.regitiny.minhshop.service;

import org.regitiny.minhshop.service.dto.ImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.regitiny.minhshop.domain.Image}.
 */
public interface ImageService
{
  /**
   * Save a image.
   *
   * @param imageDTO the entity to save.
   * @return the persisted entity.
   */
  ImageDTO save(ImageDTO imageDTO);


  /**
   * Save a image.
   *
   * @param imageData the entity to save.
   * @return the persisted entity.
   */
  ImageDTO upload(MultipartFile imageData);


  /**
   * Partially updates a image.
   *
   * @param imageDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<ImageDTO> partialUpdate(ImageDTO imageDTO);


  /**
   * Get all the images.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<ImageDTO> findAll(Pageable pageable);


  /**
   * Get the "id" image.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<ImageDTO> findOne(Long id);


  /**
   * Get image by "nameImage".
   *
   * @param nameImage the id of the entity.
   * @return the entity.
   */
  Optional<ImageDTO> findByNameImage(String nameImage);


  /**
   * Delete the "id" image.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);


  /**
   * Search for the image corresponding to the query.
   *
   * @param query    the query of the search.
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<ImageDTO> search(String query, Pageable pageable);
}
