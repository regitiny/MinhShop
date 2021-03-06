package org.regitiny.minhshop.web.rest.custom.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.regitiny.minhshop.service.dto.TypePostDTO;
import org.regitiny.minhshop.service.dto.TypePostFilterDTO;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostModel
{
  /**
   * id
   */
  private Long id;

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
  @ApiModelProperty(value = "salePrice")
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
   * otherData (Json)
   */
  @ApiModelProperty(value = "other data (String Json)")
  private String otherData;

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

  @Builder.Default
  private Set<TypePostFilterDTO> typePostFilters = new HashSet<>();
}
