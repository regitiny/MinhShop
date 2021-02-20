package org.regitiny.minhshop.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.regitiny.minhshop.IntegrationTest;
import org.regitiny.minhshop.domain.HanhChinhVN;
import org.regitiny.minhshop.repository.HanhChinhVNRepository;
import org.regitiny.minhshop.repository.search.HanhChinhVNSearchRepository;
import org.regitiny.minhshop.service.dto.HanhChinhVNDTO;
import org.regitiny.minhshop.service.mapper.HanhChinhVNMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link HanhChinhVNResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class HanhChinhVNResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_SLUG = "AAAAAAAAAA";
    private static final String UPDATED_SLUG = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_WITH_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_WITH_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PARENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PARENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PATH = "AAAAAAAAAA";
    private static final String UPDATED_PATH = "BBBBBBBBBB";

    private static final String DEFAULT_PATH_WITH_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PATH_WITH_TYPE = "BBBBBBBBBB";

    @Autowired
    private HanhChinhVNRepository hanhChinhVNRepository;

    @Autowired
    private HanhChinhVNMapper hanhChinhVNMapper;

    /**
     * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
     *
     * @see org.regitiny.minhshop.repository.search.HanhChinhVNSearchRepositoryMockConfiguration
     */
    @Autowired
    private HanhChinhVNSearchRepository mockHanhChinhVNSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHanhChinhVNMockMvc;

    private HanhChinhVN hanhChinhVN;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HanhChinhVN createEntity(EntityManager em) {
        HanhChinhVN hanhChinhVN = new HanhChinhVN()
            .name(DEFAULT_NAME)
            .slug(DEFAULT_SLUG)
            .type(DEFAULT_TYPE)
            .nameWithType(DEFAULT_NAME_WITH_TYPE)
            .code(DEFAULT_CODE)
            .parentCode(DEFAULT_PARENT_CODE)
            .path(DEFAULT_PATH)
            .pathWithType(DEFAULT_PATH_WITH_TYPE);
        return hanhChinhVN;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HanhChinhVN createUpdatedEntity(EntityManager em) {
        HanhChinhVN hanhChinhVN = new HanhChinhVN()
            .name(UPDATED_NAME)
            .slug(UPDATED_SLUG)
            .type(UPDATED_TYPE)
            .nameWithType(UPDATED_NAME_WITH_TYPE)
            .code(UPDATED_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .path(UPDATED_PATH)
            .pathWithType(UPDATED_PATH_WITH_TYPE);
        return hanhChinhVN;
    }

    @BeforeEach
    public void initTest() {
        hanhChinhVN = createEntity(em);
    }

    @Test
    @Transactional
    void createHanhChinhVN() throws Exception {
        int databaseSizeBeforeCreate = hanhChinhVNRepository.findAll().size();
        // Create the HanhChinhVN
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);
        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isCreated());

        // Validate the HanhChinhVN in the database
        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeCreate + 1);
        HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
        assertThat(testHanhChinhVN.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHanhChinhVN.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testHanhChinhVN.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(DEFAULT_NAME_WITH_TYPE);
        assertThat(testHanhChinhVN.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testHanhChinhVN.getParentCode()).isEqualTo(DEFAULT_PARENT_CODE);
        assertThat(testHanhChinhVN.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(DEFAULT_PATH_WITH_TYPE);

        // Validate the HanhChinhVN in Elasticsearch
        verify(mockHanhChinhVNSearchRepository, times(1)).save(testHanhChinhVN);
    }

    @Test
    @Transactional
    void createHanhChinhVNWithExistingId() throws Exception {
        // Create the HanhChinhVN with an existing ID
        hanhChinhVN.setId(1L);
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        int databaseSizeBeforeCreate = hanhChinhVNRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HanhChinhVN in the database
        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeCreate);

        // Validate the HanhChinhVN in Elasticsearch
        verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
        // set the field null
        hanhChinhVN.setName(null);

        // Create the HanhChinhVN, which fails.
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSlugIsRequired() throws Exception {
        int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
        // set the field null
        hanhChinhVN.setSlug(null);

        // Create the HanhChinhVN, which fails.
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
        // set the field null
        hanhChinhVN.setType(null);

        // Create the HanhChinhVN, which fails.
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkNameWithTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
        // set the field null
        hanhChinhVN.setNameWithType(null);

        // Create the HanhChinhVN, which fails.
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
        // set the field null
        hanhChinhVN.setCode(null);

        // Create the HanhChinhVN, which fails.
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkParentCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = hanhChinhVNRepository.findAll().size();
        // set the field null
        hanhChinhVN.setParentCode(null);

        // Create the HanhChinhVN, which fails.
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        restHanhChinhVNMockMvc
            .perform(
                post("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllHanhChinhVNS() throws Exception {
        // Initialize the database
        hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

        // Get all the hanhChinhVNList
        restHanhChinhVNMockMvc
            .perform(get("/api/hanh-chinh-vns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hanhChinhVN.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].nameWithType").value(hasItem(DEFAULT_NAME_WITH_TYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].pathWithType").value(hasItem(DEFAULT_PATH_WITH_TYPE)));
    }

    @Test
    @Transactional
    void getHanhChinhVN() throws Exception {
        // Initialize the database
        hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

        // Get the hanhChinhVN
        restHanhChinhVNMockMvc
            .perform(get("/api/hanh-chinh-vns/{id}", hanhChinhVN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hanhChinhVN.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.slug").value(DEFAULT_SLUG))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.nameWithType").value(DEFAULT_NAME_WITH_TYPE))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.parentCode").value(DEFAULT_PARENT_CODE))
            .andExpect(jsonPath("$.path").value(DEFAULT_PATH))
            .andExpect(jsonPath("$.pathWithType").value(DEFAULT_PATH_WITH_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingHanhChinhVN() throws Exception {
        // Get the hanhChinhVN
        restHanhChinhVNMockMvc.perform(get("/api/hanh-chinh-vns/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateHanhChinhVN() throws Exception {
        // Initialize the database
        hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

        int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();

        // Update the hanhChinhVN
        HanhChinhVN updatedHanhChinhVN = hanhChinhVNRepository.findById(hanhChinhVN.getId()).get();
        // Disconnect from session so that the updates on updatedHanhChinhVN are not directly saved in db
        em.detach(updatedHanhChinhVN);
        updatedHanhChinhVN
            .name(UPDATED_NAME)
            .slug(UPDATED_SLUG)
            .type(UPDATED_TYPE)
            .nameWithType(UPDATED_NAME_WITH_TYPE)
            .code(UPDATED_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .path(UPDATED_PATH)
            .pathWithType(UPDATED_PATH_WITH_TYPE);
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(updatedHanhChinhVN);

        restHanhChinhVNMockMvc
            .perform(
                put("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isOk());

        // Validate the HanhChinhVN in the database
        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);
        HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
        assertThat(testHanhChinhVN.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHanhChinhVN.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testHanhChinhVN.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(UPDATED_NAME_WITH_TYPE);
        assertThat(testHanhChinhVN.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testHanhChinhVN.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
        assertThat(testHanhChinhVN.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(UPDATED_PATH_WITH_TYPE);

        // Validate the HanhChinhVN in Elasticsearch
        verify(mockHanhChinhVNSearchRepository).save(testHanhChinhVN);
    }

    @Test
    @Transactional
    void updateNonExistingHanhChinhVN() throws Exception {
        int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();

        // Create the HanhChinhVN
        HanhChinhVNDTO hanhChinhVNDTO = hanhChinhVNMapper.toDto(hanhChinhVN);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHanhChinhVNMockMvc
            .perform(
                put("/api/hanh-chinh-vns")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(hanhChinhVNDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the HanhChinhVN in the database
        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);

        // Validate the HanhChinhVN in Elasticsearch
        verify(mockHanhChinhVNSearchRepository, times(0)).save(hanhChinhVN);
    }

    @Test
    @Transactional
    void partialUpdateHanhChinhVNWithPatch() throws Exception {
        // Initialize the database
        hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

        int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();

        // Update the hanhChinhVN using partial update
        HanhChinhVN partialUpdatedHanhChinhVN = new HanhChinhVN();
        partialUpdatedHanhChinhVN.setId(hanhChinhVN.getId());

        partialUpdatedHanhChinhVN
            .type(UPDATED_TYPE)
            .code(UPDATED_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .pathWithType(UPDATED_PATH_WITH_TYPE);

        restHanhChinhVNMockMvc
            .perform(
                patch("/api/hanh-chinh-vns")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHanhChinhVN))
            )
            .andExpect(status().isOk());

        // Validate the HanhChinhVN in the database
        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);
        HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
        assertThat(testHanhChinhVN.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testHanhChinhVN.getSlug()).isEqualTo(DEFAULT_SLUG);
        assertThat(testHanhChinhVN.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(DEFAULT_NAME_WITH_TYPE);
        assertThat(testHanhChinhVN.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testHanhChinhVN.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
        assertThat(testHanhChinhVN.getPath()).isEqualTo(DEFAULT_PATH);
        assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(UPDATED_PATH_WITH_TYPE);
    }

    @Test
    @Transactional
    void fullUpdateHanhChinhVNWithPatch() throws Exception {
        // Initialize the database
        hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

        int databaseSizeBeforeUpdate = hanhChinhVNRepository.findAll().size();

        // Update the hanhChinhVN using partial update
        HanhChinhVN partialUpdatedHanhChinhVN = new HanhChinhVN();
        partialUpdatedHanhChinhVN.setId(hanhChinhVN.getId());

        partialUpdatedHanhChinhVN
            .name(UPDATED_NAME)
            .slug(UPDATED_SLUG)
            .type(UPDATED_TYPE)
            .nameWithType(UPDATED_NAME_WITH_TYPE)
            .code(UPDATED_CODE)
            .parentCode(UPDATED_PARENT_CODE)
            .path(UPDATED_PATH)
            .pathWithType(UPDATED_PATH_WITH_TYPE);

        restHanhChinhVNMockMvc
            .perform(
                patch("/api/hanh-chinh-vns")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHanhChinhVN))
            )
            .andExpect(status().isOk());

        // Validate the HanhChinhVN in the database
        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeUpdate);
        HanhChinhVN testHanhChinhVN = hanhChinhVNList.get(hanhChinhVNList.size() - 1);
        assertThat(testHanhChinhVN.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testHanhChinhVN.getSlug()).isEqualTo(UPDATED_SLUG);
        assertThat(testHanhChinhVN.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testHanhChinhVN.getNameWithType()).isEqualTo(UPDATED_NAME_WITH_TYPE);
        assertThat(testHanhChinhVN.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testHanhChinhVN.getParentCode()).isEqualTo(UPDATED_PARENT_CODE);
        assertThat(testHanhChinhVN.getPath()).isEqualTo(UPDATED_PATH);
        assertThat(testHanhChinhVN.getPathWithType()).isEqualTo(UPDATED_PATH_WITH_TYPE);
    }

    @Test
    @Transactional
    void partialUpdateHanhChinhVNShouldThrown() throws Exception {
        // Update the hanhChinhVN without id should throw
        HanhChinhVN partialUpdatedHanhChinhVN = new HanhChinhVN();

        restHanhChinhVNMockMvc
            .perform(
                patch("/api/hanh-chinh-vns")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedHanhChinhVN))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteHanhChinhVN() throws Exception {
        // Initialize the database
        hanhChinhVNRepository.saveAndFlush(hanhChinhVN);

        int databaseSizeBeforeDelete = hanhChinhVNRepository.findAll().size();

        // Delete the hanhChinhVN
        restHanhChinhVNMockMvc
            .perform(delete("/api/hanh-chinh-vns/{id}", hanhChinhVN.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HanhChinhVN> hanhChinhVNList = hanhChinhVNRepository.findAll();
        assertThat(hanhChinhVNList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the HanhChinhVN in Elasticsearch
        verify(mockHanhChinhVNSearchRepository, times(1)).deleteById(hanhChinhVN.getId());
    }

    @Test
    @Transactional
    void searchHanhChinhVN() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        hanhChinhVNRepository.saveAndFlush(hanhChinhVN);
        when(mockHanhChinhVNSearchRepository.search(queryStringQuery("id:" + hanhChinhVN.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(hanhChinhVN), PageRequest.of(0, 1), 1));

        // Search the hanhChinhVN
        restHanhChinhVNMockMvc
            .perform(get("/api/_search/hanh-chinh-vns?query=id:" + hanhChinhVN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hanhChinhVN.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].slug").value(hasItem(DEFAULT_SLUG)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].nameWithType").value(hasItem(DEFAULT_NAME_WITH_TYPE)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].parentCode").value(hasItem(DEFAULT_PARENT_CODE)))
            .andExpect(jsonPath("$.[*].path").value(hasItem(DEFAULT_PATH)))
            .andExpect(jsonPath("$.[*].pathWithType").value(hasItem(DEFAULT_PATH_WITH_TYPE)));
    }
}
