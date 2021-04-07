package org.regitiny.minhshop.service.business;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.repository.FileRepository;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.dto.FileDTO;
import org.regitiny.minhshop.service.mapper.FileMapper;
import org.regitiny.minhshop.util.constant.ServerCommand;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Log4j2
public class VideoProcessingBusiness
{
  private final FfmpegBusiness ffmpegBusiness;
  private final FileRepository fileRepository;
  private final FileService fileService;
  private final FileMapper fileMapper;

  public VideoProcessingBusiness(FfmpegBusiness ffmpegBusiness, FileRepository fileRepository, FileService fileService, FileMapper fileMapper)
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
   * @return Tuple2 (resultCommand,pathFileProcessed)
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
}
