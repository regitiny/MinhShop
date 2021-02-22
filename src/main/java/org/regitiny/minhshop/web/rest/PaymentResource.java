package org.regitiny.minhshop.web.rest;

import org.regitiny.minhshop.service.PaymentService;
import org.regitiny.minhshop.service.dto.PaymentDTO;
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
 * REST controller for managing {@link org.regitiny.minhshop.domain.Payment}.
 */
@RestController
@RequestMapping("/api")
public class PaymentResource
{

  private static final String ENTITY_NAME = "payment";
  private final Logger log = LoggerFactory.getLogger(PaymentResource.class);
  private final PaymentService paymentService;

  @Value("${jhipster.clientApp.name}")
  private String applicationName;

  public PaymentResource(PaymentService paymentService)
  {
    this.paymentService = paymentService;
  }

  /**
   * {@code POST  /payments} : Create a new payment.
   *
   * @param paymentDTO the paymentDTO to create.
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentDTO, or with status {@code 400 (Bad Request)} if the payment has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/payments")
  public ResponseEntity<PaymentDTO> createPayment(@Valid @RequestBody PaymentDTO paymentDTO) throws URISyntaxException
  {
    log.debug("REST request to save Payment : {}", paymentDTO);
    if (paymentDTO.getId() != null)
    {
      throw new BadRequestAlertException("A new payment cannot already have an ID", ENTITY_NAME, "idexists");
    }
    PaymentDTO result = paymentService.save(paymentDTO);
    return ResponseEntity
      .created(new URI("/api/payments/" + result.getId()))
      .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
      .body(result);
  }

  /**
   * {@code PUT  /payments} : Updates an existing payment.
   *
   * @param paymentDTO the paymentDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentDTO,
   * or with status {@code 400 (Bad Request)} if the paymentDTO is not valid,
   * or with status {@code 500 (Internal Server Error)} if the paymentDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PutMapping("/payments")
  public ResponseEntity<PaymentDTO> updatePayment(@Valid @RequestBody PaymentDTO paymentDTO) throws URISyntaxException
  {
    log.debug("REST request to update Payment : {}", paymentDTO);
    if (paymentDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    PaymentDTO result = paymentService.save(paymentDTO);
    return ResponseEntity
      .ok()
      .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentDTO.getId().toString()))
      .body(result);
  }

  /**
   * {@code PATCH  /payments} : Updates given fields of an existing payment.
   *
   * @param paymentDTO the paymentDTO to update.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentDTO,
   * or with status {@code 400 (Bad Request)} if the paymentDTO is not valid,
   * or with status {@code 404 (Not Found)} if the paymentDTO is not found,
   * or with status {@code 500 (Internal Server Error)} if the paymentDTO couldn't be updated.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PatchMapping(value = "/payments", consumes = "application/merge-patch+json")
  public ResponseEntity<PaymentDTO> partialUpdatePayment(@NotNull @RequestBody PaymentDTO paymentDTO) throws URISyntaxException
  {
    log.debug("REST request to update Payment partially : {}", paymentDTO);
    if (paymentDTO.getId() == null)
    {
      throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }

    Optional<PaymentDTO> result = paymentService.partialUpdate(paymentDTO);

    return ResponseUtil.wrapOrNotFound(
      result,
      HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, paymentDTO.getId().toString())
    );
  }

  /**
   * {@code GET  /payments} : get all the payments.
   *
   * @param pageable the pagination information.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payments in body.
   */
  @GetMapping("/payments")
  public ResponseEntity<List<PaymentDTO>> getAllPayments(Pageable pageable)
  {
    log.debug("REST request to get a page of Payments");
    Page<PaymentDTO> page = paymentService.findAll(pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }

  /**
   * {@code GET  /payments/:id} : get the "id" payment.
   *
   * @param id the id of the paymentDTO to retrieve.
   * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentDTO, or with status {@code 404 (Not Found)}.
   */
  @GetMapping("/payments/{id}")
  public ResponseEntity<PaymentDTO> getPayment(@PathVariable Long id)
  {
    log.debug("REST request to get Payment : {}", id);
    Optional<PaymentDTO> paymentDTO = paymentService.findOne(id);
    return ResponseUtil.wrapOrNotFound(paymentDTO);
  }

  /**
   * {@code DELETE  /payments/:id} : delete the "id" payment.
   *
   * @param id the id of the paymentDTO to delete.
   * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
   */
  @DeleteMapping("/payments/{id}")
  public ResponseEntity<Void> deletePayment(@PathVariable Long id)
  {
    log.debug("REST request to delete Payment : {}", id);
    paymentService.delete(id);
    return ResponseEntity
      .noContent()
      .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
      .build();
  }

  /**
   * {@code SEARCH  /_search/payments?query=:query} : search for the payment corresponding
   * to the query.
   *
   * @param query    the query of the payment search.
   * @param pageable the pagination information.
   * @return the result of the search.
   */
  @GetMapping("/_search/payments")
  public ResponseEntity<List<PaymentDTO>> searchPayments(@RequestParam String query, Pageable pageable)
  {
    log.debug("REST request to search for a page of Payments for query {}", query);
    Page<PaymentDTO> page = paymentService.search(query, pageable);
    HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    return ResponseEntity.ok().headers(headers).body(page.getContent());
  }
}
