package org.regitiny.minhshop.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.regitiny.minhshop.domain.UserOtherInfo} entity.
 */
@ApiModel(description = "The UserOtherInfo entity.\n@author a true jhipster")
public class UserOtherInfoDTO implements Serializable {

    private Long id;

    /**
     * uuid
     */
    @NotNull
    @ApiModelProperty(value = "uuid", required = true)
    private UUID uuid;

    /**
     * phoneNumber
     */
    @Size(min = 10, max = 10)
    @Pattern(regexp = "[0-9]+")
    @ApiModelProperty(value = "phoneNumber")
    private String phoneNumber;

    /**
     * email
     */
    @ApiModelProperty(value = "email")
    private String email;

    /**
     * wardCode
     */
    @ApiModelProperty(value = "wardCode")
    private String wardCode;

    /**
     * distCode
     */
    @ApiModelProperty(value = "distCode")
    private String distCode;

    /**
     * cityCode
     */
    @ApiModelProperty(value = "cityCode")
    private String cityCode;

    /**
     * addressDetails
     */
    @ApiModelProperty(value = "addressDetails")
    private String addressDetails;

    /**
     * dateOfBirth
     */
    @ApiModelProperty(value = "dateOfBirth")
    private LocalDate dateOfBirth;

    /**
     * otherInfo
     */
    @ApiModelProperty(value = "otherInfo")
    private String otherInfo;

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

    private UserDTO userName;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
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

    public UserDTO getUserName() {
        return userName;
    }

    public void setUserName(UserDTO userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserOtherInfoDTO)) {
            return false;
        }

        UserOtherInfoDTO userOtherInfoDTO = (UserOtherInfoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userOtherInfoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserOtherInfoDTO{" +
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
            ", userName=" + getUserName() +
            "}";
    }
}
