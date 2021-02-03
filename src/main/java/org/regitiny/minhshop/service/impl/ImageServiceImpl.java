package org.regitiny.minhshop.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang.NullArgumentException;
import org.regitiny.minhshop.domain.Image;
import org.regitiny.minhshop.repository.ImageRepository;
import org.regitiny.minhshop.repository.search.ImageSearchRepository;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.ImageService;
import org.regitiny.minhshop.service.dto.ImageDTO;
import org.regitiny.minhshop.service.mapper.ImageMapper;
import org.regitiny.tools.magic.constant.StringPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link Image}.
 */
@Service
@Transactional
public class ImageServiceImpl implements ImageService {

    private final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;

    private final ImageSearchRepository imageSearchRepository;

    public ImageServiceImpl(ImageRepository imageRepository, ImageMapper imageMapper, ImageSearchRepository imageSearchRepository) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
        this.imageSearchRepository = imageSearchRepository;
    }

    @Override
    public ImageDTO save(ImageDTO imageDTO) {
        log.debug("Request to save Image : {}", imageDTO);
        Image image = imageMapper.toEntity(imageDTO);
        image = imageRepository.save(image);
        ImageDTO result = imageMapper.toDto(image);
        imageSearchRepository.save(image);
        return result;
    }

    @Override
    public ImageDTO upload(MultipartFile imageData) {
        if (imageData.isEmpty()) throw new NullArgumentException("POST cái đéo gì đấy ,đéo có dữ liệu của ảnh");

        ImageDTO imageDTO = new ImageDTO();

        UUID uuid = UUID.randomUUID();
        byte[] imageDataBytes = null;
        String nameImage = uuid.toString();
        String extension = StringPool.BLANK;
        String typeFile = imageData.getContentType();
        String role = StringPool.BLANK;
        Instant createdDate = Instant.now();
        Instant modifiedDate = Instant.now();
        String createdBy = SecurityUtils.getCurrentUserLogin().get();
        String modifiedBy = SecurityUtils.getCurrentUserLogin().get();
        long dataSize = imageData.getSize();
        String comment = null;

        String imageDataName = imageData.getOriginalFilename();
        String temp[] = imageDataName.split(StringPool.DOT_IN_REGEX);
        if (temp.length >= 2) extension = temp[temp.length - 1];
        nameImage += StringPool.PERIOD + extension;
        try {
            imageDataBytes = imageData.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageDataContentType = typeFile + dataSize + "byte";

        imageDTO.setUuid(uuid);
        imageDTO.setImageData(imageDataBytes);
        imageDTO.setImageDataContentType(imageDataContentType);
        imageDTO.setNameImage(nameImage);
        imageDTO.setExtension(extension);
        imageDTO.setTypeFile(typeFile);
        imageDTO.setRole(role);
        imageDTO.setCreatedDate(createdDate);
        imageDTO.setModifiedDate(modifiedDate);
        imageDTO.setCreatedBy(createdBy);
        imageDTO.setModifiedBy(modifiedBy);
        imageDTO.setDataSize(dataSize);
        imageDTO.setComment(comment);
        if (imageDataBytes != null) return save(imageDTO);
        throw new NullArgumentException("dữ liệu đéo có thì upload làm sao được ");
    }

    @Override
    public Optional<ImageDTO> partialUpdate(ImageDTO imageDTO) {
        log.debug("Request to partially update Image : {}", imageDTO);

        return imageRepository
            .findById(imageDTO.getId())
            .map(
                existingImage -> {
                    if (imageDTO.getUuid() != null) {
                        existingImage.setUuid(imageDTO.getUuid());
                    }

                    if (imageDTO.getImageData() != null) {
                        existingImage.setImageData(imageDTO.getImageData());
                    }
                    if (imageDTO.getImageDataContentType() != null) {
                        existingImage.setImageDataContentType(imageDTO.getImageDataContentType());
                    }

                    if (imageDTO.getNameImage() != null) {
                        existingImage.setNameImage(imageDTO.getNameImage());
                    }

                    if (imageDTO.getExtension() != null) {
                        existingImage.setExtension(imageDTO.getExtension());
                    }

                    if (imageDTO.getTypeFile() != null) {
                        existingImage.setTypeFile(imageDTO.getTypeFile());
                    }

                    if (imageDTO.getSearchField() != null) {
                        existingImage.setSearchField(imageDTO.getSearchField());
                    }

                    if (imageDTO.getRole() != null) {
                        existingImage.setRole(imageDTO.getRole());
                    }

                    if (imageDTO.getCreatedDate() != null) {
                        existingImage.setCreatedDate(imageDTO.getCreatedDate());
                    }

                    if (imageDTO.getModifiedDate() != null) {
                        existingImage.setModifiedDate(imageDTO.getModifiedDate());
                    }

                    if (imageDTO.getCreatedBy() != null) {
                        existingImage.setCreatedBy(imageDTO.getCreatedBy());
                    }

                    if (imageDTO.getModifiedBy() != null) {
                        existingImage.setModifiedBy(imageDTO.getModifiedBy());
                    }

                    if (imageDTO.getDataSize() != null) {
                        existingImage.setDataSize(imageDTO.getDataSize());
                    }

                    if (imageDTO.getComment() != null) {
                        existingImage.setComment(imageDTO.getComment());
                    }

                    return existingImage;
                }
            )
            .map(imageRepository::save)
            .map(
                savedImage -> {
                    imageSearchRepository.save(savedImage);

                    return savedImage;
                }
            )
            .map(imageMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ImageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Images");
        return imageRepository.findAll(pageable).map(imageMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ImageDTO> findOne(Long id) {
        log.debug("Request to get Image : {}", id);
        return imageRepository.findById(id).map(imageMapper::toDto);
    }

    @Override
    public Optional<ImageDTO> findByNameImage(String nameImage) {
        return imageRepository.findOneByNameImage(nameImage).map(imageMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Image : {}", id);
        imageRepository.deleteById(id);
        imageSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ImageDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Images for query {}", query);
        return imageSearchRepository.search(queryStringQuery(query), pageable).map(imageMapper::toDto);
    }
}
