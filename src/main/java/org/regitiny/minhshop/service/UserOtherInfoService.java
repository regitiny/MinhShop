package org.regitiny.minhshop.service;

import java.util.Optional;
import org.regitiny.minhshop.service.dto.UserOtherInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link org.regitiny.minhshop.domain.UserOtherInfo}.
 */
public interface UserOtherInfoService {
    /**
     * Save a userOtherInfo.
     *
     * @param userOtherInfoDTO the entity to save.
     * @return the persisted entity.
     */
    UserOtherInfoDTO save(UserOtherInfoDTO userOtherInfoDTO);

    /**
     * Partially updates a userOtherInfo.
     *
     * @param userOtherInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserOtherInfoDTO> partialUpdate(UserOtherInfoDTO userOtherInfoDTO);

    /**
     * Get all the userOtherInfos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserOtherInfoDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userOtherInfo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserOtherInfoDTO> findOne(Long id);

    /**
     * Delete the "id" userOtherInfo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the userOtherInfo corresponding to the query.
     *
     * @param query    the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserOtherInfoDTO> search(String query, Pageable pageable);
}
