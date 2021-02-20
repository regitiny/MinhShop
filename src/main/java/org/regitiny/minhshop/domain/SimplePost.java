package org.regitiny.minhshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

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
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * price
     */
    @Column(name = "price")
    private Double price;

    /**
     * price
     */
    @Column(name = "sale_price")
    private Double salePrice;

    /**
     * percentSale
     */
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "percent_sale")
    private Float percentSale;

    /**
     * imageUrl
     */
    @NotNull
    @Size(max = 2048)
    @Column(name = "image_url", length = 2048, nullable = false)
    private String imageUrl;

    /**
     * scores
     */
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    @Column(name = "scores")
    private Float scores;

    /**
     * simple content
     */
    @Column(name = "simple_content")
    private String simpleContent;

    /**
     * otherInfo (Json)
     */
    @Column(name = "other_info")
    private String otherInfo;

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

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public SimplePost uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SimplePost title(String title) {
        this.title = title;
        return this;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public SimplePost price(Double price) {
        this.price = price;
        return this;
    }

    public Double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public SimplePost salePrice(Double salePrice) {
        this.salePrice = salePrice;
        return this;
    }

    public Float getPercentSale() {
        return this.percentSale;
    }

    public void setPercentSale(Float percentSale) {
        this.percentSale = percentSale;
    }

    public SimplePost percentSale(Float percentSale) {
        this.percentSale = percentSale;
        return this;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public SimplePost imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Float getScores() {
        return this.scores;
    }

    public void setScores(Float scores) {
        this.scores = scores;
    }

    public SimplePost scores(Float scores) {
        this.scores = scores;
        return this;
    }

    public String getSimpleContent() {
        return this.simpleContent;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
    }

    public SimplePost simpleContent(String simpleContent) {
        this.simpleContent = simpleContent;
        return this;
    }

    public String getOtherInfo() {
        return this.otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public SimplePost otherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
        return this;
    }

    public String getSearchField() {
        return this.searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public SimplePost searchField(String searchField) {
        this.searchField = searchField;
        return this;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public SimplePost role(String role) {
        this.role = role;
        return this;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public SimplePost createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public SimplePost modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public SimplePost createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public SimplePost modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public Long getDataSize() {
        return this.dataSize;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
    }

    public SimplePost dataSize(Long dataSize) {
        this.dataSize = dataSize;
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public SimplePost comment(String comment) {
        this.comment = comment;
        return this;
    }

    public PostDetails getPostDetails() {
        return this.postDetails;
    }

    public void setPostDetails(PostDetails postDetails) {
        this.postDetails = postDetails;
    }

    public SimplePost postDetails(PostDetails postDetails) {
        this.setPostDetails(postDetails);
        return this;
    }

    public TypePost getTypePost() {
        return this.typePost;
    }

    public void setTypePost(TypePost typePost) {
        this.typePost = typePost;
    }

    public SimplePost typePost(TypePost typePost) {
        this.setTypePost(typePost);
        return this;
    }

    public Set<TypePostFilter> getTypePostFilters() {
        return this.typePostFilters;
    }

    public void setTypePostFilters(Set<TypePostFilter> typePostFilters) {
        this.typePostFilters = typePostFilters;
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
  public String toString()
  {
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
