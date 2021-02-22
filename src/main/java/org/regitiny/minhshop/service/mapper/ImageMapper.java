package org.regitiny.minhshop.service.mapper;

import org.mapstruct.Mapper;
import org.regitiny.minhshop.domain.Image;
import org.regitiny.minhshop.service.dto.ImageDTO;

/**
 * Mapper for the entity {@link Image} and its DTO {@link ImageDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ImageMapper extends EntityMapper<ImageDTO, Image>
{
}
