package org.regitiny.minhshop.service.impl;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.regitiny.minhshop.repository.search.PostDetailsSearchRepository;
import org.regitiny.minhshop.repository.search.SimplePostSearchRepository;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.PostDetailsService;
import org.regitiny.minhshop.service.PostService;
import org.regitiny.minhshop.service.SimplePostService;
import org.regitiny.minhshop.service.dto.PostDetailsDTO;
import org.regitiny.minhshop.service.dto.SimplePostDTO;
import org.regitiny.minhshop.service.dto.TypePostDTO;
import org.regitiny.minhshop.service.dto.TypePostFilterDTO;
import org.regitiny.minhshop.service.mapper.PostDetailsMapper;
import org.regitiny.minhshop.service.mapper.SimplePostMapper;
import org.regitiny.minhshop.web.rest.custom.model.PostModel;
import org.regitiny.minhshop.web.rest.errors.BadRequestAlertException;
import org.regitiny.tools.magic.constant.StringPool;
import org.regitiny.tools.magic.utils.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Transactional
@Service
public class PostServiceImpl implements PostService {

    private final SimplePostService simplePostService;

    private final PostDetailsService postDetailsService;

    private final SimplePostSearchRepository simplePostSearchRepository;

    private final SimplePostMapper simplePostMapper;

    private final PostDetailsMapper postDetailsMapper;

    private final PostDetailsSearchRepository postDetailsSearchRepository;

    public PostServiceImpl(
        SimplePostService simplePostService,
        PostDetailsService postDetailsService,
        SimplePostSearchRepository simplePostSearchRepository,
        SimplePostMapper simplePostMapper,
        PostDetailsMapper postDetailsMapper,
        PostDetailsSearchRepository postDetailsSearchRepository
    ) {
        this.simplePostService = simplePostService;
        this.postDetailsService = postDetailsService;
        this.simplePostSearchRepository = simplePostSearchRepository;
        this.simplePostMapper = simplePostMapper;
        this.postDetailsMapper = postDetailsMapper;
        this.postDetailsSearchRepository = postDetailsSearchRepository;
    }

    @Override
    public void createNewPost(PostModel postModel) {
        if (!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.MANAGEMENT)) throw new BadRequestAlertException(
            "đéo phải quản lý thì làm gì có cái quyền upload bạn ơi",
            null,
            "notManagement"
        );

        String title = postModel.getTitle();
        Double price = postModel.getPrice();
        Double salePrice = postModel.getSalePrice();
        Float percentSale = postModel.getPercentSale();
        String imageUrl = postModel.getImageUrl();
        Float scores = postModel.getScores();
        String simpleContent = postModel.getSimpleContent();
        String otherInfo = postModel.getOtherInfo();
        String content = postModel.getContent();
        String postDetailsId = postModel.getPostDetailsId();
        String comment = postModel.getComment();
        String role = AuthoritiesConstants.MANAGEMENT;
        TypePostDTO typePost = postModel.getTypePost();
        Set<TypePostFilterDTO> typePostFilterDTO = postModel.getTypePostFilters();

        Instant nowTime = Instant.now();
        String thisUser = SecurityUtils.getCurrentUserLogin().get();
        String searchField = StringPool.BLANK;

        PostDetailsDTO postDetailsDTO = new PostDetailsDTO();

        postDetailsDTO.setUuid(UUID.randomUUID());
        postDetailsDTO.setPostDetailsId(postDetailsId);
        postDetailsDTO.setContent(content);
        postDetailsDTO.setRole(role);
        postDetailsDTO.setCreatedDate(nowTime);
        postDetailsDTO.setModifiedDate(nowTime);
        postDetailsDTO.setCreatedBy(thisUser);
        postDetailsDTO.setModifiedBy(thisUser);
        postDetailsDTO.setDataSize((long) content.getBytes().length);
        postDetailsDTO.setComment(comment);

        log.debug("postDetailsDTO = {}", postDetailsDTO);

        PostDetailsDTO resultPostDetails = postDetailsService.save(postDetailsDTO);

        log.info(resultPostDetails);

        SimplePostDTO simplePostDTO = new SimplePostDTO();

        simplePostDTO.setUuid(UUID.randomUUID());
        simplePostDTO.setTitle(title);
        simplePostDTO.setPrice(price);
        simplePostDTO.setSalePrice(salePrice);
        simplePostDTO.setPercentSale(percentSale);
        simplePostDTO.setImageUrl(imageUrl);
        simplePostDTO.setScores(scores);
        simplePostDTO.setSimpleContent(simpleContent);
        simplePostDTO.setOtherInfo(otherInfo);
        simplePostDTO.setSearchField(searchField);
        simplePostDTO.setRole(role);
        simplePostDTO.setCreatedDate(nowTime);
        simplePostDTO.setModifiedDate(nowTime);
        simplePostDTO.setCreatedBy(thisUser);
        simplePostDTO.setModifiedBy(thisUser);
        simplePostDTO.setDataSize((long) simpleContent.getBytes().length);
        simplePostDTO.setComment(comment);
        simplePostDTO.setTypePost(typePost);
        simplePostDTO.setTypePostFilters(typePostFilterDTO);
        simplePostDTO.setPostDetails(resultPostDetails);

        log.debug("simplePostDTO = {}", simplePostDTO);

        SimplePostDTO resultSimplePost = simplePostService.save(simplePostDTO);
        //      sử lý đặc biệt chỉ dành cho search

        PostDetailsDTO postDetailsDTOForSearch = resultSimplePost.getPostDetails();
        String contentCleanHTML = Jsoup.parse(content).text();
        searchField = StringUtils.clean(content);

        postDetailsDTOForSearch.setContent(contentCleanHTML);
        simplePostDTO.setSearchField(null);
        postDetailsDTOForSearch.setSearchField(searchField);
        resultSimplePost.setPostDetails(postDetailsDTOForSearch);

        postDetailsSearchRepository.save(postDetailsMapper.toEntity(resultPostDetails));
        simplePostSearchRepository.save(simplePostMapper.toEntity(resultSimplePost));
        log.debug("sau khi lưu simple-post vào es : {} , {}", resultSimplePost, resultPostDetails);

        log.info("upload post success");
    }
}
