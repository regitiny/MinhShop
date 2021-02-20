package org.regitiny.minhshop.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
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
import org.regitiny.minhshop.domain.UserOtherInfo;
import org.regitiny.minhshop.repository.UserOtherInfoRepository;
import org.regitiny.minhshop.repository.search.UserOtherInfoSearchRepository;
import org.regitiny.minhshop.service.dto.UserOtherInfoDTO;
import org.regitiny.minhshop.service.mapper.UserOtherInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link UserOtherInfoResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class UserOtherInfoResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final String DEFAULT_PHONE_NUMBER = "089059";
    private static final String UPDATED_PHONE_NUMBER = "7";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_WARD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_WARD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DIST_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DIST_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_DETAILS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_OF_BIRTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_BIRTH = LocalDate.now(ZoneId.systemDefault());

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
    private UserOtherInfoRepository userOtherInfoRepository;

    @Autowired
    private UserOtherInfoMapper userOtherInfoMapper;

    /**
     * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
     *
     * @see org.regitiny.minhshop.repository.search.UserOtherInfoSearchRepositoryMockConfiguration
     */
    @Autowired
    private UserOtherInfoSearchRepository mockUserOtherInfoSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUserOtherInfoMockMvc;

    private UserOtherInfo userOtherInfo;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserOtherInfo createEntity(EntityManager em) {
        UserOtherInfo userOtherInfo = new UserOtherInfo()
            .uuid(DEFAULT_UUID)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .wardCode(DEFAULT_WARD_CODE)
            .distCode(DEFAULT_DIST_CODE)
            .cityCode(DEFAULT_CITY_CODE)
            .addressDetails(DEFAULT_ADDRESS_DETAILS)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .otherInfo(DEFAULT_OTHER_INFO)
            .searchField(DEFAULT_SEARCH_FIELD)
            .role(DEFAULT_ROLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .dataSize(DEFAULT_DATA_SIZE)
            .comment(DEFAULT_COMMENT);
        return userOtherInfo;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UserOtherInfo createUpdatedEntity(EntityManager em) {
        UserOtherInfo userOtherInfo = new UserOtherInfo()
            .uuid(UPDATED_UUID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .wardCode(UPDATED_WARD_CODE)
            .distCode(UPDATED_DIST_CODE)
            .cityCode(UPDATED_CITY_CODE)
            .addressDetails(UPDATED_ADDRESS_DETAILS)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        return userOtherInfo;
    }

    @BeforeEach
    public void initTest() {
        userOtherInfo = createEntity(em);
    }

    @Test
    @Transactional
    void createUserOtherInfo() throws Exception {
        int databaseSizeBeforeCreate = userOtherInfoRepository.findAll().size();
        // Create the UserOtherInfo
        UserOtherInfoDTO userOtherInfoDTO = userOtherInfoMapper.toDto(userOtherInfo);
        restUserOtherInfoMockMvc
            .perform(
                post("/api/user-other-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userOtherInfoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the UserOtherInfo in the database
        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeCreate + 1);
        UserOtherInfo testUserOtherInfo = userOtherInfoList.get(userOtherInfoList.size() - 1);
        assertThat(testUserOtherInfo.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testUserOtherInfo.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserOtherInfo.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUserOtherInfo.getWardCode()).isEqualTo(DEFAULT_WARD_CODE);
        assertThat(testUserOtherInfo.getDistCode()).isEqualTo(DEFAULT_DIST_CODE);
        assertThat(testUserOtherInfo.getCityCode()).isEqualTo(DEFAULT_CITY_CODE);
        assertThat(testUserOtherInfo.getAddressDetails()).isEqualTo(DEFAULT_ADDRESS_DETAILS);
        assertThat(testUserOtherInfo.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testUserOtherInfo.getOtherInfo()).isEqualTo(DEFAULT_OTHER_INFO);
        assertThat(testUserOtherInfo.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        assertThat(testUserOtherInfo.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testUserOtherInfo.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testUserOtherInfo.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testUserOtherInfo.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUserOtherInfo.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testUserOtherInfo.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testUserOtherInfo.getComment()).isEqualTo(DEFAULT_COMMENT);

        // Validate the UserOtherInfo in Elasticsearch
        verify(mockUserOtherInfoSearchRepository, times(1)).save(testUserOtherInfo);
    }

    @Test
    @Transactional
    void createUserOtherInfoWithExistingId() throws Exception {
        // Create the UserOtherInfo with an existing ID
        userOtherInfo.setId(1L);
        UserOtherInfoDTO userOtherInfoDTO = userOtherInfoMapper.toDto(userOtherInfo);

        int databaseSizeBeforeCreate = userOtherInfoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserOtherInfoMockMvc
            .perform(
                post("/api/user-other-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userOtherInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserOtherInfo in the database
        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeCreate);

        // Validate the UserOtherInfo in Elasticsearch
        verify(mockUserOtherInfoSearchRepository, times(0)).save(userOtherInfo);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = userOtherInfoRepository.findAll().size();
        // set the field null
        userOtherInfo.setUuid(null);

        // Create the UserOtherInfo, which fails.
        UserOtherInfoDTO userOtherInfoDTO = userOtherInfoMapper.toDto(userOtherInfo);

        restUserOtherInfoMockMvc
            .perform(
                post("/api/user-other-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userOtherInfoDTO))
            )
            .andExpect(status().isBadRequest());

        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllUserOtherInfos() throws Exception {
        // Initialize the database
        userOtherInfoRepository.saveAndFlush(userOtherInfo);

        // Get all the userOtherInfoList
        restUserOtherInfoMockMvc
            .perform(get("/api/user-other-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userOtherInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].wardCode").value(hasItem(DEFAULT_WARD_CODE)))
            .andExpect(jsonPath("$.[*].distCode").value(hasItem(DEFAULT_DIST_CODE)))
            .andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE)))
            .andExpect(jsonPath("$.[*].addressDetails").value(hasItem(DEFAULT_ADDRESS_DETAILS)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
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

    @Test
    @Transactional
    void getUserOtherInfo() throws Exception {
        // Initialize the database
        userOtherInfoRepository.saveAndFlush(userOtherInfo);

        // Get the userOtherInfo
        restUserOtherInfoMockMvc
            .perform(get("/api/user-other-infos/{id}", userOtherInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(userOtherInfo.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.wardCode").value(DEFAULT_WARD_CODE))
            .andExpect(jsonPath("$.distCode").value(DEFAULT_DIST_CODE))
            .andExpect(jsonPath("$.cityCode").value(DEFAULT_CITY_CODE))
            .andExpect(jsonPath("$.addressDetails").value(DEFAULT_ADDRESS_DETAILS))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH.toString()))
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
    void getNonExistingUserOtherInfo() throws Exception {
        // Get the userOtherInfo
        restUserOtherInfoMockMvc.perform(get("/api/user-other-infos/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateUserOtherInfo() throws Exception {
        // Initialize the database
        userOtherInfoRepository.saveAndFlush(userOtherInfo);

        int databaseSizeBeforeUpdate = userOtherInfoRepository.findAll().size();

        // Update the userOtherInfo
        UserOtherInfo updatedUserOtherInfo = userOtherInfoRepository.findById(userOtherInfo.getId()).get();
        // Disconnect from session so that the updates on updatedUserOtherInfo are not directly saved in db
        em.detach(updatedUserOtherInfo);
        updatedUserOtherInfo
            .uuid(UPDATED_UUID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .wardCode(UPDATED_WARD_CODE)
            .distCode(UPDATED_DIST_CODE)
            .cityCode(UPDATED_CITY_CODE)
            .addressDetails(UPDATED_ADDRESS_DETAILS)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        UserOtherInfoDTO userOtherInfoDTO = userOtherInfoMapper.toDto(updatedUserOtherInfo);

        restUserOtherInfoMockMvc
            .perform(
                put("/api/user-other-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userOtherInfoDTO))
            )
            .andExpect(status().isOk());

        // Validate the UserOtherInfo in the database
        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeUpdate);
        UserOtherInfo testUserOtherInfo = userOtherInfoList.get(userOtherInfoList.size() - 1);
        assertThat(testUserOtherInfo.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testUserOtherInfo.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserOtherInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserOtherInfo.getWardCode()).isEqualTo(UPDATED_WARD_CODE);
        assertThat(testUserOtherInfo.getDistCode()).isEqualTo(UPDATED_DIST_CODE);
        assertThat(testUserOtherInfo.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testUserOtherInfo.getAddressDetails()).isEqualTo(UPDATED_ADDRESS_DETAILS);
        assertThat(testUserOtherInfo.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testUserOtherInfo.getOtherInfo()).isEqualTo(UPDATED_OTHER_INFO);
        assertThat(testUserOtherInfo.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testUserOtherInfo.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testUserOtherInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserOtherInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testUserOtherInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserOtherInfo.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testUserOtherInfo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testUserOtherInfo.getComment()).isEqualTo(UPDATED_COMMENT);

        // Validate the UserOtherInfo in Elasticsearch
        verify(mockUserOtherInfoSearchRepository).save(testUserOtherInfo);
    }

    @Test
    @Transactional
    void updateNonExistingUserOtherInfo() throws Exception {
        int databaseSizeBeforeUpdate = userOtherInfoRepository.findAll().size();

        // Create the UserOtherInfo
        UserOtherInfoDTO userOtherInfoDTO = userOtherInfoMapper.toDto(userOtherInfo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUserOtherInfoMockMvc
            .perform(
                put("/api/user-other-infos")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(userOtherInfoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the UserOtherInfo in the database
        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the UserOtherInfo in Elasticsearch
        verify(mockUserOtherInfoSearchRepository, times(0)).save(userOtherInfo);
    }

    @Test
    @Transactional
    void partialUpdateUserOtherInfoWithPatch() throws Exception {
        // Initialize the database
        userOtherInfoRepository.saveAndFlush(userOtherInfo);

        int databaseSizeBeforeUpdate = userOtherInfoRepository.findAll().size();

        // Update the userOtherInfo using partial update
        UserOtherInfo partialUpdatedUserOtherInfo = new UserOtherInfo();
        partialUpdatedUserOtherInfo.setId(userOtherInfo.getId());

        partialUpdatedUserOtherInfo
            .email(UPDATED_EMAIL)
            .cityCode(UPDATED_CITY_CODE)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE);

        restUserOtherInfoMockMvc
            .perform(
                patch("/api/user-other-infos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserOtherInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserOtherInfo in the database
        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeUpdate);
        UserOtherInfo testUserOtherInfo = userOtherInfoList.get(userOtherInfoList.size() - 1);
        assertThat(testUserOtherInfo.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testUserOtherInfo.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testUserOtherInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserOtherInfo.getWardCode()).isEqualTo(DEFAULT_WARD_CODE);
        assertThat(testUserOtherInfo.getDistCode()).isEqualTo(DEFAULT_DIST_CODE);
        assertThat(testUserOtherInfo.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testUserOtherInfo.getAddressDetails()).isEqualTo(DEFAULT_ADDRESS_DETAILS);
        assertThat(testUserOtherInfo.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testUserOtherInfo.getOtherInfo()).isEqualTo(UPDATED_OTHER_INFO);
        assertThat(testUserOtherInfo.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testUserOtherInfo.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testUserOtherInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserOtherInfo.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testUserOtherInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserOtherInfo.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testUserOtherInfo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testUserOtherInfo.getComment()).isEqualTo(DEFAULT_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateUserOtherInfoWithPatch() throws Exception {
        // Initialize the database
        userOtherInfoRepository.saveAndFlush(userOtherInfo);

        int databaseSizeBeforeUpdate = userOtherInfoRepository.findAll().size();

        // Update the userOtherInfo using partial update
        UserOtherInfo partialUpdatedUserOtherInfo = new UserOtherInfo();
        partialUpdatedUserOtherInfo.setId(userOtherInfo.getId());

        partialUpdatedUserOtherInfo
            .uuid(UPDATED_UUID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .wardCode(UPDATED_WARD_CODE)
            .distCode(UPDATED_DIST_CODE)
            .cityCode(UPDATED_CITY_CODE)
            .addressDetails(UPDATED_ADDRESS_DETAILS)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .otherInfo(UPDATED_OTHER_INFO)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);

        restUserOtherInfoMockMvc
            .perform(
                patch("/api/user-other-infos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserOtherInfo))
            )
            .andExpect(status().isOk());

        // Validate the UserOtherInfo in the database
        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeUpdate);
        UserOtherInfo testUserOtherInfo = userOtherInfoList.get(userOtherInfoList.size() - 1);
        assertThat(testUserOtherInfo.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testUserOtherInfo.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testUserOtherInfo.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUserOtherInfo.getWardCode()).isEqualTo(UPDATED_WARD_CODE);
        assertThat(testUserOtherInfo.getDistCode()).isEqualTo(UPDATED_DIST_CODE);
        assertThat(testUserOtherInfo.getCityCode()).isEqualTo(UPDATED_CITY_CODE);
        assertThat(testUserOtherInfo.getAddressDetails()).isEqualTo(UPDATED_ADDRESS_DETAILS);
        assertThat(testUserOtherInfo.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testUserOtherInfo.getOtherInfo()).isEqualTo(UPDATED_OTHER_INFO);
        assertThat(testUserOtherInfo.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testUserOtherInfo.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testUserOtherInfo.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testUserOtherInfo.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testUserOtherInfo.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUserOtherInfo.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testUserOtherInfo.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testUserOtherInfo.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void partialUpdateUserOtherInfoShouldThrown() throws Exception {
        // Update the userOtherInfo without id should throw
        UserOtherInfo partialUpdatedUserOtherInfo = new UserOtherInfo();

        restUserOtherInfoMockMvc
            .perform(
                patch("/api/user-other-infos")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedUserOtherInfo))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteUserOtherInfo() throws Exception {
        // Initialize the database
        userOtherInfoRepository.saveAndFlush(userOtherInfo);

        int databaseSizeBeforeDelete = userOtherInfoRepository.findAll().size();

        // Delete the userOtherInfo
        restUserOtherInfoMockMvc
            .perform(delete("/api/user-other-infos/{id}", userOtherInfo.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UserOtherInfo> userOtherInfoList = userOtherInfoRepository.findAll();
        assertThat(userOtherInfoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the UserOtherInfo in Elasticsearch
        verify(mockUserOtherInfoSearchRepository, times(1)).deleteById(userOtherInfo.getId());
    }

    @Test
    @Transactional
    void searchUserOtherInfo() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        userOtherInfoRepository.saveAndFlush(userOtherInfo);
        when(mockUserOtherInfoSearchRepository.search(queryStringQuery("id:" + userOtherInfo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(userOtherInfo), PageRequest.of(0, 1), 1));

        // Search the userOtherInfo
        restUserOtherInfoMockMvc
            .perform(get("/api/_search/user-other-infos?query=id:" + userOtherInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(userOtherInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].wardCode").value(hasItem(DEFAULT_WARD_CODE)))
            .andExpect(jsonPath("$.[*].distCode").value(hasItem(DEFAULT_DIST_CODE)))
            .andExpect(jsonPath("$.[*].cityCode").value(hasItem(DEFAULT_CITY_CODE)))
            .andExpect(jsonPath("$.[*].addressDetails").value(hasItem(DEFAULT_ADDRESS_DETAILS)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH.toString())))
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
