package org.regitiny.minhshop.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.Optional;
import org.regitiny.minhshop.domain.File;
import org.regitiny.minhshop.repository.FileRepository;
import org.regitiny.minhshop.repository.search.FileSearchRepository;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.dto.FileDTO;
import org.regitiny.minhshop.service.mapper.FileMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link File}.
 */
@Service
@Transactional
public class FileServiceImpl implements FileService {

    private final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    private final FileRepository fileRepository;

    private final FileMapper fileMapper;

    private final FileSearchRepository fileSearchRepository;

    public FileServiceImpl(FileRepository fileRepository, FileMapper fileMapper, FileSearchRepository fileSearchRepository) {
        this.fileRepository = fileRepository;
        this.fileMapper = fileMapper;
        this.fileSearchRepository = fileSearchRepository;
    }

    @Override
    public FileDTO save(FileDTO fileDTO) {
        log.debug("Request to save File : {}", fileDTO);
        File file = fileMapper.toEntity(fileDTO);
        file = fileRepository.save(file);
        FileDTO result = fileMapper.toDto(file);
        fileSearchRepository.save(file);
        return result;
    }

    @Override
    public Optional<FileDTO> partialUpdate(FileDTO fileDTO) {
        log.debug("Request to partially update File : {}", fileDTO);

        return fileRepository
            .findById(fileDTO.getId())
            .map(
                existingFile -> {
                    if (fileDTO.getUuid() != null) {
                        existingFile.setUuid(fileDTO.getUuid());
                    }

                    if (fileDTO.getVideoData() != null) {
                        existingFile.setVideoData(fileDTO.getVideoData());
                    }
                    if (fileDTO.getVideoDataContentType() != null) {
                        existingFile.setVideoDataContentType(fileDTO.getVideoDataContentType());
                    }

                    if (fileDTO.getNameVideo() != null) {
                        existingFile.setNameVideo(fileDTO.getNameVideo());
                    }

                    if (fileDTO.getExtension() != null) {
                        existingFile.setExtension(fileDTO.getExtension());
                    }

                    if (fileDTO.getTypeFile() != null) {
                        existingFile.setTypeFile(fileDTO.getTypeFile());
                    }

                    if (fileDTO.getRole() != null) {
                        existingFile.setRole(fileDTO.getRole());
                    }

                    if (fileDTO.getCreatedDate() != null) {
                        existingFile.setCreatedDate(fileDTO.getCreatedDate());
                    }

                    if (fileDTO.getModifiedDate() != null) {
                        existingFile.setModifiedDate(fileDTO.getModifiedDate());
                    }

                    if (fileDTO.getCreatedBy() != null) {
                        existingFile.setCreatedBy(fileDTO.getCreatedBy());
                    }

                    if (fileDTO.getModifiedBy() != null) {
                        existingFile.setModifiedBy(fileDTO.getModifiedBy());
                    }

                    if (fileDTO.getDataSize() != null) {
                        existingFile.setDataSize(fileDTO.getDataSize());
                    }

                    if (fileDTO.getComment() != null) {
                        existingFile.setComment(fileDTO.getComment());
                    }

                    if (fileDTO.getDeleted() != null) {
                        existingFile.setDeleted(fileDTO.getDeleted());
                    }

                    return existingFile;
                }
            )
            .map(fileRepository::save)
            .map(
                savedFile -> {
                    fileSearchRepository.save(savedFile);

                    return savedFile;
                }
            )
            .map(fileMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Files");
        return fileRepository.findAll(pageable).map(fileMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileDTO> findOne(Long id) {
        log.debug("Request to get File : {}", id);
        return fileRepository.findById(id).map(fileMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete File : {}", id);
        fileRepository.deleteById(id);
        fileSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Files for query {}", query);
        return fileSearchRepository.search(queryStringQuery(query), pageable).map(fileMapper::toDto);
    }
}
