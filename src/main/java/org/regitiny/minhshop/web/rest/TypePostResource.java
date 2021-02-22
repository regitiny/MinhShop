package org.regitiny.minhshop.web.rest;

import org.regitiny.minhshop.service.TypePostService;
import org.regitiny.minhshop.service.dto.TypePostDTO;
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
 * REST controller for managing {@link org.regitiny.minhshop.domain.TypePost}.
 */
@RestController
@RequestMapping("/api")
public class TypePostResource
{

  private static final String ENTITY_NAME = "typePost";
  private final Logger log = LoggerFactory.getLogger(TypePostResource.class);
  private final TypePostService typePostService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public TypePostResource(TypePostService typePostService)
  {
    this.typePostService = typePostService;
  }

  /**
   * {@code POST  /type-posts} : Create a new typePost.
   *
   * @param typePostDTO the typePostDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typePostDTO, or with status {@code 400 (Bad Request)} if the typePost has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/type-posts")
  public ResponseEntity<TypePostDTO> createTypePost(@Valid @RequestBody TypePostDTO typePostDTO) throws URISyntaxException
  {
    log.debug("REST request to save TypePost : {}", typePostDTO);
    if (typePostDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new typePost cannot already have an ID", ENTITY_NAME, "idexists");
    }
    TypePostDTO result = typePostService.save(typePostDTO);
    return ResponseEntity
      .created(new URI("/api/type-posts/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /type-posts} : Updates an existing typePost.
   *
   * @param typePostDTO the typePostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePostDTO,
   * or with status {@code 400 (Bad Request)} if the typePostDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the typePostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/type-posts")
  public ResponseEntity<TypePostDTO> updateTypePost(@Valid @RequestBody TypePostDTO typePostDTO) throws URISyntaxException
  {
    log.debug("REST request to update TypePost : {}", typePostDTO);
    if (typePostDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    TypePostDTO result = typePostService.save(typePostDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePostDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /type-posts} : Updates given fields of an existing typePost.
   *
   * @param typePostDTO the typePostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typePostDTO,
   * or with status {@code 400 (Bad Request)} if the typePostDTO is not valid,
   * or with status {@code 404 (Not Found)} if the typePostDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the typePostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/type-posts", consumes = "application/merge-patch+json")
  public ResponseEntity<TypePostDTO> partialUpdateTypePost(@NotNull @RequestBody TypePostDTO typePostDTO) throws URISyntaxException
  {
    log.debug("REST request to update TypePost partially : {}", typePostDTO);
    if (typePostDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    Optional<TypePostDTO> result = typePostService.partialUpdate(typePostDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typePostDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /type-posts} : get all the typePosts.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typePosts in body.
   */
  @GetMapping("/type-posts")
  public ResponseEntity<List<TypePostDTO>> getAllTypePosts(Pageable pageable)
  {
    log.debug("REST request to get a page of TypePosts");
    Page<TypePostDTO> page = typePostService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /type-posts/:id} : get the "id" typePost.
   *
   * @param id the id of the typePostDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typePostDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/type-posts/{id}")
  public ResponseEntity<TypePostDTO> getTypePost(@PathVariable Long id)
  {
    log.debug("REST request to get TypePost : {}", id);
    Optional<TypePostDTO> typePostDTO = typePostService.findOne(id);
    return ResponseUtil.wrapOrNotFound(typePostDTO);
  }

  /**
   * {@code DELETE  /type-posts/:id} : delete the "id" typePost.
   *
   * @param id the id of the typePostDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/type-posts/{id}")
  public ResponseEntity<Void> deleteTypePost(@PathVariable Long id)
  {
    log.debug("REST request to delete TypePost : {}", id);
    typePostService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/type-posts?query=:query} : search for the typePost corresponding
   * to the query.
   *
   * @param query    the query of the typePost search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/type-posts")
  public ResponseEntity<List<TypePostDTO>> searchTypePosts(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of TypePosts for query {}", query);
    Page<TypePostDTO> page = typePostService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
