package org.regitiny.minhshop.service.business;

import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.config.constant.ServerCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Log4j2
public class VideoProcessingBusiness
{
  private final FfmpegBusiness ffmpegBusiness;

  public VideoProcessingBusiness(FfmpegBusiness ffmpegBusiness)
  {
    this.ffmpegBusiness = ffmpegBusiness;
  }

  public String saveVideoToFolder(String videoName, MultipartFile videoData)
  {
    File file = new File(ServerCommand.getFOLDER_INPUT() + videoName);

    log.debug(file.getPath());
    try
    {
      if (file.createNewFile())
        try (FileOutputStream fileOutputStream = new FileOutputStream(file))
        {
          log.debug(videoData.getBytes().length);
          fileOutputStream.write(videoData.getBytes());
          fileOutputStream.flush();
        }
        catch (IOException e)
        {
          log.warn("The video has not been saved to the folder ", e);
        }
    }
    catch (IOException e)
    {
      log.warn("not create new file");
    }
    return file.getPath();
  }

  public String ffmpegVideoQualityReductionNormal(String videoName)
  {

    var result = ffmpegBusiness.runCommand(ServerCommand.ffmpegVideoQualityReductionNormal(videoName));
    log.info(result);
    return result;
  }
}
