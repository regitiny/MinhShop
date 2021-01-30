package org.regitiny.minhshop.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.regitiny.minhshop.domain.Image} entity.
 */
@ApiModel(description = "The Image entity.\n@author yuvytung")
public class ImageDTO implements Serializable {

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
     * role
     */
    @NotNull
    @ApiModelProperty(value = "role", required = true)
    private String role;

    /**
     * createdDate
     */
    @NotNull
    @ApiModelProperty(value = "createdDate", required = true)
    private Instant createdDate;

    /**
     * modifiedDate
     */
    @NotNull
    @ApiModelProperty(value = "modifiedDate", required = true)
    private Instant modifiedDate;

    /**
     * createdBy
     */
    @NotNull
    @ApiModelProperty(value = "createdBy", required = true)
    private String createdBy;

    /**
     * modifiedBy
     */
    @NotNull
    @ApiModelProperty(value = "modifiedBy", required = true)
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

    /**
     * deleted (deleted set to true , not delete set to false)
     */
    @ApiModelProperty(value = "deleted (deleted set to true , not delete set to false)")
    private Boolean deleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getImageDataContentType() {
        return imageDataContentType;
    }

    public void setImageDataContentType(String imageDataContentType) {
        this.imageDataContentType = imageDataContentType;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getDataSize() {
        return dataSize;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ImageDTO)) {
            return false;
        }

        ImageDTO imageDTO = (ImageDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, imageDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ImageDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", imageData='" + getImageData() + "'" +
            ", nameImage='" + getNameImage() + "'" +
            ", extension='" + getExtension() + "'" +
            ", typeFile='" + getTypeFile() + "'" +
            ", role='" + getRole() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", dataSize=" + getDataSize() +
            ", comment='" + getComment() + "'" +
            ", deleted='" + getDeleted() + "'" +
            "}";
    }
}
