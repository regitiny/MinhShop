package org.regitiny.minhshop.service.mapper;

import org.mapstruct.*;
import org.regitiny.minhshop.domain.*;
import org.regitiny.minhshop.service.dto.PostDetailsDTO;

/**
 * Mapper for the entity {@link PostDetails} and its DTO {@link PostDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PostDetailsMapper extends EntityMapper<PostDetailsDTO, PostDetails> {
    @Named("publicId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "publicId", source = "publicId")
    PostDetailsDTO toDtoPublicId(PostDetails postDetails);
}
