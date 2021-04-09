package org.regitiny.minhshop.service.util;


import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.regitiny.tools.magic.constant.StringPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Log4j2
public class ServerCommand
{
  private static final String FILE_NAME_INPUT = "${fileNameInput}";
  private static final String FILE_NAME_OUTPUT = "${fileNameOutput}";
  private static final String CUSTOM_OPTION = "${customOption}";
  private static final String FOLDER_ORIGINAL_SERVER_PROCESSING = "/data/minh-shop/ffmpeg/original/";
  private static final String FOLDER_PROCESSED_SERVER_PROCESSING = "/data/minh-shop/ffmpeg/processed/";
  private static final String NAME_FOLDER_ORIGINAL_SERVER_PROCESSING = "${varOriginalServerProcessing}";
  private static final String NAME_FOLDER_PROCESSED_SERVER_PROCESSING = "${varProcessedServerProcessing}";
  @Getter
  private static String FOLDER_ORIGINAL;
  @Getter
  private static String FOLDER_PROCESSED;

  /**
   * videoQRN hay ffmpegVideoQualityReductionNormal
   * sử dụng làm mặc định để tối ưu hóa dung lượng video
   *
   * @param fileName name of file
   * @return command quality reduction
   */
  public static String videoQRN(String fileName)
  {
    return ffmpegCustom(fileName, null, " -c:v libx264 -c:a copy -preset fast -crf 26 ");
  }

  public static String ffmpegCustom(String fileNameInput, String fileNameOutput, String customOption)
  {
    String command = "ffmpeg -i ${varOriginalServerProcessing}${fileNameInput} " +
      "-y ${customOption} " +
      "${varProcessedServerProcessing}${fileNameOutput} " +
      "2> ${varProcessedServerProcessing}${fileNameOutput}.log && " +
      "cat ${varProcessedServerProcessing}${fileNameOutput}.log";
    if (customOption == null || customOption.equals(StringPool.BLANK))
      customOption = " ";
    if (fileNameOutput == null || fileNameOutput.equals(StringPool.BLANK))
      fileNameOutput = fileNameInput;
    command = command.replace(NAME_FOLDER_ORIGINAL_SERVER_PROCESSING, FOLDER_ORIGINAL_SERVER_PROCESSING)
      .replace(FILE_NAME_INPUT, fileNameInput)
      .replace(NAME_FOLDER_PROCESSED_SERVER_PROCESSING, FOLDER_PROCESSED_SERVER_PROCESSING)
      .replace(FILE_NAME_OUTPUT, fileNameOutput)
      .replace(CUSTOM_OPTION, customOption);
    log.debug("command quality reduction video : command= {}", command);
    return command;
  }

  public static String ffmpegVideoQualityReductionLittle(String fileName)
  {
    return ffmpegCustom(fileName, null, " -c:v libx264 -c:a copy -preset ultrafast -crf 24 ");
  }

  public static String ffmpegVideoQualityReductionGreat(String fileName)
  {
    return ffmpegCustom(fileName, null, " -c:v libx264 -c:a copy -preset veryslow -crf 28 ");
  }

  @Autowired
  private void setProperties(
    @Value("${yuvytung.data.video.folder-original:/data/minh-shop/ffmpeg/original/}") String fi,
    @Value("${yuvytung.data.video.folder-processed:/data/minh-shop/ffmpeg/processed/}") String fo
  )
  {
    FOLDER_ORIGINAL = fi;
    FOLDER_PROCESSED = fo;
    log.debug("mkdir folder {} is : {}", fi, new File(fi).mkdirs());
    log.debug("mkdir folder {} is : {}", fo, new File(fo).mkdirs());
  }
}
