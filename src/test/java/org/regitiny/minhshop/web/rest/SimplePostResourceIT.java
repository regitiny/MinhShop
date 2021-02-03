package org.regitiny.minhshop.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.regitiny.minhshop.IntegrationTest;
import org.regitiny.minhshop.domain.PostDetails;
import org.regitiny.minhshop.domain.SimplePost;
import org.regitiny.minhshop.repository.SimplePostRepository;
import org.regitiny.minhshop.repository.search.SimplePostSearchRepository;
import org.regitiny.minhshop.service.SimplePostService;
import org.regitiny.minhshop.service.dto.SimplePostDTO;
import org.regitiny.minhshop.service.mapper.SimplePostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

/**
 * Integration tests for the {@link SimplePostResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class SimplePostResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Double DEFAULT_PRICE = 1D;
    private static final Double UPDATED_PRICE = 2D;

    private static final Double DEFAULT_SALE_PRICE = 1D;
    private static final Double UPDATED_SALE_PRICE = 2D;

    private static final Float DEFAULT_PERCENT_SALE = 0F;
    private static final Float UPDATED_PERCENT_SALE = 1F;

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final Float DEFAULT_SCORES = 0F;
    private static final Float UPDATED_SCORES = 1F;

    private static final String DEFAULT_SIMPLE_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_SIMPLE_CONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_INFO = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_INFO = "BBBBBBBBBB";

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

    @Autowired
    private SimplePostRepository simplePostRepository;

    @Mock
    private SimplePostRepository simplePostRepositoryMock;

    @Autowired
    private SimplePostMapper simplePostMapper;

    @Mock
    private SimplePostService simplePostServiceMock;

    /**
     * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
     *
     * @see org.regitiny.minhshop.repository.search.SimplePostSearchRepositoryMockConfiguration
     */
    @Autowired
    private SimplePostSearchRepository mockSimplePostSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSimplePostMockMvc;

    private SimplePost simplePost;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SimplePost createEntity(EntityManager em) {
        SimplePost simplePost = new SimplePost()
            .uuid(DEFAULT_UUID)
            .title(DEFAULT_TITLE)
            .price(DEFAULT_PRICE)
            .salePrice(DEFAULT_SALE_PRICE)
            .percentSale(DEFAULT_PERCENT_SALE)
            .imageUrl(DEFAULT_IMAGE_URL)
            .scores(DEFAULT_SCORES)
            .simpleContent(DEFAULT_SIMPLE_CONTENT)
            .otherInfo(DEFAULT_OTHER_INFO)
            .searchField(DEFAULT_SEARCH_FIELD)
            .role(DEFAULT_ROLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .dataSize(DEFAULT_DATA_SIZE)
            .comment(DEFAULT_COMMENT);
        // Add required entity
        PostDetails postDetails;
        if (TestUtil.findAll(em, PostDetails.class).isEmpty()) {
            postDetails = PostDetailsResourceIT.createEntity(em);
            em.persist(postDetails);
            em.flush();
        } else {
            postDetails = TestUtil.findAll(em, PostDetails.class).get(0);
        }
        simplePost.setPostDetails(postDetails);
        return simplePost;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SimplePost createUpdatedEntity(EntityManager em) {
        SimplePost simplePost = new SimplePost()
            .uuid(UPDATED_UUID)
            .title(UPDATED_TITLE)
            .price(UPDATED_PRICE)
            .salePrice(UPDATED_SALE_PRICE)
            .percentSale(UPDATED_PERCENT_SALE)
            .imageUrl(UPDATED_IMAGE_URL)
            .scores(UPDATED_SCORES)
            .simpleContent(UPDATED_SIMPLE_CONTENT)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        // Add required entity
        PostDetails postDetails;
        if (TestUtil.findAll(em, PostDetails.class).isEmpty()) {
            postDetails = PostDetailsResourceIT.createUpdatedEntity(em);
            em.persist(postDetails);
            em.flush();
        } else {
            postDetails = TestUtil.findAll(em, PostDetails.class).get(0);
        }
        simplePost.setPostDetails(postDetails);
        return simplePost;
    }

    @BeforeEach
    public void initTest() {
        simplePost = createEntity(em);
    }

    @Test
    @Transactional
    void createSimplePost() throws Exception {
        int databaseSizeBeforeCreate = simplePostRepository.findAll().size();
        // Create the SimplePost
        SimplePostDTO simplePostDTO = simplePostMapper.toDto(simplePost);
        restSimplePostMockMvc
            .perform(
                post("/api/simple-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(simplePostDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SimplePost in the database
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeCreate + 1);
        SimplePost testSimplePost = simplePostList.get(simplePostList.size() - 1);
        assertThat(testSimplePost.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testSimplePost.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSimplePost.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSimplePost.getSalePrice()).isEqualTo(DEFAULT_SALE_PRICE);
        assertThat(testSimplePost.getPercentSale()).isEqualTo(DEFAULT_PERCENT_SALE);
        assertThat(testSimplePost.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testSimplePost.getScores()).isEqualTo(DEFAULT_SCORES);
        assertThat(testSimplePost.getSimpleContent()).isEqualTo(DEFAULT_SIMPLE_CONTENT);
        assertThat(testSimplePost.getOtherInfo()).isEqualTo(DEFAULT_OTHER_INFO);
        assertThat(testSimplePost.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        assertThat(testSimplePost.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testSimplePost.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSimplePost.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testSimplePost.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSimplePost.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testSimplePost.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testSimplePost.getComment()).isEqualTo(DEFAULT_COMMENT);

        // Validate the id for MapsId, the ids must be same
        assertThat(testSimplePost.getId()).isEqualTo(testSimplePost.getPostDetails().getId());

        // Validate the SimplePost in Elasticsearch
        verify(mockSimplePostSearchRepository, times(1)).save(testSimplePost);
    }

    @Test
    @Transactional
    void createSimplePostWithExistingId() throws Exception {
        // Create the SimplePost with an existing ID
        simplePost.setId(1L);
        SimplePostDTO simplePostDTO = simplePostMapper.toDto(simplePost);

        int databaseSizeBeforeCreate = simplePostRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSimplePostMockMvc
            .perform(
                post("/api/simple-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(simplePostDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SimplePost in the database
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeCreate);

        // Validate the SimplePost in Elasticsearch
        verify(mockSimplePostSearchRepository, times(0)).save(simplePost);
    }

    @Test
    @Transactional
    void updateSimplePostMapsIdAssociationWithNewId() throws Exception {
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);
        int databaseSizeBeforeCreate = simplePostRepository.findAll().size();

        // Add a new parent entity
        PostDetails postDetails = PostDetailsResourceIT.createUpdatedEntity(em);
        em.persist(postDetails);
        em.flush();

        // Load the simplePost
        SimplePost updatedSimplePost = simplePostRepository.findById(simplePost.getId()).get();
        assertThat(updatedSimplePost).isNotNull();
        // Disconnect from session so that the updates on updatedSimplePost are not directly saved in db
        em.detach(updatedSimplePost);

        // Update the PostDetails with new association value
        updatedSimplePost.setPostDetails(postDetails);
        SimplePostDTO updatedSimplePostDTO = simplePostMapper.toDto(updatedSimplePost);
        assertThat(updatedSimplePostDTO).isNotNull();

        // Update the entity
        restSimplePostMockMvc
            .perform(
                put("/api/simple-posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedSimplePostDTO))
            )
            .andExpect(status().isOk());

        // Validate the SimplePost in the database
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeCreate);
        SimplePost testSimplePost = simplePostList.get(simplePostList.size() - 1);

        // Validate the id for MapsId, the ids must be same
        // Uncomment the following line for assertion. However, please note that there is a known issue and uncommenting will fail the test.
        // Please look at https://github.com/jhipster/generator-jhipster/issues/9100. You can modify this test as necessary.
        // assertThat(testSimplePost.getId()).isEqualTo(testSimplePost.getPostDetails().getId());

        // Validate the SimplePost in Elasticsearch
        verify(mockSimplePostSearchRepository).save(simplePost);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = simplePostRepository.findAll().size();
        // set the field null
        simplePost.setUuid(null);

        // Create the SimplePost, which fails.
        SimplePostDTO simplePostDTO = simplePostMapper.toDto(simplePost);

        restSimplePostMockMvc
            .perform(
                post("/api/simple-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(simplePostDTO))
            )
            .andExpect(status().isBadRequest());

        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTitleIsRequired() throws Exception {
        int databaseSizeBeforeTest = simplePostRepository.findAll().size();
        // set the field null
        simplePost.setTitle(null);

        // Create the SimplePost, which fails.
        SimplePostDTO simplePostDTO = simplePostMapper.toDto(simplePost);

        restSimplePostMockMvc
            .perform(
                post("/api/simple-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(simplePostDTO))
            )
            .andExpect(status().isBadRequest());

        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkImageUrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = simplePostRepository.findAll().size();
        // set the field null
        simplePost.setImageUrl(null);

        // Create the SimplePost, which fails.
        SimplePostDTO simplePostDTO = simplePostMapper.toDto(simplePost);

        restSimplePostMockMvc
            .perform(
                post("/api/simple-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(simplePostDTO))
            )
            .andExpect(status().isBadRequest());

        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSimplePosts() throws Exception {
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);

        // Get all the simplePostList
        restSimplePostMockMvc
            .perform(get("/api/simple-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(simplePost.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].salePrice").value(hasItem(DEFAULT_SALE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].percentSale").value(hasItem(DEFAULT_PERCENT_SALE.doubleValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].scores").value(hasItem(DEFAULT_SCORES.doubleValue())))
            .andExpect(jsonPath("$.[*].simpleContent").value(hasItem(DEFAULT_SIMPLE_CONTENT)))
            .andExpect(jsonPath("$.[*].otherInfo").value(hasItem(DEFAULT_OTHER_INFO)))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSimplePostsWithEagerRelationshipsIsEnabled() throws Exception {
        when(simplePostServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSimplePostMockMvc.perform(get("/api/simple-posts?eagerload=true")).andExpect(status().isOk());

        verify(simplePostServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllSimplePostsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(simplePostServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restSimplePostMockMvc.perform(get("/api/simple-posts?eagerload=true")).andExpect(status().isOk());

        verify(simplePostServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getSimplePost() throws Exception {
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);

        // Get the simplePost
        restSimplePostMockMvc
            .perform(get("/api/simple-posts/{id}", simplePost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(simplePost.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
            .andExpect(jsonPath("$.price").value(DEFAULT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.salePrice").value(DEFAULT_SALE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.percentSale").value(DEFAULT_PERCENT_SALE.doubleValue()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL))
            .andExpect(jsonPath("$.scores").value(DEFAULT_SCORES.doubleValue()))
            .andExpect(jsonPath("$.simpleContent").value(DEFAULT_SIMPLE_CONTENT))
            .andExpect(jsonPath("$.otherInfo").value(DEFAULT_OTHER_INFO))
            .andExpect(jsonPath("$.searchField").value(DEFAULT_SEARCH_FIELD.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.dataSize").value(DEFAULT_DATA_SIZE.intValue()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT));
    }

    @Test
    @Transactional
    void getNonExistingSimplePost() throws Exception {
        // Get the simplePost
        restSimplePostMockMvc.perform(get("/api/simple-posts/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateSimplePost() throws Exception {
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);

        int databaseSizeBeforeUpdate = simplePostRepository.findAll().size();

        // Update the simplePost
        SimplePost updatedSimplePost = simplePostRepository.findById(simplePost.getId()).get();
        // Disconnect from session so that the updates on updatedSimplePost are not directly saved in db
        em.detach(updatedSimplePost);
        updatedSimplePost
            .uuid(UPDATED_UUID)
            .title(UPDATED_TITLE)
            .price(UPDATED_PRICE)
            .salePrice(UPDATED_SALE_PRICE)
            .percentSale(UPDATED_PERCENT_SALE)
            .imageUrl(UPDATED_IMAGE_URL)
            .scores(UPDATED_SCORES)
            .simpleContent(UPDATED_SIMPLE_CONTENT)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        SimplePostDTO simplePostDTO = simplePostMapper.toDto(updatedSimplePost);

        restSimplePostMockMvc
            .perform(
                put("/api/simple-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(simplePostDTO))
            )
            .andExpect(status().isOk());

        // Validate the SimplePost in the database
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeUpdate);
        SimplePost testSimplePost = simplePostList.get(simplePostList.size() - 1);
        assertThat(testSimplePost.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testSimplePost.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSimplePost.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSimplePost.getSalePrice()).isEqualTo(UPDATED_SALE_PRICE);
        assertThat(testSimplePost.getPercentSale()).isEqualTo(UPDATED_PERCENT_SALE);
        assertThat(testSimplePost.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testSimplePost.getScores()).isEqualTo(UPDATED_SCORES);
        assertThat(testSimplePost.getSimpleContent()).isEqualTo(UPDATED_SIMPLE_CONTENT);
        assertThat(testSimplePost.getOtherInfo()).isEqualTo(UPDATED_OTHER_INFO);
        assertThat(testSimplePost.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testSimplePost.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testSimplePost.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSimplePost.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testSimplePost.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSimplePost.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testSimplePost.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testSimplePost.getComment()).isEqualTo(UPDATED_COMMENT);

        // Validate the SimplePost in Elasticsearch
        verify(mockSimplePostSearchRepository).save(testSimplePost);
    }

    @Test
    @Transactional
    void updateNonExistingSimplePost() throws Exception {
        int databaseSizeBeforeUpdate = simplePostRepository.findAll().size();

        // Create the SimplePost
        SimplePostDTO simplePostDTO = simplePostMapper.toDto(simplePost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSimplePostMockMvc
            .perform(
                put("/api/simple-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(simplePostDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SimplePost in the database
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeUpdate);

        // Validate the SimplePost in Elasticsearch
        verify(mockSimplePostSearchRepository, times(0)).save(simplePost);
    }

    @Test
    @Transactional
    void partialUpdateSimplePostWithPatch() throws Exception {
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);

        int databaseSizeBeforeUpdate = simplePostRepository.findAll().size();

        // Update the simplePost using partial update
        SimplePost partialUpdatedSimplePost = new SimplePost();
        partialUpdatedSimplePost.setId(simplePost.getId());

        partialUpdatedSimplePost
            .imageUrl(UPDATED_IMAGE_URL)
            .scores(UPDATED_SCORES)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);

        restSimplePostMockMvc
            .perform(
                patch("/api/simple-posts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSimplePost))
            )
            .andExpect(status().isOk());

        // Validate the SimplePost in the database
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeUpdate);
        SimplePost testSimplePost = simplePostList.get(simplePostList.size() - 1);
        assertThat(testSimplePost.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testSimplePost.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testSimplePost.getPrice()).isEqualTo(DEFAULT_PRICE);
        assertThat(testSimplePost.getSalePrice()).isEqualTo(DEFAULT_SALE_PRICE);
        assertThat(testSimplePost.getPercentSale()).isEqualTo(DEFAULT_PERCENT_SALE);
        assertThat(testSimplePost.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testSimplePost.getScores()).isEqualTo(UPDATED_SCORES);
        assertThat(testSimplePost.getSimpleContent()).isEqualTo(DEFAULT_SIMPLE_CONTENT);
        assertThat(testSimplePost.getOtherInfo()).isEqualTo(UPDATED_OTHER_INFO);
        assertThat(testSimplePost.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testSimplePost.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testSimplePost.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testSimplePost.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testSimplePost.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSimplePost.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testSimplePost.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testSimplePost.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateSimplePostWithPatch() throws Exception {
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);

        int databaseSizeBeforeUpdate = simplePostRepository.findAll().size();

        // Update the simplePost using partial update
        SimplePost partialUpdatedSimplePost = new SimplePost();
        partialUpdatedSimplePost.setId(simplePost.getId());

        partialUpdatedSimplePost
            .uuid(UPDATED_UUID)
            .title(UPDATED_TITLE)
            .price(UPDATED_PRICE)
            .salePrice(UPDATED_SALE_PRICE)
            .percentSale(UPDATED_PERCENT_SALE)
            .imageUrl(UPDATED_IMAGE_URL)
            .scores(UPDATED_SCORES)
            .simpleContent(UPDATED_SIMPLE_CONTENT)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);

        restSimplePostMockMvc
            .perform(
                patch("/api/simple-posts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSimplePost))
            )
            .andExpect(status().isOk());

        // Validate the SimplePost in the database
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeUpdate);
        SimplePost testSimplePost = simplePostList.get(simplePostList.size() - 1);
        assertThat(testSimplePost.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testSimplePost.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testSimplePost.getPrice()).isEqualTo(UPDATED_PRICE);
        assertThat(testSimplePost.getSalePrice()).isEqualTo(UPDATED_SALE_PRICE);
        assertThat(testSimplePost.getPercentSale()).isEqualTo(UPDATED_PERCENT_SALE);
        assertThat(testSimplePost.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testSimplePost.getScores()).isEqualTo(UPDATED_SCORES);
        assertThat(testSimplePost.getSimpleContent()).isEqualTo(UPDATED_SIMPLE_CONTENT);
        assertThat(testSimplePost.getOtherInfo()).isEqualTo(UPDATED_OTHER_INFO);
        assertThat(testSimplePost.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testSimplePost.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testSimplePost.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testSimplePost.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testSimplePost.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSimplePost.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testSimplePost.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testSimplePost.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void partialUpdateSimplePostShouldThrown() throws Exception {
        // Update the simplePost without id should throw
        SimplePost partialUpdatedSimplePost = new SimplePost();

        restSimplePostMockMvc
            .perform(
                patch("/api/simple-posts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSimplePost))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteSimplePost() throws Exception {
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);

        int databaseSizeBeforeDelete = simplePostRepository.findAll().size();

        // Delete the simplePost
        restSimplePostMockMvc
            .perform(delete("/api/simple-posts/{id}", simplePost.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SimplePost> simplePostList = simplePostRepository.findAll();
        assertThat(simplePostList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the SimplePost in Elasticsearch
        verify(mockSimplePostSearchRepository, times(1)).deleteById(simplePost.getId());
    }

    @Test
    @Transactional
    void searchSimplePost() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        simplePostRepository.saveAndFlush(simplePost);
        when(mockSimplePostSearchRepository.search(queryStringQuery("id:" + simplePost.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(simplePost), PageRequest.of(0, 1), 1));

        // Search the simplePost
        restSimplePostMockMvc
            .perform(get("/api/_search/simple-posts?query=id:" + simplePost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(simplePost.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
            .andExpect(jsonPath("$.[*].price").value(hasItem(DEFAULT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].salePrice").value(hasItem(DEFAULT_SALE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].percentSale").value(hasItem(DEFAULT_PERCENT_SALE.doubleValue())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL)))
            .andExpect(jsonPath("$.[*].scores").value(hasItem(DEFAULT_SCORES.doubleValue())))
            .andExpect(jsonPath("$.[*].simpleContent").value(hasItem(DEFAULT_SIMPLE_CONTENT)))
            .andExpect(jsonPath("$.[*].otherInfo").value(hasItem(DEFAULT_OTHER_INFO)))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
    }
}
