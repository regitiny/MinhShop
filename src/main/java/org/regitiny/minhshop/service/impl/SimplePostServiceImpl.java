package org.regitiny.minhshop.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.Optional;
import org.regitiny.minhshop.domain.SimplePost;
import org.regitiny.minhshop.repository.PostDetailsRepository;
import org.regitiny.minhshop.repository.SimplePostRepository;
import org.regitiny.minhshop.repository.search.SimplePostSearchRepository;
import org.regitiny.minhshop.service.SimplePostService;
import org.regitiny.minhshop.service.dto.SimplePostDTO;
import org.regitiny.minhshop.service.mapper.SimplePostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link SimplePost}.
 */
@Service
@Transactional
public class SimplePostServiceImpl implements SimplePostService {

    private final Logger log = LoggerFactory.getLogger(SimplePostServiceImpl.class);

    private final SimplePostRepository simplePostRepository;

    private final SimplePostMapper simplePostMapper;

    private final SimplePostSearchRepository simplePostSearchRepository;

    private final PostDetailsRepository postDetailsRepository;

    public SimplePostServiceImpl(
        SimplePostRepository simplePostRepository,
        SimplePostMapper simplePostMapper,
        SimplePostSearchRepository simplePostSearchRepository,
        PostDetailsRepository postDetailsRepository
    ) {
        this.simplePostRepository = simplePostRepository;
        this.simplePostMapper = simplePostMapper;
        this.simplePostSearchRepository = simplePostSearchRepository;
        this.postDetailsRepository = postDetailsRepository;
    }

    @Override
    public SimplePostDTO save(SimplePostDTO simplePostDTO) {
        log.debug("Request to save SimplePost : {}", simplePostDTO);
        SimplePost simplePost = simplePostMapper.toEntity(simplePostDTO);
        Long postDetailsId = simplePostDTO.getPostDetails().getId();
        postDetailsRepository.findById(postDetailsId).ifPresent(simplePost::postDetails);
        simplePost = simplePostRepository.save(simplePost);
        SimplePostDTO result = simplePostMapper.toDto(simplePost);
        simplePostSearchRepository.save(simplePost);
        return result;
    }

    @Override
    public Optional<SimplePostDTO> partialUpdate(SimplePostDTO simplePostDTO) {
        log.debug("Request to partially update SimplePost : {}", simplePostDTO);

        return simplePostRepository
            .findById(simplePostDTO.getId())
            .map(
                existingSimplePost -> {
                    if (simplePostDTO.getUuid() != null) {
                        existingSimplePost.setUuid(simplePostDTO.getUuid());
                    }

                    if (simplePostDTO.getTitle() != null) {
                        existingSimplePost.setTitle(simplePostDTO.getTitle());
                    }

                    if (simplePostDTO.getPrice() != null) {
                        existingSimplePost.setPrice(simplePostDTO.getPrice());
                    }

                    if (simplePostDTO.getSalePrice() != null) {
                        existingSimplePost.setSalePrice(simplePostDTO.getSalePrice());
                    }

                    if (simplePostDTO.getPercentSale() != null) {
                        existingSimplePost.setPercentSale(simplePostDTO.getPercentSale());
                    }

                    if (simplePostDTO.getImageUrl() != null) {
                        existingSimplePost.setImageUrl(simplePostDTO.getImageUrl());
                    }

                    if (simplePostDTO.getScores() != null) {
                        existingSimplePost.setScores(simplePostDTO.getScores());
                    }

                    if (simplePostDTO.getSimpleContent() != null) {
                        existingSimplePost.setSimpleContent(simplePostDTO.getSimpleContent());
                    }

                    if (simplePostDTO.getOtherInfo() != null) {
                        existingSimplePost.setOtherInfo(simplePostDTO.getOtherInfo());
                    }

                    if (simplePostDTO.getSearchField() != null) {
                        existingSimplePost.setSearchField(simplePostDTO.getSearchField());
                    }

                    if (simplePostDTO.getRole() != null) {
                        existingSimplePost.setRole(simplePostDTO.getRole());
                    }

                    if (simplePostDTO.getCreatedDate() != null) {
                        existingSimplePost.setCreatedDate(simplePostDTO.getCreatedDate());
                    }

                    if (simplePostDTO.getModifiedDate() != null) {
                        existingSimplePost.setModifiedDate(simplePostDTO.getModifiedDate());
                    }

                    if (simplePostDTO.getCreatedBy() != null) {
                        existingSimplePost.setCreatedBy(simplePostDTO.getCreatedBy());
                    }

                    if (simplePostDTO.getModifiedBy() != null) {
                        existingSimplePost.setModifiedBy(simplePostDTO.getModifiedBy());
                    }

                    if (simplePostDTO.getDataSize() != null) {
                        existingSimplePost.setDataSize(simplePostDTO.getDataSize());
                    }

                    if (simplePostDTO.getComment() != null) {
                        existingSimplePost.setComment(simplePostDTO.getComment());
                    }

                    return existingSimplePost;
                }
            )
            .map(simplePostRepository::save)
            .map(
                savedSimplePost -> {
                    simplePostSearchRepository.save(savedSimplePost);

                    return savedSimplePost;
                }
            )
            .map(simplePostMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SimplePostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SimplePosts");
        return simplePostRepository.findAll(pageable).map(simplePostMapper::toDto);
    }

    public Page<SimplePostDTO> findAllWithEagerRelationships(Pageable pageable) {
        return simplePostRepository.findAllWithEagerRelationships(pageable).map(simplePostMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SimplePostDTO> findOne(Long id) {
        log.debug("Request to get SimplePost : {}", id);
        return simplePostRepository.findOneWithEagerRelationships(id).map(simplePostMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete SimplePost : {}", id);
        simplePostRepository.deleteById(id);
        simplePostSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SimplePostDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of SimplePosts for query {}", query);
        return simplePostSearchRepository.search(queryStringQuery(query), pageable).map(simplePostMapper::toDto);
    }
}
