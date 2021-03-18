package org.regitiny.tools.magic.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * response default
 */
@ApiModel(description = "response default")
public class ResponseDefault
{
  @JsonProperty("id")
  private Long id;

  @JsonProperty("uuid")
  private UUID uuid;

  @JsonProperty("title")
  private String title = "Created successfully";

  @JsonProperty("type")
  private String type = "Create";

  @JsonProperty("status")
  private Integer status = 200;

  @JsonProperty("message")
  private String message = "Done.";

  @JsonProperty("detail")
  private String detail;

  @JsonProperty("path")
  private String path;

  @JsonProperty("time")
  @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME)
  private Instant time = Instant.now();

  @JsonProperty("dataResponse")
  private Object dataResponse;

  public ResponseDefault statusDelete()
  {
    this.title = "Deleted successfully";
    this.type = "Delete";
    return this;
  }

  public ResponseDefault title(String title)
  {
    this.title = title;
    return this;
  }

  /**
   * Get title
   *
   * @return title
   */
  @ApiModelProperty(example = "Created successfully", value = "")
  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public ResponseDefault type(String type)
  {
    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   */
  @ApiModelProperty(example = "Create", value = "")
  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public ResponseDefault status(Integer status)
  {
    this.status = status;
    return this;
  }

  /**
   * Get status
   *
   * @return status
   */
  @ApiModelProperty(example = "200", value = "")
  public Integer getStatus()
  {
    return status;
  }

  public void setStatus(Integer status)
  {
    this.status = status;
  }

  public ResponseDefault message(String message)
  {
    this.message = message;
    return this;
  }

  /**
   * Get message
   *
   * @return message
   */
  @ApiModelProperty(value = "")
  public String getMessage()
  {
    return message;
  }

  public void setMessage(String message)
  {
    this.message = message;
  }

  public ResponseDefault detail(String detail)
  {
    this.detail = detail;
    return this;
  }

  /**
   * Get detail
   *
   * @return detail
   */
  @ApiModelProperty(example = "You have successfully uploaded", value = "")
  public String getDetail()
  {
    return detail;
  }

  public void setDetail(String detail)
  {
    this.detail = detail;
  }

  public ResponseDefault path(String path)
  {
    this.path = path;
    return this;
  }

  /**
   * Get path
   *
   * @return path
   */
  @ApiModelProperty(example = "/api/file/upload", value = "")
  public String getPath()
  {
    return path;
  }

  public void setPath(String path)
  {
    this.path = path;
  }

  public ResponseDefault id(Long id)
  {
    this.id = id;
    return this;
  }

  /**
   * Get id
   *
   * @return id
   */
  @ApiModelProperty(example = "10194211", value = "")
  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public ResponseDefault uuid(UUID uuid)
  {
    this.uuid = uuid;
    return this;
  }

  /**
   * Get uuid
   *
   * @return uuid
   */
  @ApiModelProperty(example = "31c202af-5ea5-4e3c-9a3d-b00ba866ba25", value = "")

  @Valid

  public UUID getUuid()
  {
    return uuid;
  }

  public void setUuid(UUID uuid)
  {
    this.uuid = uuid;
  }

  public ResponseDefault time(Instant time)
  {
    this.time = time;
    return this;
  }

  /**
   * Get time
   *
   * @return time
   */
  @ApiModelProperty(value = "")
  @Valid
  public Instant getTime()
  {
    return time;
  }

  public void setTime(Instant time)
  {
    this.time = time;
  }

  public ResponseDefault dataResponse(Object dataResponse)
  {
    this.dataResponse = dataResponse;
    return this;
  }

  /**
   * đây là dữ liệu trả về kèm theo nếu có
   *
   * @return dataResponse
   */
  @ApiModelProperty(value = "đây là dữ liệu trả về kèm theo nếu có")


  public Object getDataResponse()
  {
    return dataResponse;
  }

  public void setDataResponse(Object dataResponse)
  {
    this.dataResponse = dataResponse;
  }


  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    ResponseDefault responseDefault = (ResponseDefault) o;
    return Objects.equals(this.title, responseDefault.title) &&
      Objects.equals(this.type, responseDefault.type) &&
      Objects.equals(this.status, responseDefault.status) &&
      Objects.equals(this.message, responseDefault.message) &&
      Objects.equals(this.detail, responseDefault.detail) &&
      Objects.equals(this.path, responseDefault.path) &&
      Objects.equals(this.id, responseDefault.id) &&
      Objects.equals(this.uuid, responseDefault.uuid) &&
      Objects.equals(this.time, responseDefault.time) &&
      Objects.equals(this.dataResponse, responseDefault.dataResponse);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(title, type, status, message, detail, path, id, uuid, time, dataResponse);
  }

  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseDefault {\n");

    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    uuid: ").append(toIndentedString(uuid)).append("\n");
    sb.append("    time: ").append(toIndentedString(time)).append("\n");
    sb.append("    dataResponse: ").append(toIndentedString(dataResponse)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o)
  {
    if (o == null)
    {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

