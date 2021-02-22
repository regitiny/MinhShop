package org.regitiny.minhshop.web.rest;

import org.regitiny.minhshop.service.TypePostFilterService;
import org.regitiny.minhshop.service.dto.TypePostFilterDTO;
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
 * REST controller for managing {@link org.regitiny.minhshop.domain.TypePostFilter}.
 */
@RestController
@RequestMapping("/api")
public class TypePostFilterResource
{

  private static final String ENTITY_NAME = "typePostFilter";
  private final Logger log = LoggerFactory.getLogger(TypePostFilterResource.class);
  private final TypePostFilterService typePostFilterService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public TypePostFilterResource(TypePostFilterService typePostFilterService)
  {
    this.typePostFilterService = typePostFilterService;
  }

  /**
   * {@code POST  /type-post-filters} : Create a new typePostFilter.
   *
   * @param typePostFilterDTO the typePostFilterDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typePostFilterDTO, or with status {@code 400 (Bad Request)} if the typePostFilter has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/type-post-filters")
  public ResponseEntity<TypePostFilterDTO> createTypePostFilter(@Valid @RequestBody TypePostFilterDTO typePostFilterDTO)
    throws URISyntaxException
  {
    log.debug("REST request to save TypePostFilter : {}", typePostFilterDTO);
    if (typePostFilterDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new typePostFilter cannot already have an ID", ENTITY_NAME, "idexists");
    }
    TypePostFilterDTO result = typePostFilterService.save(typePostFilterDTO);
    return ResponseEntity
      .created(new URI("/api/type-post-filters/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /type-post-filters} : Updates an existing typePostFilter.
   *
   * @param typePostFilterDTO the typePostFilterDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePostFilterDTO,
   * or with status {@code 400 (Bad Request)} if the typePostFilterDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the typePostFilterDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/type-post-filters")
  public ResponseEntity<TypePostFilterDTO> updateTypePostFilter(@Valid @RequestBody TypePostFilterDTO typePostFilterDTO)
    throws URISyntaxException
  {
    log.debug("REST request to update TypePostFilter : {}", typePostFilterDTO);
    if (typePostFilterDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    TypePostFilterDTO result = typePostFilterService.save(typePostFilterDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePostFilterDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /type-post-filters} : Updates given fields of an existing typePostFilter.
   *
   * @param typePostFilterDTO the typePostFilterDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePostFilterDTO,
   * or with status {@code 400 (Bad Request)} if the typePostFilterDTO is not valid,
   * or with status {@code 404 (Not Found)} if the typePostFilterDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the typePostFilterDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/type-post-filters", consumes = "application/merge-patch+json")
  public ResponseEntity<TypePostFilterDTO> partialUpdateTypePostFilter(@NotNull @RequestBody TypePostFilterDTO typePostFilterDTO)
    throws URISyntaxException
  {
    log.debug("REST request to update TypePostFilter partially : {}", typePostFilterDTO);
    if (typePostFilterDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    Optional<TypePostFilterDTO> result = typePostFilterService.partialUpdate(typePostFilterDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePostFilterDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /type-post-filters} : get all the typePostFilters.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typePostFilters in body.
   */
  @GetMapping("/type-post-filters")
  public ResponseEntity<List<TypePostFilterDTO>> getAllTypePostFilters(Pageable pageable)
  {
    log.debug("REST request to get a page of TypePostFilters");
    Page<TypePostFilterDTO> page = typePostFilterService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /type-post-filters/:id} : get the "id" typePostFilter.
   *
   * @param id the id of the typePostFilterDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typePostFilterDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/type-post-filters/{id}")
  public ResponseEntity<TypePostFilterDTO> getTypePostFilter(@PathVariable Long id)
  {
    log.debug("REST request to get TypePostFilter : {}", id);
    Optional<TypePostFilterDTO> typePostFilterDTO = typePostFilterService.findOne(id);
    return ResponseUtil.wrapOrNotFound(typePostFilterDTO);
  }

  /**
   * {@code DELETE  /type-post-filters/:id} : delete the "id" typePostFilter.
   *
   * @param id the id of the typePostFilterDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/type-post-filters/{id}")
  public ResponseEntity<Void> deleteTypePostFilter(@PathVariable Long id)
  {
    log.debug("REST request to delete TypePostFilter : {}", id);
    typePostFilterService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/type-post-filters?query=:query} : search for the typePostFilter corresponding
   * to the query.
   *
   * @param query    the query of the typePostFilter search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/type-post-filters")
  public ResponseEntity<List<TypePostFilterDTO>> searchTypePostFilters(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of TypePostFilters for query {}", query);
    Page<TypePostFilterDTO> page = typePostFilterService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
