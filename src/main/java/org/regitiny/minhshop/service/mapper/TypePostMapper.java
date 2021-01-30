package org.regitiny.minhshop.service.mapper;

import org.mapstruct.*;
import org.regitiny.minhshop.domain.*;
import org.regitiny.minhshop.service.dto.TypePostDTO;

/**
 * Mapper for the entity {@link TypePost} and its DTO {@link TypePostDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypePostMapper extends EntityMapper<TypePostDTO, TypePost> {
    @Named("typeName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "typeName", source = "typeName")
    TypePostDTO toDtoTypeName(TypePost typePost);
}
