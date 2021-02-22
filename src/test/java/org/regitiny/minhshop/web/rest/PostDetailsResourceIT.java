package org.regitiny.minhshop.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.regitiny.minhshop.IntegrationTest;
import org.regitiny.minhshop.domain.PostDetails;
import org.regitiny.minhshop.repository.PostDetailsRepository;
import org.regitiny.minhshop.repository.search.PostDetailsSearchRepository;
import org.regitiny.minhshop.service.dto.PostDetailsDTO;
import org.regitiny.minhshop.service.mapper.PostDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PostDetailsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class PostDetailsResourceIT
{

  private static final UUID DEFAULT_UUID = UUID.randomUUID();
  private static final UUID UPDATED_UUID = UUID.randomUUID();

  private static final String DEFAULT_POST_DETAILS_ID = "yaVttR4";
  private static final String UPDATED_POST_DETAILS_ID = "Chb`2";

  private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
  private static final String UPDATED_CONTENT = "BBBBBBBBBB";

  private static final String DEFAULT_SEARCH_FIELD = "AAAAAAAAAA";
  private static final String UPDATED_SEARCH_FIELD = "BBBBBBBBBB";

  private static final String DEFAULT_ROLE = "AAAAAAAAAA";
  private static final String UPDATED_ROLE = "BBBBBBBBBB";

  private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
  private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

  private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
  private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

  private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
  private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

  private static final Long DEFAULT_DATA_SIZE = 1L;
  private static final Long UPDATED_DATA_SIZE = 2L;

  private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
  private static final String UPDATED_COMMENT = "BBBBBBBBBB";

  private static final String DEFAULT_OTHER_DATA = "AAAAAAAAAA";
  private static final String UPDATED_OTHER_DATA = "BBBBBBBBBB";

  @Autowired
  private PostDetailsRepository postDetailsRepository;

  @Autowired
  private PostDetailsMapper postDetailsMapper;

  /**
   * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
   *
   * @see org.regitiny.minhshop.repository.search.PostDetailsSearchRepositoryMockConfiguration
   */
  @Autowired
  private PostDetailsSearchRepository mockPostDetailsSearchRepository;

  @Autowired
  private EntityManager em;

  @Autowired
  private MockMvc restPostDetailsMockMvc;

  private PostDetails postDetails;

  /**
   * Create an entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PostDetails createEntity(EntityManager em)
  {
    PostDetails postDetails = new PostDetails()
      .uuid(DEFAULT_UUID)
      .postDetailsId(DEFAULT_POST_DETAILS_ID)
      .content(DEFAULT_CONTENT)
      .searchField(DEFAULT_SEARCH_FIELD)
      .role(DEFAULT_ROLE)
      .createdDate(DEFAULT_CREATED_DATE)
      .modifiedDate(DEFAULT_MODIFIED_DATE)
      .createdBy(DEFAULT_CREATED_BY)
      .modifiedBy(DEFAULT_MODIFIED_BY)
      .dataSize(DEFAULT_DATA_SIZE)
      .comment(DEFAULT_COMMENT)
      .otherData(DEFAULT_OTHER_DATA);
    return postDetails;
  }

  /**
   * Create an updated entity for this test.
   * <p>
   * This is a static method, as tests for other entities might also need it,
   * if they test an entity which requires the current entity.
   */
  public static PostDetails createUpdatedEntity(EntityManager em)
  {
    PostDetails postDetails = new PostDetails()
      .uuid(UPDATED_UUID)
      .postDetailsId(UPDATED_POST_DETAILS_ID)
      .content(UPDATED_CONTENT)
      .searchField(UPDATED_SEARCH_FIELD)
      .role(UPDATED_ROLE)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY)
      .dataSize(UPDATED_DATA_SIZE)
      .comment(UPDATED_COMMENT)
      .otherData(UPDATED_OTHER_DATA);
    return postDetails;
  }

  @BeforeEach
  public void initTest()
  {
    postDetails = createEntity(em);
  }

  @Test
  @Transactional
  void createPostDetails() throws Exception
  {
    int databaseSizeBeforeCreate = postDetailsRepository.findAll().size();
    // Create the PostDetails
    PostDetailsDTO postDetailsDTO = postDetailsMapper.toDto(postDetails);
    restPostDetailsMockMvc
      .perform(
        post("/api/post-details").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDetailsDTO))
      )
      .andExpect(status().isCreated());

    // Validate the PostDetails in the database
    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeCreate + 1);
    PostDetails testPostDetails = postDetailsList.get(postDetailsList.size() - 1);
    assertThat(testPostDetails.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testPostDetails.getPostDetailsId()).isEqualTo(DEFAULT_POST_DETAILS_ID);
    assertThat(testPostDetails.getContent()).isEqualTo(DEFAULT_CONTENT);
    assertThat(testPostDetails.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
    assertThat(testPostDetails.getRole()).isEqualTo(DEFAULT_ROLE);
    assertThat(testPostDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testPostDetails.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
    assertThat(testPostDetails.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
    assertThat(testPostDetails.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
    assertThat(testPostDetails.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
    assertThat(testPostDetails.getComment()).isEqualTo(DEFAULT_COMMENT);
    assertThat(testPostDetails.getOtherData()).isEqualTo(DEFAULT_OTHER_DATA);

    // Validate the PostDetails in Elasticsearch
    verify(mockPostDetailsSearchRepository, times(1)).save(testPostDetails);
  }

  @Test
  @Transactional
  void createPostDetailsWithExistingId() throws Exception
  {
    // Create the PostDetails with an existing ID
    postDetails.setId(1L);
    PostDetailsDTO postDetailsDTO = postDetailsMapper.toDto(postDetails);

    int databaseSizeBeforeCreate = postDetailsRepository.findAll().size();

    // An entity with an existing ID cannot be created, so this API call must fail
    restPostDetailsMockMvc
      .perform(
        post("/api/post-details").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDetailsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostDetails in the database
    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeCreate);

    // Validate the PostDetails in Elasticsearch
    verify(mockPostDetailsSearchRepository, times(0)).save(postDetails);
  }

  @Test
  @Transactional
  void checkUuidIsRequired() throws Exception
  {
    int databaseSizeBeforeTest = postDetailsRepository.findAll().size();
    // set the field null
    postDetails.setUuid(null);

    // Create the PostDetails, which fails.
    PostDetailsDTO postDetailsDTO = postDetailsMapper.toDto(postDetails);

    restPostDetailsMockMvc
      .perform(
        post("/api/post-details").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDetailsDTO))
      )
      .andExpect(status().isBadRequest());

    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void checkPostDetailsIdIsRequired() throws Exception
  {
    int databaseSizeBeforeTest = postDetailsRepository.findAll().size();
    // set the field null
    postDetails.setPostDetailsId(null);

    // Create the PostDetails, which fails.
    PostDetailsDTO postDetailsDTO = postDetailsMapper.toDto(postDetails);

    restPostDetailsMockMvc
      .perform(
        post("/api/post-details").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDetailsDTO))
      )
      .andExpect(status().isBadRequest());

    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeTest);
  }

  @Test
  @Transactional
  void getAllPostDetails() throws Exception
  {
    // Initialize the database
    postDetailsRepository.saveAndFlush(postDetails);

    // Get all the postDetailsList
    restPostDetailsMockMvc
      .perform(get("/api/post-details?sort=id,desc"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postDetails.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].postDetailsId").value(hasItem(DEFAULT_POST_DETAILS_ID)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
      .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())))
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
      .andExpect(jsonPath("$.[*].otherData").value(hasItem(DEFAULT_OTHER_DATA)));
  }

  @Test
  @Transactional
  void getPostDetails() throws Exception
  {
    // Initialize the database
    postDetailsRepository.saveAndFlush(postDetails);

    // Get the postDetails
    restPostDetailsMockMvc
      .perform(get("/api/post-details/{id}", postDetails.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.id").value(postDetails.getId().intValue()))
      .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
      .andExpect(jsonPath("$.postDetailsId").value(DEFAULT_POST_DETAILS_ID))
      .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
      .andExpect(jsonPath("$.searchField").value(DEFAULT_SEARCH_FIELD.toString()))
      .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
      .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
      .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
      .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
      .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
      .andExpect(jsonPath("$.dataSize").value(DEFAULT_DATA_SIZE.intValue()))
      .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
      .andExpect(jsonPath("$.otherData").value(DEFAULT_OTHER_DATA));
  }

  @Test
  @Transactional
  void getNonExistingPostDetails() throws Exception
  {
    // Get the postDetails
    restPostDetailsMockMvc.perform(get("/api/post-details/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
  }

  @Test
  @Transactional
  void updatePostDetails() throws Exception
  {
    // Initialize the database
    postDetailsRepository.saveAndFlush(postDetails);

    int databaseSizeBeforeUpdate = postDetailsRepository.findAll().size();

    // Update the postDetails
    PostDetails updatedPostDetails = postDetailsRepository.findById(postDetails.getId()).get();
    // Disconnect from session so that the updates on updatedPostDetails are not directly saved in db
    em.detach(updatedPostDetails);
    updatedPostDetails
      .uuid(UPDATED_UUID)
      .postDetailsId(UPDATED_POST_DETAILS_ID)
      .content(UPDATED_CONTENT)
      .searchField(UPDATED_SEARCH_FIELD)
      .role(UPDATED_ROLE)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY)
      .dataSize(UPDATED_DATA_SIZE)
      .comment(UPDATED_COMMENT)
      .otherData(UPDATED_OTHER_DATA);
    PostDetailsDTO postDetailsDTO = postDetailsMapper.toDto(updatedPostDetails);

    restPostDetailsMockMvc
      .perform(
        put("/api/post-details").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDetailsDTO))
      )
      .andExpect(status().isOk());

    // Validate the PostDetails in the database
    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeUpdate);
    PostDetails testPostDetails = postDetailsList.get(postDetailsList.size() - 1);
    assertThat(testPostDetails.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPostDetails.getPostDetailsId()).isEqualTo(UPDATED_POST_DETAILS_ID);
    assertThat(testPostDetails.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testPostDetails.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
    assertThat(testPostDetails.getRole()).isEqualTo(UPDATED_ROLE);
    assertThat(testPostDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testPostDetails.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testPostDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testPostDetails.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    assertThat(testPostDetails.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
    assertThat(testPostDetails.getComment()).isEqualTo(UPDATED_COMMENT);
    assertThat(testPostDetails.getOtherData()).isEqualTo(UPDATED_OTHER_DATA);

    // Validate the PostDetails in Elasticsearch
    verify(mockPostDetailsSearchRepository).save(testPostDetails);
  }

  @Test
  @Transactional
  void updateNonExistingPostDetails() throws Exception
  {
    int databaseSizeBeforeUpdate = postDetailsRepository.findAll().size();

    // Create the PostDetails
    PostDetailsDTO postDetailsDTO = postDetailsMapper.toDto(postDetails);

    // If the entity doesn't have an ID, it will throw BadRequestAlertException
    restPostDetailsMockMvc
      .perform(
        put("/api/post-details").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postDetailsDTO))
      )
      .andExpect(status().isBadRequest());

    // Validate the PostDetails in the database
    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeUpdate);

    // Validate the PostDetails in Elasticsearch
    verify(mockPostDetailsSearchRepository, times(0)).save(postDetails);
  }

  @Test
  @Transactional
  void partialUpdatePostDetailsWithPatch() throws Exception
  {
    // Initialize the database
    postDetailsRepository.saveAndFlush(postDetails);

    int databaseSizeBeforeUpdate = postDetailsRepository.findAll().size();

    // Update the postDetails using partial update
    PostDetails partialUpdatedPostDetails = new PostDetails();
    partialUpdatedPostDetails.setId(postDetails.getId());

    partialUpdatedPostDetails
      .content(UPDATED_CONTENT)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY)
      .dataSize(UPDATED_DATA_SIZE)
      .otherData(UPDATED_OTHER_DATA);

    restPostDetailsMockMvc
      .perform(
        patch("/api/post-details")
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostDetails))
      )
      .andExpect(status().isOk());

    // Validate the PostDetails in the database
    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeUpdate);
    PostDetails testPostDetails = postDetailsList.get(postDetailsList.size() - 1);
    assertThat(testPostDetails.getUuid()).isEqualTo(DEFAULT_UUID);
    assertThat(testPostDetails.getPostDetailsId()).isEqualTo(DEFAULT_POST_DETAILS_ID);
    assertThat(testPostDetails.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testPostDetails.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
    assertThat(testPostDetails.getRole()).isEqualTo(DEFAULT_ROLE);
    assertThat(testPostDetails.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
    assertThat(testPostDetails.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testPostDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testPostDetails.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    assertThat(testPostDetails.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
    assertThat(testPostDetails.getComment()).isEqualTo(DEFAULT_COMMENT);
    assertThat(testPostDetails.getOtherData()).isEqualTo(UPDATED_OTHER_DATA);
  }

  @Test
  @Transactional
  void fullUpdatePostDetailsWithPatch() throws Exception
  {
    // Initialize the database
    postDetailsRepository.saveAndFlush(postDetails);

    int databaseSizeBeforeUpdate = postDetailsRepository.findAll().size();

    // Update the postDetails using partial update
    PostDetails partialUpdatedPostDetails = new PostDetails();
    partialUpdatedPostDetails.setId(postDetails.getId());

    partialUpdatedPostDetails
      .uuid(UPDATED_UUID)
      .postDetailsId(UPDATED_POST_DETAILS_ID)
      .content(UPDATED_CONTENT)
      .searchField(UPDATED_SEARCH_FIELD)
      .role(UPDATED_ROLE)
      .createdDate(UPDATED_CREATED_DATE)
      .modifiedDate(UPDATED_MODIFIED_DATE)
      .createdBy(UPDATED_CREATED_BY)
      .modifiedBy(UPDATED_MODIFIED_BY)
      .dataSize(UPDATED_DATA_SIZE)
      .comment(UPDATED_COMMENT)
      .otherData(UPDATED_OTHER_DATA);

    restPostDetailsMockMvc
      .perform(
        patch("/api/post-details")
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostDetails))
      )
      .andExpect(status().isOk());

    // Validate the PostDetails in the database
    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeUpdate);
    PostDetails testPostDetails = postDetailsList.get(postDetailsList.size() - 1);
    assertThat(testPostDetails.getUuid()).isEqualTo(UPDATED_UUID);
    assertThat(testPostDetails.getPostDetailsId()).isEqualTo(UPDATED_POST_DETAILS_ID);
    assertThat(testPostDetails.getContent()).isEqualTo(UPDATED_CONTENT);
    assertThat(testPostDetails.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
    assertThat(testPostDetails.getRole()).isEqualTo(UPDATED_ROLE);
    assertThat(testPostDetails.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
    assertThat(testPostDetails.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
    assertThat(testPostDetails.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
    assertThat(testPostDetails.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    assertThat(testPostDetails.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
    assertThat(testPostDetails.getComment()).isEqualTo(UPDATED_COMMENT);
    assertThat(testPostDetails.getOtherData()).isEqualTo(UPDATED_OTHER_DATA);
  }

  @Test
  @Transactional
  void partialUpdatePostDetailsShouldThrown() throws Exception
  {
    // Update the postDetails without id should throw
    PostDetails partialUpdatedPostDetails = new PostDetails();

    restPostDetailsMockMvc
      .perform(
        patch("/api/post-details")
          .contentType("application/merge-patch+json")
          .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostDetails))
      )
      .andExpect(status().isBadRequest());
  }

  @Test
  @Transactional
  void deletePostDetails() throws Exception
  {
    // Initialize the database
    postDetailsRepository.saveAndFlush(postDetails);

    int databaseSizeBeforeDelete = postDetailsRepository.findAll().size();

    // Delete the postDetails
    restPostDetailsMockMvc
      .perform(delete("/api/post-details/{id}", postDetails.getId()).accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isNoContent());

    // Validate the database contains one less item
    List<PostDetails> postDetailsList = postDetailsRepository.findAll();
    assertThat(postDetailsList).hasSize(databaseSizeBeforeDelete - 1);

    // Validate the PostDetails in Elasticsearch
    verify(mockPostDetailsSearchRepository, times(1)).deleteById(postDetails.getId());
  }

  @Test
  @Transactional
  void searchPostDetails() throws Exception
  {
    // Configure the mock search repository
    // Initialize the database
    postDetailsRepository.saveAndFlush(postDetails);
    when(mockPostDetailsSearchRepository.search(queryStringQuery("id:" + postDetails.getId()), PageRequest.of(0, 20)))
      .thenReturn(new PageImpl<>(Collections.singletonList(postDetails), PageRequest.of(0, 1), 1));

    // Search the postDetails
    restPostDetailsMockMvc
      .perform(get("/api/_search/post-details?query=id:" + postDetails.getId()))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
      .andExpect(jsonPath("$.[*].id").value(hasItem(postDetails.getId().intValue())))
      .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
      .andExpect(jsonPath("$.[*].postDetailsId").value(hasItem(DEFAULT_POST_DETAILS_ID)))
      .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
      .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
      .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
      .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
      .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
      .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
      .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
      .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())))
      .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
      .andExpect(jsonPath("$.[*].otherData").value(hasItem(DEFAULT_OTHER_DATA)));
  }
}
