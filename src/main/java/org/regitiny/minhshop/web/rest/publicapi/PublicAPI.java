package org.regitiny.minhshop.web.rest.publicapi;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.service.ImageService;
import org.regitiny.minhshop.service.dto.ImageDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/public_api")
public class PublicAPI
{

  private final ImageService imageService;

  public PublicAPI(ImageService imageService)
  {
    this.imageService = imageService;
  }

  @GetMapping("/images/{nameImage}")
  public ResponseEntity<byte[]> getImage(@PathVariable String nameImage) throws FileNotFoundException
  {
    log.debug("REST request to get Image : {}", nameImage);
    Optional<ImageDTO> result = imageService.findByNameImage(nameImage);
    if (result.isPresent())
    {
      ImageDTO imageDTO = result.get();
      HttpHeaders headerUtil = new HttpHeaders();
      headerUtil.setContentType(MediaType.IMAGE_JPEG);
      return ResponseEntity.ok().headers(headerUtil).body(imageDTO.getImageData());
    }

    throw new FileNotFoundException();
  }
}
