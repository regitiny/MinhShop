package org.regitiny.minhshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * The Post entity.\n@author A true hipster
 */
@Entity
@Table(name = "simple_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "simplepost")
public class SimplePost implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    /**
     * uuid
     */
    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "uuid", length = 36, nullable = false, unique = true)
    private UUID uuid;

    /**
     * title
     */
    @NotNull
    @Size(max = 128)
    @Column(name = "title", length = 128, nullable = false)
    private String title;

    /**
     * price
     */
    @NotNull
    @Column(name = "price", nullable = false)
    private Double price;

    /**
     * price
     */
    @NotNull
    @Column(name = "sale_price", nullable = false)
    private Double salePrice;

    /**
     * percentSale
     */
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "percent_sale", nullable = false)
    private Float percentSale;

    /**
     * imageUrl
     */
    @NotNull
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    /**
     * scores
     */
    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "scores", nullable = false)
    private Float scores;

    /**
     * simple content
     */

    @Column(name = "simple_content", unique = true)
    private String simpleContent;

    /**
     * otherInfo (Json)
     */
    @NotNull
    @Column(name = "other_info", nullable = false)
    private String otherInfo;

    /**
     * role
     */
    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    /**
     * createdDate
     */
    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    /**
     * modifiedDate
     */
    @NotNull
    @Column(name = "modified_date", nullable = false)
    private Instant modifiedDate;

    /**
     * createdBy
     */
    @NotNull
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    /**
     * modifiedBy
     */
    @NotNull
    @Column(name = "modified_by", nullable = false)
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

    @JsonIgnoreProperties(value = { "simplePost" }, allowSetters = true)
    @OneToOne(optional = false)
    @NotNull
    @MapsId
    @JoinColumn(name = "id")
    private PostDetails postDetails;

    @ManyToOne
    private TypePost typePost;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "rel_simple_post__type_post_filter",
        joinColumns = @JoinColumn(name = "simple_post_id"),
        inverseJoinColumns = @JoinColumn(name = "type_post_filter_id")
    )
    @JsonIgnoreProperties(value = { "simplePosts" }, allowSetters = true)
    private Set<TypePostFilter> typePostFilters = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SimplePost id(Long id) {
        this.id = id;
        return this;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public SimplePost uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return this.title;
    }

    public SimplePost title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return this.price;
    }

    public SimplePost price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return this.salePrice;
    }

    public SimplePost salePrice(Double salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public Float getPercentSale() {
        return this.percentSale;
    }

    public SimplePost percentSale(Float percentSale) {
        this.percentSale = percentSale;
        return this;
    }

    public void setPercentSale(Float percentSale) {
        this.percentSale = percentSale;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public SimplePost imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Float getScores() {
        return this.scores;
    }

    public SimplePost scores(Float scores) {
        this.scores = scores;
        return this;
    }

    public void setScores(Float scores) {
        this.scores = scores;
    }

    public String getSimpleContent() {
        return this.simpleContent;
    }

    public SimplePost simpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
        return this;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
    }

    public String getOtherInfo() {
        return this.otherInfo;
    }

    public SimplePost otherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
        return this;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getRole() {
        return this.role;
    }

    public SimplePost role(String role) {
        this.role = role;
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public SimplePost createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public SimplePost modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public SimplePost createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public SimplePost modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Long getDataSize() {
        return this.dataSize;
    }

    public SimplePost dataSize(Long dataSize) {
        this.dataSize = dataSize;
        return this;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
    }

    public String getComment() {
        return this.comment;
    }

    public SimplePost comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PostDetails getPostDetails() {
        return this.postDetails;
    }

    public SimplePost postDetails(PostDetails postDetails) {
        this.setPostDetails(postDetails);
        return this;
    }

    public void setPostDetails(PostDetails postDetails) {
        this.postDetails = postDetails;
    }

    public TypePost getTypePost() {
        return this.typePost;
    }

    public SimplePost typePost(TypePost typePost) {
        this.setTypePost(typePost);
        return this;
    }

    public void setTypePost(TypePost typePost) {
        this.typePost = typePost;
    }

    public Set<TypePostFilter> getTypePostFilters() {
        return this.typePostFilters;
    }

    public SimplePost typePostFilters(Set<TypePostFilter> typePostFilters) {
        this.setTypePostFilters(typePostFilters);
        return this;
    }

    public SimplePost addTypePostFilter(TypePostFilter typePostFilter) {
        this.typePostFilters.add(typePostFilter);
        typePostFilter.getSimplePosts().add(this);
        return this;
    }

    public SimplePost removeTypePostFilter(TypePostFilter typePostFilter) {
        this.typePostFilters.remove(typePostFilter);
        typePostFilter.getSimplePosts().remove(this);
        return this;
    }

    public void setTypePostFilters(Set<TypePostFilter> typePostFilters) {
        this.typePostFilters = typePostFilters;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SimplePost)) {
            return false;
        }
        return id != null && id.equals(((SimplePost) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SimplePost{" +
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
            "}";
    }
}
