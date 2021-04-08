package org.regitiny.minhshop.service.business;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.regitiny.minhshop.repository.FileRepository;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.dto.FileDTO;
import org.regitiny.minhshop.service.mapper.FileMapper;
import org.regitiny.minhshop.service.util.ServerCommand;
import org.regitiny.tools.magic.exception.NhechException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import static java.lang.Math.min;

@Service
@Log4j2
public class VideoBusiness
{
  private final FfmpegBusiness ffmpegBusiness;
  private final FileRepository fileRepository;
  private final FileService fileService;
  private final FileMapper fileMapper;
  private static final Long RANGE_DEFAULT = 10 * 1024 * 1024L; // 10 MB

  public VideoBusiness(FfmpegBusiness ffmpegBusiness, FileRepository fileRepository, FileService fileService, FileMapper fileMapper)
  {
    this.ffmpegBusiness = ffmpegBusiness;
    this.fileService = fileService;
    this.fileMapper = fileMapper;
    this.fileRepository = fileRepository;
  }

  public FileDTO saveVideoToFolder(String videoName, byte[] videoData)
  {
    log.debug(ServerCommand.getFOLDER_ORIGINAL() + videoName);
    File file = new File(ServerCommand.getFOLDER_ORIGINAL() + videoName);
    log.debug("video size {} byte , path {}", videoData.length, file.getPath());
    try (FileOutputStream fileOutputStream = new FileOutputStream(file.getAbsolutePath()))
    {
      log.debug("video size {} byte , path {}", videoData.length, file.getPath());
      fileOutputStream.write(videoData);
      fileOutputStream.flush();
      return fileRepository.findByNameFile(videoName)
        .map(file1 ->
        {
          file1.pathFileOriginal(file.getAbsolutePath());
          log.debug(file1);
          log.debug(fileMapper.toDto(file1));
          log.debug(fileRepository.save(file1));
          return fileService.save(fileMapper.toDto(file1));
        })
        .map(fileDTO -> fileService.cacheUpdateFileByFileName(fileDTO.getNameFile())
          .map(fileMapper::toDto)
          .orElse(fileDTO))
        .orElse(null);
    }
    catch (IOException e)
    {
      log.warn("not create new file", e);
      fileRepository.findByNameFile(videoName).ifPresent(fileRepository::delete);
    }
    return null;
  }

  /**
   * @param videoName name of video
   */
  public void videoQualityReduction(String videoName)
  {
    var resultCommand = ffmpegBusiness.runCommand(ServerCommand.videoQRN(videoName));
    String err = "No such file or directory";
    if (!resultCommand.contains(err))
      fileRepository.findByNameFile(videoName).ifPresent(file1 ->
      {
        File file = new File(ServerCommand.getFOLDER_ORIGINAL() + videoName);
        file1.processed(true)
          .pathFileProcessed(file.getAbsolutePath());
        fileService.cacheUpdateFileByFileName(
          fileService.save(
            fileMapper.toDto(file1)).getNameFile());
      });
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
