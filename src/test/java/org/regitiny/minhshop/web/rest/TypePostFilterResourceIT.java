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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.regitiny.minhshop.IntegrationTest;
import org.regitiny.minhshop.domain.TypePostFilter;
import org.regitiny.minhshop.repository.TypePostFilterRepository;
import org.regitiny.minhshop.repository.search.TypePostFilterSearchRepository;
import org.regitiny.minhshop.service.dto.TypePostFilterDTO;
import org.regitiny.minhshop.service.mapper.TypePostFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link TypePostFilterResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TypePostFilterResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final String DEFAULT_TYPE_FILTER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FILTER_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_MODIFIED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_MODIFIED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_MODIFIED_BY = "BBBBBBBBBB";

    @Autowired
    private TypePostFilterRepository typePostFilterRepository;

    @Autowired
    private TypePostFilterMapper typePostFilterMapper;

    /**
     * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
     *
     * @see org.regitiny.minhshop.repository.search.TypePostFilterSearchRepositoryMockConfiguration
     */
    @Autowired
    private TypePostFilterSearchRepository mockTypePostFilterSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypePostFilterMockMvc;

    private TypePostFilter typePostFilter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePostFilter createEntity(EntityManager em) {
        TypePostFilter typePostFilter = new TypePostFilter()
            .uuid(DEFAULT_UUID)
            .typeFilterName(DEFAULT_TYPE_FILTER_NAME)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY);
        return typePostFilter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypePostFilter createUpdatedEntity(EntityManager em) {
        TypePostFilter typePostFilter = new TypePostFilter()
            .uuid(UPDATED_UUID)
            .typeFilterName(UPDATED_TYPE_FILTER_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);
        return typePostFilter;
    }

    @BeforeEach
    public void initTest() {
        typePostFilter = createEntity(em);
    }

    @Test
    @Transactional
    void createTypePostFilter() throws Exception {
        int databaseSizeBeforeCreate = typePostFilterRepository.findAll().size();
        // Create the TypePostFilter
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);
        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TypePostFilter in the database
        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeCreate + 1);
        TypePostFilter testTypePostFilter = typePostFilterList.get(typePostFilterList.size() - 1);
        assertThat(testTypePostFilter.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testTypePostFilter.getTypeFilterName()).isEqualTo(DEFAULT_TYPE_FILTER_NAME);
        assertThat(testTypePostFilter.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testTypePostFilter.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTypePostFilter.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testTypePostFilter.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);

        // Validate the TypePostFilter in Elasticsearch
        verify(mockTypePostFilterSearchRepository, times(1)).save(testTypePostFilter);
    }

    @Test
    @Transactional
    void createTypePostFilterWithExistingId() throws Exception {
        // Create the TypePostFilter with an existing ID
        typePostFilter.setId(1L);
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        int databaseSizeBeforeCreate = typePostFilterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypePostFilter in the database
        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeCreate);

        // Validate the TypePostFilter in Elasticsearch
        verify(mockTypePostFilterSearchRepository, times(0)).save(typePostFilter);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostFilterRepository.findAll().size();
        // set the field null
        typePostFilter.setUuid(null);

        // Create the TypePostFilter, which fails.
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeFilterNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostFilterRepository.findAll().size();
        // set the field null
        typePostFilter.setTypeFilterName(null);

        // Create the TypePostFilter, which fails.
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostFilterRepository.findAll().size();
        // set the field null
        typePostFilter.setCreatedDate(null);

        // Create the TypePostFilter, which fails.
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifiedDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostFilterRepository.findAll().size();
        // set the field null
        typePostFilter.setModifiedDate(null);

        // Create the TypePostFilter, which fails.
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCreatedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostFilterRepository.findAll().size();
        // set the field null
        typePostFilter.setCreatedBy(null);

        // Create the TypePostFilter, which fails.
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkModifiedByIsRequired() throws Exception {
        int databaseSizeBeforeTest = typePostFilterRepository.findAll().size();
        // set the field null
        typePostFilter.setModifiedBy(null);

        // Create the TypePostFilter, which fails.
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        restTypePostFilterMockMvc
            .perform(
                post("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllTypePostFilters() throws Exception {
        // Initialize the database
        typePostFilterRepository.saveAndFlush(typePostFilter);

        // Get all the typePostFilterList
        restTypePostFilterMockMvc
            .perform(get("/api/type-post-filters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typePostFilter.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].typeFilterName").value(hasItem(DEFAULT_TYPE_FILTER_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)));
    }

    @Test
    @Transactional
    void getTypePostFilter() throws Exception {
        // Initialize the database
        typePostFilterRepository.saveAndFlush(typePostFilter);

        // Get the typePostFilter
        restTypePostFilterMockMvc
            .perform(get("/api/type-post-filters/{id}", typePostFilter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typePostFilter.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.typeFilterName").value(DEFAULT_TYPE_FILTER_NAME))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY));
    }

    @Test
    @Transactional
    void getNonExistingTypePostFilter() throws Exception {
        // Get the typePostFilter
        restTypePostFilterMockMvc.perform(get("/api/type-post-filters/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateTypePostFilter() throws Exception {
        // Initialize the database
        typePostFilterRepository.saveAndFlush(typePostFilter);

        int databaseSizeBeforeUpdate = typePostFilterRepository.findAll().size();

        // Update the typePostFilter
        TypePostFilter updatedTypePostFilter = typePostFilterRepository.findById(typePostFilter.getId()).get();
        // Disconnect from session so that the updates on updatedTypePostFilter are not directly saved in db
        em.detach(updatedTypePostFilter);
        updatedTypePostFilter
            .uuid(UPDATED_UUID)
            .typeFilterName(UPDATED_TYPE_FILTER_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(updatedTypePostFilter);

        restTypePostFilterMockMvc
            .perform(
                put("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isOk());

        // Validate the TypePostFilter in the database
        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeUpdate);
        TypePostFilter testTypePostFilter = typePostFilterList.get(typePostFilterList.size() - 1);
        assertThat(testTypePostFilter.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testTypePostFilter.getTypeFilterName()).isEqualTo(UPDATED_TYPE_FILTER_NAME);
        assertThat(testTypePostFilter.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTypePostFilter.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTypePostFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTypePostFilter.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);

        // Validate the TypePostFilter in Elasticsearch
        verify(mockTypePostFilterSearchRepository).save(testTypePostFilter);
    }

    @Test
    @Transactional
    void updateNonExistingTypePostFilter() throws Exception {
        int databaseSizeBeforeUpdate = typePostFilterRepository.findAll().size();

        // Create the TypePostFilter
        TypePostFilterDTO typePostFilterDTO = typePostFilterMapper.toDto(typePostFilter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypePostFilterMockMvc
            .perform(
                put("/api/type-post-filters")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(typePostFilterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TypePostFilter in the database
        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeUpdate);

        // Validate the TypePostFilter in Elasticsearch
        verify(mockTypePostFilterSearchRepository, times(0)).save(typePostFilter);
    }

    @Test
    @Transactional
    void partialUpdateTypePostFilterWithPatch() throws Exception {
        // Initialize the database
        typePostFilterRepository.saveAndFlush(typePostFilter);

        int databaseSizeBeforeUpdate = typePostFilterRepository.findAll().size();

        // Update the typePostFilter using partial update
        TypePostFilter partialUpdatedTypePostFilter = new TypePostFilter();
        partialUpdatedTypePostFilter.setId(typePostFilter.getId());

        partialUpdatedTypePostFilter
            .typeFilterName(UPDATED_TYPE_FILTER_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTypePostFilterMockMvc
            .perform(
                patch("/api/type-post-filters")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypePostFilter))
            )
            .andExpect(status().isOk());

        // Validate the TypePostFilter in the database
        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeUpdate);
        TypePostFilter testTypePostFilter = typePostFilterList.get(typePostFilterList.size() - 1);
        assertThat(testTypePostFilter.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testTypePostFilter.getTypeFilterName()).isEqualTo(UPDATED_TYPE_FILTER_NAME);
        assertThat(testTypePostFilter.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTypePostFilter.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testTypePostFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTypePostFilter.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    void fullUpdateTypePostFilterWithPatch() throws Exception {
        // Initialize the database
        typePostFilterRepository.saveAndFlush(typePostFilter);

        int databaseSizeBeforeUpdate = typePostFilterRepository.findAll().size();

        // Update the typePostFilter using partial update
        TypePostFilter partialUpdatedTypePostFilter = new TypePostFilter();
        partialUpdatedTypePostFilter.setId(typePostFilter.getId());

        partialUpdatedTypePostFilter
            .uuid(UPDATED_UUID)
            .typeFilterName(UPDATED_TYPE_FILTER_NAME)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restTypePostFilterMockMvc
            .perform(
                patch("/api/type-post-filters")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypePostFilter))
            )
            .andExpect(status().isOk());

        // Validate the TypePostFilter in the database
        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeUpdate);
        TypePostFilter testTypePostFilter = typePostFilterList.get(typePostFilterList.size() - 1);
        assertThat(testTypePostFilter.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testTypePostFilter.getTypeFilterName()).isEqualTo(UPDATED_TYPE_FILTER_NAME);
        assertThat(testTypePostFilter.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testTypePostFilter.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testTypePostFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testTypePostFilter.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
    }

    @Test
    @Transactional
    void partialUpdateTypePostFilterShouldThrown() throws Exception {
        // Update the typePostFilter without id should throw
        TypePostFilter partialUpdatedTypePostFilter = new TypePostFilter();

        restTypePostFilterMockMvc
            .perform(
                patch("/api/type-post-filters")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTypePostFilter))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteTypePostFilter() throws Exception {
        // Initialize the database
        typePostFilterRepository.saveAndFlush(typePostFilter);

        int databaseSizeBeforeDelete = typePostFilterRepository.findAll().size();

        // Delete the typePostFilter
        restTypePostFilterMockMvc
            .perform(delete("/api/type-post-filters/{id}", typePostFilter.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypePostFilter> typePostFilterList = typePostFilterRepository.findAll();
        assertThat(typePostFilterList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the TypePostFilter in Elasticsearch
        verify(mockTypePostFilterSearchRepository, times(1)).deleteById(typePostFilter.getId());
    }

    @Test
    @Transactional
    void searchTypePostFilter() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        typePostFilterRepository.saveAndFlush(typePostFilter);
        when(mockTypePostFilterSearchRepository.search(queryStringQuery("id:" + typePostFilter.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(typePostFilter), PageRequest.of(0, 1), 1));

        // Search the typePostFilter
        restTypePostFilterMockMvc
            .perform(get("/api/_search/type-post-filters?query=id:" + typePostFilter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typePostFilter.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].typeFilterName").value(hasItem(DEFAULT_TYPE_FILTER_NAME)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)));
    }
}
