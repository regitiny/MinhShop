package org.regitiny.minhshop.service.impl;

import org.regitiny.minhshop.domain.TypePost;
import org.regitiny.minhshop.repository.TypePostRepository;
import org.regitiny.minhshop.repository.search.TypePostSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.TypePostService;
import org.regitiny.minhshop.service.dto.TypePostDTO;
import org.regitiny.minhshop.service.mapper.TypePostMapper;
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
 * Service Implementation for managing {@link TypePost}.
 */
@Service
@Transactional
public class TypePostServiceImpl implements TypePostService
{

  private final Logger log = LoggerFactory.getLogger(TypePostServiceImpl.class);

  private final TypePostRepository typePostRepository;

  private final TypePostMapper typePostMapper;

  private final TypePostSearchRepository typePostSearchRepository;

  public TypePostServiceImpl(
    TypePostRepository typePostRepository,
    TypePostMapper typePostMapper,
    TypePostSearchRepository typePostSearchRepository
  )
  {
    this.typePostRepository = typePostRepository;
    this.typePostMapper = typePostMapper;
    this.typePostSearchRepository = typePostSearchRepository;
  }

  @Override
  public TypePostDTO save(TypePostDTO typePostDTO)
  {
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    typePostDTO = (TypePostDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(typePostDTO);

    typePostDTO.setDataSize((long) typePostDTO.getTypeName().getBytes().length);

    log.debug("Request to save TypePost : {}", typePostDTO);
    TypePost typePost = typePostMapper.toEntity(typePostDTO);
    typePost = typePostRepository.save(typePost);
    TypePostDTO result = typePostMapper.toDto(typePost);
    typePostSearchRepository.save(typePost);
    return result;
  }

  @Override
  public Optional<TypePostDTO> partialUpdate(TypePostDTO typePostDTO)
  {
    log.debug("Request to partially update TypePost : {}", typePostDTO);

    return typePostRepository
      .findById(typePostDTO.getId())
      .map(
        existingTypePost ->
        {
          typePostMapper.partialUpdate(existingTypePost, typePostDTO);
          return existingTypePost;
        }
      )
      .map(typePostRepository::save)
      .map(
        savedTypePost ->
        {
          typePostSearchRepository.save(savedTypePost);

          return savedTypePost;
        }
      )
      .map(typePostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TypePostDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all TypePosts");
    return typePostRepository.findAll(pageable).map(typePostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<TypePostDTO> findOne(Long id)
  {
    log.debug("Request to get TypePost : {}", id);
    return typePostRepository.findById(id).map(typePostMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete TypePost : {}", id);
    typePostRepository.deleteById(id);
    typePostSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<TypePostDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of TypePosts for query {}", query);
    return typePostSearchRepository.search(queryStringQuery(query), pageable).map(typePostMapper::toDto);
  }
}
