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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;

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

  @GetMapping("/hihi")
  public String getImagexx()
  {
    log.info("REST request to get Image : 112");
    InputStream inputStream = getClass().getResourceAsStream("/config/data/dist_vn/tree.json");
    String result = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
    System.out.println(result.length());

    System.out.println(getClass().getResource("/config/data/dist_vn/tree.json").toString());


    return "chạy ngon đét bạn ơi ";
  }
}
