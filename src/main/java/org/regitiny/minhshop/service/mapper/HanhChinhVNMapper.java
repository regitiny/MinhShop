package org.regitiny.minhshop.service.mapper;

import org.mapstruct.Mapper;
import org.regitiny.minhshop.domain.HanhChinhVN;
import org.regitiny.minhshop.service.dto.HanhChinhVNDTO;

/**
 * Mapper for the entity {@link HanhChinhVN} and its DTO {@link HanhChinhVNDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HanhChinhVNMapper extends EntityMapper<HanhChinhVNDTO, HanhChinhVN> {}
