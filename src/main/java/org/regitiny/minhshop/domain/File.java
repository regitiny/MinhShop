package org.regitiny.minhshop.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * The File entity.\n@author yuvytung
 */
@Entity
@Table(name = "file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "file")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * the uuid equivalent id (primary key)
     */
    @NotNull
    @Type(type = "uuid-char")
    @Column(name = "uuid", length = 36, nullable = false, unique = true)
    private UUID uuid;

    /**
     * videoData is data of video in binary form
     */

    @Lob
    @Column(name = "video_data", nullable = false)
    private byte[] videoData;

    @Column(name = "video_data_content_type", nullable = false)
    private String videoDataContentType;

    /**
     * name of video (Default is uuid + extension)
     */
    @Size(max = 1024)
    @Column(name = "name_video", length = 1024)
    private String nameVideo;

    /**
     * extension of video (Example .mp4 ... )
     */
    @Size(max = 16)
    @Column(name = "extension", length = 16)
    private String extension;

    /**
     * typeFile
     */
    @Size(max = 32)
    @Column(name = "type_file", length = 32)
    private String typeFile;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public File id(Long id) {
        this.id = id;
        return this;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public File uuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public byte[] getVideoData() {
        return this.videoData;
    }

    public void setVideoData(byte[] videoData) {
        this.videoData = videoData;
    }

    public File videoData(byte[] videoData) {
        this.videoData = videoData;
        return this;
    }

    public String getVideoDataContentType() {
        return this.videoDataContentType;
    }

    public void setVideoDataContentType(String videoDataContentType) {
        this.videoDataContentType = videoDataContentType;
    }

    public File videoDataContentType(String videoDataContentType) {
        this.videoDataContentType = videoDataContentType;
        return this;
    }

    public String getNameVideo() {
        return this.nameVideo;
    }

    public void setNameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
    }

    public File nameVideo(String nameVideo) {
        this.nameVideo = nameVideo;
        return this;
    }

    public String getExtension() {
        return this.extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public File extension(String extension) {
        this.extension = extension;
        return this;
    }

    public String getTypeFile() {
        return this.typeFile;
    }

    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

    public File typeFile(String typeFile) {
        this.typeFile = typeFile;
        return this;
    }

    public String getSearchField() {
        return this.searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public File searchField(String searchField) {
        this.searchField = searchField;
        return this;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public File role(String role) {
        this.role = role;
        return this;
    }

    public Instant getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public File createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Instant getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public File modifiedDate(Instant modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public File createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public File modifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        return this;
    }

    public Long getDataSize() {
        return this.dataSize;
    }

    public void setDataSize(Long dataSize) {
        this.dataSize = dataSize;
    }

    public File dataSize(Long dataSize) {
        this.dataSize = dataSize;
        return this;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public File comment(String comment) {
        this.comment = comment;
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File)) {
            return false;
        }
        return id != null && id.equals(((File) o).id);
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
    return "File{" +
      "id=" + getId() +
      ", uuid='" + getUuid() + "'" +
      ", videoData='" + getVideoData() + "'" +
      ", videoDataContentType='" + getVideoDataContentType() + "'" +
      ", nameVideo='" + getNameVideo() + "'" +
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
