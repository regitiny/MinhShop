package org.regitiny.minhshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The TypeFilter entity.\n@author a true jhipster
 */
@Entity
@Table(name = "type_post_filter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "typepostfilter")
public class TypePostFilter implements Serializable
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
   * typeFilterName
   */
  @NotNull
  @Column(name = "type_filter_name", nullable = false, unique = true)
  private String typeFilterName;

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

  @ManyToMany(mappedBy = "typePostFilters")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = {"postDetails", "typePost", "typePostFilters"}, allowSetters = true)
  private Set<SimplePost> simplePosts = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public TypePostFilter id(Long id)
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

  public TypePostFilter uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  public String getTypeFilterName()
  {
    return this.typeFilterName;
  }

  public void setTypeFilterName(String typeFilterName)
  {
    this.typeFilterName = typeFilterName;
  }

  public TypePostFilter typeFilterName(String typeFilterName)
  {
    this.typeFilterName = typeFilterName;
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

  public TypePostFilter searchField(String searchField)
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

  public TypePostFilter role(String role)
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

  public TypePostFilter createdDate(Instant createdDate)
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

  public TypePostFilter modifiedDate(Instant modifiedDate)
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

  public TypePostFilter createdBy(String createdBy)
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

  public TypePostFilter modifiedBy(String modifiedBy)
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

  public TypePostFilter dataSize(Long dataSize)
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

  public TypePostFilter comment(String comment)
  {
    this.comment = comment;
    return this;
  }

  public Set<SimplePost> getSimplePosts()
  {
    return this.simplePosts;
  }

  public void setSimplePosts(Set<SimplePost> simplePosts)
  {
    if (simplePosts == null)
    {
      this.simplePosts = null;
      return;
    }
    if (this.simplePosts != null)
    {
      this.simplePosts.forEach(i -> i.removeTypePostFilter(this));
    }
    if (simplePosts != null)
    {
      simplePosts.forEach(i -> i.addTypePostFilter(this));
    }
    this.simplePosts = simplePosts;
  }
//  //  yuvytung: practical experience
//  public void clearSimplePosts()
//  {
//    this.simplePosts = null;
//  }

  public TypePostFilter simplePosts(Set<SimplePost> simplePosts)
  {
    this.setSimplePosts(simplePosts);
    return this;
  }

  public TypePostFilter addSimplePost(SimplePost simplePost)
  {
    this.simplePosts.add(simplePost);
    simplePost.getTypePostFilters().add(this);
    return this;
  }

  public TypePostFilter removeSimplePost(SimplePost simplePost)
  {
    this.simplePosts.remove(simplePost);
    simplePost.getTypePostFilters().remove(this);
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
    if (!(o instanceof TypePostFilter))
    {
      return false;
    }
    return id != null && id.equals(((TypePostFilter) o).id);
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
    return "TypePostFilter{" +
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

  public void cleanInfiniteInterlockingRelationship()
  {
    if (simplePosts != null)
    {
      Set<SimplePost> reuSimplePosts = new HashSet<>();
      this.simplePosts.forEach(simplePost -> reuSimplePosts.add(simplePost.typePostFilters(null)));
      this.simplePosts = reuSimplePosts;
    }
  }
}
