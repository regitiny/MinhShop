package org.regitiny.minhshop.service.impl;

import org.regitiny.minhshop.domain.UserOtherInfo;
import org.regitiny.minhshop.repository.UserOtherInfoRepository;
import org.regitiny.minhshop.repository.search.UserOtherInfoSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.UserOtherInfoService;
import org.regitiny.minhshop.service.dto.UserOtherInfoDTO;
import org.regitiny.minhshop.service.mapper.UserOtherInfoMapper;
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
 * Service Implementation for managing {@link UserOtherInfo}.
 */
@Service
@Transactional
public class UserOtherInfoServiceImpl implements UserOtherInfoService
{

  private final Logger log = LoggerFactory.getLogger(UserOtherInfoServiceImpl.class);

  private final UserOtherInfoRepository userOtherInfoRepository;

  private final UserOtherInfoMapper userOtherInfoMapper;

  private final UserOtherInfoSearchRepository userOtherInfoSearchRepository;

  public UserOtherInfoServiceImpl(
    UserOtherInfoRepository userOtherInfoRepository,
    UserOtherInfoMapper userOtherInfoMapper,
    UserOtherInfoSearchRepository userOtherInfoSearchRepository
  )
  {
    this.userOtherInfoRepository = userOtherInfoRepository;
    this.userOtherInfoMapper = userOtherInfoMapper;
    this.userOtherInfoSearchRepository = userOtherInfoSearchRepository;
  }

  @Override
  public UserOtherInfoDTO save(UserOtherInfoDTO userOtherInfoDTO)
  {
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.ADMIN);
    userOtherInfoDTO = (UserOtherInfoDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(userOtherInfoDTO);

    log.debug("Request to save UserOtherInfo : {}", userOtherInfoDTO);
    UserOtherInfo userOtherInfo = userOtherInfoMapper.toEntity(userOtherInfoDTO);
    userOtherInfo = userOtherInfoRepository.save(userOtherInfo);
    UserOtherInfoDTO result = userOtherInfoMapper.toDto(userOtherInfo);
    userOtherInfoSearchRepository.save(userOtherInfo);
    return result;
  }

  @Override
  public Optional<UserOtherInfoDTO> partialUpdate(UserOtherInfoDTO userOtherInfoDTO)
  {
    log.debug("Request to partially update UserOtherInfo : {}", userOtherInfoDTO);

    return userOtherInfoRepository
      .findById(userOtherInfoDTO.getId())
      .map(
        existingUserOtherInfo ->
        {
          userOtherInfoMapper.partialUpdate(existingUserOtherInfo, userOtherInfoDTO);
          return existingUserOtherInfo;
        }
      )
      .map(userOtherInfoRepository::save)
      .map(
        savedUserOtherInfo ->
        {
          userOtherInfoSearchRepository.save(savedUserOtherInfo);

          return savedUserOtherInfo;
        }
      )
      .map(userOtherInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<UserOtherInfoDTO> findAll(Pageable pageable)
  {
    log.debug("Request to get all UserOtherInfos");
    return userOtherInfoRepository.findAll(pageable).map(userOtherInfoMapper::toDto);
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<UserOtherInfoDTO> findOne(Long id)
  {
    log.debug("Request to get UserOtherInfo : {}", id);
    return userOtherInfoRepository.findById(id).map(userOtherInfoMapper::toDto);
  }

  @Override
  public void delete(Long id)
  {
    log.debug("Request to delete UserOtherInfo : {}", id);
    userOtherInfoRepository.deleteById(id);
    userOtherInfoSearchRepository.deleteById(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Page<UserOtherInfoDTO> search(String query, Pageable pageable)
  {
    log.debug("Request to search for a page of UserOtherInfos for query {}", query);
    return userOtherInfoSearchRepository.search(queryStringQuery(query), pageable).map(userOtherInfoMapper::toDto);
  }
}
