package org.regitiny.minhshop.web.rest;

import org.json.JSONObject;
import org.regitiny.minhshop.repository.SimplePostRepository;
import org.regitiny.minhshop.service.SimplePostService;
import org.regitiny.minhshop.service.dto.SimplePostDTO;
import org.regitiny.minhshop.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link org.regitiny.minhshop.domain.SimplePost}.
 */
@RestController
@RequestMapping("/api")
public class SimplePostResource
{

  private static final String ENTITY_NAME = "simplePost";
  private final Logger log = LoggerFactory.getLogger(SimplePostResource.class);
  private final SimplePostService simplePostService;
  private final SimplePostRepository simplePostRepository;
  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public SimplePostResource(SimplePostService simplePostService, SimplePostRepository simplePostRepository)
  {
    this.simplePostService = simplePostService;
    this.simplePostRepository = simplePostRepository;
  }

  /**
   * {@code POST  /simple-posts} : Create a new simplePost.
   *
   * @param simplePostDTO the simplePostDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new simplePostDTO, or with status {@code 400 (Bad Request)} if the simplePost has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/simple-posts")
  public ResponseEntity<SimplePostDTO> createSimplePost(@Valid @RequestBody SimplePostDTO simplePostDTO) throws URISyntaxException
  {
    log.debug("REST request to save SimplePost : {}", simplePostDTO);
    if (simplePostDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new simplePost cannot already have an ID", ENTITY_NAME, "idexists");
    }
    if (Objects.isNull(simplePostDTO.getPostDetails()))
    {
      throw new BadRequestAlertException("Invalid association value provided", ENTITY_NAME, "null");
    }
    SimplePostDTO result = simplePostService.save(simplePostDTO);
    return ResponseEntity
      .created(new URI("/api/simple-posts/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /simple-posts/:id} : Updates an existing simplePost.
   *
   * @param id            the id of the simplePostDTO to save.
   * @param simplePostDTO the simplePostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated simplePostDTO,
   * or with status {@code 400 (Bad Request)} if the simplePostDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the simplePostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/simple-posts/{id}")
  public ResponseEntity<SimplePostDTO> updateSimplePost(
    @PathVariable(value = "id", required = false) final Long id,
    @Valid @RequestBody SimplePostDTO simplePostDTO
  ) throws URISyntaxException
  {
    log.debug("REST request to update SimplePost : {}, {}", id, simplePostDTO);
    if (simplePostDTO.getId() == null)
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    if (!Objects.equals(id, simplePostDTO.getId()))
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");

    if (!simplePostRepository.existsById(id))
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");

    SimplePostDTO result = simplePostService.save(simplePostDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, simplePostDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /simple-posts/:id} : Partial updates given fields of an existing simplePost, field will ignore if it is null
   *
   * @param id            the id of the simplePostDTO to save.
   * @param simplePostDTO the simplePostDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated simplePostDTO,
   * or with status {@code 400 (Bad Request)} if the simplePostDTO is not valid,
   * or with status {@code 404 (Not Found)} if the simplePostDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the simplePostDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/simple-posts/{id}", consumes = "application/merge-patch+json")
  public ResponseEntity<SimplePostDTO> partialUpdateSimplePost(
    @PathVariable(value = "id", required = false) final Long id,
    @NotNull @RequestBody SimplePostDTO simplePostDTO
  ) throws URISyntaxException
  {
    log.debug("REST request to partial update SimplePost partially : {}, {}", id, simplePostDTO);
    if (simplePostDTO.getId() == null)
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    if (!Objects.equals(id, simplePostDTO.getId()))
      throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");

    if (!simplePostRepository.existsById(id))
      throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");

    Optional<SimplePostDTO> result = simplePostService.partialUpdate(simplePostDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, simplePostDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /simple-posts} : get all the simplePosts.
   *
   * @param pageable  the pagination information.
   * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of simplePosts in body.
   */
  @GetMapping("/simple-posts")
  public ResponseEntity<List<SimplePostDTO>> getAllSimplePosts(
    Pageable pageable,
    @RequestParam(required = false, defaultValue = "false") boolean eagerload
  )
  {
    log.debug("REST request to get a page of SimplePosts");
    Page<SimplePostDTO> page;
    if (eagerload)
    {
      page = simplePostService.findAllWithEagerRelationships(pageable);
    }
    else
    {
      page = simplePostService.findAll(pageable);
    }
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /simple-posts/:id} : get the "id" simplePost.
   *
   * @param id the id of the simplePostDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the simplePostDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/simple-posts/{id}")
  public ResponseEntity<SimplePostDTO> getSimplePost(@PathVariable Long id)
  {
    log.debug("REST request to get SimplePost : {}", id);
    Optional<SimplePostDTO> simplePostDTO = simplePostService.findOne(id);
    return ResponseUtil.wrapOrNotFound(simplePostDTO);
  }

  /**
   * {@code DELETE  /simple-posts/:id} : delete the "id" simplePost.
   *
   * @param id the id of the simplePostDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/simple-posts/{id}")
  public ResponseEntity<Void> deleteSimplePost(@PathVariable Long id)
  {
    log.debug("REST request to delete SimplePost : {}", id);
    simplePostService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/simple-posts?query=:query} : search for the simplePost corresponding
   * to the query.
   *
   * @param query    the query of the simplePost search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/simple-posts")
  public ResponseEntity<List<SimplePostDTO>> searchSimplePosts(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of SimplePosts for query {}", query);
    Page<SimplePostDTO> page = simplePostService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /simple-posts/by_type-post} : get SimplePosts GroupBy TypePost
   *
   * @return SimplePosts GroupBy TypePost
   */
  @GetMapping("/simple-posts/by_type-post")
  public ResponseEntity<String> getSimplePostsGroupByTypePost()
  {
    JSONObject resultJsonObject = simplePostService.getSimplePostsGroupByTypePost();
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(resultJsonObject.toString());
  }

  /**
   * {@code GET  /open/simple-posts?typePost_id=:id} : get a list of SimplePost by TypePost.id
   *
   * @param typePost_id the id of typePost.
   * @param pageable    the pagination information.
   * @return the result page of SimplePost.
   */
  @GetMapping("/open/simple-posts")
  public ResponseEntity<List<SimplePostDTO>> getSimplePostsByTypePost_Id(@RequestParam long typePost_id, Pageable pageable)
  {
    log.debug("REST request to search for a page of SimplePosts for query {}", typePost_id);
    Page<SimplePostDTO> page = simplePostService.getSimplePostsByTypePost_Id(typePost_id, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

}
