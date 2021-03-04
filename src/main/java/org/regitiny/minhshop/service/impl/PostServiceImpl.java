package org.regitiny.minhshop.service.impl;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.regitiny.minhshop.domain.PostDetails;
import org.regitiny.minhshop.domain.SimplePost;
import org.regitiny.minhshop.repository.PostDetailsRepository;
import org.regitiny.minhshop.repository.SimplePostRepository;
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
import org.regitiny.minhshop.service.mapper.TypePostFilterMapper;
import org.regitiny.minhshop.service.mapper.TypePostMapper;
import org.regitiny.minhshop.web.rest.custom.model.PostModel;
import org.regitiny.minhshop.web.rest.errors.BadRequestAlertException;
import org.regitiny.tools.magic.constant.StringPool;
import org.regitiny.tools.magic.exception.NhechException;
import org.regitiny.tools.magic.utils.EntityDefaultPropertiesServiceUtils;
import org.regitiny.tools.magic.utils.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;

@Log4j2
@Transactional
@Service
public class PostServiceImpl implements PostService
{

  private final SimplePostService simplePostService;

  private final PostDetailsService postDetailsService;

  private final SimplePostRepository simplePostRepository;

  private final PostDetailsRepository postDetailsRepository;

  private final SimplePostSearchRepository simplePostSearchRepository;

  private final SimplePostMapper simplePostMapper;

  private final PostDetailsMapper postDetailsMapper;

  private final PostDetailsSearchRepository postDetailsSearchRepository;

  private final TypePostMapper typePostMapper;

  private final TypePostFilterMapper typePostFilterMapper;

  public PostServiceImpl(
    SimplePostService simplePostService,
    PostDetailsService postDetailsService,
    SimplePostRepository simplePostRepository,
    PostDetailsRepository postDetailsRepository,
    SimplePostSearchRepository simplePostSearchRepository,
    SimplePostMapper simplePostMapper,
    PostDetailsMapper postDetailsMapper,
    PostDetailsSearchRepository postDetailsSearchRepository,
    TypePostMapper typePostMapper, TypePostFilterMapper typePostFilterMapper)
  {
    this.simplePostService = simplePostService;
    this.postDetailsService = postDetailsService;
    this.simplePostRepository = simplePostRepository;
    this.postDetailsRepository = postDetailsRepository;
    this.simplePostSearchRepository = simplePostSearchRepository;
    this.simplePostMapper = simplePostMapper;
    this.postDetailsMapper = postDetailsMapper;
    this.postDetailsSearchRepository = postDetailsSearchRepository;
    this.typePostMapper = typePostMapper;
    this.typePostFilterMapper = typePostFilterMapper;
  }

