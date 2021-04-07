package org.regitiny.minhshop.service.business;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.tools.magic.exception.NhechException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static java.lang.Math.min;

@Service
@Log4j2
public class VideoStreamingBusiness
{
  private final FileService fileService;
  private static final Long RANGE_DEFAULT = 10 * 1024 * 1024L; // 10 MB

  public VideoStreamingBusiness(FileService fileService)
  {
    this.fileService = fileService;
  }

  public Tuple2<ResourceRegion, String> getResourceRegion(String videoName, String range)
  {

    return fileService.getFileByFileName(videoName)
      .map(file ->
      {
        String filePath = null;
        if (Boolean.TRUE.equals(file.getProcessed()) && Objects.nonNull(file.getPathFileProcessed()))
          filePath = file.getPathFileProcessed();
        else if (Objects.nonNull(file.getPathFileOriginal()))
          filePath = file.getPathFileOriginal();
        if (filePath == null)
          throw new NhechException("Nhếch bảo chả biết cất cái video này ở đâu");
        Resource resource = new FileSystemResource(filePath);
        ResourceRegion resourceRegion;
        long contentLength = Try.of(resource::contentLength)
          .onFailure(throwable ->
          {
            log.debug("error video.contentLength or video no data", throwable);
            throw new NhechException("nhếch tìm đến nhà này chẳng thấy ai cả");
          })
          .getOrElse(0L);
        long fromRange = 0;
        long toRange = 0;
        if (StringUtils.isNotBlank(range))
        {
          String[] ranges = range
            .substring("bytes=".length())
            .split("-");
          fromRange = Integer.parseInt(ranges[0]);
          if (ranges.length > 1)
            toRange = Integer.parseInt(ranges[1]);
          else
            toRange = (int) (contentLength - 1);
        }
        if (fromRange > 0)
        {
          long rangeLength = min(RANGE_DEFAULT, toRange - fromRange + 1);
          resourceRegion = new ResourceRegion(resource, fromRange, rangeLength);
        }
        else
        {
          long rangeLength = min(RANGE_DEFAULT, contentLength - 1);
          resourceRegion = new ResourceRegion(resource, 0, rangeLength);
        }
        return Tuple.of(resourceRegion, file.getTypeFile());
      }).orElse(Tuple.of(null, null));
  }
}
