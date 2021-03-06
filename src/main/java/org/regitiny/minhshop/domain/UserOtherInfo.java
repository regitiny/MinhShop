package org.regitiny.minhshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * The UserOtherInfo entity.\n@author a true jhipster
 */
@Entity
@Table(name = "user_other_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "userotherinfo")
public class UserOtherInfo implements Serializable
{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  /**
   * uuid
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  /**
   * phoneNumber
   */
  @Size(min = 10, max = 10)
  @Pattern(regexp = "[0-9]+")
  @Column(name = "phone_number", length = 10)
  private String phoneNumber;

  /**
   * email
   */
  @Column(name = "email")
  private String email;

  /**
   * wardCode
   */
  @Column(name = "ward_code")
  private String wardCode;

  /**
   * distCode
   */
  @Column(name = "dist_code")
  private String distCode;

  /**
   * cityCode
   */
  @Column(name = "city_code")
  private String cityCode;

  /**
   * addressDetails
   */
  @Column(name = "address_details")
  private String addressDetails;

  /**
   * dateOfBirth
   */
  @Column(name = "date_of_birth")
  private LocalDate dateOfBirth;

  /**
   * otherInfo
   */
  @Column(name = "other_info")
  private String otherInfo;

  /**
   * searchField
   */
  @Lob
  @Column(name = "search_field")
  private String searchField;

  /**
   * role
   */
  @Column(name = "role")
  private String role;

  /**
   * createdDate
   */
  @Column(name = "created_date")
  private Instant createdDate;

  /**
   * modifiedDate
   */
  @Column(name = "modified_date")
  private Instant modifiedDate;

  /**
   * createdBy
   */
  @Column(name = "created_by")
  private String createdBy;

  /**
   * modifiedBy
   */
  @Column(name = "modified_by")
  private String modifiedBy;

  /**
   * dataSize
   */
  @Column(name = "data_size")
  private Long dataSize;

  /**
   * comment
   */
  @Size(max = 2048)
  @Column(name = "comment", length = 2048)
  private String comment;

  @OneToOne
  @JoinColumn(unique = true)
  private User userName;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public UserOtherInfo id(Long id)
  {
    this.id = id;
    return this;
  }

  public UUID getUuid()
  {
    return this.uuid;
  }

  public void setUuid(UUID uuid)
  {
    this.uuid = uuid;
  }

  public UserOtherInfo uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public String getPhoneNumber()
  {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  public UserOtherInfo phoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public UserOtherInfo email(String email)
  {
    this.email = email;
    return this;
  }

  public String getWardCode()
  {
    return this.wardCode;
  }

  public void setWardCode(String wardCode)
  {
    this.wardCode = wardCode;
  }

  public UserOtherInfo wardCode(String wardCode)
  {
    this.wardCode = wardCode;
    return this;
  }

  public String getDistCode()
  {
    return this.distCode;
  }

  public void setDistCode(String distCode)
  {
    this.distCode = distCode;
  }

  public UserOtherInfo distCode(String distCode)
  {
    this.distCode = distCode;
    return this;
  }

  public String getCityCode()
  {
    return this.cityCode;
  }

  public void setCityCode(String cityCode)
  {
    this.cityCode = cityCode;
  }

  public UserOtherInfo cityCode(String cityCode)
  {
    this.cityCode = cityCode;
    return this;
  }

  public String getAddressDetails()
  {
    return this.addressDetails;
  }

  public void setAddressDetails(String addressDetails)
  {
    this.addressDetails = addressDetails;
  }

  public UserOtherInfo addressDetails(String addressDetails)
  {
    this.addressDetails = addressDetails;
    return this;
  }

  public LocalDate getDateOfBirth()
  {
    return this.dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  public UserOtherInfo dateOfBirth(LocalDate dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  public String getOtherInfo()
  {
    return this.otherInfo;
  }

  public void setOtherInfo(String otherInfo)
  {
    this.otherInfo = otherInfo;
  }

  public UserOtherInfo otherInfo(String otherInfo)
  {
    this.otherInfo = otherInfo;
    return this;
  }

  public String getSearchField()
  {
    return this.searchField;
  }

  public void setSearchField(String searchField)
  {
    this.searchField = searchField;
  }

  public UserOtherInfo searchField(String searchField)
  {
    this.searchField = searchField;
    return this;
  }

  public String getRole()
  {
    return this.role;
  }

  public void setRole(String role)
  {
    this.role = role;
  }

  public UserOtherInfo role(String role)
  {
    this.role = role;
    return this;
  }

  public Instant getCreatedDate()
  {
    return this.createdDate;
  }

  public void setCreatedDate(Instant createdDate)
  {
    this.createdDate = createdDate;
  }

  public UserOtherInfo createdDate(Instant createdDate)
  {
    this.createdDate = createdDate;
    return this;
  }

  public Instant getModifiedDate()
  {
    return this.modifiedDate;
  }

  public void setModifiedDate(Instant modifiedDate)
  {
    this.modifiedDate = modifiedDate;
  }

  public UserOtherInfo modifiedDate(Instant modifiedDate)
  {
    this.modifiedDate = modifiedDate;
    return this;
  }

  public String getCreatedBy()
  {
    return this.createdBy;
  }

  public void setCreatedBy(String createdBy)
  {
    this.createdBy = createdBy;
  }

  public UserOtherInfo createdBy(String createdBy)
  {
    this.createdBy = createdBy;
    return this;
  }

  public String getModifiedBy()
  {
    return this.modifiedBy;
  }

  public void setModifiedBy(String modifiedBy)
  {
    this.modifiedBy = modifiedBy;
  }

  public UserOtherInfo modifiedBy(String modifiedBy)
  {
    this.modifiedBy = modifiedBy;
    return this;
  }

  public Long getDataSize()
  {
    return this.dataSize;
  }

  public void setDataSize(Long dataSize)
  {
    this.dataSize = dataSize;
  }

  public UserOtherInfo dataSize(Long dataSize)
  {
    this.dataSize = dataSize;
    return this;
  }

  public String getComment()
  {
    return this.comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  public UserOtherInfo comment(String comment)
  {
    this.comment = comment;
    return this;
  }

  public User getUserName()
  {
    return this.userName;
  }

  public void setUserName(User user)
  {
    this.userName = user;
  }

  public UserOtherInfo userName(User user)
  {
    this.setUserName(user);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof UserOtherInfo))
    {
      return false;
    }
    return id != null && id.equals(((UserOtherInfo) o).id);
  }

  @Override
  public int hashCode()
  {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
  @Override
  public String toString()
  {
    return "UserOtherInfo{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", phoneNumber='" + getPhoneNumber() + "'" +
      ", email='" + getEmail() + "'" +
      ", wardCode='" + getWardCode() + "'" +
      ", distCode='" + getDistCode() + "'" +
      ", cityCode='" + getCityCode() + "'" +
      ", addressDetails='" + getAddressDetails() + "'" +
      ", dateOfBirth='" + getDateOfBirth() + "'" +
      ", otherInfo='" + getOtherInfo() + "'" +
      ", searchField='" + getSearchField() + "'" +
      ", role='" + getRole() + "'" +
      ", createdDate='" + getCreatedDate() + "'" +
      ", modifiedDate='" + getModifiedDate() + "'" +
      ", createdBy='" + getCreatedBy() + "'" +
      ", modifiedBy='" + getModifiedBy() + "'" +
      ", dataSize=" + getDataSize() +
      ", comment='" + getComment() + "'" +
      "}";
  }
}
