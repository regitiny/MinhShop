package org.regitiny.minhshop.service;

import org.json.JSONObject;
import org.regitiny.minhshop.service.dto.SimplePostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.regitiny.minhshop.domain.SimplePost}.
 */
public interface SimplePostService
{
  /**
   * Save a simplePost.
   *
   * @param simplePostDTO the entity to save.
   * @return the persisted entity.
   */
  SimplePostDTO save(SimplePostDTO simplePostDTO);


  /**
   * Partially updates a simplePost.
   *
   * @param simplePostDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<SimplePostDTO> partialUpdate(SimplePostDTO simplePostDTO);


  /**
   * Get all the simplePosts.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SimplePostDTO> findAll(Pageable pageable);


  /**
   * Get all the simplePosts with eager load of many-to-many relationships.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SimplePostDTO> findAllWithEagerRelationships(Pageable pageable);


  /**
   * Get the "id" simplePost.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<SimplePostDTO> findOne(Long id);


  /**
   * Delete the "id" simplePost.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);


  /**
   * Search for the simplePost corresponding to the query.
   *
   * @param query    the query of the search.
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<SimplePostDTO> search(String query, Pageable pageable);


  JSONObject getSimplePostsGroupByTypePost();


  /**
   * get list simplePost in typePost by id.
   *
   * @param typePost_Id id of TypePost.
   * @param pageable    the pagination information.
   * @return the list of entities.
   */
  Page<SimplePostDTO> getSimplePostsByTypePost_Id(Long typePost_Id, Pageable pageable);
}
