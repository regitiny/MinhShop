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
import org.regitiny.minhshop.domain.Bill;
import org.regitiny.minhshop.repository.BillRepository;
import org.regitiny.minhshop.repository.search.BillSearchRepository;
import org.regitiny.minhshop.security.jwt.AuthUtils;
import org.regitiny.minhshop.service.dto.BillDTO;
import org.regitiny.minhshop.service.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link BillResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class BillResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final String DEFAULT_BILL_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_NUMBER = "0284730471";
    private static final String UPDATED_PHONE_NUMBER = "7083740284";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

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

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    /**
     * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
     *
     * @see org.regitiny.minhshop.repository.search.BillSearchRepositoryMockConfiguration
     */
    @Autowired
    private BillSearchRepository mockBillSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBillMockMvc;

    @Autowired
    private AuthUtils authUtils;

    private Bill bill;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bill createEntity(EntityManager em) {
        Bill bill = new Bill()
            .uuid(DEFAULT_UUID)
            .billId(DEFAULT_BILL_ID)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .addressDetails(DEFAULT_ADDRESS_DETAILS)
            .addressCode(DEFAULT_ADDRESS_CODE)
            .product(DEFAULT_PRODUCT)
            .comment(DEFAULT_COMMENT)
            .searchField(DEFAULT_SEARCH_FIELD)
            .role(DEFAULT_ROLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .dataSize(DEFAULT_DATA_SIZE);
        return bill;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bill createUpdatedEntity(EntityManager em) {
        Bill bill = new Bill()
            .uuid(UPDATED_UUID)
            .billId(UPDATED_BILL_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .addressDetails(UPDATED_ADDRESS_DETAILS)
            .addressCode(UPDATED_ADDRESS_CODE)
            .product(UPDATED_PRODUCT)
            .comment(UPDATED_COMMENT)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE);
        return bill;
    }

    @BeforeEach
    public void initTest() {
        bill = createEntity(em);
    }

    @Test
    @Transactional
    void createBill() throws Exception {
        int databaseSizeBeforeCreate = billRepository.findAll().size();
        // Create the Bill
        BillDTO billDTO = billMapper.toDto(bill);
        restBillMockMvc
            .perform(
                post("/api/bills")
                    .header("Authorization", "Bearer " + authUtils.jwtAdminFullRole())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Bill in the database
        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeCreate + 1);
        Bill testBill = billList.get(billList.size() - 1);
        //    assertThat(testBill.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testBill.getBillId()).isEqualTo(DEFAULT_BILL_ID);
        assertThat(testBill.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testBill.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testBill.getAddressDetails()).isEqualTo(DEFAULT_ADDRESS_DETAILS);
        assertThat(testBill.getAddressCode()).isEqualTo(DEFAULT_ADDRESS_CODE);
        assertThat(testBill.getProduct()).isEqualTo(DEFAULT_PRODUCT);
        assertThat(testBill.getComment()).isEqualTo(DEFAULT_COMMENT);
        //    assertThat(testBill.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        //    assertThat(testBill.getRole()).isEqualTo(DEFAULT_ROLE);
        //    assertThat(testBill.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        //    assertThat(testBill.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        //    assertThat(testBill.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        //    assertThat(testBill.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        //    assertThat(testBill.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);

        // Validate the Bill in Elasticsearch
        verify(mockBillSearchRepository, times(1)).save(testBill);
    }

    @Test
    @Transactional
    void createBillWithExistingId() throws Exception {
        // Create the Bill with an existing ID
        bill.setId(1L);
        BillDTO billDTO = billMapper.toDto(bill);

        int databaseSizeBeforeCreate = billRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBillMockMvc
            .perform(post("/api/bills").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(billDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bill in the database
        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeCreate);

        // Validate the Bill in Elasticsearch
        verify(mockBillSearchRepository, times(0)).save(bill);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = billRepository.findAll().size();
        // set the field null
        bill.setUuid(null);

        // Create the Bill, which fails.
        BillDTO billDTO = billMapper.toDto(bill);

        restBillMockMvc
            .perform(post("/api/bills").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(billDTO)))
            .andExpect(status().isBadRequest());

        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkBillIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = billRepository.findAll().size();
        // set the field null
        bill.setBillId(null);

        // Create the Bill, which fails.
        BillDTO billDTO = billMapper.toDto(bill);

        restBillMockMvc
            .perform(post("/api/bills").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(billDTO)))
            .andExpect(status().isBadRequest());

        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = billRepository.findAll().size();
        // set the field null
        bill.setPhoneNumber(null);

        // Create the Bill, which fails.
        BillDTO billDTO = billMapper.toDto(bill);

        restBillMockMvc
            .perform(post("/api/bills").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(billDTO)))
            .andExpect(status().isBadRequest());

        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllBills() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        // Get all the billList
        restBillMockMvc
            .perform(get("/api/bills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bill.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].billId").value(hasItem(DEFAULT_BILL_ID)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].addressDetails").value(hasItem(DEFAULT_ADDRESS_DETAILS)))
            .andExpect(jsonPath("$.[*].addressCode").value(hasItem(DEFAULT_ADDRESS_CODE)))
            .andExpect(jsonPath("$.[*].product").value(hasItem(DEFAULT_PRODUCT)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));
    }

    @Test
    @Transactional
    void getBill() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        // Get the bill
        restBillMockMvc
            .perform(get("/api/bills/{id}", bill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bill.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.billId").value(DEFAULT_BILL_ID))
            .andExpect(jsonPath("$.phoneNumber").value(DEFAULT_PHONE_NUMBER))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.addressDetails").value(DEFAULT_ADDRESS_DETAILS))
            .andExpect(jsonPath("$.addressCode").value(DEFAULT_ADDRESS_CODE))
            .andExpect(jsonPath("$.product").value(DEFAULT_PRODUCT))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.searchField").value(DEFAULT_SEARCH_FIELD.toString()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.modifiedDate").value(DEFAULT_MODIFIED_DATE.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.modifiedBy").value(DEFAULT_MODIFIED_BY))
            .andExpect(jsonPath("$.dataSize").value(DEFAULT_DATA_SIZE.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingBill() throws Exception {
        // Get the bill
        restBillMockMvc.perform(get("/api/bills/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateBill() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        int databaseSizeBeforeUpdate = billRepository.findAll().size();

        // Update the bill
        Bill updatedBill = billRepository.findById(bill.getId()).get();
        // Disconnect from session so that the updates on updatedBill are not directly saved in db
        em.detach(updatedBill);
        updatedBill
            .uuid(UPDATED_UUID)
            .billId(UPDATED_BILL_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .addressDetails(UPDATED_ADDRESS_DETAILS)
            .addressCode(UPDATED_ADDRESS_CODE)
            .product(UPDATED_PRODUCT)
            .comment(UPDATED_COMMENT)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE);
        BillDTO billDTO = billMapper.toDto(updatedBill);

        restBillMockMvc
            .perform(
                put("/api/bills")
                    .header("Authorization", "Bearer " + authUtils.jwtAdminFullRole())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(billDTO))
            )
            .andExpect(status().isOk());

        // Validate the Bill in the database
        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeUpdate);
        Bill testBill = billList.get(billList.size() - 1);
        //    assertThat(testBill.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testBill.getBillId()).isEqualTo(UPDATED_BILL_ID);
        assertThat(testBill.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testBill.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testBill.getAddressDetails()).isEqualTo(UPDATED_ADDRESS_DETAILS);
        assertThat(testBill.getAddressCode()).isEqualTo(UPDATED_ADDRESS_CODE);
        assertThat(testBill.getProduct()).isEqualTo(UPDATED_PRODUCT);
        assertThat(testBill.getComment()).isEqualTo(UPDATED_COMMENT);
        //    assertThat(testBill.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        //    assertThat(testBill.getRole()).isEqualTo(UPDATED_ROLE);
        //    assertThat(testBill.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        //    assertThat(testBill.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        //    assertThat(testBill.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        //    assertThat(testBill.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        //    assertThat(testBill.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);

        // Validate the Bill in Elasticsearch
        verify(mockBillSearchRepository).save(testBill);
    }

    @Test
    @Transactional
    void updateNonExistingBill() throws Exception {
        int databaseSizeBeforeUpdate = billRepository.findAll().size();

        // Create the Bill
        BillDTO billDTO = billMapper.toDto(bill);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBillMockMvc
            .perform(put("/api/bills").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(billDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bill in the database
        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Bill in Elasticsearch
        verify(mockBillSearchRepository, times(0)).save(bill);
    }

    @Test
    @Transactional
    void partialUpdateBillWithPatch() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        int databaseSizeBeforeUpdate = billRepository.findAll().size();

        // Update the bill using partial update
        Bill partialUpdatedBill = new Bill();
        partialUpdatedBill.setId(bill.getId());

        partialUpdatedBill
            .billId(UPDATED_BILL_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .addressDetails(UPDATED_ADDRESS_DETAILS)
            .addressCode(UPDATED_ADDRESS_CODE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .modifiedBy(UPDATED_MODIFIED_BY);

        restBillMockMvc
            .perform(
                patch("/api/bills")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBill))
            )
            .andExpect(status().isOk());

        // Validate the Bill in the database
        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeUpdate);
        Bill testBill = billList.get(billList.size() - 1);
        assertThat(testBill.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testBill.getBillId()).isEqualTo(UPDATED_BILL_ID);
        assertThat(testBill.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testBill.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testBill.getAddressDetails()).isEqualTo(UPDATED_ADDRESS_DETAILS);
        assertThat(testBill.getAddressCode()).isEqualTo(UPDATED_ADDRESS_CODE);
        assertThat(testBill.getProduct()).isEqualTo(DEFAULT_PRODUCT);
        assertThat(testBill.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testBill.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        assertThat(testBill.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testBill.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testBill.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testBill.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBill.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testBill.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
    }

    @Test
    @Transactional
    void fullUpdateBillWithPatch() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        int databaseSizeBeforeUpdate = billRepository.findAll().size();

        // Update the bill using partial update
        Bill partialUpdatedBill = new Bill();
        partialUpdatedBill.setId(bill.getId());

        partialUpdatedBill
            .uuid(UPDATED_UUID)
            .billId(UPDATED_BILL_ID)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .addressDetails(UPDATED_ADDRESS_DETAILS)
            .addressCode(UPDATED_ADDRESS_CODE)
            .product(UPDATED_PRODUCT)
            .comment(UPDATED_COMMENT)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE);

        restBillMockMvc
            .perform(
                patch("/api/bills")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBill))
            )
            .andExpect(status().isOk());

        // Validate the Bill in the database
        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeUpdate);
        Bill testBill = billList.get(billList.size() - 1);
        assertThat(testBill.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testBill.getBillId()).isEqualTo(UPDATED_BILL_ID);
        assertThat(testBill.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testBill.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testBill.getAddressDetails()).isEqualTo(UPDATED_ADDRESS_DETAILS);
        assertThat(testBill.getAddressCode()).isEqualTo(UPDATED_ADDRESS_CODE);
        assertThat(testBill.getProduct()).isEqualTo(UPDATED_PRODUCT);
        assertThat(testBill.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testBill.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testBill.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testBill.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testBill.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testBill.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBill.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testBill.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
    }

    @Test
    @Transactional
    void partialUpdateBillShouldThrown() throws Exception {
        // Update the bill without id should throw
        Bill partialUpdatedBill = new Bill();

        restBillMockMvc
            .perform(
                patch("/api/bills")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBill))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteBill() throws Exception {
        // Initialize the database
        billRepository.saveAndFlush(bill);

        int databaseSizeBeforeDelete = billRepository.findAll().size();

        // Delete the bill
        restBillMockMvc
            .perform(delete("/api/bills/{id}", bill.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Bill> billList = billRepository.findAll();
        assertThat(billList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Bill in Elasticsearch
        verify(mockBillSearchRepository, times(1)).deleteById(bill.getId());
    }

    @Test
    @Transactional
    void searchBill() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        billRepository.saveAndFlush(bill);
        when(mockBillSearchRepository.search(queryStringQuery("id:" + bill.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(bill), PageRequest.of(0, 1), 1));

        // Search the bill
        restBillMockMvc
            .perform(get("/api/_search/bills?query=id:" + bill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bill.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].billId").value(hasItem(DEFAULT_BILL_ID)))
            .andExpect(jsonPath("$.[*].phoneNumber").value(hasItem(DEFAULT_PHONE_NUMBER)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].addressDetails").value(hasItem(DEFAULT_ADDRESS_DETAILS)))
            .andExpect(jsonPath("$.[*].addressCode").value(hasItem(DEFAULT_ADDRESS_CODE)))
            .andExpect(jsonPath("$.[*].product").value(hasItem(DEFAULT_PRODUCT)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].searchField").value(hasItem(DEFAULT_SEARCH_FIELD.toString())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].modifiedDate").value(hasItem(DEFAULT_MODIFIED_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].modifiedBy").value(hasItem(DEFAULT_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].dataSize").value(hasItem(DEFAULT_DATA_SIZE.intValue())));
    }
}
