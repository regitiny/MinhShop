package org.regitiny.minhshop.service;

import org.regitiny.minhshop.domain.File;
import org.regitiny.minhshop.service.dto.FileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * Service Interface for managing {@link org.regitiny.minhshop.domain.File}.
 */
public interface FileService
{
  String FILE_BY_FILE_NAME_CACHE = "FILE_BY_FILE_NAME_CACHE";


  /**
   * Save a file.
   *
   * @param fileDTO the entity to save.
   * @return the persisted entity.
   */
  FileDTO save(FileDTO fileDTO);


  /**
   * save detail of file
   *
   * @param fileData data of file
   * @return FileDTO (other detail of file)
   */
  FileDTO createFileDetail(MultipartFile fileData);


  Optional<File> getFileByFileName(String fileName);


  Optional<File> cacheUpdateFileByFileName(String fileName);


  /**
   * Partially updates a file.
   *
   * @param fileDTO the entity to update partially.
   * @return the persisted entity.
   */
  Optional<FileDTO> partialUpdate(FileDTO fileDTO);


  /**
   * Get all the files.
   *
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FileDTO> findAll(Pageable pageable);


  /**
   * Get the "id" file.
   *
   * @param id the id of the entity.
   * @return the entity.
   */
  Optional<FileDTO> findOne(Long id);


  /**
   * Delete the "id" file.
   *
   * @param id the id of the entity.
   */
  void delete(Long id);


  /**
   * Search for the file corresponding to the query.
   *
   * @param query    the query of the search.
   * @param pageable the pagination information.
   * @return the list of entities.
   */
  Page<FileDTO> search(String query, Pageable pageable);
}
