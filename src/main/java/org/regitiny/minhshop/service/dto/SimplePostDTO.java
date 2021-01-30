package org.regitiny.minhshop.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.regitiny.minhshop.domain.SimplePost} entity.
 */
@ApiModel(description = "The Post entity.\n@author A true hipster")
public class SimplePostDTO implements Serializable {

    private Long id;

    /**
     * uuid
     */
    @NotNull
    @ApiModelProperty(value = "uuid", required = true)
    private UUID uuid;

    /**
     * title
     */
    @NotNull
    @Size(max = 128)
    @ApiModelProperty(value = "title", required = true)
    private String title;

    /**
     * price
     */
    @NotNull
    @ApiModelProperty(value = "price", required = true)
    private Double price;

    /**
     * price
     */
    @NotNull
    @ApiModelProperty(value = "price", required = true)
    private Double salePrice;

    /**
     * percentSale
     */
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @ApiModelProperty(value = "percentSale", required = true)
    private Float percentSale;

    /**
     * imageUrl
     */
    @NotNull
    @ApiModelProperty(value = "imageUrl", required = true)
    private String imageUrl;

    /**
     * scores
     */
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @ApiModelProperty(value = "scores", required = true)
    private Float scores;

    /**
     * simple content
     */

    @ApiModelProperty(value = "simple content")
    private String simpleContent;

    /**
     * otherInfo (Json)
     */
    @NotNull
    @ApiModelProperty(value = "otherInfo (Json)", required = true)
    private String otherInfo;

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

    private PostDetailsDTO postDetails;

    private TypePostDTO typePost;

    private Set<TypePostFilterDTO> typePostFilters = new HashSet<>();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Float getPercentSale() {
        return percentSale;
    }

    public void setPercentSale(Float percentSale) {
        this.percentSale = percentSale;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getScores() {
        return scores;
    }

    public void setScores(Float scores) {
        this.scores = scores;
    }

    public String getSimpleContent() {
        return simpleContent;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
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

    public PostDetailsDTO getPostDetails() {
        return postDetails;
    }

    public void setPostDetails(PostDetailsDTO postDetails) {
        this.postDetails = postDetails;
    }

    public TypePostDTO getTypePost() {
        return typePost;
    }

    public void setTypePost(TypePostDTO typePost) {
        this.typePost = typePost;
    }

    public Set<TypePostFilterDTO> getTypePostFilters() {
        return typePostFilters;
    }

    public void setTypePostFilters(Set<TypePostFilterDTO> typePostFilters) {
        this.typePostFilters = typePostFilters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimplePostDTO)) {
            return false;
        }

        SimplePostDTO simplePostDTO = (SimplePostDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, simplePostDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SimplePostDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", title='" + getTitle() + "'" +
            ", price=" + getPrice() +
            ", salePrice=" + getSalePrice() +
            ", percentSale=" + getPercentSale() +
            ", imageUrl='" + getImageUrl() + "'" +
            ", scores=" + getScores() +
            ", simpleContent='" + getSimpleContent() + "'" +
            ", otherInfo='" + getOtherInfo() + "'" +
            ", role='" + getRole() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", dataSize=" + getDataSize() +
            ", comment='" + getComment() + "'" +
            ", postDetails=" + getPostDetails() +
            ", typePost=" + getTypePost() +
            ", typePostFilters=" + getTypePostFilters() +
            "}";
    }
}
