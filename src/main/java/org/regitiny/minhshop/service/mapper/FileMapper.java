package org.regitiny.minhshop.service.mapper;

import org.mapstruct.Mapper;
import org.regitiny.minhshop.domain.File;
import org.regitiny.minhshop.service.dto.FileDTO;

/**
 * Mapper for the entity {@link File} and its DTO {@link FileDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FileMapper extends EntityMapper<FileDTO, File> {}
