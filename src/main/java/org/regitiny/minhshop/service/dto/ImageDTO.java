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
 * A DTO for the {@link org.regitiny.minhshop.domain.Image} entity.
 */
@ApiModel(description = "The Image entity.\n@author yuvytung")
public class ImageDTO implements Serializable
{

  private Long id;

  /**
   * the uuid equivalent id (primary key)
   */
  @NotNull
  @ApiModelProperty(value = "the uuid equivalent id (primary key)", required = true)
  private UUID uuid;

  /**
   * imageData is data of image in binary form
   */

  @ApiModelProperty(value = "imageData is data of image in binary form", required = true)
  @Lob
  private byte[] imageData;

  private String imageDataContentType;

  /**
   * name of image (Default is uuid + extension)
   */
  @Size(max = 1024)
  @ApiModelProperty(value = "name of image (Default is uuid + extension)")
  private String nameImage;

  /**
   * extension of image (Example .jpg .png ... )
   */
  @Size(max = 16)
  @ApiModelProperty(value = "extension of image (Example .jpg .png ... )")
  private String extension;

  /**
   * typeFile
   */
  @Size(max = 32)
  @ApiModelProperty(value = "typeFile")
  private String typeFile;

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

  public byte[] getImageData()
  {
    return imageData;
  }

  public void setImageData(byte[] imageData)
  {
    this.imageData = imageData;
  }

  public String getImageDataContentType()
  {
    return imageDataContentType;
  }

  public void setImageDataContentType(String imageDataContentType)
  {
    this.imageDataContentType = imageDataContentType;
  }

  public String getNameImage()
  {
    return nameImage;
  }

  public void setNameImage(String nameImage)
  {
    this.nameImage = nameImage;
  }

  public String getExtension()
  {
    return extension;
  }

  public void setExtension(String extension)
  {
    this.extension = extension;
  }

  public String getTypeFile()
  {
    return typeFile;
  }

  public void setTypeFile(String typeFile)
  {
    this.typeFile = typeFile;
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
    if (!(o instanceof ImageDTO))
    {
      return false;
    }

    ImageDTO imageDTO = (ImageDTO) o;
    if (this.id == null)
    {
      return false;
    }
    return Objects.equals(this.id, imageDTO.id);
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
    return "ImageDTO{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", imageData='" + getImageData() + "'" +
      ", nameImage='" + getNameImage() + "'" +
      ", extension='" + getExtension() + "'" +
      ", typeFile='" + getTypeFile() + "'" +
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
