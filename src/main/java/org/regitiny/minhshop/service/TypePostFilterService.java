package org.regitiny.minhshop.service;

import java.util.Optional;
import org.regitiny.minhshop.service.dto.TypePostFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.regitiny.minhshop.domain.TypePostFilter}.
 */
public interface TypePostFilterService {
    /**
     * Save a typePostFilter.
     *
     * @param typePostFilterDTO the entity to save.
     * @return the persisted entity.
     */
    TypePostFilterDTO save(TypePostFilterDTO typePostFilterDTO);

    /**
     * Partially updates a typePostFilter.
     *
     * @param typePostFilterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypePostFilterDTO> partialUpdate(TypePostFilterDTO typePostFilterDTO);

    /**
     * Get all the typePostFilters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypePostFilterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" typePostFilter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypePostFilterDTO> findOne(Long id);

    /**
     * Delete the "id" typePostFilter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the typePostFilter corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypePostFilterDTO> search(String query, Pageable pageable);
}
