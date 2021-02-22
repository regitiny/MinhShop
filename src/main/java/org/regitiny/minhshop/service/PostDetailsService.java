package org.regitiny.minhshop.service;

import org.regitiny.minhshop.service.dto.PostDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link org.regitiny.minhshop.domain.PostDetails}.
 */
public interface PostDetailsService
{
  /**
   * Save a postDetails.
   *
   * @param postDetailsDTO the entity to save.
   * @return the persisted entity.
   */
  PostDetailsDTO save(PostDetailsDTO postDetailsDTO);


  /**
   * Partially updates a postDetails.
   *
   * @param postDetailsDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<PostDetailsDTO> partialUpdate(PostDetailsDTO postDetailsDTO);


  /**
   * Get all the postDetails.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PostDetailsDTO> findAll(Pageable pageable);


  /**
   * Get all the PostDetailsDTO where SimplePost is {@code null}.
   *
   * @return the {@link List} of entities.
   */
  List<PostDetailsDTO> findAllWhereSimplePostIsNull();


  /**
   * Get the "id" postDetails.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<PostDetailsDTO> findOne(Long id);


  /**
   * Delete the "id" postDetails.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);


  /**
   * Search for the postDetails corresponding to the query.
   *
   * @param query    the query of the search.
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<PostDetailsDTO> search(String query, Pageable pageable);
}
