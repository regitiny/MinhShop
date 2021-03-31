package org.regitiny.minhshop.service.impl;

import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.regitiny.minhshop.domain.SimplePost;
import org.regitiny.minhshop.domain.TypePost;
import org.regitiny.minhshop.repository.PostDetailsRepository;
import org.regitiny.minhshop.repository.SimplePostRepository;
import org.regitiny.minhshop.repository.TypePostRepository;
import org.regitiny.minhshop.repository.search.SimplePostSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.SimplePostService;
import org.regitiny.minhshop.service.dto.SimplePostDTO;
import org.regitiny.minhshop.service.mapper.SimplePostMapper;
import org.regitiny.tools.magic.utils.EntityDefaultPropertiesServiceUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Service Implementation for managing {@link SimplePost}.
 */
@Log4j2
@Service
@Transactional
public class SimplePostServiceImpl implements SimplePostService
{

  private final SimplePostRepository simplePostRepository;

  private final SimplePostMapper simplePostMapper;

  private final SimplePostSearchRepository simplePostSearchRepository;

  private final PostDetailsRepository postDetailsRepository;

  private final TypePostRepository typePostRepository;

  public SimplePostServiceImpl(
    SimplePostRepository simplePostRepository,
    SimplePostMapper simplePostMapper,
    SimplePostSearchRepository simplePostSearchRepository,
    PostDetailsRepository postDetailsRepository,
    TypePostRepository typePostRepository
  )
  {
    this.simplePostRepository = simplePostRepository;
    this.simplePostMapper = simplePostMapper;
    this.simplePostSearchRepository = simplePostSearchRepository;
    this.postDetailsRepository = postDetailsRepository;
    this.typePostRepository = typePostRepository;
  }

  @Override
  public SimplePostDTO save(SimplePostDTO simplePostDTO)
  {
    log.debug("Request to save SimplePost : {}", simplePostDTO);
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    simplePostDTO = (SimplePostDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(simplePostDTO);
    simplePostDTO.setDataSize((long) simplePostDTO.toString().getBytes().length);

    SimplePost simplePost = simplePostMapper.toEntity(simplePostDTO);
    Long postDetailsId = simplePostDTO.getPostDetails().getId();
    postDetailsRepository.findById(postDetailsId).ifPresent(simplePost::postDetails);
    simplePost = simplePostRepository.save(simplePost);
    SimplePostDTO result = simplePostMapper.toDto(simplePost);
    simplePost.cleanInfiniteInterlockingRelationship();
    simplePostSearchRepository.save(simplePost);
    return result;
  }

  @Override
  public Optional<SimplePostDTO> partialUpdate(SimplePostDTO simplePostDTO)
  {
    log.debug("Request to partially update SimplePost : {}", simplePostDTO);

    return simplePostRepository
      .findById(simplePostDTO.getId())
      .map(
        existingSimplePost ->
        {
          simplePostMapper.partialUpdate(existingSimplePost, simplePostDTO);
          return existingSimplePost;
        }
      )
      .map(simplePostRepository::save)
      .map(
        savedSimplePost ->
        {
          simplePostSearchRepository.save(savedSimplePost);

          return savedSimplePost;
        }
      )
      .map(simplePostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<SimplePostDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all SimplePosts");
    return simplePostRepository.findAll(pageable).map(simplePostMapper::toDto);
  }

  public Page<SimplePostDTO> findAllWithEagerRelationships(Pageable pageable)
  {
    return simplePostRepository.findAllWithEagerRelationships(pageable).map(simplePostMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<SimplePostDTO> findOne(Long id)
  {
    log.debug("Request to get SimplePost : {}", id);
    return simplePostRepository.findOneWithEagerRelationships(id).map(simplePostMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete SimplePost : {}", id);
    simplePostRepository.deleteById(id);
    simplePostSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<SimplePostDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of SimplePosts for query {}", query);
    return simplePostSearchRepository.search(queryStringQuery(query), pageable).map(simplePostMapper::toDto);
  }

  @Override
  public JSONObject getSimplePostsGroupByTypePost()
  {
    JSONObject result = new JSONObject();
    JSONArray jsonArrayTypePost = new JSONArray();
    int total = 0;
    List<TypePost> allTypePosts = typePostRepository.findAllByOrderByCommentDesc();
    log.debug("allTypePosts size = {}", allTypePosts.size());
    for (TypePost typePost : allTypePosts)
    {
      long typePost_Id = typePost.getId();
      String typePost_TypeName = typePost.getTypeName();

      List<SimplePost> simplePosts = simplePostRepository.findAllByTypePostId(typePost_Id);
      if (!simplePosts.isEmpty())
      {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("SimplePosts", simplePostMapper.toDto(simplePosts));
        jsonObject.put("typePost_Id", typePost_Id);
        jsonObject.put("typePost_TypeName", typePost_TypeName);
        jsonArrayTypePost.put(jsonObject);
        total++;
      }
    }
    result.put("total", total);
    result.put("SimplePostsGroupByTypePost", jsonArrayTypePost);
    log.debug(result.toString());
    return result;
  }

  @Override
  public Page<SimplePostDTO> getSimplePostsByTypePost_Id(Long typePost_Id, Pageable pageable)
  {
    return simplePostRepository.findAllByTypePostId(typePost_Id, pageable).map(simplePostMapper::toDto);
  }
}
