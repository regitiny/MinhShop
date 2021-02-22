package org.regitiny.minhshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * The PostDetails entity.\n@author A true hipster
 */
@Entity
@Table(name = "post_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "postdetails")
public class PostDetails implements Serializable
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
   * postDetailsId
   */
  @NotNull
  @Size(min = 3, max = 16)
  @Pattern(regexp = "[A-z]+[0-9]+")
  @Column(name = "post_details_id", length = 16, nullable = false, unique = true)
  private String postDetailsId;

  /**
   * content
   */
  @Lob
  @Column(name = "content")
  private String content;

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

  @Size(max = 10000)
  @Column(name = "other_data", length = 10000)
  private String otherData;

  @JsonIgnoreProperties(value = {"postDetails", "typePost", "typePostFilters"}, allowSetters = true)
  @OneToOne(mappedBy = "postDetails")
  private SimplePost simplePost;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public PostDetails id(Long id)
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

  public PostDetails uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public String getPostDetailsId()
  {
    return this.postDetailsId;
  }

  public void setPostDetailsId(String postDetailsId)
  {
    this.postDetailsId = postDetailsId;
  }

  public PostDetails postDetailsId(String postDetailsId)
  {
    this.postDetailsId = postDetailsId;
    return this;
  }

  public String getContent()
  {
    return this.content;
  }

  public void setContent(String content)
  {
    this.content = content;
  }

  public PostDetails content(String content)
  {
    this.content = content;
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

  public PostDetails searchField(String searchField)
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

  public PostDetails role(String role)
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

  public PostDetails createdDate(Instant createdDate)
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

  public PostDetails modifiedDate(Instant modifiedDate)
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

  public PostDetails createdBy(String createdBy)
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

  public PostDetails modifiedBy(String modifiedBy)
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

  public PostDetails dataSize(Long dataSize)
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

  public PostDetails comment(String comment)
  {
    this.comment = comment;
    return this;
  }

  public String getOtherData()
  {
    return this.otherData;
  }

  public void setOtherData(String otherData)
  {
    this.otherData = otherData;
  }

  public PostDetails otherData(String otherData)
  {
    this.otherData = otherData;
    return this;
  }

  public SimplePost getSimplePost()
  {
    return this.simplePost;
  }

  public void setSimplePost(SimplePost simplePost)
  {
    if (this.simplePost != null)
    {
      this.simplePost.setPostDetails(null);
    }
    if (simplePost != null)
    {
      simplePost.setPostDetails(this);
    }
    this.simplePost = simplePost;
  }

  public PostDetails simplePost(SimplePost simplePost)
  {
    this.setSimplePost(simplePost);
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
    if (!(o instanceof PostDetails))
    {
      return false;
    }
    return id != null && id.equals(((PostDetails) o).id);
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
    return "PostDetails{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", postDetailsId='" + getPostDetailsId() + "'" +
      ", content='" + getContent() + "'" +
      ", searchField='" + getSearchField() + "'" +
      ", role='" + getRole() + "'" +
      ", createdDate='" + getCreatedDate() + "'" +
      ", modifiedDate='" + getModifiedDate() + "'" +
      ", createdBy='" + getCreatedBy() + "'" +
      ", modifiedBy='" + getModifiedBy() + "'" +
      ", dataSize=" + getDataSize() +
      ", comment='" + getComment() + "'" +
      ", otherData='" + getOtherData() + "'" +
      "}";
  }
}
