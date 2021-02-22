package org.regitiny.minhshop.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.regitiny.minhshop.domain.Payment;
import org.regitiny.minhshop.service.dto.PaymentDTO;

/**
 * Mapper for the entity {@link Payment} and its DTO {@link PaymentDTO}.
 */
@Mapper(componentModel = "spring", uses = {BillMapper.class})
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment>
{
  @Mapping(target = "billId", source = "billId", qualifiedByName = "billId")
  PaymentDTO toDto(Payment s);
}
