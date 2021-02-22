package org.regitiny.minhshop.web.rest.custom.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.regitiny.minhshop.service.dto.TypePostDTO;
import org.regitiny.minhshop.service.dto.TypePostFilterDTO;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class PostModel
{

  /**
   * title
   */
  @NotNull
  @ApiModelProperty(value = "title", required = true)
  private String title;

  /**
   * price
   */
  @ApiModelProperty(value = "price")
  private Double price;

  /**
   * price
   */
  @ApiModelProperty(value = "price")
  private Double salePrice;

  /**
   * percentSale
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "100")
  @ApiModelProperty(value = "percentSale")
  private Float percentSale;

  /**
   * imageUrl
   */
  @NotNull
  @Size(max = 2048)
  @ApiModelProperty(value = "imageUrl", required = true)
  private String imageUrl;

  /**
   * scores
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "100")
  @ApiModelProperty(value = "scores")
  private Float scores;

  /**
   * simple content
   */
  @ApiModelProperty(value = "simple content")
  private String simpleContent;

  /**
   * otherInfo (Json)
   */
  @ApiModelProperty(value = "otherInfo (Json)")
  private String otherInfo;

  /**
   * content
   */
  @ApiModelProperty(value = "content")
  @Lob
  private String content;

  private String comment;

  /**
   * postDetailsId
   */
  @NotNull
  @Size(min = 3, max = 16)
  @Pattern(regexp = "[A-z]+[0-9]+")
  @ApiModelProperty(value = "postDetailsId", required = true)
  private String postDetailsId;

  private TypePostDTO typePost;

  private Set<TypePostFilterDTO> typePostFilters = new HashSet<>();
}
