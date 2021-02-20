package org.regitiny.minhshop.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.Optional;
import org.regitiny.minhshop.domain.UserOtherInfo;
import org.regitiny.minhshop.repository.UserOtherInfoRepository;
import org.regitiny.minhshop.repository.search.UserOtherInfoSearchRepository;
import org.regitiny.minhshop.service.UserOtherInfoService;
import org.regitiny.minhshop.service.dto.UserOtherInfoDTO;
import org.regitiny.minhshop.service.mapper.UserOtherInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserOtherInfo}.
 */
@Service
@Transactional
public class UserOtherInfoServiceImpl implements UserOtherInfoService {

    private final Logger log = LoggerFactory.getLogger(UserOtherInfoServiceImpl.class);

    private final UserOtherInfoRepository userOtherInfoRepository;

    private final UserOtherInfoMapper userOtherInfoMapper;

    private final UserOtherInfoSearchRepository userOtherInfoSearchRepository;

    public UserOtherInfoServiceImpl(
        UserOtherInfoRepository userOtherInfoRepository,
        UserOtherInfoMapper userOtherInfoMapper,
        UserOtherInfoSearchRepository userOtherInfoSearchRepository
    ) {
        this.userOtherInfoRepository = userOtherInfoRepository;
        this.userOtherInfoMapper = userOtherInfoMapper;
        this.userOtherInfoSearchRepository = userOtherInfoSearchRepository;
    }

    @Override
    public UserOtherInfoDTO save(UserOtherInfoDTO userOtherInfoDTO) {
        log.debug("Request to save UserOtherInfo : {}", userOtherInfoDTO);
        UserOtherInfo userOtherInfo = userOtherInfoMapper.toEntity(userOtherInfoDTO);
        userOtherInfo = userOtherInfoRepository.save(userOtherInfo);
        UserOtherInfoDTO result = userOtherInfoMapper.toDto(userOtherInfo);
        userOtherInfoSearchRepository.save(userOtherInfo);
        return result;
    }

    @Override
    public Optional<UserOtherInfoDTO> partialUpdate(UserOtherInfoDTO userOtherInfoDTO) {
        log.debug("Request to partially update UserOtherInfo : {}", userOtherInfoDTO);

        return userOtherInfoRepository
            .findById(userOtherInfoDTO.getId())
            .map(
                existingUserOtherInfo -> {
                    if (userOtherInfoDTO.getUuid() != null) {
                        existingUserOtherInfo.setUuid(userOtherInfoDTO.getUuid());
                    }

                    if (userOtherInfoDTO.getPhoneNumber() != null) {
                        existingUserOtherInfo.setPhoneNumber(userOtherInfoDTO.getPhoneNumber());
                    }

                    if (userOtherInfoDTO.getEmail() != null) {
                        existingUserOtherInfo.setEmail(userOtherInfoDTO.getEmail());
                    }

                    if (userOtherInfoDTO.getWardCode() != null) {
                        existingUserOtherInfo.setWardCode(userOtherInfoDTO.getWardCode());
                    }

                    if (userOtherInfoDTO.getDistCode() != null) {
                        existingUserOtherInfo.setDistCode(userOtherInfoDTO.getDistCode());
                    }

                    if (userOtherInfoDTO.getCityCode() != null) {
                        existingUserOtherInfo.setCityCode(userOtherInfoDTO.getCityCode());
                    }

                    if (userOtherInfoDTO.getAddressDetails() != null) {
                        existingUserOtherInfo.setAddressDetails(userOtherInfoDTO.getAddressDetails());
                    }

                    if (userOtherInfoDTO.getDateOfBirth() != null) {
                        existingUserOtherInfo.setDateOfBirth(userOtherInfoDTO.getDateOfBirth());
                    }

                    if (userOtherInfoDTO.getOtherInfo() != null) {
                        existingUserOtherInfo.setOtherInfo(userOtherInfoDTO.getOtherInfo());
                    }

                    if (userOtherInfoDTO.getSearchField() != null) {
                        existingUserOtherInfo.setSearchField(userOtherInfoDTO.getSearchField());
                    }

                    if (userOtherInfoDTO.getRole() != null) {
                        existingUserOtherInfo.setRole(userOtherInfoDTO.getRole());
                    }

                    if (userOtherInfoDTO.getCreatedDate() != null) {
                        existingUserOtherInfo.setCreatedDate(userOtherInfoDTO.getCreatedDate());
                    }

                    if (userOtherInfoDTO.getModifiedDate() != null) {
                        existingUserOtherInfo.setModifiedDate(userOtherInfoDTO.getModifiedDate());
                    }

                    if (userOtherInfoDTO.getCreatedBy() != null) {
                        existingUserOtherInfo.setCreatedBy(userOtherInfoDTO.getCreatedBy());
                    }

                    if (userOtherInfoDTO.getModifiedBy() != null) {
                        existingUserOtherInfo.setModifiedBy(userOtherInfoDTO.getModifiedBy());
                    }

                    if (userOtherInfoDTO.getDataSize() != null) {
                        existingUserOtherInfo.setDataSize(userOtherInfoDTO.getDataSize());
                    }

                    if (userOtherInfoDTO.getComment() != null) {
                        existingUserOtherInfo.setComment(userOtherInfoDTO.getComment());
                    }

                    return existingUserOtherInfo;
                }
            )
            .map(userOtherInfoRepository::save)
            .map(
                savedUserOtherInfo -> {
                    userOtherInfoSearchRepository.save(savedUserOtherInfo);

                    return savedUserOtherInfo;
                }
            )
            .map(userOtherInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserOtherInfoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserOtherInfos");
        return userOtherInfoRepository.findAll(pageable).map(userOtherInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserOtherInfoDTO> findOne(Long id) {
        log.debug("Request to get UserOtherInfo : {}", id);
        return userOtherInfoRepository.findById(id).map(userOtherInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserOtherInfo : {}", id);
        userOtherInfoRepository.deleteById(id);
        userOtherInfoSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserOtherInfoDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of UserOtherInfos for query {}", query);
        return userOtherInfoSearchRepository.search(queryStringQuery(query), pageable).map(userOtherInfoMapper::toDto);
    }
}
