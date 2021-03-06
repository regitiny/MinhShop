package org.regitiny.minhshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * The BillDetails entity.\n@author a true jhipster
 */
@Entity
@Table(name = "bill")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "bill")
public class Bill implements Serializable
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
   * idShow
   */
  @NotNull
  @Size(max = 16)
  @Column(name = "bill_id", length = 16, nullable = false, unique = true)
  private String billId;

  /**
   * phoneNumber
   */
  @NotNull
  @Size(min = 10, max = 10)
  @Pattern(regexp = "[0-9]+")
  @Column(name = "phone_number", length = 10, nullable = false)
  private String phoneNumber;

  /**
   * email
   */
  @Column(name = "email")
  private String email;

  /**
   * addressDetails
   */
  @Column(name = "address_details")
  private String addressDetails;

  /**
   * addressCode
   */
  @Column(name = "address_code")
  private String addressCode;

  @Size(max = 65535)
  @Column(name = "product", length = 65535)
  private String product;

  /**
   * comment
   */
  @Size(max = 2048)
  @Column(name = "comment", length = 2048)
  private String comment;

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

  @ManyToOne
  @JsonIgnoreProperties(value = {"userName"}, allowSetters = true)
  private UserOtherInfo userOtherInfo;

  @JsonIgnoreProperties(value = {"billId"}, allowSetters = true)
  @OneToOne(mappedBy = "billId")
  private Payment payment;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public Bill id(Long id)
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

  public Bill uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public String getBillId()
  {
    return this.billId;
  }

  public void setBillId(String billId)
  {
    this.billId = billId;
  }

  public Bill billId(String billId)
  {
    this.billId = billId;
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

  public Bill phoneNumber(String phoneNumber)
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

  public Bill email(String email)
  {
    this.email = email;
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

  public Bill addressDetails(String addressDetails)
  {
    this.addressDetails = addressDetails;
    return this;
  }

  public String getAddressCode()
  {
    return this.addressCode;
  }

  public void setAddressCode(String addressCode)
  {
    this.addressCode = addressCode;
  }

  public Bill addressCode(String addressCode)
  {
    this.addressCode = addressCode;
    return this;
  }

  public String getProduct()
  {
    return this.product;
  }

  public void setProduct(String product)
  {
    this.product = product;
  }

  public Bill product(String product)
  {
    this.product = product;
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

  public Bill comment(String comment)
  {
    this.comment = comment;
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

  public Bill searchField(String searchField)
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

  public Bill role(String role)
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

  public Bill createdDate(Instant createdDate)
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

  public Bill modifiedDate(Instant modifiedDate)
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

  public Bill createdBy(String createdBy)
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

  public Bill modifiedBy(String modifiedBy)
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

  public Bill dataSize(Long dataSize)
  {
    this.dataSize = dataSize;
    return this;
  }

  public UserOtherInfo getUserOtherInfo()
  {
    return this.userOtherInfo;
  }

  public void setUserOtherInfo(UserOtherInfo userOtherInfo)
  {
    this.userOtherInfo = userOtherInfo;
  }

  public Bill userOtherInfo(UserOtherInfo userOtherInfo)
  {
    this.setUserOtherInfo(userOtherInfo);
    return this;
  }

  public Payment getPayment()
  {
    return this.payment;
  }

  public void setPayment(Payment payment)
  {
    if (this.payment != null)
    {
      this.payment.setBillId(null);
    }
    if (payment != null)
    {
      payment.setBillId(this);
    }
    this.payment = payment;
  }

  public Bill payment(Payment payment)
  {
    this.setPayment(payment);
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
    if (!(o instanceof Bill))
    {
      return false;
    }
    return id != null && id.equals(((Bill) o).id);
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
    return "Bill{" +
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
      "}";
  }

  public void cleanInfiniteInterlockingRelationship()
  {
    if (payment != null)
      payment = payment.billId(null);
  }
}
