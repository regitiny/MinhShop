package org.regitiny.minhshop.web.rest;

import org.regitiny.minhshop.service.BillService;
import org.regitiny.minhshop.service.dto.BillDTO;
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
 * REST controller for managing {@link org.regitiny.minhshop.domain.Bill}.
 */
@RestController
@RequestMapping("/api")
public class BillResource
{

  private static final String ENTITY_NAME = "bill";
  private final Logger log = LoggerFactory.getLogger(BillResource.class);
  private final BillService billService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public BillResource(BillService billService)
  {
    this.billService = billService;
  }

  /**
   * {@code POST  /bills} : Create a new bill.
   *
   * @param billDTO the billDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billDTO, or with status {@code 400 (Bad Request)} if the bill has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/bills")
  public ResponseEntity<BillDTO> createBill(@Valid @RequestBody BillDTO billDTO) throws URISyntaxException
  {
    log.debug("REST request to save Bill : {}", billDTO);
    if (billDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new bill cannot already have an ID", ENTITY_NAME, "idexists");
    }
    BillDTO result = billService.save(billDTO);
    return ResponseEntity
      .created(new URI("/api/bills/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /bills} : Updates an existing bill.
   *
   * @param billDTO the billDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billDTO,
   * or with status {@code 400 (Bad Request)} if the billDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the billDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/bills")
  public ResponseEntity<BillDTO> updateBill(@Valid @RequestBody BillDTO billDTO) throws URISyntaxException
  {
    log.debug("REST request to update Bill : {}", billDTO);
    if (billDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    BillDTO result = billService.save(billDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /bills} : Updates given fields of an existing bill.
   *
   * @param billDTO the billDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billDTO,
   * or with status {@code 400 (Bad Request)} if the billDTO is not valid,
   * or with status {@code 404 (Not Found)} if the billDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the billDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/bills", consumes = "application/merge-patch+json")
  public ResponseEntity<BillDTO> partialUpdateBill(@NotNull @RequestBody BillDTO billDTO) throws URISyntaxException
  {
    log.debug("REST request to update Bill partially : {}", billDTO);
    if (billDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    Optional<BillDTO> result = billService.partialUpdate(billDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, billDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /bills} : get all the bills.
   *
   * @param pageable the pagination information.
   * @param filter   the filter of the request.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of bills in body.
   */
  @GetMapping("/bills")
  public ResponseEntity<List<BillDTO>> getAllBills(Pageable pageable, @RequestParam(required = false) String filter)
  {
    if ("payment-is-null".equals(filter))
    {
      log.debug("REST request to get all Bills where payment is null");
      return new ResponseEntity<>(billService.findAllWherePaymentIsNull(), HttpStatus.OK);
    }
    log.debug("REST request to get a page of Bills");
    Page<BillDTO> page = billService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /bills/:id} : get the "id" bill.
   *
   * @param id the id of the billDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/bills/{id}")
  public ResponseEntity<BillDTO> getBill(@PathVariable Long id)
  {
    log.debug("REST request to get Bill : {}", id);
    Optional<BillDTO> billDTO = billService.findOne(id);
    return ResponseUtil.wrapOrNotFound(billDTO);
  }

  /**
   * {@code DELETE  /bills/:id} : delete the "id" bill.
   *
   * @param id the id of the billDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/bills/{id}")
  public ResponseEntity<Void> deleteBill(@PathVariable Long id)
  {
    log.debug("REST request to delete Bill : {}", id);
    billService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/bills?query=:query} : search for the bill corresponding
   * to the query.
   *
   * @param query    the query of the bill search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/bills")
  public ResponseEntity<List<BillDTO>> searchBills(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of Bills for query {}", query);
    Page<BillDTO> page = billService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
