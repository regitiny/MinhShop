package org.regitiny.minhshop.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.regitiny.minhshop.domain.Bill;
import org.regitiny.minhshop.service.dto.BillDTO;

/**
 * Mapper for the entity {@link Bill} and its DTO {@link BillDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserOtherInfoMapper.class})
public interface BillMapper extends EntityMapper<BillDTO, Bill>
{
  @Mapping(target = "userOtherInfo", source = "userOtherInfo", qualifiedByName = "id")
  BillDTO toDto(Bill s);


  @Named("billId")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  @Mapping(target = "billId", source = "billId")
  BillDTO toDtoBillId(Bill bill);
}