  //  @Override
  public Long createNewPost(final PostModel postModel)
  {
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    PostDetailsDTO postDetailsDTO = new PostDetailsDTO();
    SimplePostDTO simplePostDTO = new SimplePostDTO();
    if (postModel.getId() != null)
    {
      simplePostDTO = simplePostService.findOne(postModel.getId()).orElse(simplePostDTO);
      if (simplePostDTO.getPostDetails() != null)
        postDetailsDTO = postDetailsService.findOne(simplePostDTO.getPostDetails().getId()).orElse(postDetailsDTO);
    }

    simplePostDTO = (SimplePostDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(simplePostDTO);
    postDetailsDTO = (PostDetailsDTO) EntityDefaultPropertiesServiceUtils.setPropertiesBeforeSave(postDetailsDTO);

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
    TypePostDTO typePost = postModel.getTypePost();
    Set<TypePostFilterDTO> typePostFilterDTO = postModel.getTypePostFilters();

    String contentCleanHTML = Jsoup.parse(content).text();
    String tiengVietKhongDau = StringUtils.clean(content);
    String searchField = tiengVietKhongDau + StringPool.SPACE + contentCleanHTML;

    if (salePrice != null && price != null) percentSale = (float) (1 - (salePrice / price)) * 100;

    postDetailsDTO.setUuid(UUID.randomUUID());
    postDetailsDTO.setPostDetailsId(postDetailsId);
    postDetailsDTO.setContent(content);
    postDetailsDTO.setDataSize((long) content.getBytes().length);
    postDetailsDTO.setComment(comment);
    postDetailsDTO.setSearchField(searchField);

    log.debug("postDetailsDTO = {}", postDetailsDTO);

    PostDetails resultPostDetails = postDetailsRepository.save(postDetailsMapper.toEntity(postDetailsDTO));
    resultPostDetails.cleanInfiniteInterlockingRelationship();
    postDetailsSearchRepository.save(resultPostDetails);

    log.info(resultPostDetails);


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
    simplePostDTO.setDataSize((long) simpleContent.getBytes().length);
    simplePostDTO.setComment(comment);
    simplePostDTO.setTypePost(typePost);
    simplePostDTO.setTypePostFilters(typePostFilterDTO);
    simplePostDTO.setPostDetails(postDetailsMapper.toDto(resultPostDetails));
    SimplePost simplePost = simplePostMapper.toEntity(simplePostDTO);

    log.debug("simplePostDTO = {}", simplePostDTO);

    Long postDetails_Id = resultPostDetails.getId();
    postDetailsRepository.findById(postDetails_Id).ifPresent(simplePost::setPostDetails);

    SimplePost resultSimplePost = simplePostRepository.save(simplePost);
    resultSimplePost.cleanInfiniteInterlockingRelationship();
    simplePostSearchRepository.save(resultSimplePost);
    log.debug("sau khi lưu simple-post vào es : {} , {}", resultSimplePost, resultPostDetails);

    if (postModel.getId() == null)
      log.info("upload post success");
    else
      log.info("update post success");

//    PostDetails postDetailsDTOForSearch = resultSimplePost.getPostDetails();
//    String contentCleanHTML = Jsoup.parse(content).text();
//    searchField = StringUtils.clean(content);
//
//    postDetailsDTOForSearch.setContent(contentCleanHTML);
//    simplePostDTO.setSearchField(null);
//    postDetailsDTOForSearch.setSearchField(searchField);
//    resultSimplePost.setPostDetails(postDetailsDTOForSearch);
//
//    postDetailsSearchRepository.save(resultPostDetails);
//    simplePostSearchRepository.save(resultSimplePost);
//    log.debug("sau khi lưu simple-post vào es : {} , {}", resultSimplePost, resultPostDetails);
//
//    log.info("upload post success");

    return resultSimplePost.getId();
  }

  //  @Override
  public Long partialUpdatePost(PostModel postModel, Long simplePost_Id, Long posDetail_Id)
  {
    SimplePostDTO simplePostDTO = new SimplePostDTO();
    PostDetailsDTO postDetailsDTO = new PostDetailsDTO();
    simplePostDTO.setId(simplePost_Id);
    postDetailsDTO.setId(posDetail_Id);

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
    Instant nowTime = Instant.now();
    String thisUser = SecurityUtils.getCurrentUserLogin().orElse(StringPool.BLANK);
    TypePostDTO typePost = postModel.getTypePost();
    Set<TypePostFilterDTO> typePostFilterDTO = postModel.getTypePostFilters();

    String searchField = StringPool.BLANK;

    if (salePrice != null && price != null && price != 0)
      percentSale = (float) (salePrice / price);
    else throw new NhechException(
      "bạn cần phải xem lại giá bán , giảm giá"
    );

    postDetailsDTO.setPostDetailsId(postDetailsId);
    postDetailsDTO.setContent(content);
    postDetailsDTO.setModifiedDate(nowTime);
    postDetailsDTO.setModifiedBy(thisUser);
    postDetailsDTO.setDataSize((long) content.getBytes().length);
    postDetailsDTO.setComment(comment);

    Optional<PostDetailsDTO> resultPostDetails = postDetailsService.partialUpdate(postDetailsDTO);
    if (resultPostDetails.isEmpty()) throw new NhechException();

    simplePostDTO.setTitle(title);
    simplePostDTO.setPrice(price);
    simplePostDTO.setSalePrice(salePrice);
    simplePostDTO.setPercentSale(percentSale);
    simplePostDTO.setImageUrl(imageUrl);
    simplePostDTO.setScores(scores);
    simplePostDTO.setSimpleContent(simpleContent);
    simplePostDTO.setOtherInfo(otherInfo);
    simplePostDTO.setSearchField(searchField);
    simplePostDTO.setModifiedDate(nowTime);
    simplePostDTO.setModifiedBy(thisUser);
    simplePostDTO.setDataSize((long) simpleContent.getBytes().length);
    simplePostDTO.setComment(comment);
    simplePostDTO.setTypePost(typePost);
    simplePostDTO.setTypePostFilters(typePostFilterDTO);
    simplePostDTO.setPostDetails(resultPostDetails.get());

    if (simplePostService.partialUpdate(simplePostDTO).isEmpty()) throw new NhechException();
    else log.info(
      "update simplePost and PostDetails succeed."
    );
    return simplePost_Id;
  }

  @Override
  public Long save(PostModel postModel)
  {
    if (!SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.MANAGEMENT) || SecurityUtils.getCurrentUserLogin().isEmpty())
      throw new BadRequestAlertException("đéo phải quản lý thì làm gì có cái quyền upload bạn ơi", null, "notManagement");

    return createNewPost(postModel);
//    if (optionalSimplePostDTO.isEmpty())
//    {
//      return createNewPost(postModel);
//    }
//    else
//    {
//      SimplePostDTO oldSimplePostDTO = optionalSimplePostDTO.get();
//      long simplePostDTO_ID = oldSimplePostDTO.getId();
//      Optional<PostDetailsDTO> optionalPostDetailsDTO = postDetailsService.findOne(simplePostDTO_ID);
//      if (optionalPostDetailsDTO.isPresent())
//      {
//        PostDetailsDTO oldPostDetailsDTO = optionalPostDetailsDTO.get();
//        return partialUpdatePost(postModel, oldSimplePostDTO.getId(), oldPostDetailsDTO.getId());
//      }
//      log.warn("cần xử lý trong tương lai");
//      return null;
//    }
  }

