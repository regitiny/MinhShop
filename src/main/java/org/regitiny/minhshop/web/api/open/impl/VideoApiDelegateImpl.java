package org.regitiny.minhshop.web.api.open.impl;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.regitiny.minhshop.security.AuthoritiesConstants;
import org.regitiny.minhshop.security.SecurityUtils;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.business.VideoProcessingBusiness;
import org.regitiny.minhshop.service.business.VideoStreamingBusiness;
import org.regitiny.minhshop.service.dto.FileDTO;
import org.regitiny.minhshop.web.api.open.FileApiDelegate;
import org.regitiny.minhshop.web.api.open.VideoApiDelegate;
import org.regitiny.tools.magic.exception.NhechException;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Slf4j
public class VideoApiDelegateImpl implements VideoApiDelegate, FileApiDelegate
{
  private final FileService fileService;
  private final VideoStreamingBusiness videoStreamingBusiness;
  private final VideoProcessingBusiness videoProcessingBusiness;

  public VideoApiDelegateImpl(FileService fileService, VideoStreamingBusiness videoStreamingBusiness, VideoProcessingBusiness videoProcessingBusiness)
  {
    this.fileService = fileService;
    this.videoStreamingBusiness = videoStreamingBusiness;
    this.videoProcessingBusiness = videoProcessingBusiness;
  }

  @Override
  public Optional<NativeWebRequest> getRequest()
  {
    return Optional.empty();
  }

  @Override
  public ResponseEntity<Object> uploadVideos(@Valid List<MultipartFile> videoDatas)
  {
    log.debug("number of videos uploaded is = {}", videoDatas.size());
    log.debug("Request to upload File : dataIsEmpty = {}", videoDatas.isEmpty());
    SecurityUtils.checkAuthenticationAndAuthority(AuthoritiesConstants.MANAGEMENT);
    var resultFileSaved = new ArrayList<FileDTO>();
    Flux.fromIterable(videoDatas)
      .filter(Objects::nonNull)
      .map(videoData ->
      {
        var resultFileDTO = fileService.createFileDetail(videoData);
        Mono.just(resultFileDTO)
          .map(fileDetailCreated ->
            Try.of(videoData::getBytes)
              .onFailure(throwable -> log.info("data of file exist problem , originalFileName = {}", videoData.getOriginalFilename(), throwable))
              .mapTry(bytes -> videoProcessingBusiness.saveVideoToFolder(fileDetailCreated.getNameFile(), bytes))
              .filter(Objects::nonNull)
              .map(fileSaved -> videoProcessingBusiness.videoQualityReduction(fileSaved.getNameFile()))
          )
          .subscribeOn(Schedulers.boundedElastic()).subscribe();
        return resultFileSaved.add(resultFileDTO);
      }).subscribe();
    return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(resultFileSaved);
  }


  @Override
  public ResponseEntity<byte[]> getVideoDataByName(String videoName)
  {
    return fileService.getFileByFileName(videoName)
      .map(file ->
      {
        byte[] fileData = null;
//        var fileData = file.getFileData();
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
    return videoStreamingBusiness.getResourceRegion(videoName, range)
      .apply((resourceRegion, fileType) -> ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).header(fileType).body(resourceRegion));
  }


}


