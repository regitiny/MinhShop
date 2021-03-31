package org.regitiny.minhshop.service.impl;

import org.regitiny.minhshop.domain.Payment;
import org.regitiny.minhshop.repository.PaymentRepository;
import org.regitiny.minhshop.repository.search.PaymentSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.PaymentService;
import org.regitiny.minhshop.service.dto.PaymentDTO;
import org.regitiny.minhshop.service.mapper.PaymentMapper;
import org.regitiny.tools.magic.utils.EntityDefaultPropertiesServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link Payment}.
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService
{

  private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);

  private final PaymentRepository paymentRepository;

  private final PaymentMapper paymentMapper;

  private final PaymentSearchRepository paymentSearchRepository;

  public PaymentServiceImpl(
    PaymentRepository paymentRepository,
    PaymentMapper paymentMapper,
    PaymentSearchRepository paymentSearchRepository
  )
  {
    this.paymentRepository = paymentRepository;
    this.paymentMapper = paymentMapper;
    this.paymentSearchRepository = paymentSearchRepository;
  }

  @Override
  public PaymentDTO save(PaymentDTO paymentDTO)
  {
    log.debug("Request to save Payment : {}", paymentDTO);
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    paymentDTO = (PaymentDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(paymentDTO);
    Payment payment = paymentMapper.toEntity(paymentDTO);
    payment = paymentRepository.save(payment);
    PaymentDTO result = paymentMapper.toDto(payment);
    payment.cleanInfiniteInterlockingRelationship();
    paymentSearchRepository.save(payment);
    return result;
  }

  @Override
  public Optional<PaymentDTO> partialUpdate(PaymentDTO paymentDTO)
  {
    log.debug("Request to partially update Payment : {}", paymentDTO);

    return paymentRepository
      .findById(paymentDTO.getId())
      .map(
        existingPayment ->
        {
          paymentMapper.partialUpdate(existingPayment, paymentDTO);
          return existingPayment;
        }
      )
      .map(paymentRepository::save)
      .map(
        savedPayment ->
        {
          paymentSearchRepository.save(savedPayment);

          return savedPayment;
        }
      )
      .map(paymentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PaymentDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all Payments");
    return paymentRepository.findAll(pageable).map(paymentMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PaymentDTO> findOne(Long id)
  {
    log.debug("Request to get Payment : {}", id);
    return paymentRepository.findById(id).map(paymentMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete Payment : {}", id);
    paymentRepository.deleteById(id);
    paymentSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PaymentDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of Payments for query {}", query);
    return paymentSearchRepository.search(queryStringQuery(query), pageable).map(paymentMapper::toDto);
  }
}
