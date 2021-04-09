package org.regitiny.minhshop.web.api.open.impl;

import io.vavr.Tuple;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.business.FfmpegBusiness;
import org.regitiny.minhshop.service.business.VideoBusiness;
import org.regitiny.minhshop.service.dto.FileDTO;
import org.regitiny.minhshop.service.util.ServerCommand;
import org.regitiny.minhshop.web.api.open.FileApiDelegate;
import org.regitiny.minhshop.web.api.open.ImageApiDelegate;
import org.regitiny.minhshop.web.api.open.VideoApiDelegate;
import org.regitiny.tools.magic.exception.NhechException;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.zalando.problem.Status;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;


@Service
@Log4j2
public class FileApiDelegateImpl implements VideoApiDelegate, FileApiDelegate, ImageApiDelegate
{
  private final FileService fileService;
  private final FfmpegBusiness ffmpegBusiness;
  private final VideoBusiness videoBusiness;

  public FileApiDelegateImpl(FileService fileService, FfmpegBusiness ffmpegBusiness, VideoBusiness videoBusiness)
  {
    this.fileService = fileService;
    this.ffmpegBusiness = ffmpegBusiness;
    this.videoBusiness = videoBusiness;
  }

  @Override
  public Optional<NativeWebRequest> getRequest()
  {
    return Optional.empty();
  }

  @Override
  public ResponseEntity<Object> uploadVideos(List<MultipartFile> videoDatas, String uploadByFroala)
  {
    log.debug("number of videos uploaded is = {}", videoDatas.size());
    log.debug("Request to upload File : dataIsEmpty = {}", videoDatas.isEmpty());
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    var resultFileSaved = new ArrayList<FileDTO>();
    Flux.fromIterable(videoDatas)
      .filter(Objects::nonNull)
      .doOnNext(videoData ->
      {
        var resultFileDTO = fileService.createFileDetail(videoData);
        Mono.just(resultFileDTO)
          .doOnNext(fileDetailCreated ->
            Try.of(videoData::getBytes)
              .onFailure(throwable -> log.info("data of file exist problem , originalFileName = {}", videoData.getOriginalFilename(), throwable))
              .mapTry(bytes -> videoBusiness.saveVideoToFolder(fileDetailCreated.getNameFile(), bytes))
              .filter(Objects::nonNull)
              .andThen(fileSaved -> videoBusiness.videoQualityReduction(fileSaved.getNameFile()))
          ).subscribeOn(Schedulers.boundedElastic()).subscribe();
        resultFileSaved.add(resultFileDTO);
      }).subscribe();

    if ("true".equals(uploadByFroala) && resultFileSaved.size() == 1)
    {
      var map = new HashMap<String, String>();
      map.put("link", "/api/open/file/videos/stream/" + resultFileSaved.get(0).getNameFile());
      return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(map);
    }
    else
      return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(resultFileSaved);
  }

  @Override
  public ResponseEntity<byte[]> getVideoDataByName(String videoName)
  {
    return fileService.getFileByFileName(videoName)
      .map(file ->
      {
        byte[] fileData = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, file.getTypeFile());
        return ResponseEntity.ok().headers(httpHeaders).body(fileData);
      })
      .orElseThrow(() ->
      {
        throw new NhechException(null, "File not found", Status.OK, "làm méo gì có cái file này");
      });
  }

  @Override
  public ResponseEntity<ResourceRegion> getVideoStreamDataByName(String videoName, String range)
  {
    log.debug("videoName = {} , ranger = {}", videoName, range);
    return videoBusiness.getResourceRegion(videoName, range)
      .apply((resourceRegion, fileType) ->
        ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).contentType(MediaType.parseMediaType(fileType)).body(resourceRegion));
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
  public ResponseEntity<Object> uploadImages(List<MultipartFile> imageDatas, String uploadByFroala)
  {
    log.debug("REST request to save Image : {}", imageDatas.size());
    List<FileDTO> resultFileDTO = new ArrayList<>();
    Flux.fromIterable(imageDatas)
      .filter(Objects::nonNull)
      .map(multipartFile -> Tuple.of(fileService.createFileDetail(multipartFile), multipartFile))
      .filter(tuple2 -> Objects.nonNull(tuple2._1().getTypeFile()) && tuple2._1().getTypeFile().contains("image"))
      .map(tuple2 ->
      {
        var fileDTO = tuple2._1;
        var imageData = tuple2._2;
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
        var resultAfterSaved = fileService.save(fileDTO);
        resultFileDTO.add(resultAfterSaved);
        return resultAfterSaved;
      })
      .map(fileDTO ->
      {
        var fileNameOriginal = fileDTO.getNameFile();
        var fileNameProcessed = fileNameOriginal.replace(fileDTO.getExtension(), "jpg");
        var command = ServerCommand.ffmpegCustom(fileNameOriginal, fileNameProcessed, null);
        return Mono.just(ffmpegBusiness.runCommand(command))
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
    if ("true".equals(uploadByFroala) && resultFileDTO.size() == 1)
    {
      var map = new HashMap<String, String>();
      var link = "/api/open/file/images/" + resultFileDTO.get(0).getNameFile();
      map.put("link", link);
      return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(map);
    }
    return ResponseEntity.ok()
      .contentType(MediaType.APPLICATION_JSON)
      .body(resultFileDTO);
  }
}

