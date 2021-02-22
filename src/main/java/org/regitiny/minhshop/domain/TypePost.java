package org.regitiny.minhshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * The TypePost entity.\n@author a true jhipster
 */
@Entity
@Table(name = "type_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "typepost")
public class TypePost implements Serializable
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
   * typeName
   */
  @NotNull
  @Column(name = "type_name", nullable = false, unique = true)
  private String typeName;

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

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public TypePost id(Long id)
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

  public TypePost uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public String getTypeName()
  {
    return this.typeName;
  }

  public void setTypeName(String typeName)
  {
    this.typeName = typeName;
  }

  public TypePost typeName(String typeName)
  {
    this.typeName = typeName;
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

  public TypePost searchField(String searchField)
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

  public TypePost role(String role)
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

  public TypePost createdDate(Instant createdDate)
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

  public TypePost modifiedDate(Instant modifiedDate)
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

  public TypePost createdBy(String createdBy)
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

  public TypePost modifiedBy(String modifiedBy)
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

  public TypePost dataSize(Long dataSize)
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

  public TypePost comment(String comment)
  {
    this.comment = comment;
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
    if (!(o instanceof TypePost))
    {
      return false;
    }
    return id != null && id.equals(((TypePost) o).id);
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
    return "TypePost{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", typeName='" + getTypeName() + "'" +
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
