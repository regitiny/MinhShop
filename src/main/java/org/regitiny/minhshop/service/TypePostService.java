package org.regitiny.minhshop.service;

import java.util.Optional;
import org.regitiny.minhshop.service.dto.TypePostDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.regitiny.minhshop.domain.TypePost}.
 */
public interface TypePostService {
    /**
     * Save a typePost.
     *
     * @param typePostDTO the entity to save.
     * @return the persisted entity.
     */
    TypePostDTO save(TypePostDTO typePostDTO);

    /**
     * Partially updates a typePost.
     *
     * @param typePostDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypePostDTO> partialUpdate(TypePostDTO typePostDTO);

    /**
     * Get all the typePosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypePostDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typePost.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypePostDTO> findOne(Long id);

    /**
     * Delete the "id" typePost.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the typePost corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypePostDTO> search(String query, Pageable pageable);
}
