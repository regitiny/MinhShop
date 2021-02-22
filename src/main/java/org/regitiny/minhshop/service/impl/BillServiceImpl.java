package org.regitiny.minhshop.service.impl;

import org.regitiny.minhshop.domain.Bill;
import org.regitiny.minhshop.repository.BillRepository;
import org.regitiny.minhshop.repository.search.BillSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.BillService;
import org.regitiny.minhshop.service.dto.BillDTO;
import org.regitiny.minhshop.service.dto.ImageDTO;
import org.regitiny.minhshop.service.mapper.BillMapper;
import org.regitiny.tools.magic.utils.EntityDefaultPropertiesServiceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link Bill}.
 */
@Service
@Transactional
public class BillServiceImpl implements BillService
{

  private final Logger log = LoggerFactory.getLogger(BillServiceImpl.class);

  private final BillRepository billRepository;

  private final BillMapper billMapper;

  private final BillSearchRepository billSearchRepository;

  public BillServiceImpl(BillRepository billRepository, BillMapper billMapper, BillSearchRepository billSearchRepository)
  {
    this.billRepository = billRepository;
    this.billMapper = billMapper;
    this.billSearchRepository = billSearchRepository;
  }

  @Override
  public BillDTO save(BillDTO billDTO)
  {
    log.debug("Request to save Bill : {}", billDTO);
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.USER);
    // EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave role mặc định là management
    ImageDTO imageDTO = (ImageDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(new ImageDTO());
    billDTO.setRole(AuthoritiesConstants.USER);
    Bill bill = billMapper.toEntity(billDTO);
    bill = billRepository.save(bill);
    BillDTO result = billMapper.toDto(bill);
    billSearchRepository.save(bill);
    return result;
  }

  @Override
  public Optional<BillDTO> partialUpdate(BillDTO billDTO)
  {
    log.debug("Request to partially update Bill : {}", billDTO);

    return billRepository
      .findById(billDTO.getId())
      .map(
        existingBill ->
        {
          if (billDTO.getUuid() != null)
          {
            existingBill.setUuid(billDTO.getUuid());
          }

          if (billDTO.getBillId() != null)
          {
            existingBill.setBillId(billDTO.getBillId());
          }

          if (billDTO.getPhoneNumber() != null)
          {
            existingBill.setPhoneNumber(billDTO.getPhoneNumber());
          }

          if (billDTO.getEmail() != null)
          {
            existingBill.setEmail(billDTO.getEmail());
          }

          if (billDTO.getAddressDetails() != null)
          {
            existingBill.setAddressDetails(billDTO.getAddressDetails());
          }

          if (billDTO.getAddressCode() != null)
          {
            existingBill.setAddressCode(billDTO.getAddressCode());
          }

          if (billDTO.getProduct() != null)
          {
            existingBill.setProduct(billDTO.getProduct());
          }

          if (billDTO.getComment() != null)
          {
            existingBill.setComment(billDTO.getComment());
          }

          if (billDTO.getSearchField() != null)
          {
            existingBill.setSearchField(billDTO.getSearchField());
          }

          if (billDTO.getRole() != null)
          {
            existingBill.setRole(billDTO.getRole());
          }

          if (billDTO.getCreatedDate() != null)
          {
            existingBill.setCreatedDate(billDTO.getCreatedDate());
          }

          if (billDTO.getModifiedDate() != null)
          {
            existingBill.setModifiedDate(billDTO.getModifiedDate());
          }

          if (billDTO.getCreatedBy() != null)
          {
            existingBill.setCreatedBy(billDTO.getCreatedBy());
          }

          if (billDTO.getModifiedBy() != null)
          {
            existingBill.setModifiedBy(billDTO.getModifiedBy());
          }

          if (billDTO.getDataSize() != null)
          {
            existingBill.setDataSize(billDTO.getDataSize());
          }

          return existingBill;
        }
      )
      .map(billRepository::save)
      .map(
        savedBill ->
        {
          billSearchRepository.save(savedBill);

          return savedBill;
        }
      )
      .map(billMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<BillDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all Bills");
    return billRepository.findAll(pageable).map(billMapper::toDto);
  }

  /**
   * Get all the bills where Payment is {@code null}.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<BillDTO> findAllWherePaymentIsNull()
  {
    log.debug("Request to get all bills where Payment is null");
    return StreamSupport
      .stream(billRepository.findAll().spliterator(), false)
      .filter(bill -> bill.getPayment() == null)
      .map(billMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<BillDTO> findOne(Long id)
  {
    log.debug("Request to get Bill : {}", id);
    return billRepository.findById(id).map(billMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete Bill : {}", id);
    billRepository.deleteById(id);
    billSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<BillDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of Bills for query {}", query);
    return billSearchRepository.search(queryStringQuery(query), pageable).map(billMapper::toDto);
  }
}