  @Override
  public void deletePost(long simplePostId)
  {
    simplePostService.findOne(simplePostId)
      .ifPresent(simplePostDTO ->
      {
        if (simplePostDTO.getPostDetails() != null)
        {
          Long postDetailsId = simplePostDTO.getPostDetails().getId();
          if (postDetailsId != null)
          {
            simplePostService.delete(simplePostId);
            postDetailsService.delete(postDetailsId);
            log.debug("deleted succeed : simplePostId = {} , posDetailId = {}", simplePostId, postDetailsId);
          }
          else throw new NhechException("not find postDetails");
        }
        else simplePostService.delete(simplePostId);
      });
  }

  @Override
  public Optional<PostModel> getPostBySimplePost_Id(long simplePostId)
  {
    return simplePostRepository.findById(simplePostId).map(simplePost ->
    {
      PostDetails postDetails = simplePost.getPostDetails() != null ? simplePost.getPostDetails() : new PostDetails();
      TypePostDTO typePostDTO = typePostMapper.toDto(simplePost.getTypePost());
      Set<TypePostFilterDTO> typePostFilterDTOs = new HashSet<>();
      simplePost.getTypePostFilters().forEach(typePostFilter -> typePostFilterDTOs.add(typePostFilterMapper.toDto(typePostFilter)));
      return PostModel.builder()
        .id(simplePost.getId())
        .imageUrl(simplePost.getImageUrl())
        .otherInfo(simplePost.getOtherInfo())
        .comment(simplePost.getComment())
        .simpleContent(simplePost.getSimpleContent())
        .percentSale(simplePost.getPercentSale())
        .price(simplePost.getPrice())
        .typePost(typePostDTO)
        .salePrice(simplePost.getSalePrice())
        .scores(simplePost.getScores())
        .title(simplePost.getTitle())
        .typePostFilters(typePostFilterDTOs)

        .postDetailsId(postDetails.getPostDetailsId())
        .content(postDetails.getContent())
        .build();
    });
  }

  @Override
  public Page<PostModel> getAllPost(Pageable pageable)
  {
    List<PostModel> postModels = new ArrayList<>();
    simplePostRepository.findAll(pageable).forEach(simplePost ->
    {
      PostDetails postDetails = simplePost.getPostDetails() != null ? simplePost.getPostDetails() : new PostDetails();
      TypePostDTO typePostDTO = typePostMapper.toDto(simplePost.getTypePost());
      Set<TypePostFilterDTO> typePostFilterDTOs = new HashSet<>();
      simplePost.getTypePostFilters().forEach(typePostFilter -> typePostFilterDTOs.add(typePostFilterMapper.toDto(typePostFilter)));

      postModels.add(PostModel.builder()
        .id(simplePost.getId())
        .imageUrl(simplePost.getImageUrl())
        .otherInfo(simplePost.getOtherInfo())
        .comment(simplePost.getComment())
        .simpleContent(simplePost.getSimpleContent())
        .percentSale(simplePost.getPercentSale())
        .price(simplePost.getPrice())
        .typePost(typePostDTO)
        .salePrice(simplePost.getSalePrice())
        .scores(simplePost.getScores())
        .title(simplePost.getTitle())
        .typePostFilters(typePostFilterDTOs)

        .content(postDetails.getContent())
        .postDetailsId(postDetails.getPostDetailsId())
        .build());
    });
    return new PageImpl<>(postModels);

  }
}
