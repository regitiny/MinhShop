package org.regitiny.minhshop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * The HanhChinhVN entity.\n@author a true jhipster
 */
@Entity
@Table(name = "hanh_chinh_vn")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "hanhchinhvn")
public class HanhChinhVN implements Serializable
{

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  /**
   * name Example(\"Ninh Kiều\")
   */
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * slug Example(\"ninh-kieu\")
   */
  @NotNull
  @Column(name = "slug", nullable = false)
  private String slug;

  /**
   * type Example(\"quan\")
   */
  @NotNull
  @Column(name = "type", nullable = false)
  private String type;

  /**
   * nameWithType Example(\"Quận Ninh Kiều\")
   */
  @NotNull
  @Column(name = "name_with_type", nullable = false)
  private String nameWithType;

  /**
   * code Example(\"916\")
   */
  @NotNull
  @Column(name = "code", nullable = false)
  private String code;

  /**
   * parentCode Example(\"92\") , equal to 0 is the city
   */
  @NotNull
  @Column(name = "parent_code", nullable = false)
  private String parentCode;

  /**
   * path Example(\"Ninh Kiều, Cần Thơ\")
   */
  @Column(name = "path")
  private String path;

  /**
   * pathWithType Example(\"Quận Ninh Kiều, Thành phố Cần Thơ\")
   */
  @Column(name = "path_with_type")
  private String pathWithType;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public HanhChinhVN id(Long id)
  {
    this.id = id;
    return this;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public HanhChinhVN name(String name)
  {
    this.name = name;
    return this;
  }

  public String getSlug()
  {
    return this.slug;
  }

  public void setSlug(String slug)
  {
    this.slug = slug;
  }

  public HanhChinhVN slug(String slug)
  {
    this.slug = slug;
    return this;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public HanhChinhVN type(String type)
  {
    this.type = type;
    return this;
  }

  public String getNameWithType()
  {
    return this.nameWithType;
  }

  public void setNameWithType(String nameWithType)
  {
    this.nameWithType = nameWithType;
  }

  public HanhChinhVN nameWithType(String nameWithType)
  {
    this.nameWithType = nameWithType;
    return this;
  }

  public String getCode()
  {
    return this.code;
  }

  public void setCode(String code)
  {
    this.code = code;
  }

  public HanhChinhVN code(String code)
  {
    this.code = code;
    return this;
  }

  public String getParentCode()
  {
    return this.parentCode;
  }

  public void setParentCode(String parentCode)
  {
    this.parentCode = parentCode;
  }

  public HanhChinhVN parentCode(String parentCode)
  {
    this.parentCode = parentCode;
    return this;
  }

  public String getPath()
  {
    return this.path;
  }

  public void setPath(String path)
  {
    this.path = path;
  }

  public HanhChinhVN path(String path)
  {
    this.path = path;
    return this;
  }

  public String getPathWithType()
  {
    return this.pathWithType;
  }

  public void setPathWithType(String pathWithType)
  {
    this.pathWithType = pathWithType;
  }

  public HanhChinhVN pathWithType(String pathWithType)
  {
    this.pathWithType = pathWithType;
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
    if (!(o instanceof HanhChinhVN))
    {
      return false;
    }
    return id != null && id.equals(((HanhChinhVN) o).id);
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
    return "HanhChinhVN{" +
      "id=" + getId() +
      ", name='" + getName() + "'" +
      ", slug='" + getSlug() + "'" +
      ", type='" + getType() + "'" +
      ", nameWithType='" + getNameWithType() + "'" +
      ", code='" + getCode() + "'" +
      ", parentCode='" + getParentCode() + "'" +
      ", path='" + getPath() + "'" +
      ", pathWithType='" + getPathWithType() + "'" +
      "}";
  }
}
