package org.regitiny.minhshop.service.impl;

import org.regitiny.minhshop.domain.TypePostFilter;
import org.regitiny.minhshop.repository.TypePostFilterRepository;
import org.regitiny.minhshop.repository.search.TypePostFilterSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.TypePostFilterService;
import org.regitiny.minhshop.service.dto.TypePostFilterDTO;
import org.regitiny.minhshop.service.mapper.TypePostFilterMapper;
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
 * Service Implementation for managing {@link TypePostFilter}.
 */
@Service
@Transactional
public class TypePostFilterServiceImpl implements TypePostFilterService
{

  private final Logger log = LoggerFactory.getLogger(TypePostFilterServiceImpl.class);

  private final TypePostFilterRepository typePostFilterRepository;

  private final TypePostFilterMapper typePostFilterMapper;

  private final TypePostFilterSearchRepository typePostFilterSearchRepository;

  public TypePostFilterServiceImpl(
    TypePostFilterRepository typePostFilterRepository,
    TypePostFilterMapper typePostFilterMapper,
    TypePostFilterSearchRepository typePostFilterSearchRepository
  )
  {
    this.typePostFilterRepository = typePostFilterRepository;
    this.typePostFilterMapper = typePostFilterMapper;
    this.typePostFilterSearchRepository = typePostFilterSearchRepository;
  }

  @Override
  public TypePostFilterDTO save(TypePostFilterDTO typePostFilterDTO)
  {
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    typePostFilterDTO = (TypePostFilterDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(typePostFilterDTO);
    typePostFilterDTO.setDataSize((long) typePostFilterDTO.getTypeFilterName().getBytes().length);

    log.debug("Request to save TypePostFilter : {}", typePostFilterDTO);
    TypePostFilter typePostFilter = typePostFilterMapper.toEntity(typePostFilterDTO);
    typePostFilter = typePostFilterRepository.save(typePostFilter);
    TypePostFilterDTO result = typePostFilterMapper.toDto(typePostFilter);
    typePostFilterSearchRepository.save(typePostFilter);
    return result;
  }

  @Override
  public Optional<TypePostFilterDTO> partialUpdate(TypePostFilterDTO typePostFilterDTO)
  {
    log.debug("Request to partially update TypePostFilter : {}", typePostFilterDTO);

    return typePostFilterRepository
      .findById(typePostFilterDTO.getId())
      .map(
        existingTypePostFilter ->
        {
          typePostFilterMapper.partialUpdate(existingTypePostFilter, typePostFilterDTO);
          return existingTypePostFilter;
        }
      )
      .map(typePostFilterRepository::save)
      .map(
        savedTypePostFilter ->
        {
          typePostFilterSearchRepository.save(savedTypePostFilter);

          return savedTypePostFilter;
        }
      )
      .map(typePostFilterMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TypePostFilterDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all TypePostFilters");
    return typePostFilterRepository.findAll(pageable).map(typePostFilterMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TypePostFilterDTO> findOne(Long id)
  {
    log.debug("Request to get TypePostFilter : {}", id);
    return typePostFilterRepository.findById(id).map(typePostFilterMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete TypePostFilter : {}", id);
    typePostFilterRepository.deleteById(id);
    typePostFilterSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TypePostFilterDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of TypePostFilters for query {}", query);
    return typePostFilterSearchRepository.search(queryStringQuery(query), pageable).map(typePostFilterMapper::toDto);
  }
}
