package org.regitiny.minhshop.service.mapper;

import org.mapstruct.*;
import org.regitiny.minhshop.domain.*;
import org.regitiny.minhshop.service.dto.PostDetailsDTO;

/**
 * Mapper for the entity {@link PostDetails} and its DTO {@link PostDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PostDetailsMapper extends EntityMapper<PostDetailsDTO, PostDetails> {
    @Named("postDetailsId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "postDetailsId", source = "postDetailsId")
    PostDetailsDTO toDtoPostDetailsId(PostDetails postDetails);
}
