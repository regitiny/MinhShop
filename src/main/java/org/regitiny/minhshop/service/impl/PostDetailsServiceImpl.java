package org.regitiny.minhshop.service.impl;

import org.regitiny.minhshop.domain.PostDetails;
import org.regitiny.minhshop.repository.PostDetailsRepository;
import org.regitiny.minhshop.repository.search.PostDetailsSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.PostDetailsService;
import org.regitiny.minhshop.service.dto.PostDetailsDTO;
import org.regitiny.minhshop.service.mapper.PostDetailsMapper;
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
 * Service Implementation for managing {@link PostDetails}.
 */
@Service
@Transactional
public class PostDetailsServiceImpl implements PostDetailsService
{

  private final Logger log = LoggerFactory.getLogger(PostDetailsServiceImpl.class);

  private final PostDetailsRepository postDetailsRepository;

  private final PostDetailsMapper postDetailsMapper;

  private final PostDetailsSearchRepository postDetailsSearchRepository;

  public PostDetailsServiceImpl(
    PostDetailsRepository postDetailsRepository,
    PostDetailsMapper postDetailsMapper,
    PostDetailsSearchRepository postDetailsSearchRepository
  )
  {
    this.postDetailsRepository = postDetailsRepository;
    this.postDetailsMapper = postDetailsMapper;
    this.postDetailsSearchRepository = postDetailsSearchRepository;
  }

  @Override
  public PostDetailsDTO save(PostDetailsDTO postDetailsDTO)
  {
    log.debug("Request to save PostDetails : {}", postDetailsDTO);
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    postDetailsDTO = (PostDetailsDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(postDetailsDTO);
    postDetailsDTO.setDataSize((long) postDetailsDTO.toString().getBytes().length);
    PostDetails postDetails = postDetailsMapper.toEntity(postDetailsDTO);
    postDetails = postDetailsRepository.save(postDetails);
    PostDetailsDTO result = postDetailsMapper.toDto(postDetails);
    postDetailsSearchRepository.save(postDetails);
    return result;
  }

  @Override
  public Optional<PostDetailsDTO> partialUpdate(PostDetailsDTO postDetailsDTO)
  {
    log.debug("Request to partially update PostDetails : {}", postDetailsDTO);

    return postDetailsRepository
      .findById(postDetailsDTO.getId())
      .map(
        existingPostDetails ->
        {
          postDetailsMapper.partialUpdate(existingPostDetails, postDetailsDTO);
          return existingPostDetails;
        }
      )
      .map(postDetailsRepository::save)
      .map(
        savedPostDetails ->
        {
          postDetailsSearchRepository.save(savedPostDetails);

          return savedPostDetails;
        }
      )
      .map(postDetailsMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PostDetailsDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all PostDetails");
    return postDetailsRepository.findAll(pageable).map(postDetailsMapper::toDto);
  }

  /**
   * Get all the postDetails where SimplePost is {@code null}.
   *
   * @return the list of entities.
   */
  @Transactional(readOnly = true)
  public List<PostDetailsDTO> findAllWhereSimplePostIsNull()
  {
    log.debug("Request to get all postDetails where SimplePost is null");
    return StreamSupport
      .stream(postDetailsRepository.findAll().spliterator(), false)
      .filter(postDetails -> postDetails.getSimplePost() == null)
      .map(postDetailsMapper::toDto)
      .collect(Collectors.toCollection(LinkedList::new));
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<PostDetailsDTO> findOne(Long id)
  {
    log.debug("Request to get PostDetails : {}", id);
    return postDetailsRepository.findById(id).map(postDetailsMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete PostDetails : {}", id);
    postDetailsRepository.deleteById(id);
    postDetailsSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<PostDetailsDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of PostDetails for query {}", query);
    return postDetailsSearchRepository.search(queryStringQuery(query), pageable).map(postDetailsMapper::toDto);
  }
}
