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
 * A DTO for the {@link org.regitiny.minhshop.domain.File} entity.
 */
@ApiModel(description = "The File entity.\n@author yuvytung")
public class FileDTO implements Serializable
{

  private Long id;

  /**
   * the uuid equivalent id (primary key)
   */
  @NotNull
  @ApiModelProperty(value = "the uuid equivalent id (primary key)", required = true)
  private UUID uuid;

  /**
   * fileData is data of video in binary form
   */

  @ApiModelProperty(value = "fileData is data of video in binary form", required = true)
  @Lob
  private byte[] fileData;

  private String fileDataContentType;

  /**
   * name of file (Default is uuid + extension)
   */
  @Size(max = 1024)
  @ApiModelProperty(value = "name of file (Default is uuid + extension)")
  private String nameFile;

  /**
   * extension of video (Example .mp4 ... )
   */
  @Size(max = 16)
  @ApiModelProperty(value = "extension of video (Example .mp4 ... )")
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

  public byte[] getFileData()
  {
    return fileData;
  }

  public void setFileData(byte[] fileData)
  {
    this.fileData = fileData;
  }

  public String getFileDataContentType()
  {
    return fileDataContentType;
  }

  public void setFileDataContentType(String fileDataContentType)
  {
    this.fileDataContentType = fileDataContentType;
  }

  public String getNameFile()
  {
    return nameFile;
  }

  public void setNameFile(String nameFile)
  {
    this.nameFile = nameFile;
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
    if (!(o instanceof FileDTO))
    {
      return false;
    }

    FileDTO fileDTO = (FileDTO) o;
    if (this.id == null)
    {
      return false;
    }
    return Objects.equals(this.id, fileDTO.id);
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
    return "FileDTO{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", fileData='" + getFileData() + "'" +
      ", nameFile='" + getNameFile() + "'" +
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
