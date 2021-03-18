package org.regitiny.minhshop.web.rest.publicapi;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.ImageService;
import org.regitiny.minhshop.service.dto.ImageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.util.InMemoryResource;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/public_api")
public class PublicAPI
{

  private final ImageService imageService;
  public static final String VideoUploadingDir = "D:\\";
  private final FileService fileService;

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
  @Autowired
  VideoStreamingService service;


  public PublicAPI(ImageService imageService, FileService fileService)
  {
    this.imageService = imageService;
    this.fileService = fileService;
  }

  @GetMapping(value = "/hihi", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
//  @Cacheable(key = "#id")
  public Flux<String> getImagexx(@RequestParam("id") String id)
  {

    return Flux.just("xxxx");

  }
//  public static final String VideoUploadingDir = System.getProperty("user.dir") + "/Uploads/Posts/Videos";

  @GetMapping(value = "/video", produces = "application/octet-stream")
  public ResponseEntity<ResourceRegion> getVideo(@RequestHeader(value = "Range", required = false) String rangeHeader)
    throws IOException
  {

    if (!new java.io.File(VideoUploadingDir).exists())
    {
      new java.io.File(VideoUploadingDir).mkdirs();
    }
    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
      .contentType(MediaType.valueOf("video/mp4"))
      .body(service.getVideoRegion(rangeHeader, VideoUploadingDir));


  }

  @GetMapping(value = "/video/flux")
  public ResponseEntity<byte[]> getVideoFlux(@RequestHeader(value = "Range", required = false) String rangeHeader)
    throws IOException
  {
    var start = 0;
    if (rangeHeader != null)
    {
      start = Integer.parseInt(rangeHeader.replace("bytes=", "").replace("-", ""));
    }
    java.io.File file = new java.io.File(VideoUploadingDir + "video.mp4");
    java.io.File file2 = new java.io.File(VideoUploadingDir + "video2.mp4");

    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");

    byte[] result = new byte[10 * 1024 * 1024];
    randomAccessFile.seek(start);
    randomAccessFile.read(result);

    if (start > randomAccessFile2.length())
    {
      byte[] newFile2 = new byte[start + 10 * 1024 * 1024 - (int) randomAccessFile2.length()];
      randomAccessFile.seek(randomAccessFile2.length());
      randomAccessFile.read(newFile2);
      randomAccessFile2.seek(randomAccessFile2.length());
      randomAccessFile2.write(newFile2);
    }


    int end = Math.min((start + 10 * 1024 * 1024 - 1), (int) randomAccessFile.length() - 1);
    return ResponseEntity.status(206)
      .contentType(MediaType.parseMediaType("video/mp4"))
      .contentLength(result.length)
      .header("Content-Range", "bytes " + start + "-" + end + "/" + randomAccessFile.length())
      .body(result);
  }

  @GetMapping(value = "/video/flux2")
  public ResponseEntity<ResourceRegion> getVideoFlux2(@RequestHeader(value = "Range", required = false) String rangeHeader)
    throws IOException
  {
    var start = 0;
    if (rangeHeader != null)
      start = Integer.parseInt(rangeHeader.replace("bytes=", "").replace("-", ""));
    java.io.File file = new java.io.File(VideoUploadingDir + "video2.mp4");
    byte[] data = new FileInputStream(file).readAllBytes();
    ResourceRegion result2 = new ResourceRegion(new InMemoryResource(data), start, 10 * 1024 * 1024);
    return ResponseEntity.status(206)
      .contentType(MediaType.parseMediaType("video/mp4"))
      .body(result2);
  }

}




