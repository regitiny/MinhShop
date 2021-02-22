package org.regitiny.minhshop.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.minhshop.domain.Bill} entity.
 */
@ApiModel(description = "The BillDetails entity.\n@author a true jhipster")
public class BillDTO implements Serializable
{

  private Long id;

  /**
   * uuid
   */
  @NotNull
  @ApiModelProperty(value = "uuid", required = true)
  private UUID uuid;

  /**
   * idShow
   */
  @NotNull
  @Size(max = 16)
  @ApiModelProperty(value = "idShow", required = true)
  private String billId;

  /**
   * phoneNumber
   */
  @NotNull
  @Size(min = 10, max = 10)
  @Pattern(regexp = "[0-9]+")
  @ApiModelProperty(value = "phoneNumber", required = true)
  private String phoneNumber;

  /**
   * email
   */
  @ApiModelProperty(value = "email")
  private String email;

  /**
   * addressDetails
   */
  @ApiModelProperty(value = "addressDetails")
  private String addressDetails;

  /**
   * addressCode
   */
  @ApiModelProperty(value = "addressCode")
  private String addressCode;

  @Size(max = 65535)
  private String product;

  /**
   * comment
   */
  @Size(max = 2048)
  @ApiModelProperty(value = "comment")
  private String comment;

  /**
   * searchField
   */
  @ApiModelProperty(value = "searchField")
  @Lob
  private String searchField;

  /**
   * role
   */
  @ApiModelProperty(value = "role")
  private String role;

  /**
   * createdDate
   */
  @ApiModelProperty(value = "createdDate")
  private Instant createdDate;

  /**
   * modifiedDate
   */
  @ApiModelProperty(value = "modifiedDate")
  private Instant modifiedDate;

  /**
   * createdBy
   */
  @ApiModelProperty(value = "createdBy")
  private String createdBy;

  /**
   * modifiedBy
   */
  @ApiModelProperty(value = "modifiedBy")
  private String modifiedBy;

  /**
   * dataSize
   */
  @ApiModelProperty(value = "dataSize")
  private Long dataSize;

  private UserOtherInfoDTO userOtherInfo;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public UUID getUuid()
  {
    return uuid;
  }

  public void setUuid(UUID uuid)
  {
    this.uuid = uuid;
  }

  public String getBillId()
  {
    return billId;
  }

  public void setBillId(String billId)
  {
    this.billId = billId;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getAddressDetails()
  {
    return addressDetails;
  }

  public void setAddressDetails(String addressDetails)
  {
    this.addressDetails = addressDetails;
  }

  public String getAddressCode()
  {
    return addressCode;
  }

  public void setAddressCode(String addressCode)
  {
    this.addressCode = addressCode;
  }

  public String getProduct()
  {
    return product;
  }

  public void setProduct(String product)
  {
    this.product = product;
  }

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public String getSearchField()
  {
    return searchField;
  }

  public void setSearchField(String searchField)
  {
    this.searchField = searchField;
  }

  public String getRole()
  {
    return role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public Instant getCreatedDate()
  {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate)
  {
    this.createdDate = createdDate;
  }

  public Instant getModifiedDate()
  {
    return modifiedDate;
  }

  public void setModifiedDate(Instant modifiedDate)
  {
    this.modifiedDate = modifiedDate;
  }

  public String getCreatedBy()
  {
    return createdBy;
  }

  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }

  public String getModifiedBy()
  {
    return modifiedBy;
  }

  public void setModifiedBy(String modifiedBy)
  {
    this.modifiedBy = modifiedBy;
  }

  public Long getDataSize()
  {
    return dataSize;
  }

  public void setDataSize(Long dataSize)
  {
    this.dataSize = dataSize;
  }

  public UserOtherInfoDTO getUserOtherInfo()
  {
    return userOtherInfo;
  }

  public void setUserOtherInfo(UserOtherInfoDTO userOtherInfo)
  {
    this.userOtherInfo = userOtherInfo;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof BillDTO))
    {
      return false;
    }

    BillDTO billDTO = (BillDTO) o;
    if (this.id == null)
    {
      return false;
    }
    return Objects.equals(this.id, billDTO.id);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(this.id);
  }

  // prettier-ignore
  @Override
  public String toString()
  {
    return "BillDTO{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", billId='" + getBillId() + "'" +
      ", phoneNumber='" + getPhoneNumber() + "'" +
      ", email='" + getEmail() + "'" +
      ", addressDetails='" + getAddressDetails() + "'" +
      ", addressCode='" + getAddressCode() + "'" +
      ", product='" + getProduct() + "'" +
      ", comment='" + getComment() + "'" +
      ", searchField='" + getSearchField() + "'" +
      ", role='" + getRole() + "'" +
      ", createdDate='" + getCreatedDate() + "'" +
      ", modifiedDate='" + getModifiedDate() + "'" +
      ", createdBy='" + getCreatedBy() + "'" +
      ", modifiedBy='" + getModifiedBy() + "'" +
      ", dataSize=" + getDataSize() +
      ", userOtherInfo=" + getUserOtherInfo() +
      "}";
  }
}
