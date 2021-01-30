package org.regitiny.minhshop.service.mapper;

import java.util.Set;
import org.mapstruct.*;
import org.regitiny.minhshop.domain.*;
import org.regitiny.minhshop.service.dto.SimplePostDTO;

/**
 * Mapper for the entity {@link SimplePost} and its DTO {@link SimplePostDTO}.
 */
@Mapper(componentModel = "spring", uses = { PostDetailsMapper.class, TypePostMapper.class, TypePostFilterMapper.class })
public interface SimplePostMapper extends EntityMapper<SimplePostDTO, SimplePost> {
    @Mapping(target = "postDetails", source = "postDetails", qualifiedByName = "publicId")
    @Mapping(target = "typePost", source = "typePost", qualifiedByName = "typeName")
    @Mapping(target = "typePostFilters", source = "typePostFilters", qualifiedByName = "typeFilterNameSet")
    SimplePostDTO toDto(SimplePost s);

    @Mapping(target = "removeTypePostFilter", ignore = true)
    SimplePost toEntity(SimplePostDTO simplePostDTO);
}
