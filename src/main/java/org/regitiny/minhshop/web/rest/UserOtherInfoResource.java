package org.regitiny.minhshop.web.rest;

import org.regitiny.minhshop.service.UserOtherInfoService;
import org.regitiny.minhshop.service.dto.UserOtherInfoDTO;
import org.regitiny.minhshop.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.regitiny.minhshop.domain.UserOtherInfo}.
 */
@RestController
@RequestMapping("/api")
public class UserOtherInfoResource
{

  private static final String ENTITY_NAME = "userOtherInfo";
  private final Logger log = LoggerFactory.getLogger(UserOtherInfoResource.class);
  private final UserOtherInfoService userOtherInfoService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public UserOtherInfoResource(UserOtherInfoService userOtherInfoService)
  {
    this.userOtherInfoService = userOtherInfoService;
  }

  /**
   * {@code POST  /user-other-infos} : Create a new userOtherInfo.
   *
   * @param userOtherInfoDTO the userOtherInfoDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userOtherInfoDTO, or with status {@code 400 (Bad Request)} if the userOtherInfo has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/user-other-infos")
  public ResponseEntity<UserOtherInfoDTO> createUserOtherInfo(@Valid @RequestBody UserOtherInfoDTO userOtherInfoDTO)
    throws URISyntaxException
  {
    log.debug("REST request to save UserOtherInfo : {}", userOtherInfoDTO);
    if (userOtherInfoDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new userOtherInfo cannot already have an ID", ENTITY_NAME, "idexists");
    }
    UserOtherInfoDTO result = userOtherInfoService.save(userOtherInfoDTO);
    return ResponseEntity
      .created(new URI("/api/user-other-infos/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /user-other-infos} : Updates an existing userOtherInfo.
   *
   * @param userOtherInfoDTO the userOtherInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userOtherInfoDTO,
   * or with status {@code 400 (Bad Request)} if the userOtherInfoDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the userOtherInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/user-other-infos")
  public ResponseEntity<UserOtherInfoDTO> updateUserOtherInfo(@Valid @RequestBody UserOtherInfoDTO userOtherInfoDTO)
    throws URISyntaxException
  {
    log.debug("REST request to update UserOtherInfo : {}", userOtherInfoDTO);
    if (userOtherInfoDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    UserOtherInfoDTO result = userOtherInfoService.save(userOtherInfoDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userOtherInfoDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /user-other-infos} : Updates given fields of an existing userOtherInfo.
   *
   * @param userOtherInfoDTO the userOtherInfoDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userOtherInfoDTO,
   * or with status {@code 400 (Bad Request)} if the userOtherInfoDTO is not valid,
   * or with status {@code 404 (Not Found)} if the userOtherInfoDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the userOtherInfoDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/user-other-infos", consumes = "application/merge-patch+json")
  public ResponseEntity<UserOtherInfoDTO> partialUpdateUserOtherInfo(@NotNull @RequestBody UserOtherInfoDTO userOtherInfoDTO)
    throws URISyntaxException
  {
    log.debug("REST request to update UserOtherInfo partially : {}", userOtherInfoDTO);
    if (userOtherInfoDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    Optional<UserOtherInfoDTO> result = userOtherInfoService.partialUpdate(userOtherInfoDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, userOtherInfoDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /user-other-infos} : get all the userOtherInfos.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userOtherInfos in body.
   */
  @GetMapping("/user-other-infos")
  public ResponseEntity<List<UserOtherInfoDTO>> getAllUserOtherInfos(Pageable pageable)
  {
    log.debug("REST request to get a page of UserOtherInfos");
    Page<UserOtherInfoDTO> page = userOtherInfoService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /user-other-infos/:id} : get the "id" userOtherInfo.
   *
   * @param id the id of the userOtherInfoDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userOtherInfoDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/user-other-infos/{id}")
  public ResponseEntity<UserOtherInfoDTO> getUserOtherInfo(@PathVariable Long id)
  {
    log.debug("REST request to get UserOtherInfo : {}", id);
    Optional<UserOtherInfoDTO> userOtherInfoDTO = userOtherInfoService.findOne(id);
    return ResponseUtil.wrapOrNotFound(userOtherInfoDTO);
  }

  /**
   * {@code DELETE  /user-other-infos/:id} : delete the "id" userOtherInfo.
   *
   * @param id the id of the userOtherInfoDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/user-other-infos/{id}")
  public ResponseEntity<Void> deleteUserOtherInfo(@PathVariable Long id)
  {
    log.debug("REST request to delete UserOtherInfo : {}", id);
    userOtherInfoService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/user-other-infos?query=:query} : search for the userOtherInfo corresponding
   * to the query.
   *
   * @param query    the query of the userOtherInfo search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/user-other-infos")
  public ResponseEntity<List<UserOtherInfoDTO>> searchUserOtherInfos(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of UserOtherInfos for query {}", query);
    Page<UserOtherInfoDTO> page = userOtherInfoService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
