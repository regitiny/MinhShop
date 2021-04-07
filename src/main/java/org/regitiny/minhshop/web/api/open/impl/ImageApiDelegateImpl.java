package org.regitiny.minhshop.web.api.open.impl;

import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.business.FfmpegBusiness;
import org.regitiny.minhshop.util.constant.ServerCommand;
import org.regitiny.minhshop.web.api.open.ImageApiDelegate;
import org.regitiny.tools.magic.exception.NhechException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Log4j2
public class ImageApiDelegateImpl implements ImageApiDelegate
{
  private final FileService fileService;
  private final FfmpegBusiness ffmpegBusiness;

  public ImageApiDelegateImpl(FileService fileService, FfmpegBusiness ffmpegBusiness)
  {
    this.fileService = fileService;
    this.ffmpegBusiness = ffmpegBusiness;
  }

  @Override
  public ResponseEntity<byte[]> getImageByName(String imageName)
  {
    log.debug("REST request to save Image : {}", imageName);

    return fileService.getFileByFileName(imageName).map(image ->
    {
      if (!image.getTypeFile().contains("image"))
        throw new NhechException("nhếch là mèo nên không thích ăn đồ hạt của chó : đây không phải ảnh");
      String path;
      if (Boolean.TRUE.equals(image.getProcessed()) && Objects.nonNull(image.getPathFileProcessed()))
        path = image.getPathFileProcessed();
      else if (Objects.nonNull(image.getPathFileOriginal()))
        path = image.getPathFileOriginal();
      else
        throw new NhechException("cho Nhếch xin cái địa chỉ bạn ơi");
      var file = new FileSystemResource(path).getFile();
      var data = Try.of(() -> new FileInputStream(file).readAllBytes())
        .onFailure(throwable ->
        {
          throw new NhechException("ầy nhếch đến địa chỉ của bạn cho chẳng thấy con chuột nào cả: không thấy file");
        }).get();
      return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getTypeFile())).body(data);
    }).orElse(null);
  }

  @Override
  public ResponseEntity<Object> froalaUploadImage(MultipartFile imageData)
  {
    log.debug("REST request to save Image : {}", imageData.getSize());
    Map<String, String> map = new HashMap<>();
    Mono.just(fileService.createFileDetail(imageData))
      .filter(Objects::nonNull)
      .filter(fileDTO -> Objects.nonNull(fileDTO.getTypeFile()) && fileDTO.getTypeFile().contains("image"))
      .map(fileDTO ->
      {
        var link = "/api/open/file/images/" + fileDTO.getNameFile();
        map.put("link", link);
        var folderSaveImage = ServerCommand.getFOLDER_ORIGINAL() + fileDTO.getNameFile();
        fileDTO.setPathFileOriginal(folderSaveImage);
        Try.of(() -> new FileOutputStream(folderSaveImage))
          .andThenTry(fileOutputStream ->
          {
            fileOutputStream.write(imageData.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
          })
          .onFailure(throwable -> fileService.delete(fileDTO.getId()));
        return fileService.save(fileDTO);
      })
      .doOnNext(fileDTO ->
      {
        var fileNameOriginal = fileDTO.getNameFile();
        var fileNameProcessed = fileNameOriginal.replace(fileDTO.getExtension(), "jpg");
        var command = ServerCommand.ffmpegCustom(fileNameOriginal, fileNameProcessed, null);
        Mono.just(ffmpegBusiness.runCommand(command))
          .doOnNext(result ->
          {
            log.debug(result);
            fileDTO.setProcessed(true);
            fileDTO.setTypeFile("image/jpeg");
            fileDTO.setPathFileProcessed(ServerCommand.getFOLDER_PROCESSED() + fileNameProcessed);
            fileService.save(fileDTO);
          })
          .subscribeOn(Schedulers.boundedElastic()).subscribe();
      }).subscribe();
    return ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(map);
  }
}
