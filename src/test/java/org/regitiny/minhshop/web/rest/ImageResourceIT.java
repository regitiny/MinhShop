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
import org.regitiny.minhshop.domain.Image;
import org.regitiny.minhshop.repository.ImageRepository;
import org.regitiny.minhshop.repository.search.ImageSearchRepository;
import org.regitiny.minhshop.service.dto.ImageDTO;
import org.regitiny.minhshop.service.mapper.ImageMapper;
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
 * Integration tests for the {@link ImageResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ImageResourceIT {

    private static final UUID DEFAULT_UUID = UUID.randomUUID();
    private static final UUID UPDATED_UUID = UUID.randomUUID();

    private static final byte[] DEFAULT_IMAGE_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_DATA_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_NAME_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_NAME_IMAGE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTENSION = "AAAAAAAAAA";
    private static final String UPDATED_EXTENSION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_FILE = "BBBBBBBBBB";

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
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    /**
     * This repository is mocked in the org.regitiny.minhshop.repository.search test package.
     *
     * @see org.regitiny.minhshop.repository.search.ImageSearchRepositoryMockConfiguration
     */
    @Autowired
    private ImageSearchRepository mockImageSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restImageMockMvc;

    private Image image;

    /**
     * Create an entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Image createEntity(EntityManager em) {
        Image image = new Image()
            .uuid(DEFAULT_UUID)
            .imageData(DEFAULT_IMAGE_DATA)
            .imageDataContentType(DEFAULT_IMAGE_DATA_CONTENT_TYPE)
            .nameImage(DEFAULT_NAME_IMAGE)
            .extension(DEFAULT_EXTENSION)
            .typeFile(DEFAULT_TYPE_FILE)
            .searchField(DEFAULT_SEARCH_FIELD)
            .role(DEFAULT_ROLE)
            .createdDate(DEFAULT_CREATED_DATE)
            .modifiedDate(DEFAULT_MODIFIED_DATE)
            .createdBy(DEFAULT_CREATED_BY)
            .modifiedBy(DEFAULT_MODIFIED_BY)
            .dataSize(DEFAULT_DATA_SIZE)
            .comment(DEFAULT_COMMENT);
        return image;
    }

    /**
     * Create an updated entity for this test.
     * <p>
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Image createUpdatedEntity(EntityManager em) {
        Image image = new Image()
            .uuid(UPDATED_UUID)
            .imageData(UPDATED_IMAGE_DATA)
            .imageDataContentType(UPDATED_IMAGE_DATA_CONTENT_TYPE)
            .nameImage(UPDATED_NAME_IMAGE)
            .extension(UPDATED_EXTENSION)
            .typeFile(UPDATED_TYPE_FILE)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        return image;
    }

    @BeforeEach
    public void initTest() {
        image = createEntity(em);
    }

    @Test
    @Transactional
    void createImage() throws Exception {
        int databaseSizeBeforeCreate = imageRepository.findAll().size();
        // Create the Image
        ImageDTO imageDTO = imageMapper.toDto(image);
        restImageMockMvc
            .perform(post("/api/images").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
            .andExpect(status().isCreated());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeCreate + 1);
        Image testImage = imageList.get(imageList.size() - 1);
        assertThat(testImage.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testImage.getImageData()).isEqualTo(DEFAULT_IMAGE_DATA);
        assertThat(testImage.getImageDataContentType()).isEqualTo(DEFAULT_IMAGE_DATA_CONTENT_TYPE);
        assertThat(testImage.getNameImage()).isEqualTo(DEFAULT_NAME_IMAGE);
        assertThat(testImage.getExtension()).isEqualTo(DEFAULT_EXTENSION);
        assertThat(testImage.getTypeFile()).isEqualTo(DEFAULT_TYPE_FILE);
        assertThat(testImage.getSearchField()).isEqualTo(DEFAULT_SEARCH_FIELD);
        assertThat(testImage.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testImage.getCreatedDate()).isEqualTo(DEFAULT_CREATED_DATE);
        assertThat(testImage.getModifiedDate()).isEqualTo(DEFAULT_MODIFIED_DATE);
        assertThat(testImage.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testImage.getModifiedBy()).isEqualTo(DEFAULT_MODIFIED_BY);
        assertThat(testImage.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testImage.getComment()).isEqualTo(DEFAULT_COMMENT);

        // Validate the Image in Elasticsearch
        verify(mockImageSearchRepository, times(1)).save(testImage);
    }

    @Test
    @Transactional
    void createImageWithExistingId() throws Exception {
        // Create the Image with an existing ID
        image.setId(1L);
        ImageDTO imageDTO = imageMapper.toDto(image);

        int databaseSizeBeforeCreate = imageRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restImageMockMvc
            .perform(post("/api/images").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeCreate);

        // Validate the Image in Elasticsearch
        verify(mockImageSearchRepository, times(0)).save(image);
    }

    @Test
    @Transactional
    void checkUuidIsRequired() throws Exception {
        int databaseSizeBeforeTest = imageRepository.findAll().size();
        // set the field null
        image.setUuid(null);

        // Create the Image, which fails.
        ImageDTO imageDTO = imageMapper.toDto(image);

        restImageMockMvc
            .perform(post("/api/images").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
            .andExpect(status().isBadRequest());

        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllImages() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        // Get all the imageList
        restImageMockMvc
            .perform(get("/api/images?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(image.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].imageDataContentType").value(hasItem(DEFAULT_IMAGE_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageData").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_DATA))))
            .andExpect(jsonPath("$.[*].nameImage").value(hasItem(DEFAULT_NAME_IMAGE)))
            .andExpect(jsonPath("$.[*].extension").value(hasItem(DEFAULT_EXTENSION)))
            .andExpect(jsonPath("$.[*].typeFile").value(hasItem(DEFAULT_TYPE_FILE)))
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
    void getImage() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        // Get the image
        restImageMockMvc
            .perform(get("/api/images/{id}", image.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(image.getId().intValue()))
            .andExpect(jsonPath("$.uuid").value(DEFAULT_UUID.toString()))
            .andExpect(jsonPath("$.imageDataContentType").value(DEFAULT_IMAGE_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.imageData").value(Base64Utils.encodeToString(DEFAULT_IMAGE_DATA)))
            .andExpect(jsonPath("$.nameImage").value(DEFAULT_NAME_IMAGE))
            .andExpect(jsonPath("$.extension").value(DEFAULT_EXTENSION))
            .andExpect(jsonPath("$.typeFile").value(DEFAULT_TYPE_FILE))
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
    void getNonExistingImage() throws Exception {
        // Get the image
        restImageMockMvc.perform(get("/api/images/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void updateImage() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        int databaseSizeBeforeUpdate = imageRepository.findAll().size();

        // Update the image
        Image updatedImage = imageRepository.findById(image.getId()).get();
        // Disconnect from session so that the updates on updatedImage are not directly saved in db
        em.detach(updatedImage);
        updatedImage
            .uuid(UPDATED_UUID)
            .imageData(UPDATED_IMAGE_DATA)
            .imageDataContentType(UPDATED_IMAGE_DATA_CONTENT_TYPE)
            .nameImage(UPDATED_NAME_IMAGE)
            .extension(UPDATED_EXTENSION)
            .typeFile(UPDATED_TYPE_FILE)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);
        ImageDTO imageDTO = imageMapper.toDto(updatedImage);

        restImageMockMvc
            .perform(put("/api/images").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
            .andExpect(status().isOk());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
        Image testImage = imageList.get(imageList.size() - 1);
        assertThat(testImage.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testImage.getImageData()).isEqualTo(UPDATED_IMAGE_DATA);
        assertThat(testImage.getImageDataContentType()).isEqualTo(UPDATED_IMAGE_DATA_CONTENT_TYPE);
        assertThat(testImage.getNameImage()).isEqualTo(UPDATED_NAME_IMAGE);
        assertThat(testImage.getExtension()).isEqualTo(UPDATED_EXTENSION);
        assertThat(testImage.getTypeFile()).isEqualTo(UPDATED_TYPE_FILE);
        assertThat(testImage.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testImage.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testImage.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testImage.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testImage.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testImage.getComment()).isEqualTo(UPDATED_COMMENT);

        // Validate the Image in Elasticsearch
        verify(mockImageSearchRepository).save(testImage);
    }

    @Test
    @Transactional
    void updateNonExistingImage() throws Exception {
        int databaseSizeBeforeUpdate = imageRepository.findAll().size();

        // Create the Image
        ImageDTO imageDTO = imageMapper.toDto(image);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restImageMockMvc
            .perform(put("/api/images").contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(imageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Image in Elasticsearch
        verify(mockImageSearchRepository, times(0)).save(image);
    }

    @Test
    @Transactional
    void partialUpdateImageWithPatch() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        int databaseSizeBeforeUpdate = imageRepository.findAll().size();

        // Update the image using partial update
        Image partialUpdatedImage = new Image();
        partialUpdatedImage.setId(image.getId());

        partialUpdatedImage
            .nameImage(UPDATED_NAME_IMAGE)
            .extension(UPDATED_EXTENSION)
            .typeFile(UPDATED_TYPE_FILE)
            .searchField(UPDATED_SEARCH_FIELD)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .comment(UPDATED_COMMENT);

        restImageMockMvc
            .perform(
                patch("/api/images")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedImage))
            )
            .andExpect(status().isOk());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
        Image testImage = imageList.get(imageList.size() - 1);
        assertThat(testImage.getUuid()).isEqualTo(DEFAULT_UUID);
        assertThat(testImage.getImageData()).isEqualTo(DEFAULT_IMAGE_DATA);
        assertThat(testImage.getImageDataContentType()).isEqualTo(DEFAULT_IMAGE_DATA_CONTENT_TYPE);
        assertThat(testImage.getNameImage()).isEqualTo(UPDATED_NAME_IMAGE);
        assertThat(testImage.getExtension()).isEqualTo(UPDATED_EXTENSION);
        assertThat(testImage.getTypeFile()).isEqualTo(UPDATED_TYPE_FILE);
        assertThat(testImage.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testImage.getRole()).isEqualTo(DEFAULT_ROLE);
        assertThat(testImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testImage.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testImage.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testImage.getDataSize()).isEqualTo(DEFAULT_DATA_SIZE);
        assertThat(testImage.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void fullUpdateImageWithPatch() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        int databaseSizeBeforeUpdate = imageRepository.findAll().size();

        // Update the image using partial update
        Image partialUpdatedImage = new Image();
        partialUpdatedImage.setId(image.getId());

        partialUpdatedImage
            .uuid(UPDATED_UUID)
            .imageData(UPDATED_IMAGE_DATA)
            .imageDataContentType(UPDATED_IMAGE_DATA_CONTENT_TYPE)
            .nameImage(UPDATED_NAME_IMAGE)
            .extension(UPDATED_EXTENSION)
            .typeFile(UPDATED_TYPE_FILE)
            .searchField(UPDATED_SEARCH_FIELD)
            .role(UPDATED_ROLE)
            .createdDate(UPDATED_CREATED_DATE)
            .modifiedDate(UPDATED_MODIFIED_DATE)
            .createdBy(UPDATED_CREATED_BY)
            .modifiedBy(UPDATED_MODIFIED_BY)
            .dataSize(UPDATED_DATA_SIZE)
            .comment(UPDATED_COMMENT);

        restImageMockMvc
            .perform(
                patch("/api/images")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedImage))
            )
            .andExpect(status().isOk());

        // Validate the Image in the database
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeUpdate);
        Image testImage = imageList.get(imageList.size() - 1);
        assertThat(testImage.getUuid()).isEqualTo(UPDATED_UUID);
        assertThat(testImage.getImageData()).isEqualTo(UPDATED_IMAGE_DATA);
        assertThat(testImage.getImageDataContentType()).isEqualTo(UPDATED_IMAGE_DATA_CONTENT_TYPE);
        assertThat(testImage.getNameImage()).isEqualTo(UPDATED_NAME_IMAGE);
        assertThat(testImage.getExtension()).isEqualTo(UPDATED_EXTENSION);
        assertThat(testImage.getTypeFile()).isEqualTo(UPDATED_TYPE_FILE);
        assertThat(testImage.getSearchField()).isEqualTo(UPDATED_SEARCH_FIELD);
        assertThat(testImage.getRole()).isEqualTo(UPDATED_ROLE);
        assertThat(testImage.getCreatedDate()).isEqualTo(UPDATED_CREATED_DATE);
        assertThat(testImage.getModifiedDate()).isEqualTo(UPDATED_MODIFIED_DATE);
        assertThat(testImage.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testImage.getModifiedBy()).isEqualTo(UPDATED_MODIFIED_BY);
        assertThat(testImage.getDataSize()).isEqualTo(UPDATED_DATA_SIZE);
        assertThat(testImage.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    void partialUpdateImageShouldThrown() throws Exception {
        // Update the image without id should throw
        Image partialUpdatedImage = new Image();

        restImageMockMvc
            .perform(
                patch("/api/images")
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedImage))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    void deleteImage() throws Exception {
        // Initialize the database
        imageRepository.saveAndFlush(image);

        int databaseSizeBeforeDelete = imageRepository.findAll().size();

        // Delete the image
        restImageMockMvc
            .perform(delete("/api/images/{id}", image.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Image> imageList = imageRepository.findAll();
        assertThat(imageList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Image in Elasticsearch
        verify(mockImageSearchRepository, times(1)).deleteById(image.getId());
    }

    @Test
    @Transactional
    void searchImage() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        imageRepository.saveAndFlush(image);
        when(mockImageSearchRepository.search(queryStringQuery("id:" + image.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(image), PageRequest.of(0, 1), 1));

        // Search the image
        restImageMockMvc
            .perform(get("/api/_search/images?query=id:" + image.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(image.getId().intValue())))
            .andExpect(jsonPath("$.[*].uuid").value(hasItem(DEFAULT_UUID.toString())))
            .andExpect(jsonPath("$.[*].imageDataContentType").value(hasItem(DEFAULT_IMAGE_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].imageData").value(hasItem(Base64Utils.encodeToString(DEFAULT_IMAGE_DATA))))
            .andExpect(jsonPath("$.[*].nameImage").value(hasItem(DEFAULT_NAME_IMAGE)))
            .andExpect(jsonPath("$.[*].extension").value(hasItem(DEFAULT_EXTENSION)))
            .andExpect(jsonPath("$.[*].typeFile").value(hasItem(DEFAULT_TYPE_FILE)))
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
