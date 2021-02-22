package org.regitiny.minhshop.web.rest;

import org.regitiny.minhshop.service.PostDetailsService;
import org.regitiny.minhshop.service.dto.PostDetailsDTO;
import org.regitiny.minhshop.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
 * REST controller for managing {@link org.regitiny.minhshop.domain.PostDetails}.
 */
@RestController
@RequestMapping("/api")
public class PostDetailsResource
{

  private static final String ENTITY_NAME = "postDetails";
  private final Logger log = LoggerFactory.getLogger(PostDetailsResource.class);
  private final PostDetailsService postDetailsService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public PostDetailsResource(PostDetailsService postDetailsService)
  {
    this.postDetailsService = postDetailsService;
  }

  /**
   * {@code POST  /post-details} : Create a new postDetails.
   *
   * @param postDetailsDTO the postDetailsDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postDetailsDTO, or with status {@code 400 (Bad Request)} if the postDetails has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/post-details")
  public ResponseEntity<PostDetailsDTO> createPostDetails(@Valid @RequestBody PostDetailsDTO postDetailsDTO) throws URISyntaxException
  {
    log.debug("REST request to save PostDetails : {}", postDetailsDTO);
    if (postDetailsDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new postDetails cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PostDetailsDTO result = postDetailsService.save(postDetailsDTO);
    return ResponseEntity
      .created(new URI("/api/post-details/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /post-details} : Updates an existing postDetails.
   *
   * @param postDetailsDTO the postDetailsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postDetailsDTO,
   * or with status {@code 400 (Bad Request)} if the postDetailsDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the postDetailsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/post-details")
  public ResponseEntity<PostDetailsDTO> updatePostDetails(@Valid @RequestBody PostDetailsDTO postDetailsDTO) throws URISyntaxException
  {
    log.debug("REST request to update PostDetails : {}", postDetailsDTO);
    if (postDetailsDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    PostDetailsDTO result = postDetailsService.save(postDetailsDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postDetailsDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /post-details} : Updates given fields of an existing postDetails.
   *
   * @param postDetailsDTO the postDetailsDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postDetailsDTO,
   * or with status {@code 400 (Bad Request)} if the postDetailsDTO is not valid,
   * or with status {@code 404 (Not Found)} if the postDetailsDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the postDetailsDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/post-details", consumes = "application/merge-patch+json")
  public ResponseEntity<PostDetailsDTO> partialUpdatePostDetails(@NotNull @RequestBody PostDetailsDTO postDetailsDTO)
    throws URISyntaxException
  {
    log.debug("REST request to update PostDetails partially : {}", postDetailsDTO);
    if (postDetailsDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    Optional<PostDetailsDTO> result = postDetailsService.partialUpdate(postDetailsDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postDetailsDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /post-details} : get all the postDetails.
   *
   * @param pageable the pagination information.
   * @param filter   the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postDetails in body.
   */
  @GetMapping("/post-details")
  public ResponseEntity<List<PostDetailsDTO>> getAllPostDetails(Pageable pageable, @RequestParam(required = false) String filter)
  {
    if ("simplepost-is-null".equals(filter))
    {
      log.debug("REST request to get all PostDetailss where simplePost is null");
      return new ResponseEntity<>(postDetailsService.findAllWhereSimplePostIsNull(), HttpStatus.OK);
    }
    log.debug("REST request to get a page of PostDetails");
    Page<PostDetailsDTO> page = postDetailsService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /post-details/:id} : get the "id" postDetails.
   *
   * @param id the id of the postDetailsDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postDetailsDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/post-details/{id}")
  public ResponseEntity<PostDetailsDTO> getPostDetails(@PathVariable Long id)
  {
    log.debug("REST request to get PostDetails : {}", id);
    Optional<PostDetailsDTO> postDetailsDTO = postDetailsService.findOne(id);
    return ResponseUtil.wrapOrNotFound(postDetailsDTO);
  }

  /**
   * {@code DELETE  /post-details/:id} : delete the "id" postDetails.
   *
   * @param id the id of the postDetailsDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/post-details/{id}")
  public ResponseEntity<Void> deletePostDetails(@PathVariable Long id)
  {
    log.debug("REST request to delete PostDetails : {}", id);
    postDetailsService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/post-details?query=:query} : search for the postDetails corresponding
   * to the query.
   *
   * @param query    the query of the postDetails search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/post-details")
  public ResponseEntity<List<PostDetailsDTO>> searchPostDetails(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of PostDetails for query {}", query);
    Page<PostDetailsDTO> page = postDetailsService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
