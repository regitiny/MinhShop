package org.regitiny.minhshop.web.rest.publicapi;

import org.apache.commons.lang3.StringUtils;
import org.regitiny.minhshop.service.FileService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.MediaTypeFactory;
import org.springframework.security.util.InMemoryResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static java.lang.Math.min;

@Service
public class VideoStreamingService
{
  private static final long CHUNK_SIZE = 1000000L;
  private final FileService fileService;

  VideoStreamingService(FileService fileService)
  {
    this.fileService = fileService;
  }

  public ResourceRegion getVideoRegion(String rangeHeader, String directory) throws IOException
  {
//    byte[] data = fileService.getFileByFileName("c4666ad0-e438-447d-b366-0635ec96dc0d.mp4").map(File::getFileData).orElseGet(null);
    java.io.File outputFile = new java.io.File(directory + "/video.mp4");
//    try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
//      outputStream.write(data);
//    }
    byte[] data = Files.readAllBytes(outputFile.toPath());
    Resource resource = new InMemoryResource(data);
    FileUrlResource videoResource = new FileUrlResource(directory + "/video.mp4");
    String b = String.valueOf(MediaTypeFactory.getMediaType(resource));
    String a = String.valueOf(MediaTypeFactory.getMediaType(videoResource));
    ResourceRegion resourceRegion = getResourceRegion(resource, rangeHeader);

    return resourceRegion;

//    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
//      .contentType(MediaTypeFactory.getMediaType(resource).orElse(MediaType.valueOf("video/mp4")))
//      .body(resourceRegion);
  }

  private ResourceRegion getResourceRegion(Resource video, String httpHeaders) throws IOException
  {
    ResourceRegion resourceRegion;

    long contentLength = video.contentLength();
    int fromRange = 0;
    int toRange = 0;
    if (StringUtils.isNotBlank(httpHeaders))
    {
      String[] ranges = httpHeaders.substring("bytes=".length()).split("-");
      fromRange = Integer.parseInt(ranges[0]);
      if (ranges.length > 1)
      {
        toRange = Integer.parseInt(ranges[1]);
      }
      else
      {
        toRange = (int) (contentLength - 1);
      }
    }

    if (fromRange > 0)
    {
      long rangeLength = min(1000000, toRange - fromRange + 1);
      resourceRegion = new ResourceRegion(video, fromRange, rangeLength);
    }
    else
    {
      long rangeLength = min(1000000, contentLength);
      resourceRegion = new ResourceRegion(video, 0, rangeLength);
    }

    return resourceRegion;
  }

  @Cacheable("hidi")
  public File readFile()
  {
    System.out.println("xx.xxxx");
    return new java.io.File("D:\\video.mp4");
  }
}
