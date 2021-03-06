package org.regitiny.minhshop.web.rest;

import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.dto.FileDTO;
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
 * REST controller for managing {@link org.regitiny.minhshop.domain.File}.
 */
@RestController
@RequestMapping("/api")
public class FileResource
{

  private static final String ENTITY_NAME = "file";
  private final Logger log = LoggerFactory.getLogger(FileResource.class);
  private final FileService fileService;
  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public FileResource(FileService fileService)
  {
    this.fileService = fileService;
  }

  /**
   * {@code POST  /files} : Create a new file.
   *
   * @param fileDTO the fileDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileDTO, or with status {@code 400 (Bad Request)} if the file has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/files")
  public ResponseEntity<FileDTO> createFile(@Valid @RequestBody FileDTO fileDTO) throws URISyntaxException
  {
    log.debug("REST request to save File : {}", fileDTO);
    if (fileDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new file cannot already have an ID", ENTITY_NAME, "idexists");
    }
    FileDTO result = fileService.save(fileDTO);
    return ResponseEntity
      .created(new URI("/api/files/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /files} : Updates an existing file.
   *
   * @param fileDTO the fileDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileDTO,
   * or with status {@code 400 (Bad Request)} if the fileDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the fileDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/files")
  public ResponseEntity<FileDTO> updateFile(@Valid @RequestBody FileDTO fileDTO) throws URISyntaxException
  {
    log.debug("REST request to update File : {}", fileDTO);
    if (fileDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    FileDTO result = fileService.save(fileDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /files} : Updates given fields of an existing file.
   *
   * @param fileDTO the fileDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileDTO,
   * or with status {@code 400 (Bad Request)} if the fileDTO is not valid,
   * or with status {@code 404 (Not Found)} if the fileDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the fileDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/files", consumes = "application/merge-patch+json")
  public ResponseEntity<FileDTO> partialUpdateFile(@NotNull @RequestBody FileDTO fileDTO) throws URISyntaxException
  {
    log.debug("REST request to update File partially : {}", fileDTO);
    if (fileDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    Optional<FileDTO> result = fileService.partialUpdate(fileDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fileDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /files} : get all the files.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of files in body.
   */
  @GetMapping("/files")
  public ResponseEntity<List<FileDTO>> getAllFiles(Pageable pageable)
  {
    log.debug("REST request to get a page of Files");
    Page<FileDTO> page = fileService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /files/:id} : get the "id" file.
   *
   * @param id the id of the fileDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/files/{id}")
  public ResponseEntity<FileDTO> getFile(@PathVariable Long id)
  {
    log.debug("REST request to get File : {}", id);
    Optional<FileDTO> fileDTO = fileService.findOne(id);
    return ResponseUtil.wrapOrNotFound(fileDTO);
  }

  /**
   * {@code DELETE  /files/:id} : delete the "id" file.
   *
   * @param id the id of the fileDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/files/{id}")
  public ResponseEntity<Void> deleteFile(@PathVariable Long id)
  {
    log.debug("REST request to delete File : {}", id);
    fileService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/files?query=:query} : search for the file corresponding
   * to the query.
   *
   * @param query    the query of the file search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/files")
  public ResponseEntity<List<FileDTO>> searchFiles(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of Files for query {}", query);
    Page<FileDTO> page = fileService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
