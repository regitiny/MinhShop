package org.regitiny.minhshop.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.regitiny.minhshop.IntegrationTest;
import org.regitiny.minhshop.domain.TypePost;
import org.regitiny.minhshop.repository.TypePostRepository;
import org.regitiny.minhshop.repository.search.TypePostSearchRepository;
import org.regitiny.minhshop.security.jwt.AuthUtils;
import org.regitiny.minhshop.service.dto.TypePostDTO;
import org.regitiny.minhshop.service.mapper.TypePostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TypePostResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TypePostResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final String DEFAULT_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_NAME = "BBBBBBBBBB";

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
    private TypePostRepository typePostRepository;

    @Autowired
    private TypePostMapper typePostMapper;

    /**
     * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
     *
     * @see org.regitiny.minhshop.repository.search.TypePostSearchRepositoryMockConfiguration
     */
    @Autowired
    private TypePostSearchRepository mockTypePostSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypePostMockMvc;

    @Autowired
    private AuthUtils authUtils;

    private TypePost typePost;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePost createEntity(EntityManager em) {
        TypePost typePost = new TypePost()
            .uuid(DEFAULT_UUID)
            .typeName(DEFAULT_TYPE_NAME)
            .searchField(DEFAULT_SEARCH_FIELD)
            .role(DEFAULT_ROLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .dataSize(DEFAULT_DATA_SIZE)
            .comment(DEFAULT_COMMENT);
        return typePost;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePost createUpdatedEntity(EntityManager em) {
        TypePost typePost = new TypePost()
            .uuid(UPDATED_UUID)
            .typeName(UPDATED_TYPE_NAME)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        return typePost;
    }

    @BeforeEach
    public void initTest() {
        typePost = createEntity(em);
    }

    @Test
    @Transactional
    void createTypePost() throws Exception {
        int databaseSizeBeforeCreate = typePostRepository.findAll().size();
        // Create the TypePost
        TypePostDTO typePostDTO = typePostMapper.toDto(typePost);
        restTypePostMockMvc
            .perform(
                post("/api/type-posts")
                    .header("Authorization", "Bearer " + authUtils.jwtAdminFullRole())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TypePost in the database
        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeCreate + 1);
        TypePost testTypePost = typePostList.get(typePostList.size() - 1);
        //    assertThat(testTypePost.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testTypePost.getTypeName()).isEqualTo(DEFAULT_TYPE_NAME);
        //    assertThat(testTypePost.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        //    assertThat(testTypePost.getRole()).isEqualTo(DEFAULT_ROLE);
        //    assertThat(testTypePost.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        //    assertThat(testTypePost.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        //    assertThat(testTypePost.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        //    assertThat(testTypePost.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        //    assertThat(testTypePost.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testTypePost.getComment()).isEqualTo(DEFAULT_COMMENT);

        // Validate the TypePost in Elasticsearch
        verify(mockTypePostSearchRepository, times(1)).save(testTypePost);
    }

    @Test
    @Transactional
    void createTypePostWithExistingId() throws Exception {
        // Create the TypePost with an existing ID
        typePost.setId(1L);
        TypePostDTO typePostDTO = typePostMapper.toDto(typePost);

        int databaseSizeBeforeCreate = typePostRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypePostMockMvc
            .perform(
                post("/api/type-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typePostDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypePost in the database
        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeCreate);

        // Validate the TypePost in Elasticsearch
        verify(mockTypePostSearchRepository, times(0)).save(typePost);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostRepository.findAll().size();
        // set the field null
        typePost.setUuid(null);

        // Create the TypePost, which fails.
        TypePostDTO typePostDTO = typePostMapper.toDto(typePost);

        restTypePostMockMvc
            .perform(
                post("/api/type-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typePostDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostRepository.findAll().size();
        // set the field null
        typePost.setTypeName(null);

        // Create the TypePost, which fails.
        TypePostDTO typePostDTO = typePostMapper.toDto(typePost);

        restTypePostMockMvc
            .perform(
                post("/api/type-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typePostDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTypePosts() throws Exception {
        // Initialize the database
        typePostRepository.saveAndFlush(typePost);

        // Get all the typePostList
        restTypePostMockMvc
            .perform(get("/api/type-posts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typePost.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].typeName").value(hasItem(DEFAULT_TYPE_NAME)))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)));
    }

    @Test
    @Transactional
    void getTypePost() throws Exception {
        // Initialize the database
        typePostRepository.saveAndFlush(typePost);

        // Get the typePost
        restTypePostMockMvc
            .perform(get("/api/type-posts/{id}", typePost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typePost.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.typeName").value(DEFAULT_TYPE_NAME))
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
    void getNonExistingTypePost() throws Exception {
        // Get the typePost
        restTypePostMockMvc.perform(get("/api/type-posts/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateTypePost() throws Exception {
        // Initialize the database
        typePostRepository.saveAndFlush(typePost);

        int databaseSizeBeforeUpdate = typePostRepository.findAll().size();

        // Update the typePost
        TypePost updatedTypePost = typePostRepository.findById(typePost.getId()).get();
        // Disconnect from session so that the updates on updatedTypePost are not directly saved in db
        em.detach(updatedTypePost);
        updatedTypePost
            .uuid(UPDATED_UUID)
            .typeName(UPDATED_TYPE_NAME)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        TypePostDTO typePostDTO = typePostMapper.toDto(updatedTypePost);

        restTypePostMockMvc
            .perform(
                put("/api/type-posts")
                    .header("Authorization", "Bearer " + authUtils.jwtAdminFullRole())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostDTO))
            )
            .andExpect(status().isOk());

        // Validate the TypePost in the database
        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeUpdate);
        TypePost testTypePost = typePostList.get(typePostList.size() - 1);
        //    assertThat(testTypePost.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testTypePost.getTypeName()).isEqualTo(UPDATED_TYPE_NAME);
        //    assertThat(testTypePost.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        //    assertThat(testTypePost.getRole()).isEqualTo(UPDATED_ROLE);
        //    assertThat(testTypePost.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        //    assertThat(testTypePost.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        //    assertThat(testTypePost.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        //    assertThat(testTypePost.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        //    assertThat(testTypePost.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testTypePost.getComment()).isEqualTo(UPDATED_COMMENT);

        // Validate the TypePost in Elasticsearch
        verify(mockTypePostSearchRepository).save(testTypePost);
    }

    @Test
    @Transactional
    void updateNonExistingTypePost() throws Exception {
        int databaseSizeBeforeUpdate = typePostRepository.findAll().size();

        // Create the TypePost
        TypePostDTO typePostDTO = typePostMapper.toDto(typePost);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypePostMockMvc
            .perform(put("/api/type-posts").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(typePostDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypePost in the database
        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TypePost in Elasticsearch
        verify(mockTypePostSearchRepository, times(0)).save(typePost);
    }

    @Test
    @Transactional
    void partialUpdateTypePostWithPatch() throws Exception {
        // Initialize the database
        typePostRepository.saveAndFlush(typePost);

        int databaseSizeBeforeUpdate = typePostRepository.findAll().size();

        // Update the typePost using partial update
        TypePost partialUpdatedTypePost = new TypePost();
        partialUpdatedTypePost.setId(typePost.getId());

        partialUpdatedTypePost.uuid(UPDATED_UUID).typeName(UPDATED_TYPE_NAME).role(UPDATED_ROLE).modifiedBy(UPDATED_MODIFIED_BY);

        restTypePostMockMvc
            .perform(
                patch("/api/type-posts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypePost))
            )
            .andExpect(status().isOk());

        // Validate the TypePost in the database
        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeUpdate);
        TypePost testTypePost = typePostList.get(typePostList.size() - 1);
        assertThat(testTypePost.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testTypePost.getTypeName()).isEqualTo(UPDATED_TYPE_NAME);
        assertThat(testTypePost.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        assertThat(testTypePost.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testTypePost.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTypePost.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTypePost.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTypePost.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTypePost.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testTypePost.getComment()).isEqualTo(DEFAULT_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateTypePostWithPatch() throws Exception {
        // Initialize the database
        typePostRepository.saveAndFlush(typePost);

        int databaseSizeBeforeUpdate = typePostRepository.findAll().size();

        // Update the typePost using partial update
        TypePost partialUpdatedTypePost = new TypePost();
        partialUpdatedTypePost.setId(typePost.getId());

        partialUpdatedTypePost
            .uuid(UPDATED_UUID)
            .typeName(UPDATED_TYPE_NAME)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);

        restTypePostMockMvc
            .perform(
                patch("/api/type-posts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypePost))
            )
            .andExpect(status().isOk());

        // Validate the TypePost in the database
        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeUpdate);
        TypePost testTypePost = typePostList.get(typePostList.size() - 1);
        assertThat(testTypePost.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testTypePost.getTypeName()).isEqualTo(UPDATED_TYPE_NAME);
        assertThat(testTypePost.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testTypePost.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testTypePost.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTypePost.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTypePost.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTypePost.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testTypePost.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testTypePost.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void partialUpdateTypePostShouldThrown() throws Exception {
        // Update the typePost without id should throw
        TypePost partialUpdatedTypePost = new TypePost();

        restTypePostMockMvc
            .perform(
                patch("/api/type-posts")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypePost))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteTypePost() throws Exception {
        // Initialize the database
        typePostRepository.saveAndFlush(typePost);

        int databaseSizeBeforeDelete = typePostRepository.findAll().size();

        // Delete the typePost
        restTypePostMockMvc
            .perform(delete("/api/type-posts/{id}", typePost.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypePost> typePostList = typePostRepository.findAll();
        assertThat(typePostList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TypePost in Elasticsearch
        verify(mockTypePostSearchRepository, times(1)).deleteById(typePost.getId());
    }

    @Test
    @Transactional
    void searchTypePost() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        typePostRepository.saveAndFlush(typePost);
        when(mockTypePostSearchRepository.search(queryStringQuery("id:" + typePost.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(typePost), PageRequest.of(0, 1), 1));

        // Search the typePost
        restTypePostMockMvc
            .perform(get("/api/_search/type-posts?query=id:" + typePost.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typePost.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].typeName").value(hasItem(DEFAULT_TYPE_NAME)))
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
