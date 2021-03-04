package org.regitiny.minhshop.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.regitiny.minhshop.domain.UserOtherInfo;
import org.regitiny.minhshop.service.dto.UserOtherInfoDTO;

/**
 * Mapper for the entity {@link UserOtherInfo} and its DTO {@link UserOtherInfoDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserOtherInfoMapper extends EntityMapper<UserOtherInfoDTO, UserOtherInfo>
{
  @Mapping(target = "userName", source = "userName", qualifiedByName = "login")
  UserOtherInfoDTO toDto(UserOtherInfo s);


  @Mapping(target = "userName.id", ignore = true)
  @Mapping(target = "userName.createdBy", ignore = true)
  @Mapping(target = "userName.createdDate", ignore = true)
  @Mapping(target = "userName.lastModifiedBy", ignore = true)
  @Mapping(target = "userName.lastModifiedDate", ignore = true)
  @Mapping(target = "userName.password", ignore = true)
  @Mapping(target = "userName.firstName", ignore = true)
  @Mapping(target = "userName.lastName", ignore = true)
  @Mapping(target = "userName.email", ignore = true)
  @Mapping(target = "userName.imageUrl", ignore = true)
  @Mapping(target = "userName.activated", ignore = true)
  @Mapping(target = "userName.activationKey", ignore = true)
  @Mapping(target = "userName.resetKey", ignore = true)
  @Mapping(target = "userName.langKey", ignore = true)
  @Mapping(target = "userName.authorities", ignore = true)
  @Mapping(target = "userName.resetDate", ignore = true)
  UserOtherInfo toEntity(UserOtherInfoDTO userOtherInfoDTO);


  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  UserOtherInfoDTO toDtoId(UserOtherInfo userOtherInfo);
}
