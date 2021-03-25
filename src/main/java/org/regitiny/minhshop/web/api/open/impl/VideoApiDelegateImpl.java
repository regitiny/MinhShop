package org.regitiny.minhshop.web.api.open.impl;

import lombok.extern.slf4j.Slf4j;
import org.regitiny.minhshop.service.FileService;
import org.regitiny.minhshop.service.business.VideoStreamingBusiness;
import org.regitiny.minhshop.web.api.open.VideoApiDelegate;
import org.regitiny.minhshop.web.api.open.model.ResponseDefaultModel;
import org.regitiny.tools.magic.exception.NhechException;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.util.List;


@Service
@Slf4j
public class VideoApiDelegateImpl implements VideoApiDelegate
{
  private final FileService fileService;
  private final VideoStreamingBusiness videoStreamingBusiness;

  public VideoApiDelegateImpl(FileService fileService, VideoStreamingBusiness videoStreamingBusiness)
  {
    this.fileService = fileService;
    this.videoStreamingBusiness = videoStreamingBusiness;
  }

  @Override
  public ResponseEntity<ResponseDefaultModel> uploadVideos(@Valid List<MultipartFile> videoData)
  {
    fileService.uploads(videoData);
    return ResponseEntity.ok(new ResponseDefaultModel());
  }

  @Override
  public ResponseEntity<byte[]> getVideoDataByName(String videoName)
  {
    return fileService.getFileByFileName(videoName)
      .map(file ->
      {
        var fileData = file.getFileData();
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
//    log.debug("videoName = {} , ranger = {}",videoName,range);
//    var dataRegion_TypeFile = videoStreamingBusiness.getResourceRegion(videoName,range);
//    return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).header(dataRegion_TypeFile._2()).body(dataRegion_TypeFile._1());

    log.debug("videoName = {} , ranger = {}", videoName, range);
    return videoStreamingBusiness.getResourceRegion(videoName, range)
      .apply((resourceRegion, fileType) ->
        ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).header(fileType).body(resourceRegion)
      );

  }


}


