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
 * A DTO for the {@link org.regitiny.minhshop.domain.Payment} entity.
 */
@ApiModel(description = "The Payment entity.\n@author A true hipster")
public class PaymentDTO implements Serializable {

    private Long id;

    /**
     * uuid
     */
    @NotNull
    @ApiModelProperty(value = "uuid", required = true)
    private UUID uuid;

    /**
     * status
     */
    @ApiModelProperty(value = "status")
    private String status;

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

    private BillDTO billId;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
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

    public BillDTO getBillId() {
        return billId;
    }

    public void setBillId(BillDTO billId) {
        this.billId = billId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PaymentDTO)) {
            return false;
        }

        PaymentDTO paymentDTO = (PaymentDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, paymentDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", status='" + getStatus() + "'" +
            ", searchField='" + getSearchField() + "'" +
            ", role='" + getRole() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", modifiedBy='" + getModifiedBy() + "'" +
            ", dataSize=" + getDataSize() +
            ", comment='" + getComment() + "'" +
            ", billId=" + getBillId() +
            "}";
    }
}
