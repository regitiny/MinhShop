package org.regitiny.minhshop.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * A DTO for the {@link org.regitiny.minhshop.domain.TypePostFilter} entity.
 */
@ApiModel(description = "The TypeFilter entity.\n@author a true jhipster")
public class TypePostFilterDTO implements Serializable
{

  private Long id;

  /**
   * uuid
   */
  @NotNull
  @ApiModelProperty(value = "uuid", required = true)
  private UUID uuid;

  /**
   * typeFilterName
   */
  @NotNull
  @ApiModelProperty(value = "typeFilterName", required = true)
  private String typeFilterName;

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

  /**
   * comment
   */
  @Size(max = 2048)
  @ApiModelProperty(value = "comment")
  private String comment;

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

  public String getTypeFilterName()
  {
    return typeFilterName;
  }

  public void setTypeFilterName(String typeFilterName)
  {
    this.typeFilterName = typeFilterName;
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

  public String getComment()
  {
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (!(o instanceof TypePostFilterDTO))
    {
      return false;
    }

    TypePostFilterDTO typePostFilterDTO = (TypePostFilterDTO) o;
    if (this.id == null)
    {
      return false;
    }
    return Objects.equals(this.id, typePostFilterDTO.id);
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
    return "TypePostFilterDTO{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", typeFilterName='" + getTypeFilterName() + "'" +
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
